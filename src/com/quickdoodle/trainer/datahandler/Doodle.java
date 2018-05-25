package com.quickdoodle.trainer.datahandler;

import com.quickdoodle.model.activationfunction.Matrix;

public class Doodle {
	/**Instace variable untuk menyimpan nilai pixel dari doodle pada array satu dimensi*/
	private double[] pixelValues;
	/**Instace variable untuk menyimpan target vector dari doodle*/
	private double[] target;
	/**Instace variable untuk menyimpan nilai label dari doodle. Nilai ini digunakan untuk menetapkan nilai pada target vector dari doodle*/
	private int label;
	/**Instace variable untuk menyimpan nama label dari doodle.*/
	private String labelName;

	/**
	 * Mengembalikan  object Doodle yang akan digunakan untuk proses training.
	 * 
	 * @param pixelValues
	 *          nilai pixel doodle yang disimpan pada array satu dimensi (one hot vector)
	 * @param label
	 * 			nilai label dari doodle
	 * @param classSize
	 * 			ukuran class dari model
	 * @return Doodle dengan vector target
	 */
	Doodle(int label, String labelName, int classSize, String pixelValues) {
		this.pixelValues = Matrix.stringToDoubleArray(pixelValues);
		this.target = new double[classSize];
		this.target[label] = 1.0;
		this.label = label;
		this.labelName = labelName;
	}	
	
	/**
	 * Method ini digunakan untuk mengambil nilai pixel doodle yang disimpan pada array satu dimensi (one hot vector)
	 * 
	 * @return nilai pixel doodle yang disimpan pada array satu dimensi (one hot vector)
	 */
	public double[] getPixelValues() {
		return pixelValues;
	}
	
	/**
	 * Method ini digunakan untuk mengambil target vector dari doodle
	 * 
	 * @return nilai target vector dari doodle
	 */
	public double[] getTarget() {
		return target;
	}
	
	/**
	 * Method ini digunakan untuk mengambil nilai label dari doodle
	 * 
	 * @return nilai label dari doodle
	 */
	public int getLabel() {
		return label;
	}
	
	/**
	 * Method ini digunakan untuk mengambil nama label dari doodle
	 * 
	 * @return nama label dari doodle
	 */
	public String getLabelName() {
		return labelName;
	}
}
