package com.quickdoodle.model.activationfunction;

import org.ejml.simple.SimpleMatrix;

public class Softmax implements ActivationFunction {
	
	private static double sum;
	
	public SimpleMatrix apply(SimpleMatrix input, boolean derivative) {
		SimpleMatrix output = new SimpleMatrix(input.numRows(), input.numCols());
		sum = 0;
		for (int i = 0; i < input.numRows(); i++) {
			for (int j = 0; j < input.numCols(); j++) {
				double value = input.get(i, j);
				sum += Math.exp(value);
			}
		}
		
		for (int i = 0; i < input.numRows(); i++) {
			for (int j = 0; j < input.numCols(); j++) {
				if (derivative) {
					output.set(i, j, derivative(input.get(i, j)));
				} else {
					output.set(i, j, function(input.get(i, j)));
				}
			}
		}
		return output;
	}
	
	private double function(double value) {
		return Math.exp(value) / sum;
	}
	
	private double derivative(double value) {
		double y = function(value);
		return y * (1 - y);
	}
}
