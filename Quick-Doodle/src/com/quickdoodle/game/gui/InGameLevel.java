package com.quickdoodle.game.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Canvas;

public class InGameLevel extends JPanel {
	DrawArea drawArea;
	
	/**
	 * Create the panel.
	 */
	public InGameLevel() {
		setBackground(Color.BLUE);
		setBounds(0, 0, 680, 430);
		setLayout(null);
		setVisible(false);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setBounds(0, 0, 62, 430);
		add(leftPanel);
		leftPanel.setLayout(null);
		
		JLabel lblBack = new JLabel("");
		lblBack.setIcon(new ImageIcon("./img/BackButton.png"));
		lblBack.setBounds(10, 25, 40, 25);
		leftPanel.add(lblBack);
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				close();
			}
		});
		lblBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblExitButton = new JLabel("");
		lblExitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		lblExitButton.setIcon(new ImageIcon("./img/ExitButton.png"));
		lblExitButton.setHorizontalAlignment(SwingConstants.CENTER);
		lblExitButton.setBounds(10, 399, 20, 20);
		leftPanel.add(lblExitButton);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new java.awt.Color(111, 190, 75));
		centerPanel.setBounds(62, 0, 375, 430);
		add(centerPanel);
		centerPanel.setLayout(null);
		
		drawArea = new DrawArea();
		drawArea.setBackground(Color.WHITE);
		drawArea.setBounds(20, 18, 336, 336);
	    centerPanel.add(drawArea);
		
		JPanel deleteButtonPanel = new JPanel();
		deleteButtonPanel.setLayout(null);
		deleteButtonPanel.setBackground(new Color(0, 80, 115));
		deleteButtonPanel.setBounds(265, 377, 90, 28);
		centerPanel.add(deleteButtonPanel);
		
		JLabel lblDelete = new JLabel("Delete");
		lblDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				drawArea.clear();
			}
		});
		lblDelete.setBounds(0, 0, 90, 28);
		deleteButtonPanel.add(lblDelete);
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setForeground(Color.WHITE);
		lblDelete.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		
		JPanel checkButtonPanel = new JPanel();
		checkButtonPanel.setForeground(Color.WHITE);
		checkButtonPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				drawArea.print();
			}
		});
		checkButtonPanel.setBackground(Color.RED);
		checkButtonPanel.setBounds(20, 377, 90, 28);
		centerPanel.add(checkButtonPanel);
		checkButtonPanel.setLayout(null);
		
		JLabel lblCheck = new JLabel("Check");
		lblCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheck.setForeground(Color.WHITE);
		lblCheck.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblCheck.setBounds(0, 0, 90, 28);
		checkButtonPanel.add(lblCheck);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new java.awt.Color(0, 80, 115));
		rightPanel.setBounds(437, 0, 243, 430);
		add(rightPanel);
		rightPanel.setLayout(null);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setForeground(new java.awt.Color(1, 187, 234));
		lblTime.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setBounds(29, 70, 46, 28);
		rightPanel.add(lblTime);
		
		JLabel lblTarget = new JLabel("Target");
		lblTarget.setHorizontalAlignment(SwingConstants.LEFT);
		lblTarget.setForeground(new java.awt.Color(1, 187, 234));
		lblTarget.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblTarget.setBounds(29, 98, 46, 28);
		rightPanel.add(lblTarget);
		
		JLabel lblPredict = new JLabel("Predict");
		lblPredict.setHorizontalAlignment(SwingConstants.LEFT);
		lblPredict.setForeground(new java.awt.Color(1, 187, 234));
		lblPredict.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblPredict.setBounds(29, 126, 46, 28);
		rightPanel.add(lblPredict);
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setHorizontalAlignment(SwingConstants.LEFT);
		lblLevel.setForeground(new java.awt.Color(1, 187, 234));
		lblLevel.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblLevel.setBounds(29, 154, 46, 28);
		rightPanel.add(lblLevel);
		
		JLabel colonTime = new JLabel(":");
		colonTime.setHorizontalAlignment(SwingConstants.CENTER);
		colonTime.setForeground(Color.WHITE);
		colonTime.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		colonTime.setBounds(85, 70, 10, 28);
		rightPanel.add(colonTime);
		
		JLabel colonTarget = new JLabel(":");
		colonTarget.setHorizontalAlignment(SwingConstants.CENTER);
		colonTarget.setForeground(Color.WHITE);
		colonTarget.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		colonTarget.setBounds(85, 98, 10, 28);
		rightPanel.add(colonTarget);
		
		JLabel colonPredict = new JLabel(":");
		colonPredict.setHorizontalAlignment(SwingConstants.CENTER);
		colonPredict.setForeground(Color.WHITE);
		colonPredict.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		colonPredict.setBounds(85, 126, 10, 28);
		rightPanel.add(colonPredict);
		
		JLabel colonLevel = new JLabel(":");
		colonLevel.setHorizontalAlignment(SwingConstants.CENTER);
		colonLevel.setForeground(Color.WHITE);
		colonLevel.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		colonLevel.setBounds(85, 154, 10, 28);
		rightPanel.add(colonLevel);
		
		JLabel currentTime = new JLabel("28");
		currentTime.setHorizontalAlignment(SwingConstants.LEFT);
		currentTime.setForeground(Color.WHITE);
		currentTime.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		currentTime.setBounds(103, 70, 100, 28);
		rightPanel.add(currentTime);
		
		JLabel currentTarget = new JLabel("UMBRELLA");
		currentTarget.setHorizontalAlignment(SwingConstants.LEFT);
		currentTarget.setForeground(Color.WHITE);
		currentTarget.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		currentTarget.setBounds(105, 98, 98, 28);
		rightPanel.add(currentTarget);
		
		JLabel currentPredict = new JLabel("CLOUD");
		currentPredict.setHorizontalAlignment(SwingConstants.LEFT);
		currentPredict.setForeground(Color.WHITE);
		currentPredict.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		currentPredict.setBounds(105, 126, 98, 28);
		rightPanel.add(currentPredict);
		
		JLabel currentLevel = new JLabel("2");
		currentLevel.setHorizontalAlignment(SwingConstants.LEFT);
		currentLevel.setForeground(Color.WHITE);
		currentLevel.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		currentLevel.setBounds(105, 154, 98, 28);
		rightPanel.add(currentLevel);
		
		JLabel pattern = new JLabel("");
		pattern.setIcon(new ImageIcon("./img/PatternGame.png"));
		pattern.setHorizontalAlignment(SwingConstants.CENTER);
		pattern.setBounds(29, 215, 195, 190);
		rightPanel.add(pattern);
	}
	
	public void close(){
		setVisible(false);
	}
}
