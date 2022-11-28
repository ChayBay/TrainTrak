import java.io.*;
import java.util.Scanner;
import java.util.Vector;


public class TrainAdmin {
	
	public static Vector<Train> trainConvert = new Vector<Train>();

	public static void main(String[] args) throws IOException {
		FileReader file = new FileReader("C:\\Users\\Edene\\eclipse-workspace\\The tester\\src\\TrainSchedule.txt"); //replace with the location of the text file
		
		// This reads the file and separates them into each of the public ArrayLists		
		read(file);
		printTrains();
		//System.out.println();
		//System.out.println("Were going to add one here");
		//addTrain("New Port", "On A Porch", "0010", "0020");
		//addDelay(5, 30); THIS DOESN'T APPEND THE 00 TO THE FRONT OF MILITARY TIME
		//THIS IS JUST A MATH ISSUE CAN BE SOLVED EASILY
		//deleteTrain(5);
		//Working with train Object and train Vector
		//System.out.println();
		//printTrains();
		
		
	}
	
	public static void Initialize() throws IOException {
		
		FileReader file = new FileReader("C:\\Users\\Edene\\eclipse-workspace\\The tester\\src\\TrainSchedule.txt"); //replace with the location of the text file	
		read(file);
	}
	
	public static void InitializeFiltered() throws IOException {
		
		FileReader file = new FileReader("C:\\Users\\Edene\\eclipse-workspace\\The tester\\src\\FilteredSchedule.txt"); //replace with the location of the text file	
		read(file);
	}
	
	public static void addTrain(String Orig, String Dest, String Depart, String Arriv) throws IOException {
		Initialize();
		Train t = new Train(trainConvert.size(), Orig, Dest, Depart, Arriv);
		trainConvert.add(t);
		write();
	}
	
	public static void addDelay(int ID, int delay) throws IOException {
		Initialize();
		Train t = trainConvert.get(ID);
		int departure = Integer.parseInt(t.getDeparture());
		int arrival = Integer.parseInt(t.getArrival());
		// This adds the delay to the departure and arrival time
		departure = departure + delay;
		arrival = arrival + delay;
		
		// This changed the departure from an integer back into a string
		String delayedDeparture = Integer.toString(departure);
		String delayedArrival = Integer.toString(arrival);
		deleteTrain(ID);
		Train newT = new Train(ID, t.getOrigin(), t.getDestination(), delayedDeparture, delayedArrival);
		trainConvert.add(newT);
		write();
	}
	
	public static void deleteTrain(int ID) throws IOException {
		Initialize();
		// This takes the value from the user and deletes each entry in that respective index
		trainConvert.removeElementAt(ID);
		write();
	}
	
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
	
	public static void write() throws IOException {
		// This exports the new data points into the already existing TrainSchedule document
		FileWriter newSchedule = new FileWriter("C:\\Users\\Edene\\eclipse-workspace\\The tester\\src\\TrainSchedule.txt");
		
		for (int i = 0; i < trainConvert.size(); i++) {	
			Train t = trainConvert.get(i);
			newSchedule.write(t.getOrigin()+"-"+t.getDestination()+"-"+t.getDeparture()+"-"+t.getArrival()+System.lineSeparator());
			//newSchedule.write(Origin.get(i)+"-"+Destination.get(i)+"-"+DepartureTime.get(i)+"-"+ArrivalTime.get(i)+System.lineSeparator());
		}
		newSchedule.close();
	}
	
	public static void writeFiltered(String filter, String variable) throws IOException {
		FileWriter filteredSchedule = new FileWriter("C:\\Users\\Edene\\eclipse-workspace\\The tester\\src\\FilteredSchedule.txt");
		
		if (filter.equalsIgnoreCase("arrival")) {
			for (int i = 0; i < trainConvert.size(); i++) {
				Train t = trainConvert.get(i);
				if (t.Arrival == variable) {
					filteredSchedule.write(t.getOrigin()+"-"+t.getDestination()+"-"+t.getDeparture()+"-"+t.getArrival()+System.lineSeparator());
				}
			}
		}
		else if(filter.equalsIgnoreCase("departure")) {
			for (int i = 0; i < trainConvert.size(); i++) {
				Train t = trainConvert.get(i);
				if (t.Departure == variable) {
					filteredSchedule.write(t.getOrigin()+"-"+t.getDestination()+"-"+t.getDeparture()+"-"+t.getArrival()+System.lineSeparator());
				}
			}
		}
		else if(filter.equalsIgnoreCase("origin")) {
			for (int i = 0; i < trainConvert.size(); i++) {
				Train t = trainConvert.get(i);
				if (t.Origin == variable) {
					filteredSchedule.write(t.getOrigin()+"-"+t.getDestination()+"-"+t.getDeparture()+"-"+t.getArrival()+System.lineSeparator());
				}
			}
		}
		else if(filter.equalsIgnoreCase("destination")) {
			for (int i = 0; i < trainConvert.size(); i++) {
				Train t = trainConvert.get(i);
				if (t.Destination == variable) {
					filteredSchedule.write(t.getOrigin()+"-"+t.getDestination()+"-"+t.getDeparture()+"-"+t.getArrival()+System.lineSeparator());
				}
			}
		}
		filteredSchedule.close();
	}
	
	public static void filterFiltered(String filter, String variable) throws IOException { 
		InitializeFiltered();
		Vector<Train> t = new Vector<Train>();
		
		if (filter.equalsIgnoreCase("arrival")) {
			for (int i = 0; i < trainConvert.size(); i++) {
				if (trainConvert.get(i).Arrival == variable) {
					t.add(trainConvert.get(i));
				}
			}
		}
		else if(filter.equalsIgnoreCase("departure")) {
			for (int i = 0; i < trainConvert.size(); i++) {
				if (trainConvert.get(i).Departure == variable) {
					t.add(trainConvert.get(i));
				}
			}
		}
		else if(filter.equalsIgnoreCase("origin")) {
			for (int i = 0; i < trainConvert.size(); i++) {
				if (trainConvert.get(i).Origin == variable) {
					t.add(trainConvert.get(i));
				}
			}
		}
		else if(filter.equalsIgnoreCase("destination")) {
			for (int i = 0; i < trainConvert.size(); i++) {
				if (trainConvert.get(i).Destination == variable) {
					t.add(trainConvert.get(i));
				}
			}
		}
		FileWriter filteredSchedule = new FileWriter("C:\\Users\\Edene\\eclipse-workspace\\The tester\\src\\FilteredSchedule.txt");
		
		for (int i = 0; i < trainConvert.size(); i++) {	
			filteredSchedule.write(t.get(i).Origin+"-"+t.get(i).Destination+"-"+t.get(i).Departure+"-"+t.get(i).Arrival+System.lineSeparator());
		}
		filteredSchedule.close();
	}

	public static Vector<Train> send() throws IOException {
		Initialize();
		return trainConvert;
	}
	
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