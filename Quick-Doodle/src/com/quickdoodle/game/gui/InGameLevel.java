package com.quickdoodle.game.gui;

import java.awt.Color;

import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;

public class InGameLevel extends JPanel {
	DrawArea drawArea;
	Timer timer;
	public JPanel resultPanel;
	JLabel currentTime;
	JLabel currentTarget;
	JLabel currentPredict;
	JLabel currentLevel;
	private boolean inGameStatus;
	
	/**
	 * Create the panel.
	 */
	public InGameLevel() {
		setBackground(Color.BLUE);
		setBounds(0, 30, 680, 430);
		setLayout(null);
		
		resultPanel = new JPanel();
		resultPanel.setBackground(new Color(0, 80, 115));
		resultPanel.setBounds(0, 0, 680, 430);
		add(resultPanel);
		resultPanel.setLayout(null);
		resultPanel.setVisible(false);
		
		JLabel lblTextScore = new JLabel("YOUR SCORE");
		lblTextScore.setForeground(Color.WHITE);
		lblTextScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextScore.setFont(new Font("Tw Cen MT", Font.BOLD, 40));
		lblTextScore.setBounds(224, 98, 232, 68);
		resultPanel.add(lblTextScore);
		
		JLabel lblScore = new JLabel("40");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setForeground(new Color(1, 187, 234));
		lblScore.setFont(new Font("Tw Cen MT", Font.BOLD, 50));
		lblScore.setBounds(224, 177, 232, 68);
		resultPanel.add(lblScore);
		
		JPanel menuButtonPanel = new JPanel();
		menuButtonPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				resultPanel.setVisible(false);
				close();
			}
		});
		menuButtonPanel.setBackground(new Color(111, 190, 75));
		menuButtonPanel.setBounds(224, 266, 90, 28);
		resultPanel.add(menuButtonPanel);
		menuButtonPanel.setLayout(null);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setForeground(Color.WHITE);
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblMenu.setBounds(0, 0, 90, 28);
		menuButtonPanel.add(lblMenu);
		
		JPanel playAgainButtonPanel = new JPanel();
		playAgainButtonPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resultPanel.setVisible(false);
			}
		});
		playAgainButtonPanel.setBounds(366, 266, 90, 28);
		resultPanel.add(playAgainButtonPanel);
		playAgainButtonPanel.setLayout(null);
		playAgainButtonPanel.setBackground(new Color(111, 190, 75));
		
		JLabel lblPlayAgain = new JLabel("Play Again");
		lblPlayAgain.setBounds(0, 0, 90, 28);
		playAgainButtonPanel.add(lblPlayAgain);
		lblPlayAgain.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayAgain.setForeground(Color.WHITE);
		lblPlayAgain.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		
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
		
		currentTime = new JLabel("5");
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
		
		currentLevel = new JLabel("1");
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
//		timer.cancel();
	}

	public void showTimer() {
//		timer = new Timer();
//      timer.schedule(new countDown(), 0, 1000);
	}
	
	public void showPrediction() {}
	
	public void showTarget() {}
	
	public void showLevel() {}
	
	public boolean getInGameStatus() {
		return inGameStatus;
	}
	
	public void setInGameStatus(boolean status) {
		inGameStatus = status;
	}
	
	public void play() {
		while(true) {
			playLevel();
			resultPanel.setVisible(true);
		}
	}
	
	public void playLevel() {
			timer = new Timer();
			timer.schedule(new countDown(), 0, 1000);
	}
	
	class countDown extends TimerTask {
        int sec = Integer.parseInt(currentTime.getText());
        int level = Integer.parseInt(currentLevel.getText());
        
        public void run() {
        	currentLevel.setText(String.valueOf(level));
        	
        	if (sec > 0) {
            	currentTime.setText(String.valueOf(sec));
                sec--;
            } else {
            	currentTime.setText("0");
            	if(level<5) {
            		level++;
            		sec = 5;
            	} else {
            		cancel();
            		resultPanel.setVisible(true);
            	}
            }
        }
    }
	
	public void updatePixelValue() {}
}
