package test;

import java.util.LinkedHashMap;

import com.quickdoodle.model.activationfunction.DataUtils;
import com.quickdoodle.trainer.ModelTrainer;

public class Test {
	public static void main(String[] args) {
		LinkedHashMap<Integer, Float> schedule = new LinkedHashMap<Integer, Float>();
		schedule.put(1, 0.1f);
		schedule.put(3, 0.05f);
		schedule.put(6, 0.02f);
		schedule.put(8, 0.01f);
		
		ModelTrainer trainer = new ModelTrainer();
		trainer.setMaxEpoch(10);
		trainer.setSchedule(schedule);
		String[] objectList = {
				"0 bat", "1 cat", "2 circle", "3 diamond", "4 fish",
			"5 flower", "6 line", "7 nail", "8 star", "9 zigzag"};		
		
		trainer.initializeModel(784, new int[]{15}, 10);
		trainer.initializeDataset(objectList, 20, 0.9f);
		trainer.train();
		DataUtils.saveText("./model.csv", trainer.exportModel());
		/*Dataset dataset = new Dataset(objectList, 10, 0.8f);
		TrainableModel m = new TrainableModel(784, new int[]{1000}, 10);
		ArrayList<Doodle> train = dataset.getTrainDatas();
		Doodle data = train.get(0);
		m.train(data.getPixelValues(), data.getTarget());
		double[] result = m.guess(data.getPixelValues());
		//m.train(new double[]{10}, new double[] {1, 0});
		System.out.println(Arrays.toString(result));*/
		
		/*int[] hiddenLayers = {10};
		TrainableModel nn = new TrainableModel(2, hiddenLayers, 2);
		nn.setLearningRate(0.03);
		double[][] input = {
				{1.0, 90}, 
				{190, 1.0}, 
				{120, 1.0}, 
				{6.0, 200}, 
				{5.0, 90}, 
				{80, 2.0}, 
				{98, 1.0}, 
				{1.0, 100}};
		double[][] target = {
				{0.0, 1.0}, 
				{1.0, 0.0}, 
				{1.0, 0.0}, 
				{0.0, 1.0}, 
				{0.0, 1.0}, 
				{1.0, 0.0}, 
				{1.0, 0.0}, 
				{0.0, 1.0}};
		for(int i = 0; i < 100; i++) {
			for(int j = 0; i < input.length; i++)
				nn.train(input[j], target[j]);
			nn.setLearningRate(nn.getLearningRate() * 0.99);
		}
		double[][] test = {
				{1.0, 190}, 
				{190, 1.0}, 
				{120, 1.0}};
		String model = nn.export();
		
		for(int i = 0; i < test.length; i++) {
			double[] result = nn.guess(test[i]);
			for(int j = 0; j < result.length; j++)
				System.out.print(result[j] +"  ");
			System.out.println();
		}
		System.out.println();
		Model m = new Model(model);
		
		//System.out.println(m.export());
		for(int i = 0; i < test.length; i++) {
			double[] result = m.guess(test[i]);
			for(int j = 0; j < result.length; j++)
				System.out.print(result[j] +"  ");
			System.out.println();
		}*/
	}
}
