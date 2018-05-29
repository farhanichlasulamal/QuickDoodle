package com.quickdoodle.model.activationfunction;

import org.ejml.simple.SimpleMatrix;

public interface ActivationFunction {
	
	public SimpleMatrix apply(SimpleMatrix input, boolean derivative);
	
}
