package com.quickdoodle.trainer.datahandler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Batch {
	private List<Doodle> doodles;
	private int capacity;
	private int classSize;
	private String labelName;

	public Batch(String object, int capacity, int classSize) {
		doodles = new ArrayList<>();
		this.capacity = capacity;
		this.classSize = classSize;
		readFile(object);
	}

	private void readFile(String filename) {
		InputStream ins = null;
		Reader r = null;
		BufferedReader br = null;
		String[] doodleData = filename.split(" ");
		labelName = doodleData[1];
		try {
			String pixelValues;
			ins = new FileInputStream("./Database Dummy/" + filename + ".csv");
			r = new InputStreamReader(ins);
			br = new BufferedReader(r);
			while ((pixelValues = br.readLine()) != null && doodles.size() < capacity) {
				doodles.add(new Doodle(Integer.valueOf(doodleData[0]), labelName, classSize, pixelValues));
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Throwable t) {
				}
			}
			if (r != null) {
				try {
					r.close();
				} catch (Throwable t) {
				}
			}
			if (ins != null) {
				try {
					ins.close();
				} catch (Throwable t) {
				}
			}
		}
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public int getSize() {
		return this.doodles.size();
	}
	
	public List<Doodle> getDoodles() {
		return doodles;
	}

	public String getLabelName() {
		return labelName;
	}
}
