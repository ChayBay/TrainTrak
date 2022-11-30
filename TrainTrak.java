// GUI creation and implementation By Chason Bibeau
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Vector;
 
public class TrainTrak {
	
	public static mainMenu menu;
	public static adminPage adPage;
	public static userPage usePage;
	public static adminPopup log;
	public static addPopup addMin;
	public static routePopup route;
	public static delayPopup delay;
	public static origPopup origFilter;
	public static destPopup destFilter;
	public static departPopup departFilter;
	public static arivPopup arivFilter;
	public static boolean filtered = false;
	static Vector<Train> trainSched = new Vector<Train>();
	
	public static void main(String[] args) throws IOException{
        menu = new mainMenu();
    }
	
	//Chason
	public static boolean validator(String str, String pat) {
		Pattern pattern = Pattern.compile(pat, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}

	//Connor
	public static Vector<String> buttonGen(String filter) {
		Vector<String> theButtons = new Vector<String>();
		
		if(filter.equalsIgnoreCase("origin")) {
			for (int i = 0; i < TrainTrak.trainSched.size(); i++) {
				Train t = TrainTrak.trainSched.get(i);
				if(theButtons.size()<1) {
					theButtons.add(t.getOrigin());
				}
				else if (loopButton(t.getOrigin(), theButtons)) {
					theButtons.add(t.getOrigin());
				}
			}
		}
		else if(filter.equalsIgnoreCase("destination")) {
			for (int i = 0; i < TrainTrak.trainSched.size(); i++) {
				Train t = TrainTrak.trainSched.get(i);
				if(theButtons.size()<1) {
					theButtons.add(t.getDestination());
				}
				else if (loopButton(t.getDestination(), theButtons)) {
					theButtons.add(t.getDestination());
				}
			}
		}
		else if(filter.equalsIgnoreCase("departure")) {
			for (int i = 0; i < TrainTrak.trainSched.size(); i++) {
				Train t = TrainTrak.trainSched.get(i);
				if(theButtons.size()<1) {
					theButtons.add(t.getDeparture());
				}
				else if (loopButton(t.getDeparture(), theButtons)) {
					theButtons.add(t.getDeparture());
				}
			}
		}
		else if (filter.equalsIgnoreCase("arrival")) {
			for (int i = 0; i < TrainTrak.trainSched.size(); i++) {
				Train t = TrainTrak.trainSched.get(i);
				if(theButtons.size()<1) {
					theButtons.add(t.getArrival());
				}
				else if (loopButton(t.getArrival(), theButtons)) {
					theButtons.add(t.getArrival());
				}
			}
		}
		return theButtons;
	}
	
	//Chason
	public static boolean loopButton(String value, Vector<String> strVec) {
		boolean notIn = true;
		for(int i = 0; i < strVec.size(); i++) {
			if(value.equals(strVec.get(i))) {
				notIn = false;
			}
		}
		return notIn;
	}
	
	//Calloway
	public static String convertTime(String str) {
		String hours, minutes;
	    // Get Hours
	    int h1 = (int)str.charAt(0) - '0';
        int h2 = (int)str.charAt(1)- '0';
        minutes = str.substring(2, 4); // this needed 2,4 not 3,5
        int hh = h1 * 10 + h2;
        
        // Finding out the Meridiem of time (AM/PM)
        String meridiem;
        if (hh < 12) {
            meridiem = "AM";
        }
        else {
            meridiem = "PM";
        }
        hh %= 12;
        
        //If 0 hours = 12
        if (hh == 0) {
            hours = "12";
        }
        else {
            hours = String.valueOf(hh);
        }              
        String convertedTime = (hours + ":" + minutes + meridiem);
        return convertedTime;
    }

	static String textPat= "^[0-9,A-Z,a-z]{0,20}$";
	static String numPat = "[0-9]{4}";
	
}


class mainMenu extends JFrame{
	
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
	    JLabel TrainTrek = new JLabel("Train Trak");
	    TrainTrek.setFont(new Font("Comic Sans MS", Font.BOLD,30));
	    TrainTrek.setHorizontalAlignment(JLabel.CENTER);
	    add(TrainTrek, c);
			
	    c.gridx = 0;
	    c.gridy = 1;
	    c.ipady = 20;
	    c.anchor = GridBagConstraints.CENTER;
	    String path = "C:\\Users\\Edene\\Desktop\\540\\project things\\trainTrakPull\\tommy.jfif";
	    File picFile = new File(path);
	    BufferedImage trainPic = ImageIO.read(picFile);
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
				try {
					TrainTrak.usePage = new userPage();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				TrainTrak.menu.dispose();
			}
	    });
	    MenuButtons.add(user, mbc);
	    
	    mbc.gridx = 1;
	    mbc.gridy = 0;
	    JButton admin = new JButton("ADMIN");
	    
	    admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainTrak.log = new adminPopup();
			}
	    });
	    MenuButtons.add(admin, mbc);
	    add(MenuButtons, c);
	    getContentPane();
	    setSize(700, 700);
	    setVisible(true);
	    revalidate();
	    repaint();
	}
}

class adminPage extends JFrame{
	 
	public adminPage() throws IOException {
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
	    JButton home = new JButton("Home");
	    home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TrainTrak.menu = new mainMenu();
					TrainTrak.adPage.dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	    topButtons.add(home, tb);
	    
	    tb.gridx = 1;
	    tb.gridy = 0;
	    JButton addR = new JButton("Add Route");
	    addR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainTrak.route = new routePopup();
			}
	    });
	    topButtons.add(addR, tb);
	   
	    tb.gridx = 2;
	    tb.gridy = 0;
	    JButton addA = new JButton("Add Admin");
	    addA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainTrak.addMin = new addPopup();
			}
	    });
	    topButtons.add(addA, tb);
	    add(topButtons, ad);
	    
	    ad.gridx = 0;
	    ad.gridy = 1;
	    ad.anchor = GridBagConstraints.NORTH;
	    ad.fill = GridBagConstraints.BOTH;
	    
	    //make a call to get the train data and train obj array
	    TrainTrak.trainSched = TrainAdmin.send();
	    JPanel routes =  new JPanel();
	    routes.setLayout(new GridBagLayout());
	    GridBagConstraints loop = new GridBagConstraints();
	    loop.insets = new Insets(10,20,10,20);
	    loop.gridx = 0;
	    loop.gridy = 0;
	    for (int i = 0; i<TrainTrak.trainSched.size(); i++) {
	    	JPanel train = new JPanel();
	    	Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			train.setBorder(border);
	    	Train t = TrainTrak.trainSched.get(i);
	    	train.setLayout(new GridBagLayout());
	    	GridBagConstraints tSet = new GridBagConstraints();
	    	tSet.insets = new Insets(10,10,10,10);
	    	tSet.gridx = 0;
	    	tSet.gridy = 0;
	    	tSet.anchor = GridBagConstraints.CENTER;
	    	
	    	JLabel o = new JLabel(t.getOrigin());
	    	o.setHorizontalAlignment(JLabel.CENTER);
	    	train.add(o,tSet);
	    	
	    	tSet.gridx = 1;
	    	JLabel des = new JLabel(t.getDestination());
	    	des.setHorizontalAlignment(JLabel.CENTER);
	    	train.add(des,tSet);
	    	
	    	tSet.gridx = 2;
	    	JButton del = new JButton("Delete");
	    	del.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						TrainAdmin.deleteTrain(t.getID());
						TrainTrak.adPage.dispose();
						TrainTrak.adPage = new adminPage();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
		    });
	    	train.add(del,tSet);
	    	
	    	tSet.gridx = 0;
	    	tSet.gridy = 1;
	    	String deptTime = TrainTrak.convertTime(t.getDeparture());
	    	JLabel dep = new JLabel(deptTime);
	    	dep.setHorizontalAlignment(JLabel.CENTER);
	    	train.add(dep,tSet);
	    	
	    	tSet.gridx = 1;
	    	String arivTime = TrainTrak.convertTime(t.getArrival());
	    	JLabel ari = new JLabel(arivTime);
	    	ari.setHorizontalAlignment(JLabel.CENTER);
	    	train.add(ari,tSet);
	    	
	    	tSet.gridx = 2;
	    	JButton delay = new JButton("Delay");
	    	delay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TrainTrak.delay = new delayPopup(t.getID());
				}
		    });
	    	train.add(delay,tSet);
	    	if (loop.gridy > (TrainTrak.trainSched.size())/3) {
	    		loop.gridx += 1;
	    		loop.gridy = 0;
	    	}
	    	loop.anchor = GridBagConstraints.CENTER;
		    loop.fill = GridBagConstraints.HORIZONTAL;
	    	routes.add(train,loop);
	    	loop.gridy += 1;
	    }
	    add(routes, ad);
	    
	    getContentPane();
	    setSize(900, 900);
	    setVisible(true);
	    revalidate();
	    repaint();
	}
}

class userPage extends JFrame{
	 
	public userPage() throws IOException {
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
	    JButton home = new JButton("Home");
	    home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TrainTrak.menu = new mainMenu();
					TrainTrak.usePage.dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	    topButtons.add(home, tb);
	    
	    tb.gridx = 1;
	    tb.gridy = 0;
	    JButton orig = new JButton("Origin Filter");
	    orig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainTrak.origFilter = new origPopup();
			}
	    });
	    topButtons.add(orig, tb);
	    add(topButtons, us);
	    
	    tb.gridx = 2;
	    tb.gridy = 0;
	    JButton dest = new JButton("Destination Filter");
	    dest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainTrak.destFilter = new destPopup();
			}
	    });
	    topButtons.add(dest, tb);
	    add(topButtons, us);
	    
	    tb.gridx = 3;
	    tb.gridy = 0;
	    JButton depart = new JButton("Departure Filter");
	    depart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainTrak.departFilter = new departPopup();
			}
	    });
	    topButtons.add(depart, tb);
	    add(topButtons, us);
	    
	    tb.gridx = 4;
	    tb.gridy = 0;
	    JButton arriv = new JButton("Arrival Filter");
	    arriv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrainTrak.arivFilter = new arivPopup();
			}
	    });
	    topButtons.add(arriv, tb);
	    add(topButtons, us);
	    
	    tb.gridx = 5;
	    tb.gridy = 0;
	    JButton clear = new JButton("Clear Filter");
	    clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TrainTrak.filtered = false;
					TrainTrak.usePage.dispose();
					TrainTrak.usePage = new userPage();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
	    });
	    topButtons.add(clear, tb);
	    add(topButtons, us);
	    
	    us.gridx = 0;
	    us.gridy = 1;
	    us.anchor = GridBagConstraints.NORTH;
	    us.fill = GridBagConstraints.BOTH;
	    
	    //make a call to get the train data and train obj array
	    if(TrainTrak.filtered) {
	    	TrainTrak.trainSched = TrainAdmin.sendFiltered();
	    }else {
	    	TrainTrak.trainSched = TrainAdmin.send();
	    }
	    JPanel routes =  new JPanel();
	    if (TrainTrak.trainSched.size()<1) {
	    	
	    	 routes.setLayout(new FlowLayout());
	    	 JLabel noTrains = new JLabel("No Trains Matched The Filter!");
	    	 add(routes,us);
	    	 
	    }else {
	    	
	    	routes.setLayout(new GridBagLayout());
		    GridBagConstraints loop = new GridBagConstraints();
		    loop.insets = new Insets(20,20,20,20);
		    loop.gridx = 0;
		    loop.gridy = 0;
		    for (int i = 0; i<TrainTrak.trainSched.size(); i++) {
		    	JPanel train = new JPanel();
		    	Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
				train.setBorder(border);
		    	Train t = TrainTrak.trainSched.get(i);
		    	train.setLayout(new GridBagLayout());
		    	GridBagConstraints tSet = new GridBagConstraints();
		    	tSet.insets = new Insets(10,10,10,10);
		    	tSet.gridx = 0;
		    	tSet.gridy = 0;
		    	tSet.anchor = GridBagConstraints.CENTER;
		    	JLabel o = new JLabel(t.getOrigin());
		    	o.setHorizontalAlignment(JLabel.CENTER);
		    	train.add(o,tSet);
		    	
		    	tSet.gridx = 1;
		    	JLabel des = new JLabel(t.getDestination());
		    	des.setHorizontalAlignment(JLabel.CENTER);
		    	train.add(des,tSet);
		    	
		    	tSet.gridx = 0;
		    	tSet.gridy = 1;
		    	String deptTime = TrainTrak.convertTime(t.getDeparture());
		    	JLabel dep = new JLabel(deptTime);
		    	dep.setHorizontalAlignment(JLabel.CENTER);
		    	train.add(dep,tSet);
		    	
		    	tSet.gridx = 1;
		    	String arivTime = TrainTrak.convertTime(t.getArrival());
		    	JLabel ari = new JLabel(arivTime);
		    	ari.setHorizontalAlignment(JLabel.CENTER);
		    	train.add(ari,tSet);
		    	
		    	if (loop.gridy > (TrainTrak.trainSched.size())/3) {
		    		loop.gridx += 1;
		    		loop.gridy = 0;
		    	}
		    	loop.anchor = GridBagConstraints.CENTER;
			    loop.fill = GridBagConstraints.HORIZONTAL;
		    	routes.add(train,loop);
		    	loop.gridy += 1;
		    }
		    add(routes, us);
	    }    
	    
	    getContentPane();
	    setSize(900, 900);
	    setVisible(true);
	    revalidate();
	    repaint();
	}
}

class adminPopup extends JFrame{

	public adminPopup() {
	    
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
		JTextFieldLimit userField = new JTextFieldLimit(20);
		userField.setColumns(20);
		topAd.add(userField, inner1);
		
		inner1.gridx = 0;
		inner1.gridy = 1;
		topAd.add(new JLabel("Password"), inner1);
		
		inner1.gridx = 1;
		inner1.gridy = 1;
		JPasswordLimit passField = new JPasswordLimit(20);
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
				String userF = userField.getText();
				String passF = String.valueOf(passField.getPassword());
				boolean check = false;
				try {
					check = AdminLogger.check(userF,passF);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				if (check==true) {
					try {
						TrainTrak.adPage = new adminPage();
						TrainTrak.log.dispose();
						TrainTrak.menu.dispose();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(TrainTrak.log, "Invalid Login!");
				}
			}
		});
		subHolder.add(submit,s);
		
		out.gridx = 0;
		out.gridy = 1;
		add(subHolder, out);
		
	    getContentPane();
	    setSize(420, 305);
	    setVisible(true);
	    revalidate();
	    repaint();
	}
}

class addPopup extends JFrame{
	
	public addPopup() {
		
		setTitle("New Admin");
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
		topAd.add(new JLabel("New Username"), inner1);
		
		inner1.gridx = 1;
		inner1.gridy = 0;
		JTextFieldLimit userField = new JTextFieldLimit(20);
		userField.setColumns(20);
		topAd.add(userField, inner1);
		
		inner1.gridx = 0;
		inner1.gridy = 1;
		topAd.add(new JLabel("New Password"), inner1);
		
		inner1.gridx = 1;
		inner1.gridy = 1;
		JTextFieldLimit passField = new JTextFieldLimit(20);
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
				String userF = userField.getText();
				String passF = passField.getText();
				boolean check1 = TrainTrak.validator(userF, TrainTrak.textPat);
				boolean check2 = TrainTrak.validator(passF, TrainTrak.textPat);
				
				if (check1 && check2) {
					try {
						AdminLogger.addAdmin(userF,passF);
						JOptionPane.showMessageDialog(TrainTrak.addMin, "Admin Created!");
						TrainTrak.adPage.dispose();
						TrainTrak.addMin.dispose();
						TrainTrak.adPage = new adminPage();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(TrainTrak.addMin, "Invalid input!\nOnly letter and numbers accepted!");
				}
			}
		});
		subHolder.add(submit,s);
		
		out.gridx = 0;
		out.gridy = 1;
		add(subHolder, out);
		
	    getContentPane();
	    setSize(450, 305);
	    setVisible(true);
	    revalidate();
	    repaint();
	}
}

class routePopup extends JFrame{
	
	public routePopup() {
		
		setTitle("New Route");
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
		topAd.add(new JLabel("Origin", SwingConstants.CENTER), inner1);
		
		inner1.gridx = 1;
		inner1.gridy = 0;
		JTextFieldLimit origField = new JTextFieldLimit(20);
		origField.setColumns(20);
		topAd.add(origField, inner1);
		
		inner1.gridx = 0;
		inner1.gridy = 1;
		topAd.add(new JLabel("Destination", SwingConstants.CENTER), inner1);
		
		inner1.gridx = 1;
		inner1.gridy = 1;
		JTextFieldLimit destField = new JTextFieldLimit(20);
		destField.setColumns(20);
		topAd.add(destField, inner1);
		
		inner1.gridx = 0;
		inner1.gridy = 2;
		topAd.add(new JLabel("Departure", SwingConstants.CENTER), inner1);
		
		
		inner1.gridx = 1;
		inner1.gridy = 2;
		JTextFieldLimit departField = new JTextFieldLimit(4);
		TextPrompt format = new TextPrompt("#### (Military Time Format)", departField);
		format.setForeground(Color.GRAY);
		departField.add(format);
		departField.setColumns(4);
		topAd.add(departField, inner1);
		
		inner1.gridx = 0;
		inner1.gridy = 3;
		topAd.add(new JLabel("Arrival", SwingConstants.CENTER), inner1);
		
		
		inner1.gridx = 1;
		inner1.gridy = 3;
		JTextFieldLimit arrivField = new JTextFieldLimit(4);
		TextPrompt format2 = new TextPrompt("#### (Military Time Format)", arrivField);
		format2.setForeground(Color.GRAY);
		arrivField.add(format2);
		arrivField.setColumns(4);
		topAd.add(arrivField, inner1);
		
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
				String origF = origField.getText();
				String destF = destField.getText();
				String departF = departField.getText();
				String arrivF = arrivField.getText();
				boolean check1 = TrainTrak.validator(origF, TrainTrak.textPat);
				boolean check2 = TrainTrak.validator(destF, TrainTrak.textPat);
				boolean check3 = TrainTrak.validator(departF, TrainTrak.numPat);
				boolean check4 = TrainTrak.validator(arrivF, TrainTrak.numPat);
				boolean check5 = 2360>(Integer.parseInt(departF));
				boolean check6 = 2360>(Integer.parseInt(arrivF));
				boolean allChecks = (check1 && check2 && check3 && check4 && check5 && check6);
				
				
				if (allChecks) {
					try {
						TrainAdmin.addTrain(origF, destF, departF, arrivF);
						JOptionPane.showMessageDialog(TrainTrak.route, "Route Created!");
						TrainTrak.adPage.dispose();
						TrainTrak.route.dispose();
						TrainTrak.adPage = new adminPage();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}else {
					if(check1 == false || check2 == false) {
						JOptionPane.showMessageDialog(TrainTrak.route, "Invalid input!\nOnly letter and numbers accepted in origin and destination boxes!");
					}
					if(check3 == false || check4 == false) {
						JOptionPane.showMessageDialog(TrainTrak.route, "Invalid input!\nOnly 4 numbers accepted in departure and arrival boxes!");
					}
					if(check5 == false) {
						JOptionPane.showMessageDialog(TrainTrak.route, "Departure time must be a valid number! (0000-2359)");
					}
					if(check6 == false) {
						JOptionPane.showMessageDialog(TrainTrak.route, "Arrival time must be a valid number! (0000-2359)");
					}
				}		
			}
		});
		subHolder.add(submit,s);
		
		out.gridx = 0;
		out.gridy = 1;
		add(subHolder, out);
		
	    getContentPane();
	    setSize(500, 500);
	    setVisible(true);
	    revalidate();
	    repaint();
	}
}

class delayPopup extends JFrame{
	
	public delayPopup(int trainID) {
		
		setTitle("Add Delay");
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
		topAd.add(new JLabel("Delay Amount"), inner1);
		
		inner1.gridx = 1;
		inner1.gridy = 0;
		JTextFieldLimit delayField = new JTextFieldLimit(4);
		TextPrompt format3 = new TextPrompt("#### (Military Time Format)", delayField);
		format3.setForeground(Color.GRAY);
		delayField.add(format3);
		delayField.setColumns(14);
		topAd.add(delayField, inner1);
		
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
				String delayF = delayField.getText();
				boolean check1 = TrainTrak.validator(delayF, TrainTrak.numPat);
				boolean check2 = false;
				try {
					check2 = 2360>(Integer.parseInt(delayF));
				} catch (NumberFormatException e1) {
					//e1.printStackTrace();
				}
				
				if (check1 && check2) {
					try {
						int delayN = Integer.parseInt(delayF);
						TrainAdmin.addDelay(trainID, delayN);
						JOptionPane.showMessageDialog(TrainTrak.delay, "Train Delayed!");
						TrainTrak.adPage.dispose();
						TrainTrak.delay.dispose();
						TrainTrak.adPage = new adminPage();
					} catch (IOException e2) {
						//e2.printStackTrace();
					}
				}else {
					if(check1 == false) {
						JOptionPane.showMessageDialog(TrainTrak.delay, "Invalid input!\nOnly 4 numbers accepted!");
					}
					if(check2 == false) {
						JOptionPane.showMessageDialog(TrainTrak.delay, "Delay Time must be a valid number! (0000-2359)");
					}
				}
			}
		});
		subHolder.add(submit,s);
		
		out.gridx = 0;
		out.gridy = 1;
		add(subHolder, out);
		
	    getContentPane();
	    setSize(500, 250);
	    setVisible(true);
	    revalidate();
	    repaint();
	}
}

class origPopup extends JFrame{
	
	public origPopup() {
		
		setTitle("Origin Filter");
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
		topAd.add(new JLabel("Origin Filter"), inner1);
		
		out.gridx = 0;
		out.gridy = 0;
		add(topAd, out);
		
		out.gridx = 0;
		out.gridy = 1;
		out.anchor = GridBagConstraints.NORTH;
		out.fill = GridBagConstraints.BOTH;
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridBagLayout());
	    GridBagConstraints loop = new GridBagConstraints();
	    loop.insets = new Insets(20,20,20,20);
	    loop.gridx = 0;
	    loop.gridy = 0;
	    Vector<String> buton = TrainTrak.buttonGen("origin");
	    for (int i = 0; i<buton.size(); i++) {
	    	String t = buton.get(i);
	    	JButton time = new JButton(t);
	    	time.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(TrainTrak.filtered) {
							TrainAdmin.filterFiltered("origin", t);
						}
						else {
							TrainAdmin.writeFiltered("origin", t);
						}
						JOptionPane.showMessageDialog(TrainTrak.origFilter, "Filter Applied!");
						TrainTrak.usePage.dispose();
						TrainTrak.origFilter.dispose();
						TrainTrak.filtered = true;
						TrainTrak.usePage = new userPage();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
	    	if (loop.gridy > (TrainTrak.trainSched.size())/3) {
	    		loop.gridx += 1;
	    		loop.gridy = 0;
	    	}
	    	loop.anchor = GridBagConstraints.CENTER;
		    loop.fill = GridBagConstraints.HORIZONTAL;
	    	buttons.add(time,loop);
	    	loop.gridy += 1;
	    }
	    add(buttons, out);
	    
	    getContentPane();
	    setSize(500, 500);
	    setVisible(true);
	    revalidate();
	    repaint();
	}
}

class destPopup extends JFrame{
	
	public destPopup() {
		
		setTitle("Destination Filter");
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
		topAd.add(new JLabel("Destination Filter"), inner1);
		
		out.gridx = 0;
		out.gridy = 0;
		add(topAd, out);
		
		out.gridx = 0;
		out.gridy = 1;
		out.anchor = GridBagConstraints.NORTH;
		out.fill = GridBagConstraints.BOTH;
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridBagLayout());
	    GridBagConstraints loop = new GridBagConstraints();
	    loop.insets = new Insets(20,20,20,20);
	    loop.gridx = 0;
	    loop.gridy = 0;
	    Vector<String> buton = TrainTrak.buttonGen("destination");
	    for (int i = 0; i<buton.size(); i++) {
	    	String t = buton.get(i);
	    	JButton time = new JButton(t);
	    	time.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(TrainTrak.filtered) {
							TrainAdmin.filterFiltered("destination", t);
						}
						else {
							TrainAdmin.writeFiltered("destination", t);
						}
						JOptionPane.showMessageDialog(TrainTrak.destFilter, "Filter Applied!");
						TrainTrak.usePage.dispose();
						TrainTrak.destFilter.dispose();
						TrainTrak.filtered = true;
						TrainTrak.usePage = new userPage();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
	    	if (loop.gridy > (TrainTrak.trainSched.size())/3) {
	    		loop.gridx += 1;
	    		loop.gridy = 0;
	    	}
	    	loop.anchor = GridBagConstraints.CENTER;
		    loop.fill = GridBagConstraints.HORIZONTAL;
	    	buttons.add(time,loop);
	    	loop.gridy += 1;
	    }
	    add(buttons, out);
	    
	    getContentPane();
	    setSize(500, 500);
	    setVisible(true);
	    revalidate();
	    repaint();
	}
}

class departPopup extends JFrame{
	
	public departPopup() {
		
		setTitle("Departure Filter");
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
		topAd.add(new JLabel("Departure Filter"), inner1);
		
		out.gridx = 0;
		out.gridy = 0;
		add(topAd, out);
		
		out.gridx = 0;
		out.gridy = 1;
		out.anchor = GridBagConstraints.NORTH;
		out.fill = GridBagConstraints.BOTH;
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridBagLayout());
	    GridBagConstraints loop = new GridBagConstraints();
	    loop.insets = new Insets(20,20,20,20);
	    loop.gridx = 0;
	    loop.gridy = 0;
	    Vector<String> buton = TrainTrak.buttonGen("departure");
	    for (int i = 0; i<buton.size(); i++) {
	    	String t = buton.get(i);
	    	String stringTime = TrainTrak.convertTime(t);
	    	JButton time = new JButton(stringTime);
	    	time.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(TrainTrak.filtered) {
							TrainAdmin.filterFiltered("departure", t);
						}
						else {
							TrainAdmin.writeFiltered("departure", t);
						}
						JOptionPane.showMessageDialog(TrainTrak.departFilter, "Filter Applied!");
						TrainTrak.usePage.dispose();
						TrainTrak.departFilter.dispose();
						TrainTrak.filtered = true;
						TrainTrak.usePage = new userPage();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
	    	if (loop.gridy > (TrainTrak.trainSched.size())/3) {
	    		loop.gridx += 1;
	    		loop.gridy = 0;
	    	}
	    	loop.anchor = GridBagConstraints.CENTER;
		    loop.fill = GridBagConstraints.HORIZONTAL;
	    	buttons.add(time,loop);
	    	loop.gridy += 1;
	    }
	    add(buttons, out);
	    
	    getContentPane();
	    setSize(500, 500);
	    setVisible(true);
	    revalidate();
	    repaint();
	}
}

class arivPopup extends JFrame{
	
	public arivPopup() {
		
		setTitle("Arrival Filter");
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
		topAd.add(new JLabel("Arrival Filter"), inner1);
		
		out.gridx = 0;
		out.gridy = 0;
		add(topAd, out);
		
		out.gridx = 0;
		out.gridy = 1;
		out.anchor = GridBagConstraints.NORTH;
		out.fill = GridBagConstraints.BOTH;
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridBagLayout());
	    GridBagConstraints loop = new GridBagConstraints();
	    loop.insets = new Insets(20,20,20,20);
	    loop.gridx = 0;
	    loop.gridy = 0;
	    Vector<String> buton = TrainTrak.buttonGen("arrival");
	    for (int i = 0; i<buton.size(); i++) {
	    	String t = buton.get(i);
	    	String stringTime = TrainTrak.convertTime(t);
	    	JButton time = new JButton(stringTime);
	    	time.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(TrainTrak.filtered) {
							TrainAdmin.filterFiltered("arrival", t);
						}
						else {
							TrainAdmin.writeFiltered("arrival", t);
						}
						JOptionPane.showMessageDialog(TrainTrak.arivFilter, "Filter Applied!");
						TrainTrak.usePage.dispose();
						TrainTrak.arivFilter.dispose();
						TrainTrak.filtered = true;
						TrainTrak.usePage = new userPage();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
	    	if (loop.gridy > (TrainTrak.trainSched.size())/3) {
	    		loop.gridx += 1;
	    		loop.gridy = 0;
	    	}
	    	loop.anchor = GridBagConstraints.CENTER;
		    loop.fill = GridBagConstraints.HORIZONTAL;
	    	buttons.add(time,loop);
	    	loop.gridy += 1;
	    }
	    add(buttons, out);
		
	    getContentPane();
	    setSize(500, 500);
	    setVisible(true);
	    revalidate();
	    repaint();
	}
}