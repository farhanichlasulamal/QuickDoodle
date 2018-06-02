package com.quickdoodle.trainer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.quickdoodle.model.TrainableModel;
import com.quickdoodle.trainer.datahandler.Dataset;
import com.quickdoodle.trainer.datahandler.Doodle;

public class ModelTrainer {

	private LinkedHashMap<Integer, Float> schedule;
	private int maxEpoch;
	private TrainableModel model;
	private Dataset dataset;

	public ModelTrainer() {
		maxEpoch = 1;
		schedule = new LinkedHashMap<>();
		schedule.put(1, 0.1f);
	}

	public String initializeDataset(String[] objectNames, int quantity, float ratio) {
		if (quantity < 1) {
			throw new IllegalArgumentException("Invalid Quantity Value");
		}
		dataset = new Dataset(objectNames, quantity, ratio);
		return dataset.evaluate();
	}

	public String initializeModel(int inputNodes, int[] hiddenLayerNodes, int outputNodes) {
		this.model = new TrainableModel(inputNodes, hiddenLayerNodes, outputNodes);
		//System.out.println(model.export());
		return String.format("%8H", model.hashCode());
	}

	public Map<Integer, Float> getSchedule() {
		return schedule;
	}
	
	public void train() {
		Float learningRates;
		
		for (int i = 1; i <= maxEpoch; i++) {
			if ((learningRates = schedule.get(i)) != null) {
				model.setLearningRate(learningRates);
			}
			for(Doodle trainData : dataset.getTrainDatas()) {
				model.train(trainData.getPixelValues(), trainData.getTarget());
			}
			if ((maxEpoch / 10) == 0 || i % (maxEpoch / 10) == 0) {
				float[] modelAccuracy = new float[dataset.getClassSize()];
				for(Doodle testData : dataset.getTestDatas()) {
					double[] result = model.guess(testData.getPixelValues());
					if(isCorrect(result, testData.getLabel())) {
						modelAccuracy[testData.getLabel()]++;
					}
				}
				for(int j = 0; j < dataset.getClassSize(); j++) {
					System.out.println(dataset.getLabelName(j) +"\t" +(float)modelAccuracy[j] / dataset.getTestQuantity(j)+"\t");
				}
				System.out.println("");
			}
		}
	}
	
	private boolean isCorrect(double[] guessResult, int label) {
	  int awnser = 0;
	  System.out.println();
	  for ( int i = 1; i < guessResult.length; i++ ) {
	      if ( guessResult[i] > guessResult[awnser] ) awnser = i;
	  }
	  return awnser == label;
	}

	public void setSchedule(LinkedHashMap<Integer, Float> schedule) {
		List<Entry<Integer, Float>> scheduleList = new ArrayList<Map.Entry<Integer, Float>>(schedule.entrySet());
		// Cek apakah order schedule benar
		for (int i = 0; i < scheduleList.size() - 1; i++) {
			Entry<Integer, Float> current = scheduleList.get(i);
			Entry<Integer, Float> next = scheduleList.get(i + 1);
			if (current.getKey() > next.getKey()) {
				throw new IllegalArgumentException("Invalid Schedule Order");
			}
		}
		// Cek apakah nilai input schedule benar
		for (Map.Entry<Integer, Float> entry : schedule.entrySet()) {
			if (entry.getValue() > 1.0) {
				throw new IllegalArgumentException("Invalid Learning Rate Value at Schedule");
			}
			if (entry.getKey() > maxEpoch || entry.getKey() < 1) {
				throw new IllegalArgumentException("Invalid Epoch Value at Schedule");
			}
		}
		this.schedule = schedule;
	}

	public int getMaxEpoch() {
		return maxEpoch;
	}

	public void setMaxEpoch(int epoch) {
		if (epoch < 1) {
			throw new IllegalArgumentException("Invalid Epoch Value");
		}
		this.maxEpoch = epoch;
	}

	public String exportModel() {
		return model.export();
	}
}
