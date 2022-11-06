// Made by Connor Shipman
// Much of this code can be refined to work better or look cleaner.  This is a basic draft and outline of the adding routes and delays.

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;

public class TrainAdmin {
	
	public static ArrayList<String> Origin = new ArrayList<String>();
	public static ArrayList<String> Destination = new ArrayList<String>();
	public static ArrayList<String> DepartureTime = new ArrayList<String>();
	public static ArrayList<String> ArrivalTime = new ArrayList<String>();

	public static void main(String[] args) throws Exception
	{
		FileReader file = new FileReader("C:\\Users\\Public\\Documents\\TrainSchedule.txt"); //replace with the location of the text file
		
		
		// This reads the file and separates them into each of the public ArrayLists		
		read(file);
		
		// This prints out all the Train Schedules one entry at a time
		for(int i = 0; i < Origin.size(); i++)
		{
			System.out.println(Origin.get(i)+"-"+Destination.get(i)+"-"+DepartureTime.get(i)+"-"+ArrivalTime.get(i));
		}
		
		// These are here to test the functions below
		addRoute();
		addDelay();
		deleteRoute();
		
		// This exports the new data points into the already existing TrainSchedule document
		FileWriter newSchedule = new FileWriter("C:\\Users\\Public\\Documents\\TrainSchedule.txt");
		for (int i = 0; i < Origin.size(); i++)
		{
			newSchedule.write(Origin.get(i)+"-"+Destination.get(i)+"-"+DepartureTime.get(i)+"-"+ArrivalTime.get(i)+System.lineSeparator());
		}
		newSchedule.close();
	}
	
	public static void read(FileReader file) throws IOException
	{
		Scanner temp = new Scanner(file);
		
		//This tests the file, reading it until it reaches the end of the file
		while (temp.hasNext()) {
			//Here, we have the file being read, each data point being split by a -, and separating it into a string array
			String[] data = temp.nextLine().split("-");
			// These add each data point into their respective ArrayList, again all being in order and separated by a -
			Origin.add(data[0]);
			Destination.add(data[1]);
			DepartureTime.add(data[2]);
			ArrivalTime.add(data[3]);
		}
	}
	
	public static void addRoute() {
		Scanner temp = new Scanner(System.in);
		
		// This simply asks for all the details of the new route, prompting the user to enter in new information for the route
		System.out.print("Where is the Origin of your route? ");
		String origin = temp.nextLine();
		
		System.out.print("What is the Destination of your route? ");
		String destination = temp.nextLine();
		
		System.out.print("When is the Departure Time? ");
		String departure = temp.nextLine();
		
		System.out.print("When is the expected Arrival Time? ");
		String arrival = temp.nextLine();
		
		// After all the information is gathered, add all the information to the public ArrayLists.
		Origin.add(origin);
		Destination.add(destination);
		DepartureTime.add(departure);
		ArrivalTime.add(arrival);
		
	}
	
	public static void addDelay() {
		Scanner temp = new Scanner(System.in);
		
		// This will prompt the user to give the system a number, that number will be matched with the index in the departure time and arrival time
		System.out.println("Which route would you like to add a delay to? ");
		int route = temp.nextInt();
		
		// This will prompt the user to give the estimated delay
		System.out.println("How long will the delay be? ");
		int delay = temp.nextInt();
		
		// This is used to get the departure and arrival time of the route the admin requested, changing them from a string to an integer in the process
		int departure = Integer.parseInt(DepartureTime.get(route));
		int arrival = Integer.parseInt(ArrivalTime.get(route));
		
		// This adds the delay to the departure and arrival time
		departure = departure + delay;
		arrival = arrival + delay;
		
		// This changed the departure from an integer back into a string
		String delayedDeparture = Integer.toString(departure);
		String delayedArrival = Integer.toString(arrival);
		
		// This sets the route from the old departure and arrival time to the new delayed departure and arrival time
		DepartureTime.set(route, delayedDeparture);
		ArrivalTime.set(route, delayedArrival);
		
	}
	
	public static void deleteRoute() {
		Scanner temp = new Scanner(System.in);
		
		// This prompts the user to give a route to delete
		System.out.println("Which route would you like to delete? ");
		int route = temp.nextInt();
		
		// This takes the value from the user and deletes each entry in that respective index
		Origin.remove(route);
		Destination.remove(route);
		DepartureTime.remove(route);
		ArrivalTime.remove(route);
		
		// This confirms the deletion
		System.out.println("Deletion Complete");
	}
}
