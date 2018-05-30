package com.quickdoodle.model.activationfunction;

import org.ejml.simple.SimpleMatrix;

public class Matrix {

	public static SimpleMatrix arrayToMatrix(double[] i) {
		double[][] input = { i };
		return new SimpleMatrix(input).transpose();
	}

	public static double[][] matrixTo2dArray(SimpleMatrix i) {
		double[][] result = new double[i.numRows()][i.numCols()];
		for (int j = 0; j < result.length; j++) {
			for (int k = 0; k < result[0].length; k++) {
				result[j][k] = i.get(j, k);
			}
		}
		return result;
	}

	public static double[] matrixToArray(SimpleMatrix data) {
		double[][] resultAs2dArray = Matrix.matrixTo2dArray(data);

		double[] result = new double[resultAs2dArray.length];

		for (int i = 0; i < result.length; i++) {
			result[i] = resultAs2dArray[i][0];
		}

		return result;
	}
	
	public static double[] stringToDoubleArray(String data) {
		String[] values = data.split(",");
		double[] result = new double[values.length];
		for(int i  = 0; i < values.length; i++) {
			result[i] = Double.valueOf(values[i]) / 255;
		}
		return result;
	}
	
	public static int[] stringToIntArray(String data) {
		String[] values = data.split(",");
		int[] result = new int[values.length];
		for(int i  = 0; i < values.length; i++) {
			result[i] = Integer.valueOf(values[i]);
		}
		return result;
	}
	
	public static String matrixToString(SimpleMatrix data, String header) {
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < data.numRows(); i++) {
			result.append(header +",");
			for(int j = 0; j < data.numCols(); j++) {
				result.append(Double.toString(data.get(i, j))+",");
			}
			result.append("\n");
		}

		return result.toString();
	}
}
