package com.quickdoodle.trainer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class TrainerGUI extends JFrame {
	static Point compCoords;
	private JTextField textFieldName;
	private JTextField textFieldEpoch;
	private JTextField textFieldLayer;
	private JTextField textFieldBatch;
	private JTextField textFieldRatio;
	private JTextArea textAreaSchedule;
	private JTextArea textAreaLog;

	public TrainerGUI() {
		addFrame();
		GUI();
		this.setLocationRelativeTo(null);
	}
	
	public void addFrame() {
		setTitle("Quick Doodle Trainer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setUndecorated(true);
		setBounds(0, 0, 680, 660);
	}
	
	public JPanel TopPanel() {
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
		
		JLabel titleBar = new JLabel("Trainer Mode");
		titleBar.setHorizontalAlignment(SwingConstants.LEFT);
		titleBar.setForeground(Color.WHITE);
		titleBar.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		titleBar.setBounds(10, 0, 144, 28);
		topPanel.add(titleBar);
		
		return topPanel;
	}
	
	public void GUI() {
		//add Panel
		JPanel Trainer = new JPanel();
		Trainer.setBounds(0, 0, 680, 430);
		Trainer.setBackground(Color.BLACK);
		Trainer.setForeground(Color.WHITE);
		Trainer.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Trainer);
		Trainer.setLayout(null);
		
		//add Top Panel
		Trainer.add(TopPanel());
		
		//add Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(0, 30, 680, 430);
		mainPanel.setLayout(null);
		Trainer.add(mainPanel);
		
		JLabel logo = new JLabel("");
		logo.setBounds(208, 40, 265, 98);
		mainPanel.add(logo);
		logo.setIcon(new ImageIcon("./img/Logo.png"));
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		//panel buttom
		JPanel buttomPanel = new JPanel();
		buttomPanel.setBounds(0, 165, 680, 265);
		mainPanel.add(buttomPanel);
		buttomPanel.setBackground(new Color(0, 80, 115));
		buttomPanel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(36, 18, 72, 28);
		buttomPanel.add(lblName);
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		
		textFieldName = new JTextField();
		textFieldName.setBounds(127, 23, 155, 20);
		buttomPanel.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblSchedule = new JLabel("Schedule");
		lblSchedule.setBounds(36, 67, 72, 28);
		buttomPanel.add(lblSchedule);
		lblSchedule.setHorizontalAlignment(SwingConstants.LEFT);
		lblSchedule.setForeground(Color.WHITE);
		lblSchedule.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		
		textAreaSchedule = new JTextArea();
		textAreaSchedule.setBounds(127, 70, 155, 119);
		buttomPanel.add(textAreaSchedule);
		
		JLabel lblEpoch = new JLabel("Maximum Epoch");
		lblEpoch.setHorizontalAlignment(SwingConstants.LEFT);
		lblEpoch.setForeground(Color.WHITE);
		lblEpoch.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblEpoch.setBounds(337, 18, 131, 28);
		buttomPanel.add(lblEpoch);
		
		textFieldEpoch = new JTextField();
		textFieldEpoch.setColumns(10);
		textFieldEpoch.setBounds(491, 23, 144, 20);
		buttomPanel.add(textFieldEpoch);
		
		JLabel lblLayer = new JLabel("Layer Configuration");
		lblLayer.setHorizontalAlignment(SwingConstants.LEFT);
		lblLayer.setForeground(Color.WHITE);
		lblLayer.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblLayer.setBounds(337, 67, 144, 28);
		buttomPanel.add(lblLayer);
		
		textFieldLayer = new JTextField();
		textFieldLayer.setBounds(491, 72, 144, 20);
		buttomPanel.add(textFieldLayer);
		textFieldLayer.setColumns(10);
		
		JLabel lblBatch = new JLabel("Batch");
		lblBatch.setHorizontalAlignment(SwingConstants.LEFT);
		lblBatch.setForeground(Color.WHITE);
		lblBatch.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblBatch.setBounds(337, 116, 72, 28);
		buttomPanel.add(lblBatch);
		
		textFieldBatch = new JTextField();
		textFieldBatch.setBounds(491, 121, 144, 20);
		buttomPanel.add(textFieldBatch);
		textFieldBatch.setColumns(10);
		
		JLabel lblRatio = new JLabel("Ratio");
		lblRatio.setHorizontalAlignment(SwingConstants.LEFT);
		lblRatio.setForeground(Color.WHITE);
		lblRatio.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblRatio.setBounds(337, 161, 72, 28);
		buttomPanel.add(lblRatio);
		
		textFieldRatio = new JTextField();
		textFieldRatio.setColumns(10);
		textFieldRatio.setBounds(491, 166, 144, 20);
		buttomPanel.add(textFieldRatio);
		
		JPanel runButtonPanel = new JPanel();
		runButtonPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Name = textFieldName.getText();
				String Schedule = textAreaSchedule.getText();
				String Epoch = textFieldEpoch.getText();
				String Layer = textFieldLayer.getText();
				String Batch = textFieldBatch.getText();
				String Ratio = textFieldRatio.getText();
				
				
			}
		});
		runButtonPanel.setBackground(new Color(111, 190, 75));
		runButtonPanel.setBounds(295, 216, 90, 28);
		buttomPanel.add(runButtonPanel);
		runButtonPanel.setLayout(null);
		
		JLabel lblRun = new JLabel("Run");
		lblRun.setBounds(0, 0, 90, 28);
		runButtonPanel.add(lblRun);
		lblRun.setForeground(Color.WHITE);
		lblRun.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblRun.setHorizontalAlignment(SwingConstants.CENTER);

		textAreaLog = new JTextArea("laaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaAAAAAAAAAAAAAAAAABBBBBBDHCHABCDHKJZFBCKDSCB\nla\nla\nla\nla\nla\nla\nla\nB\nH\nG\n");
		textAreaLog.setForeground(Color.WHITE);
		textAreaLog.setBackground(Color.BLACK);
		JScrollPane scroll = new JScrollPane(textAreaLog);
		scroll.setBounds(0, 460, 680, 200);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Trainer.add(scroll);

		
	}

	public void Try() {
		
	}
}
