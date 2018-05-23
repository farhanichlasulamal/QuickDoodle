package com.quickdoodle.game.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import java.awt.Checkbox;
import javax.swing.JRadioButton;
import java.awt.Panel;
import javax.swing.ImageIcon;

public class Setting extends JPanel {
	/**
	 * Create the frame.
	 */
	public Setting() {
		setBackground(new java.awt.Color(0, 80, 115));
		setBounds(470, 0, 210, 430);
		setVisible(false);
		
		JLabel lblClose = new JLabel("");
		lblClose.setIcon(new ImageIcon("./img/CloseSettingButton.png"));
		lblClose.setBounds(169, 21, 20, 20);
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				close();
			}
		});
		setLayout(null);
		lblClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblClose);
		
		JCheckBox shareDataCheck = new JCheckBox("Share Data Check");
		shareDataCheck.setFont(new Font("Tw Cen MT", Font.PLAIN, 12));
		shareDataCheck.setForeground(Color.WHITE);
		shareDataCheck.setBackground(new java.awt.Color(0, 80, 115));
		shareDataCheck.setBounds(31, 120, 129, 23);
		add(shareDataCheck);
		
		JPanel saveButtonPanel = new JPanel();
		saveButtonPanel.setBackground(new java.awt.Color(111, 190, 75));
		saveButtonPanel.setBounds(31, 162, 90, 28);
		add(saveButtonPanel);
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
		add(lblSetting);
	}
	
	public void close(){
		setVisible(false);
	}
}
