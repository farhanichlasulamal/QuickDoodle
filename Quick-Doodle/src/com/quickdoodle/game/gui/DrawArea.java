package com.quickdoodle.game.gui;

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
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

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
	 
	    addMouseMotionListener(
	    new MouseMotionAdapter() {
	      public void mouseDragged(MouseEvent e) {
	        // coord x,y when drag mouse
	        currentX = e.getX();
	        currentY = e.getY();
	 
	        if (g2d != null) {
	          // draw line if g2 context not null
	          g2d.drawLine(oldX, oldY, currentX, currentY);
	          // set wight
	          g2d.setStroke(new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
	          // refresh draw area to repaint
	          repaint();
	          // store current coords x,y as olds x,y
	          oldX = currentX;
	          oldY = currentY;
	        }
	      }   
	    });
	}
	
	protected void paintComponent(Graphics g) {
	    if (img == null) {
	    	// image to draw null ==> we create
	    	img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
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
	
	public void print() {
		width = img.getWidth();
	    height = img.getHeight();
	    array = new int[height][width];
	    
	    for (int row = 0; row < height; row++) {
	    	for (int col = 0; col < width; col++) {
	    		array[row][col] = img.getRGB(col, row);
	    	}
	    }
	    
		for(int[] row : array) {
			for(int val : row) {
				System.out.print(val!=-1 ? "#" : " ");
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
