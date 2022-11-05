// Java program to demonstrate GridBagLayout class.
// if anyone looks at this, this is the original template to test gui sorry!!
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
 
public class TrainTrak {
	
	public static void main(String[] args){
		
        gui();
        
    }
 
    public static void gui(){
    	int padX = 100;
    	int padY = 100;
    	
    	// Function to set title of JFrame.
        JFrame f = new JFrame("Main Menu");
        // set the layout
        f.setLayout(new GridBagLayout());
 
        // creates a constraints object
        GridBagConstraints c = new GridBagConstraints();
        // insets for all components
        c.insets = new Insets(20,20,20,20);
        
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = padX;
        c.ipady = padY;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        JLabel TrainTrak = new JLabel("Train Trak");
        TrainTrak.setHorizontalAlignment(JLabel.CENTER);
        f.add(TrainTrak, c);
 		
        
        // column
        c.gridx = 1;
        // row
        c.gridy = 1;
        // width
        c.ipadx = padX;
        c.fill = GridBagConstraints.BOTH;
        // height
        c.ipady = padY;
        JPanel MenuButtons = new JPanel();
        MenuButtons.setLayout(new GridLayout(1,0,20,0));
        JButton b1 = new JButton("Buy A Ticket");
        JButton b2 = new JButton("Select A Destination");
        JButton b3 = new JButton("Train Schedule");
        MenuButtons.add(b1);
        MenuButtons.add(b2);
        MenuButtons.add(b3);
        f.add(MenuButtons, c);
        
        
        // add the searchbar
        JPanel SearchBar = new JPanel(); // the panel is not visible in output
        SearchBar.setLayout(new GridBagLayout());
        GridBagConstraints sbc = new GridBagConstraints();
        sbc.insets = new Insets(2,2,2,2);
        
        sbc.gridx = 0;
        sbc.gridy = 0;
        sbc.ipadx = 10;
        sbc.ipady = 10;
        SearchBar.add(new JLabel("Enter Train Info"),sbc);
        
        sbc.gridx = 0;
        sbc.gridy = 1;
        sbc.fill = GridBagConstraints.BOTH;
        //sbc.ipadx = 10;
        //sbc.ipady = 10;
        SearchBar.add(new JTextField(15),sbc);
        
        JPanel ActionButtons = new JPanel();
        ActionButtons.setLayout(new FlowLayout());//gamer move
        ActionButtons.add(new JButton("Search"));
        // need to put the last buttons in its own panel
        sbc.gridx = 0;
        sbc.gridy = 2;
        sbc.ipadx = 10;
        sbc.ipady = 10;
        SearchBar.add(ActionButtons,sbc);
        //end of searchbar panel
        
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.ipadx = 20;
        c.ipady = 20;
        f.add(SearchBar, c);
 
        // Creating Object of "wndcloser"
        // class of windowlistener
        WindowListener wndCloser = new WindowAdapter() {
 
            public void windowClosing(WindowEvent e)
            {
                // exit the system
                System.exit(0);
            }
        };
        // add the actionwindowlistener
        f.addWindowListener(wndCloser);
        f.getContentPane();
        f.setSize(700, 700);
        f.setVisible(true);
    }
}
