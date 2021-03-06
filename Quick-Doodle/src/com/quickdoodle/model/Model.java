package com.quickdoodle.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import org.ejml.simple.SimpleMatrix;

import com.quickdoodle.model.activationfunction.ActivationFunction;
import com.quickdoodle.model.activationfunction.Matrix;
import com.quickdoodle.model.activationfunction.ReLU;
import com.quickdoodle.model.activationfunction.Sigmoid;
import com.quickdoodle.model.activationfunction.Softmax;
import com.quickdoodle.trainer.datahandler.DBHandler;

public class Model {

	protected int inputNodes;
	protected int hiddenLayers;
	protected int outputNodes;

	protected int[] hiddenLayerNodes;

	protected ActivationFunction hiddenLayerActivation;
	protected ActivationFunction outputLayerActivation;

	protected SimpleMatrix[] weights;
	protected SimpleMatrix[] biases;

	protected Model(int inputNodes, int[] hiddenLayerNodes, int outputNodes) {
		this.inputNodes = inputNodes;
		this.hiddenLayerNodes = hiddenLayerNodes;
		this.hiddenLayers = hiddenLayerNodes.length;
		this.outputNodes = outputNodes;
	}

	public Model() {
		String data = null;
		try {
			data = loadData();
		} catch (FileNotFoundException e) {
			try {
				DBHandler.downloadFileEnd("model.csv");
				data = loadData();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			loadModel(data);
			setActivationFunction();
		}
	}

	private String loadData() throws FileNotFoundException {
		StringBuilder data = new StringBuilder();

		File file = new File("./data/local/model.csv");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				if (!line.equals(""))
					data.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data.toString();
	}

	protected void setActivationFunction() {
		if (hiddenLayers == 1) {
			hiddenLayerActivation = outputLayerActivation = new Sigmoid();
		} else {
			hiddenLayerActivation = new ReLU();
			outputLayerActivation = new Softmax();
		}
	}

	public void loadModel(String text) {
		String[] lines = text.split("\n");
		LinkedList<double[]> weightArray = new LinkedList<>();
		LinkedList<double[]> biasArray = new LinkedList<>();
		int[] config = null;
		for (int i = 0; i < lines.length; i++) {
			String[] values = lines[i].split(",");
			// Load model configuration
			if (values[0].equals("config")) {
				config = new int[values.length - 1];
				hiddenLayerNodes = new int[config.length - 2];
				inputNodes = Integer.parseInt(values[1]);
				for (int j = 0; j < config.length; j++) {
					config[j] = Integer.parseInt(values[j + 1]);
					if (j == 0) {
						inputNodes = config[j];
					} else if (j == config.length - 1) {
						outputNodes = config[j];
					} else {
						hiddenLayerNodes[j - 1] = config[j];
					}
				}
			}
			// Load model weights
			else if (values[0].equals("w")) {
				double[] weightValue = new double[values.length - 1];
				for (int j = 0; j < weightValue.length; j++) {
					weightValue[j] = Double.parseDouble(values[j + 1]);
				}
				weightArray.addLast(weightValue);
			}
			// Load model biases
			else if (values[0].equals("b")) {
				double[] biasValue = new double[values.length - 1];
				for (int j = 0; j < biasValue.length; j++) {
					biasValue[j] = Double.parseDouble(values[j + 1]);
				}
				biasArray.addLast(biasValue);
			}
		}

		// Reconstruct model with weight and bias
		hiddenLayers = hiddenLayerNodes.length;
		int modelDepth = hiddenLayers + 1;
		weights = new SimpleMatrix[modelDepth];
		biases = new SimpleMatrix[modelDepth];
		for (int i = 0; i < modelDepth; i++) {
			double[][] weightValue = new double[config[i + 1]][config[i]];
			double[][] biasValue = new double[config[i + 1]][1];
			for (int j = 0; j < config[i + 1]; j++) {
				// Reconstruct weights
				weightValue[j] = weightArray.remove();
				// Reconstruct biases
				biasValue[j] = biasArray.remove();
			}
			weights[i] = new SimpleMatrix(weightValue);
			biases[i] = new SimpleMatrix(biasValue);
		}
	}

	public double[] guess(double[] input) {
		SimpleMatrix output = Matrix.arrayToMatrix(input);
		for (int i = 0; i < hiddenLayers; i++) {
			output = calculateLayer(weights[i], biases[i], output, hiddenLayerActivation);

		}
		output = calculateLayer(weights[hiddenLayers], biases[hiddenLayers], output, outputLayerActivation);
		return Matrix.matrixToArray(output);
	}

	protected SimpleMatrix calculateLayer(SimpleMatrix weights, SimpleMatrix bias, SimpleMatrix input,
			ActivationFunction function) {
		SimpleMatrix result = weights.mult(input);
		result = result.plus(bias);
		return function.apply(result, false);
	}

	public String export() {
		// Save model configuration
		StringBuilder buffer = new StringBuilder();
		buffer.append("config," + String.valueOf(inputNodes) + ",");
		for (int nodes : hiddenLayerNodes) {
			buffer.append(String.valueOf(nodes) + ",");
		}
		buffer.append(String.valueOf(outputNodes + "\n"));
		// Save weights
		for (SimpleMatrix weight : weights) {
			buffer.append(Matrix.matrixToString(weight, "w") + "\n");
		}
		// Save biases
		for (SimpleMatrix bias : biases) {
			buffer.append(Matrix.matrixToString(bias, "b") + "\n");
		}
		return buffer.toString();
	}
}
