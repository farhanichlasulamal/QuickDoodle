package com.quickdoodle.game.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;

public class StartMenu extends JFrame {
	static Point compCoords;
	
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
		setBounds(0, 0, 680, 460);
		JPanel contentPane = new JPanel();
		contentPane.setBounds(0, 0, 680, 430);
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		Setting setting = new Setting();
		
		JPanel topPanel = new JPanel();
		topPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen();
                setLocation(currCoords.x - compCoords.x, currCoords.y - compCoords.y);
			}
		});
		topPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				compCoords = e.getPoint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				compCoords = null;
			}
		});
		topPanel.setBackground(new Color(0, 80, 115));
		topPanel.setBounds(0, 0, 680, 30);
		contentPane.add(topPanel);
		topPanel.setLayout(null);
		
		JLabel minimizeWindowButton = new JLabel("");
		minimizeWindowButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		minimizeWindowButton.setIcon(new ImageIcon("./img/minimizeWindowButton.png"));
		minimizeWindowButton.setBounds(600, 0, 40, 30);
		topPanel.add(minimizeWindowButton);
		minimizeWindowButton.setHorizontalAlignment(SwingConstants.CENTER);
		minimizeWindowButton.setForeground(Color.WHITE);
		minimizeWindowButton.setFont(new Font("Tw Cen MT", Font.BOLD, 14));
		
		JLabel exitWindowButton = new JLabel("");
		exitWindowButton.setBounds(640, 0, 40, 30);
		topPanel.add(exitWindowButton);
		exitWindowButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		exitWindowButton.setIcon(new ImageIcon("./img/closeWindowButton.png"));
		exitWindowButton.setHorizontalAlignment(SwingConstants.CENTER);
		InGameLevel game = new InGameLevel();
		
		contentPane.add(game);
		contentPane.add(setting);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(0, 30, 680, 430);
		mainPanel.setLayout(null);
		contentPane.add(mainPanel);
		
		JPanel buttomPanel = new JPanel();
		buttomPanel.setBounds(0, 245, 680, 185);
		mainPanel.add(buttomPanel);
		buttomPanel.setBackground(new Color(111, 190, 75));
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
		playButtonPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				game.setVisible(true);
				setting.setVisible(false);
			}
		});
		playButtonPanel.setBackground(new Color(0, 80, 115));
		playButtonPanel.setBounds(219, 20, 90, 28);
		buttomPanel.add(playButtonPanel);
		playButtonPanel.setLayout(null);
		
		JLabel lblPlay = new JLabel("Play");
		lblPlay.setBounds(0, 0, 90, 28);
		playButtonPanel.add(lblPlay);
		lblPlay.setForeground(Color.WHITE);
		lblPlay.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblPlay.setHorizontalAlignment(SwingConstants.CENTER);
		
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
		
		JLabel label = new JLabel("\u00A92018");
		label.setFont(new Font("Tw Cen MT", Font.PLAIN, 12));
		label.setForeground(new Color(0, 80, 115));
		label.setBounds(10, 165, 46, 14);
		buttomPanel.add(label);
		
		JLabel logo = new JLabel("");
		logo.setBounds(208, 137, 265, 98);
		mainPanel.add(logo);
		logo.setIcon(new ImageIcon("./img/Logo.png"));
		logo.setHorizontalAlignment(SwingConstants.CENTER);
	}
}
