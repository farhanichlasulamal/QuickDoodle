package com.quickdoodle.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GameGUI extends JFrame {
	static Point compCoords;
	static Point compCoords2;
	JLabel currentTime;
	JLabel currentTarget;
	JLabel currentPredict;
	JLabel currentLevel;
	DrawArea drawArea;
	boolean finished;
	
	private static HashMap<Integer, String> doodles = new HashMap<>();
	
	private void prepareDoodles() {
		doodles.put(-1, "None");
		doodles.put(0, "bat");
		doodles.put(1, "cat");
		doodles.put(2, "circle");
		doodles.put(3, "diamond");
		doodles.put(4, "fish");
		doodles.put(5, "flower");
		doodles.put(6, "line");
		doodles.put(7, "nail");
		doodles.put(8, "star");
		doodles.put(9, "zigzag");
	}
	
	public GameGUI() {
		prepareDoodles();
		addFrame();
		StartMenu();
		//ResultPanel();
		//InGameLevel();
		this.setLocationRelativeTo(null);
	}

	public void addFrame() {
		setTitle("Quick Doodle Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setUndecorated(true);
		setBounds(0, 0, 680, 460);
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
		
		return topPanel;
	}
	
	public void StartMenu() {
		
		//add Panel
		JPanel startMenu = new JPanel();
		startMenu.setBounds(0, 0, 680, 430);
		startMenu.setBackground(Color.BLACK);
		startMenu.setForeground(Color.WHITE);
		startMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(startMenu);
		startMenu.setLayout(null);
		
		//add Top Panel
		startMenu.add(TopPanel());
		
		//add Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(0, 30, 680, 430);
		mainPanel.setLayout(null);
		startMenu.add(mainPanel);

		//Panel Setting
		JPanel setting = new JPanel();
		setting.setBackground(new java.awt.Color(0, 80, 115));
		setting.setBounds(470, 0, 210, 430);
		setting.setVisible(false);
		mainPanel.add(setting);
		setting.setLayout(null);
		
		JLabel lblClose = new JLabel("");
		lblClose.setIcon(new ImageIcon("./img/CloseSettingButton.png"));
		lblClose.setBounds(169, 21, 20, 20);
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setting.setVisible(false);
			}
		});
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		setting.add(lblClose);
		
		JCheckBox shareDataCheck = new JCheckBox("Share Data Check");
		shareDataCheck.setFont(new Font("Tw Cen MT", Font.PLAIN, 12));
		shareDataCheck.setForeground(Color.WHITE);
		shareDataCheck.setBackground(new Color(0, 80, 115));
		shareDataCheck.setBounds(31, 120, 129, 23);
		setting.add(shareDataCheck);
		
		JPanel saveButtonPanel = new JPanel();
		saveButtonPanel.setBackground(new Color(111, 190, 75));
		saveButtonPanel.setBounds(31, 162, 90, 28);
		setting.add(saveButtonPanel);
		saveButtonPanel.setLayout(null);
		
		JLabel lblSave = new JLabel("Save");
		lblSave.setBounds(0, 0, 90, 28);
		saveButtonPanel.add(lblSave);
		lblSave.setForeground(Color.WHITE);
		lblSave.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblSave.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblSetting = new JLabel("Setting");
		lblSetting.setHorizontalAlignment(SwingConstants.LEFT);
		lblSetting.setFont(new Font("Tw Cen MT", Font.PLAIN, 34));
		lblSetting.setForeground(new java.awt.Color(1,187, 234));
		lblSetting.setBounds(31, 67, 100, 35);
		setting.add(lblSetting);
		
		
		//panel buttom
		JPanel buttomPanel = new JPanel();
		buttomPanel.setBounds(0, 245, 680, 185);
		mainPanel.add(buttomPanel);
		buttomPanel.setBackground(new Color(111, 190, 75));
		buttomPanel.setLayout(null);
		
		JPanel howToButtonPanel = new JPanel();
		howToButtonPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				HowTo();
			}
		});
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
				InGameLevel();
				play();
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
	
	public void InGameLevel() {
		
		//add Panel
		JPanel inGameLevel = new JPanel();
		inGameLevel.setBounds(0, 0, 680, 430);
		inGameLevel.setBackground(Color.BLACK);
		inGameLevel.setForeground(Color.WHITE);
		inGameLevel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(inGameLevel);
		inGameLevel.setLayout(null);
		
		//add Top Panel
		inGameLevel.add(TopPanel());
		
		//add Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(0, 30, 680, 430);
		mainPanel.setLayout(null);
		inGameLevel.add(mainPanel);
		
		//add Left Panel
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setBounds(0, 0, 62, 430);
		mainPanel.add(leftPanel);
		leftPanel.setLayout(null);

		JLabel lblBack = new JLabel("");
		lblBack.setIcon(new ImageIcon("./img/BackButton.png"));
		lblBack.setBounds(10, 25, 40, 25);
		leftPanel.add(lblBack);
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//BACK TO MENU
				StartMenu();
			}
		});
		lblBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);

		//add Center Panel
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(111, 190, 75));
		centerPanel.setBounds(62, 0, 375, 430);
		mainPanel.add(centerPanel);
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
				//DELETE DOODLE
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
				//PRINT DOODLE
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

		//add Right Panel
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(0, 80, 115));
		rightPanel.setBounds(437, 0, 243, 430);
		mainPanel.add(rightPanel);
		rightPanel.setLayout(null);

		JLabel lblTime = new JLabel("Time");
		lblTime.setForeground(new Color(1, 187, 234));
		lblTime.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setBounds(29, 70, 46, 28);
		rightPanel.add(lblTime);

		JLabel lblTarget = new JLabel("Target");
		lblTarget.setHorizontalAlignment(SwingConstants.LEFT);
		lblTarget.setForeground(new Color(1, 187, 234));
		lblTarget.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblTarget.setBounds(29, 98, 46, 28);
		rightPanel.add(lblTarget);

		JLabel lblPredict = new JLabel("Predict");
		lblPredict.setHorizontalAlignment(SwingConstants.LEFT);
		lblPredict.setForeground(new Color(1, 187, 234));
		lblPredict.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblPredict.setBounds(29, 126, 46, 28);
		rightPanel.add(lblPredict);

		JLabel lblLevel = new JLabel("Level");
		lblLevel.setHorizontalAlignment(SwingConstants.LEFT);
		lblLevel.setForeground(new Color(1, 187, 234));
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

		currentTime = new JLabel("");
		currentTime.setHorizontalAlignment(SwingConstants.LEFT);
		currentTime.setForeground(Color.WHITE);
		currentTime.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		currentTime.setBounds(103, 70, 100, 28);
		rightPanel.add(currentTime);

		currentTarget = new JLabel("UMBRELLA");
		currentTarget.setHorizontalAlignment(SwingConstants.LEFT);
		currentTarget.setForeground(Color.WHITE);
		currentTarget.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		currentTarget.setBounds(105, 98, 98, 28);
		rightPanel.add(currentTarget);

		currentPredict = new JLabel("CLOUD");
		currentPredict.setHorizontalAlignment(SwingConstants.LEFT);
		currentPredict.setForeground(Color.WHITE);
		currentPredict.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		currentPredict.setBounds(105, 126, 98, 28);
		rightPanel.add(currentPredict);

		currentLevel = new JLabel("");
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

	public void ResultPanel() {
		//add Panel
		JPanel resultPanel = new JPanel();
		resultPanel.setBounds(0, 0, 680, 430);
		resultPanel.setBackground(Color.BLACK);
		resultPanel.setForeground(Color.WHITE);
		resultPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(resultPanel);
		resultPanel.setLayout(null);
		
		//add Top Panel
		resultPanel.add(TopPanel());
		
		//add Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(0, 80, 115));
		mainPanel.setBounds(0, 30, 680, 430);
		mainPanel.setLayout(null);
		resultPanel.add(mainPanel);
		
		JLabel lblTextScore = new JLabel("YOUR SCORE");
		lblTextScore.setForeground(Color.WHITE);
		lblTextScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextScore.setFont(new Font("Tw Cen MT", Font.BOLD, 40));
		lblTextScore.setBounds(224, 98, 232, 68);
		mainPanel.add(lblTextScore);
		
		JLabel lblScore = new JLabel("40");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setForeground(new Color(1, 187, 234));
		lblScore.setFont(new Font("Tw Cen MT", Font.BOLD, 50));
		lblScore.setBounds(224, 177, 232, 68);
		mainPanel.add(lblScore);
		
		//tombol back to menu
		JPanel menuButtonPanel = new JPanel();
		menuButtonPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				StartMenu();
			}
		});
		menuButtonPanel.setBounds(224, 266, 90, 28);
		mainPanel.add(menuButtonPanel);
		menuButtonPanel.setLayout(null);
		menuButtonPanel.setBackground(new Color(111, 190, 75));
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setBounds(0, 0, 90, 28);
		menuButtonPanel.add(lblMenu);
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setForeground(Color.WHITE);
		lblMenu.setFont(new Font("Tw Cen MT", Font.BOLD, 16));

		
		//tombol play again
		JPanel playAgainButtonPanel = new JPanel();
		playAgainButtonPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InGameLevel();
				play();
			}
		});
		playAgainButtonPanel.setBounds(366, 266, 90, 28);
		mainPanel.add(playAgainButtonPanel);
		playAgainButtonPanel.setLayout(null);
		playAgainButtonPanel.setBackground(new Color(111, 190, 75));
		
		JLabel lblPlayAgain = new JLabel("Play Again");
		lblPlayAgain.setBounds(0, 0, 90, 28);
		playAgainButtonPanel.add(lblPlayAgain);
		lblPlayAgain.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayAgain.setForeground(Color.WHITE);
		lblPlayAgain.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
	}

	public void HowTo() {
		JFrame frame2 = new JFrame("How To Play");
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setUndecorated(true);
		frame2.setBounds(0, 0, 348, 503);
		frame2.setVisible(true);
		frame2.setLocationRelativeTo(null);
	    
		JPanel contentPane = new JPanel();
		contentPane.setBounds(0, 0, 348, 503);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 80, 115));
		contentPane.setLayout(null);
		frame2.setContentPane(contentPane);
		
		//add Top Panel
		JPanel topPanel = new JPanel();
		contentPane.add(topPanel);
		topPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen();
                frame2.setLocation(currCoords.x - compCoords2.x, currCoords.y - compCoords2.y);
			}
		});
		
		topPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				compCoords2 = e.getPoint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				compCoords2 = null;
			}
		});
		topPanel.setBackground(new Color(0, 80, 115));
		topPanel.setBounds(0, 0, 348, 31);
		topPanel.setLayout(null);
		
		JLabel exitWindowButton = new JLabel("");
		exitWindowButton.setBounds(308, 0, 40, 30);
		topPanel.add(exitWindowButton);
		exitWindowButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame2.dispose();
			}
		});
		exitWindowButton.setIcon(new ImageIcon("./img/closeWindowButton.png"));
		exitWindowButton.setHorizontalAlignment(SwingConstants.CENTER);
		

		JPanel main = new JPanel();
		main.setBounds(0, 30, 348, 473);
		main.setBorder(new EmptyBorder(5, 5, 5, 5));
		main.setLayout(null);
		contentPane.add(main);
		
		JLabel image = new JLabel("");
		image.setIcon(new ImageIcon("./img/HowTo.png"));
		image.setBounds(0, 0, 348, 473);
		image.setHorizontalAlignment(SwingConstants.CENTER);
		main.add(image);
	}
	
	
	private Integer[] prepareLevel() {
		Set<Integer> levels = new HashSet<>();
		while(levels.size() <= 5) {
			levels.add((int) (Math.random() * (doodles.size() - 1)));
		}
		return levels.stream().toArray(Integer[]::new);
	}
	
	public void play() {
		Integer[] levels = prepareLevel();
		finished = false;	
		Thread level = new Thread(new Runnable() {
			@Override
			public void run() {
				final double frameTime = 1.0 / 60.0;
				long frameCounter = 0;
				
				int MAX_TIME = 5;
				int timer = -1;
				
				long lastTime = System.nanoTime();
				double unprocessedTime = 0;
				for (int i = 0; i < 5; i++) {
					currentLevel.setText(String.valueOf(i + 1));
					currentTarget.setText(doodles.get(levels[i]));
					System.out.println(i);
					while (timer <= MAX_TIME) {
						boolean update = false;
						long startTime = System.nanoTime();
						long passedTime = startTime - lastTime;
						lastTime = startTime;
						unprocessedTime += passedTime / (double) 1000000000L;
						frameCounter += passedTime;
						while (unprocessedTime > frameTime) {
							unprocessedTime -= frameTime;
							update = true;
							if (frameCounter >= 1000000000L) {
								frameCounter = 0;
								currentTime.setText(String.valueOf(MAX_TIME - timer++));
							}
							if (update) {	
								currentPredict.setText(doodles.get(drawArea.currentPredict));
							} else {
								try {
									Thread.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}
					drawArea.clear();
					timer = -1;
				}
				ResultPanel();
			}
			
		});
		level.start();
		System.out.println(finished);
	}
}
