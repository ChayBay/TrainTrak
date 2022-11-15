// Java program to demonstrate GridBagLayout class.
// if anyone looks at this, this is the original template to test gui sorry!!
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
 
public class TrainTrak {
	
	public static void main(String[] args) throws IOException{
        new mainMenu();
    }
}

class mainMenu extends JFrame{
	public static mainMenu menu;
	
	public static void main() throws IOException {
		mainMenu menu = new mainMenu();
		adminPopup.menuClose = menu;
	}
	
	public mainMenu() throws IOException {
		
		int padX = 100;
		int padY = 100;
		
		setTitle("Main Menu");
		 // set the layout
	    setLayout(new GridBagLayout());
	    // creates a constraints object
	    GridBagConstraints c = new GridBagConstraints();
	    // insets for all components
	    c.insets = new Insets(20,20,20,20);
	    //stays constant for all main panel items
	    c.fill = GridBagConstraints.HORIZONTAL;
	    //
	    
	    c.gridx = 0; // column
	    c.gridy = 0; // row
	    c.ipady = padY;
	    c.anchor = GridBagConstraints.CENTER;
	    JLabel TrainTrak = new JLabel("Train Trak");
	    TrainTrak.setFont(new Font("Comic Sans MS", Font.BOLD,30));
	    TrainTrak.setHorizontalAlignment(JLabel.CENTER);
	    add(TrainTrak, c);
			
	    c.gridx = 0;
	    c.gridy = 1;
	    c.ipady = 20;
	    c.anchor = GridBagConstraints.CENTER;
	    //This path may change depening on where you have the image in your desktop
	    //I will add the image with the push
	    String path = "C:\\Users\\Edene\\Desktop\\540\\project things\\trainTrakPull\\tommy.jfif";
	    File picFile = new File(path);
	    BufferedImage trainPic = ImageIO.read(picFile);
	    /*
	    dont know how to resize images yet
	    am using smaller picture currently
	    BufferedImage trainResize = Scalr.resize(trainPic, 200);
	    JLabel trainLabel = new JLabel(new ImageIcon(trainResize));
	    */
	    JLabel trainLabel = new JLabel(new ImageIcon(trainPic));
	    add(trainLabel, c);
	
	    c.gridx = 0;
	    c.gridy = 2;
	    c.anchor = GridBagConstraints.NORTH;
	    c.weightx = 2;
	    c.weighty = 2;
	    JPanel MenuButtons = new JPanel();
	    MenuButtons.setLayout(new GridBagLayout());
	    GridBagConstraints mbc = new GridBagConstraints();
	    mbc.insets = new Insets(20,20,20,20);
	    
	    //these are constant for both buttons
	    mbc.weightx = 2;
	    mbc.ipady = 20;
	    mbc.fill = GridBagConstraints.BOTH;
	    mbc.anchor = GridBagConstraints.CENTER;
	    
	    mbc.gridx = 0;
	    mbc.gridy = 0;
	    JButton user = new JButton("USER");
	    user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new userPage();
				menu.dispose();
			}
	    });
	    MenuButtons.add(user, mbc);
	    
	    mbc.gridx = 1;
	    mbc.gridy = 0;
	    JButton admin = new JButton("ADMIN");
	    admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new adminPopup(menu);
			}
	    });
	    MenuButtons.add(admin, mbc);
	    add(MenuButtons, c);
	    getContentPane();
	    setSize(700, 700);
	    setVisible(true);
	}
}

class adminPopup extends JFrame{
	
	public static adminPopup log;
	public static mainMenu menuClose;
	
	public static void main() {
		adminPopup log = new adminPopup(menuClose);
	}

	public adminPopup(JFrame menuClose) {
	    
		setTitle("Login");
		setLayout(new GridBagLayout());
		GridBagConstraints out = new GridBagConstraints();
		out.insets = new Insets(20,20,20,20);
		out.anchor = GridBagConstraints.CENTER;
		out.fill = GridBagConstraints.BOTH;
		
		JPanel topAd = new JPanel();
		topAd.setLayout(new GridBagLayout());
		GridBagConstraints inner1 = new GridBagConstraints();
		inner1.insets = new Insets(20,20,20,20);
		inner1.fill = GridBagConstraints.BOTH;
		
		inner1.gridx = 0;
		inner1.gridy = 0;
		topAd.add(new JLabel("Username"), inner1);
		
		inner1.gridx = 1;
		inner1.gridy = 0;
		JTextField userField = new JTextField(20);
		userField.setColumns(20);
		topAd.add(userField, inner1);
		
		inner1.gridx = 0;
		inner1.gridy = 1;
		topAd.add(new JLabel("Password"), inner1);
		
		inner1.gridx = 1;
		inner1.gridy = 1;
		JPasswordField passField = new JPasswordField(20);
		passField.setColumns(20);
		topAd.add(passField, inner1);
		
		out.gridx = 0;
		out.gridy = 0;
		add(topAd, out);
		
		JPanel subHolder = new JPanel();
		subHolder.setLayout(new GridBagLayout());
		GridBagConstraints s = new GridBagConstraints();
		s.insets = new Insets(20,20,20,20);
		s.gridx = 0;
		s.gridy = 0;
		s.fill = GridBagConstraints.BOTH;
		s.anchor = GridBagConstraints.CENTER;
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if (User.login==true) do stuff
				
				new adminPage();
				//these dont wORKKKKKKKKKKKKKKKKKKKKKKKK
				menuClose.dispose();
				log.dispose();
			}
		});
		subHolder.add(submit,s);
		
		out.gridx = 0;
		out.gridy = 1;
		add(subHolder, out);
		
	    getContentPane();
	    setSize(420, 305);
	    setVisible(true);	
	}
}

class adminPage extends JFrame{
	static JFrame adPage;
	 
	public static void main() throws IOException {
	    	adPage = new JFrame("Admin Page");
	}
	 
	public adminPage() {
		setLayout(new GridBagLayout());
	    GridBagConstraints ad = new GridBagConstraints();
	    ad.insets = new Insets(20,20,20,20);
	    
	    
	    ad.gridx = 0;
	    ad.gridy = 0;
	    ad.anchor = GridBagConstraints.NORTH;
	    ad.fill = GridBagConstraints.HORIZONTAL;
	    JPanel topButtons = new JPanel();
	    topButtons.setLayout(new GridBagLayout());
	    GridBagConstraints tb = new GridBagConstraints();
	    tb.insets = new Insets(20,20,20,20);
	    
	    //these are constant for both buttons
	    tb.weightx = 2;
	    tb.ipady = 20;
	    tb.fill = GridBagConstraints.HORIZONTAL;
	    tb.anchor = GridBagConstraints.NORTH;
	    
	    tb.gridx = 0;
	    tb.gridy = 0;
	    JButton home =  new JButton("Home");
	    topButtons.add(home, tb);
	    
	    tb.gridx = 1;
	    tb.gridy = 0;
	    JButton addR =  new JButton("Add Route");
	    topButtons.add(addR, tb);
	    add(topButtons, ad);
	    
	    //make a call to get the train data and train obj array
	    int[] trainSched = new int[] {0,1,2,3,4,5,6,7,8,9};
	    
	    ad.gridx = 0;
	    ad.gridy = 1;
	    ad.anchor = GridBagConstraints.NORTH;
	    ad.fill = GridBagConstraints.BOTH;
	    
	    
	    JPanel routes =  new JPanel();
	    routes.setLayout(new GridBagLayout());
	    GridBagConstraints loop = new GridBagConstraints();
	    loop.insets = new Insets(20,20,20,20);
	    loop.gridx = 0;
	    loop.gridy = 0;
	    for (int i = 0; i<trainSched.length; i++) {
	    	JPanel train = new JPanel();
	    	train.setLayout(new FlowLayout());
	    	train.add(new JLabel("train"+i));
	    	train.add(new JLabel("destination"));
	    	train.add(new JLabel("origin"));
	    	train.add(new JButton("Delete"));
	    	train.add(new JButton("Delay"));
	    	if (loop.gridy >= (trainSched.length)/2) {
	    		System.out.println("HERE");
	    		loop.gridx += 1;
	    		loop.gridy = 0;
	    	}
	    	//biigg issue
	    	loop.anchor = GridBagConstraints.CENTER;
		    loop.fill = GridBagConstraints.HORIZONTAL;
	    	routes.add(train,loop);
	    	loop.gridy += 1;
	    }
	    
	    add(routes, ad);
	    
	    getContentPane();
	    setSize(700, 700);
	    setVisible(true);
	}
}

class userPage extends JFrame{
	static JFrame usePage;
	 
	public static void main() throws IOException {
	    	usePage = new JFrame("User Page");
	}
	 
	public userPage() {
		setLayout(new GridBagLayout());
	    GridBagConstraints us = new GridBagConstraints();
	    us.insets = new Insets(20,20,20,20);
	    
	    
	    us.gridx = 0;
	    us.gridy = 0;
	    us.anchor = GridBagConstraints.NORTH;
	    us.fill = GridBagConstraints.HORIZONTAL;
	    JPanel topButtons = new JPanel();
	    topButtons.setLayout(new GridBagLayout());
	    GridBagConstraints tb = new GridBagConstraints();
	    tb.insets = new Insets(20,20,20,20);
	    
	    //these are constant for both buttons
	    //tb.weightx = 2;
	    tb.ipady = 20;
	    tb.fill = GridBagConstraints.HORIZONTAL;
	    tb.anchor = GridBagConstraints.NORTH;
	    
	    tb.gridx = 0;
	    tb.gridy = 0;
	    JButton home =  new JButton("Home");
	    topButtons.add(home, tb);
	    
	    tb.gridx = 1;
	    tb.gridy = 0;
	    JButton orig =  new JButton("Origin Filter");
	    topButtons.add(orig, tb);
	    add(topButtons, us);
	    
	    tb.gridx = 2;
	    tb.gridy = 0;
	    JButton dest =  new JButton("Destination Filter");
	    topButtons.add(dest, tb);
	    add(topButtons, us);
	    
	    tb.gridx = 3;
	    tb.gridy = 0;
	    JButton depart =  new JButton("Departure Filter");
	    topButtons.add(depart, tb);
	    add(topButtons, us);
	    
	    tb.gridx = 4;
	    tb.gridy = 0;
	    JButton arriv =  new JButton("Arrival Filter");
	    topButtons.add(arriv, tb);
	    add(topButtons, us);
	    
	    //make a call to get the train data and train obj array
	    int[] trainSched = new int[] {0,1,2,3,4,5,6,7,8,9};
	    
	    us.gridx = 0;
	    us.gridy = 1;
	    us.anchor = GridBagConstraints.NORTH;
	    us.fill = GridBagConstraints.BOTH;
	    
	    
	    JPanel routes =  new JPanel();
	    routes.setLayout(new GridBagLayout());
	    GridBagConstraints loop = new GridBagConstraints();
	    loop.insets = new Insets(20,20,20,20);
	    loop.gridx = 0;
	    loop.gridy = 0;
	    for (int i = 0; i<trainSched.length; i++) {
	    	JPanel train = new JPanel();
	    	train.setLayout(new FlowLayout());
	    	train.add(new JLabel("train"+i));
	    	train.add(new JLabel("destination"));
	    	train.add(new JLabel("origin"));
	    	train.add(new JButton("Delete"));
	    	train.add(new JButton("Delay"));
	    	if (loop.gridy >= (trainSched.length)/2) {
	    		System.out.println("HERE");
	    		loop.gridx += 1;
	    		loop.gridy = 0;
	    	}
	    	//biigg issue
	    	loop.anchor = GridBagConstraints.CENTER;
		    loop.fill = GridBagConstraints.HORIZONTAL;
	    	routes.add(train,loop);
	    	loop.gridy += 1;
	    }
	    
	    add(routes, us);
	    
	    getContentPane();
	    setSize(750, 750);
	    setVisible(true);
	}
}