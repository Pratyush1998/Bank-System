/*Name: Pratyush Bhandari
 *Date: 5/18/2016
 *Description: This class displays all the customers in the database in a list and also creates an edit button for each customer
 *
 *Method List: 
 * void actionPerformed(ActionEvent e)
 */
 
//importing needed packages
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class EditAccounts extends JFrame implements ActionListener  {
        
    //declaring private instance variables
    private JLabel lblAccount[];
    private MyButton btnEditAccount[];
    private JPanel inputPanel;
    private BankHomepage bh;
    private EditAccountView ea;
    private JButton btnHome;
    
    public EditAccounts(BankHomepage BH, Customer cust[]) 
    {
    	super("Edit Accounts");
    	
    	//setting layout of frame
    	setLayout(new GridLayout(1, 1));
    	
    	//initialzing inputPanel
    	inputPanel = new JPanel();
    	
    	//intializing all other components
    	lblAccount = new JLabel[AddAccount.getCounter()];
    	btnEditAccount = new MyButton [AddAccount.getCounter()];
    	btnHome = new JButton("Home");
    	
    	//for loop iterating for all the customers in the database
    	for (int i=0; i<AddAccount.getCounter(); i++)
    	{
    		//making a label for each element in the label array
    		lblAccount[i] = new JLabel (cust[i].getName());
    		
    		//making an edit button that takes  in a customer object
    		btnEditAccount[i] = new MyButton (cust[i], "Edit Account Info");
    		
    		//changing layout by adding 1 each time a new button and label are added
    		inputPanel.setLayout(new GridLayout (i+2, 2));
    		
    		//adding button and label to input panel
    		inputPanel.add(lblAccount[i]);
    		inputPanel.add(btnEditAccount[i]);
  			
  			//adding action listener to the button
  			btnEditAccount[i].addActionListener(this);
    	}
    	//adding home button to input panel
    	inputPanel.add(btnHome);
    	
    	//adding action listener to home button
    	btnHome.addActionListener(this);
    	
    	//making a scrollPane that takes in the inputPanel
    	JScrollPane scrollPane = new JScrollPane(inputPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 500, 600);
        
        //adding scrollPane to frame
        add(scrollPane);
    	
    	//seeting size and location of frame
    	setSize(500, 600);
		setVisible(true);
		setLocation(100, 100);
		
		bh = BH;
			
    }
    
    //this method runs everytime an object with an action listener is pressed
    public void actionPerformed (ActionEvent e)
    {
    	
    	//runs through the size of the customer database
    	for (int i = 0; i<AddAccount.getCounter(); i++)
    	{
    	
	    	//if this specific edit button is pressed 
	    	if (e.getSource() == btnEditAccount[i])
	    	{
	    		//set current window to invisible
	    		setVisible (false);
	    		
	    		//make a new EditAccountView object
		        ea = new EditAccountView(this.bh, btnEditAccount[i].getCustomerObject(), i);
		        //set this EditAccountview object to visible
		        ea.setVisible(true);
		    	
	    	}
    	}
    	
    	//if the home button is pressed
    	if (e.getSource() == btnHome)
    	{
    		//set current window to invisible
    		setVisible (false);
    		//set home page to visible
    		bh.setVisible (true);
    	}
    	
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
