import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.FileNotFoundException;

public class User {

    public static void main(String[] args) throws FileNotFoundException {

        //Create file to store admin info
        String fileName = "admin_info.txt";
        File adminFile = new File(fileName);

        try{
            adminFile.createNewFile();
        }catch(IOException e){

        }


        //Create list to store user info from file for verification
        List<String> userData = new ArrayList<String>();

        //Read file into list
        Scanner scan0 = new Scanner(new FileReader(fileName));
        while (scan0.hasNext()) {
            String next = scan0.next();
            userData.add(next);
        }
        System.out.println(userData.toString());


        //Prompt user to create new account, if answer is no
        //initiate login to existing account
        Scanner scan1 = new Scanner(System.in);
        System.out.println("Create new account(y/n)?");
        String newAcctPrompt = scan1.nextLine();
        System.out.println("answer: " + newAcctPrompt);
        if (newAcctPrompt.equalsIgnoreCase("y")) {
            System.out.println("Enter location: ");
            String location = scan1.nextLine();
            System.out.println("Enter new username: ");
            String newUser = scan1.nextLine();
            System.out.println("Enter new password: ");
            String newPassword = scan1.nextLine();
            New_Login(adminFile, userData, newUser, newPassword, location);
        }else if (newAcctPrompt.equalsIgnoreCase("n")) {
            System.out.println("Enter username: ");
            String userName = scan1.nextLine();
            System.out.println("Enter password: ");
            String password = scan1.nextLine();
            Login(userData, userName, password);
        }else {
            System.out.println("Please enter y for yes or n for no");
        }


    }

    //This method will create a new admin account. Pass username and password
    //then verify there is no existing account with the same name.
    static void New_Login(File adminFile, List userData, String newUser, String newPassword, String location) {

        //Loop through the list and check for a match on existing username
        //If no match, write to file. If this is the first entry, write to file.
        for (int i = 0; i+3 <= userData.size(); i += 3) {
            //No data is present so write to file.
            if(userData.isEmpty()){
                try {
                    FileWriter myWriter = new FileWriter(adminFile);
                    myWriter.write("\n" + newUser + " " + newPassword + " " + location);
                    myWriter.close();
                    System.out.println("User " + newUser + " added to the system. Welcome to Megatrain " + location);
                } catch (IOException f) {
                    System.out.println("File not found");
                    f.printStackTrace();
                }
            }
            else if (newUser.equalsIgnoreCase(userData.get(i).toString())) {
                System.out.println("Username already exists.");
            } else {
                try {
                    FileWriter myWriter = new FileWriter(adminFile);
                    myWriter.write("\n" + newUser + " " + newPassword + " " + location);
                    System.out.println("User " + newUser + " added to the system. Welcome to Megatrain " + location);
                } catch (IOException f) {
                    System.out.println("File not found");
                    f.printStackTrace();
                }
            }
        }


    }

    //This method will log in an admin. Pass username and password
    //then check the list for their respective matches.
    static void Login(List userData, String userName, String password) {

        //Loop through list and check for a matching username, if there is a match, check for matching password.
        for (int i = 0; i+3 <= userData.size(); i += 3) {
            if (userName.equalsIgnoreCase(userData.get(i).toString())) {
                //Username match.
                for (int j = 1; j <= userData.size(); j += 3) {
                    //Password match.
                    if (password.equalsIgnoreCase(userData.get(j).toString())) {
                        System.out.println("Login success");
                    } //Password Fail
                    else {
                        System.out.println("Incorrect Password");
                    }
                }//Username fail
            } else {
                System.out.println("Username not found");
            }
        }
    }
}
