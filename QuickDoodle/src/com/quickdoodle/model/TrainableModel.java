package com.quickdoodle.model;

import java.util.Random;

import org.ejml.simple.SimpleMatrix;

import com.quickdoodle.model.activationfunction.ActivationFunction;
import com.quickdoodle.model.activationfunction.Matrix;

public class TrainableModel extends Model{
	
	private Random r = new Random(123);
	private double learningRate = 0.1;
	
	public TrainableModel(int inputNodes, int[] hiddenLayerNodes, int outputNodes) {
		this.inputNodes = inputNodes;
		this.hiddenLayerNodes = hiddenLayerNodes;
		this.hiddenLayers = hiddenLayerNodes.length;
		this.outputNodes = outputNodes;
		initializeWeights();
		initializeBiases();
		setActivationFunction();
	}
	
	public String export() {
		//Save model configuration
		StringBuilder buffer = new StringBuilder();
		buffer.append("config," +String.valueOf(inputNodes) +",");
		for(int nodes : hiddenLayerNodes) {
			buffer.append(String.valueOf(nodes) +",");
		}
		buffer.append(String.valueOf(outputNodes +"\n"));
		//Save weights
		for(SimpleMatrix weight : weights) {
			buffer.append(Matrix.matrixToString(weight, "w") +"\n");
		}
		//Save biases
		for(SimpleMatrix bias : biases) {
			buffer.append(Matrix.matrixToString(bias, "b") +"\n");
		}
		return buffer.toString();
	}
	
	private void initializeWeights() {
		weights = new SimpleMatrix[hiddenLayers + 1];

		for (int i = 0; i < weights.length; i++) {
			if (i == 0) {
				weights[i] = SimpleMatrix.random64(hiddenLayerNodes[0], inputNodes, -1, 1, r);
			} else if (i == weights.length - 1) {
				weights[i] = SimpleMatrix.random64(outputNodes, hiddenLayerNodes[hiddenLayers - 1], -1, 1, r);
			} else {
				weights[i] = SimpleMatrix.random64(hiddenLayerNodes[i], hiddenLayerNodes[i - 1], -1, 1, r);
			}
		}
	}

	private void initializeBiases() {
		biases = new SimpleMatrix[hiddenLayers + 1];
		for (int i = 0; i < biases.length; i++) {
			if (i == biases.length - 1) {
				biases[i] = SimpleMatrix.random64(outputNodes, 1, -1, 1, r);
			} else {
				biases[i] = SimpleMatrix.random64(hiddenLayerNodes[i], 1, -1, 1, r);
			}
		}
	}

	public void train(double[] i, double[] t) {
		SimpleMatrix inputs = Matrix.arrayToMatrix(i);
		SimpleMatrix targets = Matrix.arrayToMatrix(t);

		SimpleMatrix layers[] = new SimpleMatrix[hiddenLayers + 2];
		layers[0] = inputs;
		for (int j = 1; j < hiddenLayers + 2; j++) {
			ActivationFunction function = j == hiddenLayers + 1 ? outputLayerActivation : hiddenLayerActivation;
			layers[j] = calculateLayer(weights[j - 1], biases[j - 1], inputs, function);
			inputs = layers[j];
		}

		for (int n = hiddenLayers + 1; n > 0; n--) {
			ActivationFunction function = n == hiddenLayers + 1 ? outputLayerActivation : hiddenLayerActivation;
			SimpleMatrix errors = targets.minus(layers[n]);
			SimpleMatrix gradients = calculateGradient(layers[n], errors, function);
			SimpleMatrix deltas = calculateDeltas(gradients, layers[n - 1]);

			biases[n - 1] = biases[n - 1].plus(gradients);
			weights[n - 1] = weights[n - 1].plus(deltas);

			SimpleMatrix previousError = weights[n - 1].transpose().mult(errors);
			targets = previousError.plus(layers[n - 1]);
		}
	}

	private SimpleMatrix calculateGradient(SimpleMatrix layer, SimpleMatrix error, ActivationFunction function) {
		SimpleMatrix gradient = function.apply(layer, true);
		gradient = gradient.elementMult(error);
		return gradient.scale(learningRate);
	}

	private SimpleMatrix calculateDeltas(SimpleMatrix gradient, SimpleMatrix layer) {
		return gradient.mult(layer.transpose());
	}
	
	public double getLearningRate() {
		return learningRate;
	}

	public void setLearningRate(double learningRate) {
		this.learningRate = learningRate;
	}
}
