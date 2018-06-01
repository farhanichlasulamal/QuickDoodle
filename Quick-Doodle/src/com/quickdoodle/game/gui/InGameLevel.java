package com.quickdoodle.game.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InGameLevel extends JPanel {
	DrawArea drawArea;
	Timer timer;
	JLabel currentTime;
	JLabel currentTarget;
	JLabel currentPredict;
	JLabel currentLevel;
	boolean finished;

	/**
	 * Create the panel.
	 */
	public InGameLevel() {
		setBackground(Color.BLUE);
		setBounds(0, 30, 680, 430);
		setLayout(null);

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

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(111, 190, 75));
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
		rightPanel.setBackground(new Color(0, 80, 115));
		rightPanel.setBounds(437, 0, 243, 430);
		add(rightPanel);
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

	public void close() {
		setVisible(false);
		// timer.cancel();
	}

	public void showTimer() {
		// timer = new Timer();
		// timer.schedule(new countDown(), 0, 1000);
	}

	public void showPrediction() {
	}

	public void showTarget() {
	}

	public void showLevel() {
	}

	public void play() {
		String[] levels = {"A", "B", "C", "D", "E"};
		Thread thread = new Thread() {
			public void run() {
				final double frameTime = 1.0 / 60.0;
				long frameCounter = 0;

				int MAX_TIME = 5;
				int timer = 0;

				long lastTime = System.nanoTime();
				double unprocessedTime = 0;
				for (int i = 0; i < 5; i++) {
					currentLevel.setText(String.valueOf(i + 1));
					currentTarget.setText(levels[i]);
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
							if (frameCounter >= 1000000000L) {
								frameCounter = 0;
								currentTime.setText(String.valueOf(MAX_TIME - timer));
								timer++;
							}
							if (update) {
								// Ibarat fungsi draw diprocessing, semua operasi disini
							} else {
								try {
									Thread.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}
					timer = 0;
				}
				close();
			}
			
		};
		thread.start();
		
	}

	public void updatePixelValue() {
	}

}
