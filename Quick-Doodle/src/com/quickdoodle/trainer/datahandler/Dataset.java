package com.quickdoodle.trainer.datahandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class Dataset {
	
	private ArrayList<Doodle> train;
	private ArrayList<Doodle> test;
	private int[] trainQuantities;
	private int[] testQuantites;
	private float ratio;
	private String[] labelNames;
	private int capacity;
	private int classSize;
	
	public Dataset(String[] classes, int capacity, float ratio) {
		this.capacity = capacity;
		this.ratio = ratio;
		this.classSize = classes.length;
		train = new ArrayList<>();
		test = new ArrayList<>();
		trainQuantities = new int[classes.length];
		testQuantites = new int[classes.length];
		labelNames = new String[classes.length];
		for(int i = 0; i < classes.length; i++) {
			Batch batch = new Batch(classes[i], capacity, classes.length);
			trainQuantities[i] = (int) (batch.getSize() * ratio);
			testQuantites[i] = batch.getSize() - trainQuantities[i];
			train.addAll(batch.getDoodles().subList(0, trainQuantities[i]));
			test.addAll(batch.getDoodles().subList(trainQuantities[i], batch.getSize()));
			labelNames[i] = batch.getLabelName();
			
		}
		Collections.shuffle(train);
	}
	
	public ArrayList<Doodle> getTrainDatas() {
		return train;
	}
	
	public ArrayList<Doodle> getTestDatas() {
		return test;
	}
	
	public String evaluate() {
		StringBuilder eval = new StringBuilder();
		int trainSizeSum = IntStream.of(trainQuantities).sum();
		int testSizeSum = IntStream.of(testQuantites).sum();
		eval.append("Dataset Created\n");
		eval.append("Total Capacity: " +capacity * labelNames.length +"\n");
		eval.append("Dataset Ratio : " +ratio +"\n");
		eval.append("Train Dataset : " +trainSizeSum +"\n");
		eval.append("Test  Dataset : " +testSizeSum +"\n");
		eval.append("Total Dataset : " +(trainSizeSum + testSizeSum)+"\n");
		eval.append("===================================\n");
		eval.append("Doodle Name       Train       Test \n");
		eval.append("===================================\n");
		float trainSize = (float) Math.floor(capacity * ratio);
		float testSize = capacity - trainSize;
		for(int i = 0; i < labelNames.length; i++) {
			eval.append(String.format("%-13s    %3.2f%%    %3.2f%% \n", labelNames[i], (trainQuantities[i] / trainSize) * 100, (testQuantites[i] / testSize) * 100));
		}
		return eval.toString();
	}

	public int getTrainDataSize() {
		return IntStream.of(trainQuantities).sum();
	}
	
	public int getTestDataSize() {
		return IntStream.of(testQuantites).sum();
	}
	
	public int getTestQuantity(int index) {
		return this.testQuantites[index];
	}
	
	public String getLabelName(int index) {
		return labelNames[index];
	}
	public int getClassSize() {
		return classSize;
	}
}
