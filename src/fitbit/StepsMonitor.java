package fitbit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StepsMonitor extends Thread {
	
	private int currentSteps;

	public StepsMonitor() {
		currentSteps = 0;
	}
	
	public int getCurrentSteps() {
		return currentSteps;
	}
	
	public void run() {
		startMonitoringSteps();
	}

	private void startMonitoringSteps() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				readStepsDataFromFile("steps_data.txt");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	private void readStepsDataFromFile(String path) throws InterruptedException{
		BufferedReader stepsReader = null;
		
		try{   			
			stepsReader = new BufferedReader(new FileReader(path));
		    String stepsLine;
			while((stepsLine = stepsReader.readLine()) != null){
				if(stepsLine.equals("s")) {
					currentSteps += 1;
				}
				// get next step data point every 3 seconds
				Thread.sleep(3000);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally {
			if(stepsReader != null) {
				try {
				stepsReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		        		
	}
}