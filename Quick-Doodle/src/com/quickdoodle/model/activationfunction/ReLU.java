package com.quickdoodle.model.activationfunction;

import org.ejml.simple.SimpleMatrix;

public class ReLU implements ActivationFunction{
	
	private double leak;
	
	public ReLU() {
		this(0.0);
	}
	
	public ReLU(double leak) {
		this.leak = leak;
	}
	
	public SimpleMatrix apply(SimpleMatrix input, boolean derivative) {
		SimpleMatrix output = new SimpleMatrix(input.numRows(), input.numCols());
		for (int i = 0; i < input.numRows(); i++) {
			for (int j = 0; j < input.numCols(); j++) {
				double value = input.get(i, j);
				if (derivative) {
					output.set(i, j, derivative(value));
				} else {
					output.set(i, j, function(value));
				}
			}
		}

		return output;
	}

	private double function(double input) {
		return input >= 0 ? input : input * leak;
	}

	private double derivative(double input) {
		return input >= 0 ? 1 : 0;
	}
}
