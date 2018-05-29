package com.quickdoodle.game;

import java.util.List;
import java.util.Map;
import java.util.Timer;

import com.quickdoodle.model.Model;
import com.quickdoodle.trainer.datahandler.Doodle;

public class QuickDoodle {
	Model guesser;
	List<Doodle> correctUserDoodles;
	List<Integer> level;
	Map<Integer,String> doodleDataBase;
	List<boolean[][]> userLevelDoodles;
	boolean[][] pixelValue;
	List<String> levelStatus;
	Timer timer;
	
	public void prepareLevel() {}
	
	public int predictDoodle() {
		return 0;
	}
	
	public void playLevel() {
		
	}
	
	public void storeDoodleToDatabase() {}
	
	public void checkLatestModel() {}
	
	public void updateModelDatabase() {}
	
	public void loadConfig() {}
	
	public void saveConfig() {}
	
	public void updateLevelStatus() {}
	
	public void countDown() {}
}
