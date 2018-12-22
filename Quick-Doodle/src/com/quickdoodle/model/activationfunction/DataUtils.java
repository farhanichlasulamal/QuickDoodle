package com.quickdoodle.model.activationfunction;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataUtils {
	
	static final int DOODLE_PIXELS = 784;
	
	static public String npyToOneHotVector(String path) {
		Path fileLocation = Paths.get(path);
		StringBuffer buffer = new StringBuffer();
		int header = 80;
		try {
			byte[] datas = Files.readAllBytes(fileLocation);
			int pixelCounter = 0;
			for(int i = 0; i < datas.length - header; i++) {
				buffer.append(String.valueOf(datas[i + header] & 0xff));
				pixelCounter++;
				if(pixelCounter == DOODLE_PIXELS) {
					pixelCounter = 0;
					buffer.append("\n");
				} else {
					buffer.append(",");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
	static public void saveText(String path, String text) {
		try {
			File file = new File(path);
			file.getParentFile().mkdirs();
			file.createNewFile();
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
			writer.print(text);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		String name = "zigzag";
//		String text = npyToOneHotVector("D:\\My Data Set\\Google Quick Draw\\npy\\" +name +".npy");
//		saveText("./"+name+".csv", text);
//	}
//	
}
