package com.quickdoodle.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import com.quickdoodle.model.Model;

public class DrawArea extends JComponent {

	// Image in which we're going to draw
	private BufferedImage img;
	// Graphics2D object ==> used to draw on
	private Graphics2D g2d;
	// Mouse coordinates
	private int currentX, currentY, oldX, oldY;
	
	
	
	private int width, height;
	private int[][] array;

	public DrawArea() {
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// save coord x,y when mouse is pressed
				oldX = e.getX();
				oldY = e.getY();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				// coord x,y when drag mouse
				currentX = e.getX();
				currentY = e.getY();

				if (g2d != null) {
					// draw line if g2 context not null
					g2d.drawLine(oldX, oldY, currentX, currentY);
					// set wight
					g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
					// refresh draw area to repaint
					repaint();
					// store current coords x,y as olds x,y
					oldX = currentX;
					oldY = currentY;
				}
			}
			
			public void mouseReleased(MouseEvent e) {
				Model m = Model.getInstance();
				double[] input = imageToInput(img);
				double[] result = m.guess(input);
			}
		});
	}

	protected void paintComponent(Graphics g) {
		if (img == null) {
			// image to draw null ==> we create
			img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
			g2d = (Graphics2D) img.getGraphics();
			// enable antialiasing
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			// clear draw area
			clear();
		}
		try {
			g.drawImage(img, 0, 0, null);
		} catch (Exception e) {

		}
	}

	private BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}
	
	public double[] imageToInput(BufferedImage image) {
		double[] result = new double[784];
		BufferedImage scaled = resize(img, width, height);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixel = scaled.getRGB(x, y);
				if (pixel != -1) {
					int r = (pixel >> 16) & 0xFF;
					int g = (pixel >> 8) & 0xFF;
					int b = (pixel & 0xFF);
					result[y * x + x] = (r + g + b) / (3.0 * 255.0);
				}
			}
		}
		return result;
	}
	
	public void print() {
		width = 28;
		height = 28;
		array = new int[28][28];

		BufferedImage scaled = resize(img, width, height);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixel = scaled.getRGB(x, y);
				if (pixel != -1) {
					int r = (pixel >> 16) & 0xFF;
					int g = (pixel >> 8) & 0xFF;
					int b = (pixel & 0xFF);
					array[y][x] = (r + g + b) / 3;
				}
			}
		}

		for (int[] row : array) {
			for (int val : row) {
				System.out.print(String.format("%3d ", val));
			}
			System.out.println();
		}
	}

	public void clear() {
		g2d.setPaint(Color.white);
		// draw white on entire draw area to clear
		g2d.fillRect(0, 0, getSize().width, getSize().height);
		g2d.setPaint(Color.black);
		repaint();
	}
}
