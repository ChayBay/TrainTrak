// Made by Connor Shipman
// Much of this code can be refined to work better or look cleaner.  This is a basic draft and outline of the adding routes and delays.

// Edited by Chason Bibeau
//Train Object and Vector Implementation By Chason Bibeau
import java.io.*;
import java.util.Scanner;
import java.util.Vector;


public class TrainAdmin {
	
	public static Vector<Train> trainConvert = new Vector<Train>();
	
	/* kept for testing
	public static void main(String args[]) {
	}
	*/
	
	//Chason
	public static void Initialize() throws IOException {
		
		FileReader file = new FileReader("C:\\Users\\Edene\\eclipse-workspace\\The tester\\src\\TrainSchedule.txt"); //replace with the location of the text file	
		read(file);
	}
	
	//Chason
	public static void InitializeFiltered() throws IOException {
		
		FileReader file = new FileReader("C:\\Users\\Edene\\eclipse-workspace\\The tester\\src\\FilteredSchedule.txt"); //replace with the location of the text file	
		read(file);
	}
	
	//Connor
	public static void addTrain(String Orig, String Dest, String Depart, String Arriv) throws IOException {
		Initialize();
		Train t = new Train(trainConvert.size(), Orig, Dest, Depart, Arriv);
		trainConvert.add(t);
		write();
	}
	
	//Connor and Chason
	public static void addDelay(int ID, int delay) throws IOException {
		Initialize();
		Train t = trainConvert.get(ID);
		int departure = Integer.parseInt(t.getDeparture());
		int arrival = Integer.parseInt(t.getArrival());
		// This adds the delay to the departure and arrival time
		departure += delay;
		arrival += delay;
		
		// This changed the departure from an integer back into a string
		String delayedDeparture = militaryRollover(departure);
		String delayedArrival = militaryRollover(arrival);
		deleteTrain(ID);
		Train newT = new Train(ID, t.getOrigin(), t.getDestination(), delayedDeparture, delayedArrival);
		trainConvert.add(newT);
		write();
	}
	
	//Chason
	public static String militaryRollover(int time) {
		StringBuilder miliTime = new StringBuilder(); 
		if (time>2359) {
			time -= 2360;
		}
		miliTime.append(Integer.toString(time));
		while (miliTime.length()<4) {
			miliTime.insert(0,"0");
		}
		String m = miliTime.toString();
		String hours = m.substring(0, 2);
		String minutes = m.substring(2, 4);
		int min = Integer.parseInt(minutes);
		int hr = Integer.parseInt(hours);
		if(min>=60) {
			hr += 1;
			min -= 60;
		}
		if(hr>=24) {
			hr -= 24;
		}
		String hStr = String.valueOf(hr);
		String mStr = String.valueOf(min);
		String part1 = "";
		String part2 = "";
		if(hStr.length() == 1) {
			part1 = "0"+hStr;
		}else {
			part1 = hStr;
		}
		if(mStr.length() == 1) {
			part2 = "0"+mStr;
		}
		else {
			part2 = mStr;
		}
		String send = (part1+part2);
		
		return send;
	}
	
	//Connor and Chason
	public static void deleteTrain(int ID) throws IOException {
		Initialize();
		// This takes the value from the user and deletes each entry in that respective index
		trainConvert.removeElementAt(ID);
		write();
	}
	
	//Connor
	public static void read(FileReader file) throws IOException {
		trainConvert.clear();
		Scanner temp = new Scanner(file);
		//This tests the file, reading it until it reaches the end of the file
		int i = 0;
		while (temp.hasNext()) {
			//Here, we have the file being read, each data point being split by a -, and separating it into a string array
			String[] data = temp.nextLine().split("-");
			
			String Orig = data[0];
			String Dest = data[1];
			String Depart = data[2];
			String Arriv = data[3];
			
			Train t = new Train(i, Orig, Dest, Depart, Arriv);
			trainConvert.add(t);
			i++;
		}
	}
	
	//Connor
	public static void write() throws IOException {
		// This exports the new data points into the already existing TrainSchedule document
		FileWriter newSchedule = new FileWriter("C:\\Users\\Edene\\eclipse-workspace\\The tester\\src\\TrainSchedule.txt");
		
		for (int i = 0; i < trainConvert.size(); i++) {	
			Train t = trainConvert.get(i);
			newSchedule.write(t.getOrigin()+"-"+t.getDestination()+"-"+t.getDeparture()+"-"+t.getArrival()+System.lineSeparator());
		}
		newSchedule.close();
	}
	
	//Connor
	public static void writeFiltered(String filter, String variable) throws IOException {
		Initialize();
		FileWriter filteredSchedule = new FileWriter("C:\\Users\\Edene\\eclipse-workspace\\The tester\\src\\FilteredSchedule.txt");
		
		if(filter.equalsIgnoreCase("origin")) {
			for (int i = 0; i < trainConvert.size(); i++) {
				Train t = trainConvert.get(i);
				if (t.getOrigin().equals(variable)) {
					filteredSchedule.write(t.getOrigin()+"-"+t.getDestination()+"-"+t.getDeparture()+"-"+t.getArrival()+System.lineSeparator());
				}
			}
		}
		else if(filter.equalsIgnoreCase("destination")) {
			for (int i = 0; i < trainConvert.size(); i++) {
				Train t = trainConvert.get(i);
				if (t.getDestination().equals(variable)) {
					filteredSchedule.write(t.getOrigin()+"-"+t.getDestination()+"-"+t.getDeparture()+"-"+t.getArrival()+System.lineSeparator());
				}
			}
		}
		else if(filter.equalsIgnoreCase("departure")) {
			for (int i = 0; i < trainConvert.size(); i++) {
				Train t = trainConvert.get(i);
				if (t.getDeparture().equals(variable)) {
					filteredSchedule.write(t.getOrigin()+"-"+t.getDestination()+"-"+t.getDeparture()+"-"+t.getArrival()+System.lineSeparator());
				}
			}
		}
		else if (filter.equalsIgnoreCase("arrival")) {
			for (int i = 0; i < trainConvert.size(); i++) {
				Train t = trainConvert.get(i);
				if (t.getArrival().equals(variable)) {
					filteredSchedule.write(t.getOrigin()+"-"+t.getDestination()+"-"+t.getDeparture()+"-"+t.getArrival()+System.lineSeparator());
				}
			}
		}
		filteredSchedule.close();
	}
	
	//Connor
	public static void filterFiltered(String filter, String variable) throws IOException { 
		InitializeFiltered();
		Vector<Train> filteredVector = new Vector<Train>();
		
		if(filter.equalsIgnoreCase("origin")) {
			for (int i = 0; i < trainConvert.size(); i++) {
				Train t = trainConvert.get(i);
				if (t.getOrigin().equals(variable)) {
					filteredVector.add(trainConvert.get(i));
				}
			}
		}
		else if(filter.equalsIgnoreCase("destination")) {
			for (int i = 0; i < trainConvert.size(); i++) {
				Train t = trainConvert.get(i);
				if (t.getDestination().equals(variable)) {
					filteredVector.add(trainConvert.get(i));
				}
			}
		}
		else if(filter.equalsIgnoreCase("departure")) {
			for (int i = 0; i < trainConvert.size(); i++) {
				Train t = trainConvert.get(i);
				if (t.getDeparture().equals(variable)) {
					filteredVector.add(trainConvert.get(i));
				}
			}
		}
		else if (filter.equalsIgnoreCase("arrival")) {
			for (int i = 0; i < trainConvert.size(); i++) {
				Train t = trainConvert.get(i);
				if (t.getArrival().equals(variable)) {
					filteredVector.add(trainConvert.get(i));
				}
			}
		}
		
		FileWriter filteredSchedule = new FileWriter("C:\\Users\\Edene\\eclipse-workspace\\The tester\\src\\FilteredSchedule.txt");
		
		for (int i = 0; i < filteredVector.size(); i++) {
			Train t = trainConvert.get(i);
			filteredSchedule.write(t.getOrigin()+"-"+t.getDestination()+"-"+t.getDeparture()+"-"+t.getArrival()+System.lineSeparator());
		}
		filteredSchedule.close();
	}
	
	//Chason
	public static Vector<Train> send() throws IOException {
		Initialize();
		return trainConvert;
	}
	
	//Chason
	public static Vector<Train> sendFiltered() throws IOException {
		InitializeFiltered();
		return trainConvert;
	}
	
	//This shows that it is in there
	public static void printTrains() {
		for(int i = 0; i < trainConvert.size(); i++) {
			System.out.println(trainConvert.get(i).toString());
			System.out.println();
		}
	}
}
