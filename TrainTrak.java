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
	    String path = "C:\\Users\\Edene\\Desktop\\540\\project things\\tommy.jfif";
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
		GridBagConstraints l = new GridBagConstraints();
		l.insets = new Insets(20,20,20,20);
		
		l.gridx = 0;
		l.gridy = 0;
		add(new JLabel("Username"), l);
		
		l.gridx = 1;
		l.gridy = 0;
		l.fill = GridBagConstraints.BOTH;
		JTextField userField = new JTextField(20);
		add(userField, l);
		
		l.gridx = 0;
		l.gridy = 1;
		add(new JLabel("Password"), l);
		
		l.gridx = 1;
		l.gridy = 1;
		l.fill = GridBagConstraints.BOTH;
		JPasswordField passField = new JPasswordField(20);
		add(passField, l);
		
		l.gridx = 0;
		l.gridy = 2;
		l.weightx = 2;
		JButton submit = new JButton("Submit");
		//if (someCode()==true)
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new adminPage();
				menuClose.dispose();
				log.dispose();
			}
		});
		
		add(submit, l);
	    getContentPane();
	    setSize(300, 300);
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
	    add(new JLabel("You Win"), ad);
	    getContentPane();
	    setSize(700, 700);
	    setVisible(true);
	}
}