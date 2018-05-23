package com.quickdoodle.game.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartMenu extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartMenu frame = new StartMenu();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public StartMenu() {
		setTitle("Quick Doodle Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setUndecorated(true);
		setBounds(0, 0, 680, 430);
		JPanel contentPane = new JPanel();
		contentPane.setBounds(0, 0, 680, 430);
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		Setting setting = new Setting();
		InGameLevel game = new InGameLevel();
		
		contentPane.add(game);
		contentPane.add(setting);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(0, 0, 680, 430);
		mainPanel.setLayout(null);
		contentPane.add(mainPanel);
		
		JPanel buttomPanel = new JPanel();
		buttomPanel.setBounds(0, 245, 680, 185);
		mainPanel.add(buttomPanel);
		buttomPanel.setBackground(new java.awt.Color(111, 190, 75));
		buttomPanel.setLayout(null);
		
		JPanel howToButtonPanel = new JPanel();
		howToButtonPanel.setLayout(null);
		howToButtonPanel.setBackground(new Color(0, 80, 115));
		howToButtonPanel.setBounds(375, 20, 90, 28);
		buttomPanel.add(howToButtonPanel);
		
		JLabel lblHowTo = new JLabel("How To");
		lblHowTo.setBounds(0, 0, 90, 28);
		howToButtonPanel.add(lblHowTo);
		lblHowTo.setForeground(Color.WHITE);
		lblHowTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblHowTo.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		
		JPanel playButtonPanel = new JPanel();
		playButtonPanel.setBackground(new java.awt.Color(0, 80, 115));
		playButtonPanel.setBounds(219, 20, 90, 28);
		buttomPanel.add(playButtonPanel);
		playButtonPanel.setLayout(null);
		
		JLabel lblPlay = new JLabel("Play");
		lblPlay.setBounds(0, 0, 90, 28);
		playButtonPanel.add(lblPlay);
		lblPlay.setForeground(Color.WHITE);
		lblPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				game.setVisible(true);
				setting.setVisible(false);
			}
		});
		lblPlay.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblPlay.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblExitButton = new JLabel("");
		lblExitButton.setBounds(10, 154, 20, 20);
		buttomPanel.add(lblExitButton);
		lblExitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblExitButton.setIcon(new ImageIcon("./img/ExitButton.png"));
		lblExitButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblSettingButton = new JLabel("");
		lblSettingButton.setBounds(650, 154, 20, 20);
		buttomPanel.add(lblSettingButton);
		lblSettingButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setting.setVisible(true);
				game.setVisible(false);
			}
		});
		lblSettingButton.setIcon(new ImageIcon("./img/SettingButton.png"));
		lblSettingButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel logo = new JLabel("");
		logo.setBounds(208, 137, 265, 98);
		mainPanel.add(logo);
		logo.setIcon(new ImageIcon("./img/Logo.png"));
		logo.setHorizontalAlignment(SwingConstants.CENTER);
	}
}
