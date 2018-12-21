package com.quickdoodle.trainer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Arrays;
import java.util.LinkedHashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.quickdoodle.model.activationfunction.DataUtils;

public class TrainerGUI extends JFrame {
	static Point compCoords;
	private JTextField textFieldName;
	private JTextField textFieldEpoch;
	private JTextField textFieldLayer;
	private JTextField textFieldBatch;
	private JTextField textFieldRatio;
	private JTextArea textAreaSchedule;
	private JTextArea textAreaLog;
	
	private ModelTrainer trainer;
	private String[] objectList;
	private Thread trainerThread;
	
	public TrainerGUI() {
		trainer = new ModelTrainer();
		//ini object list, seharusnya dari data base
		objectList = new String[]{
			"0 bus", 
			"1 cat", 
			"2 carrot", 
			"3 diamond", 
			"4 fish",
			"5 flower"};
		addFrame();
		buildGUI();
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
	
	public void buildGUI() {
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
					String stringName = textFieldName.getText();
					String stringSchedule = textAreaSchedule.getText();
					String stringEpoch = textFieldEpoch.getText();
					String stringLayer = textFieldLayer.getText();
					String stringBatch = textFieldBatch.getText();
					String stringRatio = textFieldRatio.getText();
					
					if(stringName.isEmpty()) {
						JOptionPane.showMessageDialog(null, "silakan masukkan nama!");
					} else if (stringSchedule.isEmpty()) {
						JOptionPane.showMessageDialog(null, "silakan masukkan Schedule!");
					} else if (stringEpoch.isEmpty()) {
						JOptionPane.showMessageDialog(null, "silakan masukkan jumlah Epoch!");
					} else if (stringLayer.isEmpty()) {
						JOptionPane.showMessageDialog(null, "silakan masukkan Layer!");
					} else if (stringBatch.isEmpty()) {
						JOptionPane.showMessageDialog(null, "silakan masukkan Batch!");
					} else if (stringRatio.isEmpty()) {
						JOptionPane.showMessageDialog(null, "silakan masukkan Rasio!");
					} else {
						try {
							textAreaLog.removeAll();
							if(trainerThread != null) {
								trainerThread.stop();
							}
							LinkedHashMap<Integer, Float> schedule = parseScheduleInput(stringSchedule);
							int[] hiddenLayers = parseLayerInput(stringLayer);
							trainer.setMaxEpoch(Integer.parseInt(stringEpoch));
							trainer.setSchedule(schedule);
							trainer.initializeModel(784, hiddenLayers, objectList.length);
							String dataSet = trainer.initializeDataset(objectList, Integer.parseInt(stringBatch), Float.parseFloat(stringRatio));
							textAreaLog.setText(dataSet);
							trainerThread = new Thread(new Runnable() {

								@Override
								public void run() {
									trainer.train(textAreaLog);							
								}
								
							});
							trainerThread.start();
							String result = trainer.exportModel();
							//Kirim kedata base
							DataUtils.saveText("./" +stringName + ".csv", result);
						} catch (NumberFormatException a) {
							JOptionPane.showMessageDialog(null, "Epoch dan Batch harus berupa angka!");
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}					
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
		
		JPanel shareButtonPanel = new JPanel();
		shareButtonPanel.setBounds(545, 216, 90, 28);
		buttomPanel.add(shareButtonPanel);
		shareButtonPanel.setLayout(null);
		shareButtonPanel.setBackground(Color.RED);
		
		JLabel lblShare = new JLabel("Share");
		lblShare.setHorizontalAlignment(SwingConstants.CENTER);
		lblShare.setForeground(Color.WHITE);
		lblShare.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
		lblShare.setBounds(0, 0, 90, 28);
		shareButtonPanel.add(lblShare);
		
		textAreaLog = new JTextArea();
		textAreaLog.setForeground(Color.WHITE);
		textAreaLog.setBackground(Color.BLACK);
		JScrollPane scroll = new JScrollPane(textAreaLog);
		scroll.setBounds(0, 460, 680, 200);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Trainer.add(scroll);

		
	}
	
	public LinkedHashMap<Integer, Float> parseScheduleInput(String input) throws Exception {
		String[] schedules = input.split("\n");
		LinkedHashMap<Integer, Float> result = new LinkedHashMap<>();
		for(String schedule : schedules) {
			schedule = schedule.replaceAll(" ","");
			String[] values = schedule.split(",");
			if(values.length != 2) {
				throw new IllegalArgumentException();
			}
			result.put(Integer.parseInt(values[0]), Float.parseFloat(values[1]));
		}
		return result;
	}
	
	public int[] parseLayerInput(String input) throws Exception{
		input = input.replaceAll(" ","");
		String[] layers = input.split(",");
		int[] result = Arrays.asList(layers).stream().mapToInt(Integer::parseInt).toArray();
		return result;
	}
}
