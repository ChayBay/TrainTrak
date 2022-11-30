// Made By Calloway Hodges

// edited by Chason Bibeau to implement an Admin Object and Vector Implementation
import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class AdminLogger {	
	public static Vector<Admin> adminConvert = new Vector<Admin>();
	
	public static void Initialize() throws IOException {
		
		FileReader file = new FileReader("C:\\Users\\Edene\\eclipse-workspace\\The tester\\src\\AdminList.txt"); //replace with the location of the text file	
		read(file);
	}
	
	public static boolean check(String userF, String passF) throws IOException {
		Initialize();
		boolean isIn = false;
		for (int i = 0; i < adminConvert.size(); i++) {	
			Admin a = adminConvert.get(i);
			if (a.getUsername().equals(userF)) {
				if (a.getPassword().equals(passF)) {
					isIn = true;
				}
			}	
		}
		return isIn;
	}
	
	public static void addAdmin(String user, String pass) throws IOException {
		Admin a = new Admin(user, pass);
		adminConvert.add(a);
		write();
	}
	
	public static void read(FileReader file) throws IOException {
		Scanner temp = new Scanner(file);
		//This tests the file, reading it until it reaches the end of the file
		int i = 0;
		while (temp.hasNext()) {
			//Here, we have the file being read, each data point being split by a -, and separating it into a string array
			String[] data = temp.nextLine().split("-");
			
			String user = data[0];
			String pass = data[1];
			
			Admin a = new Admin(user, pass);
			adminConvert.add(a);
			i++;
		}
	}
	
	public static void write() throws IOException {
		FileWriter newChadmin = new FileWriter("C:\\Users\\Edene\\eclipse-workspace\\The tester\\src\\AdminList.txt");
		
		for (int i = 0; i < adminConvert.size(); i++) {	
			Admin a = adminConvert.get(i);
			newChadmin.write(a.getUsername()+"-"+a.getPassword()+System.lineSeparator());
		}
		newChadmin.close();
	}

	public static Vector<Admin> send(Vector<Admin> trainConvert) {
		return adminConvert;
	}
	
	//This shows that it is in there
	public static void printAdmins() {
		for(int i = 0; i < adminConvert.size(); i++) {
			System.out.println(adminConvert.get(i).toString());
			System.out.println();
		}
	}

}
