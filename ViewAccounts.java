/*Name: Pratyush Bhandari
 *Date: 5/18/2016
 *Description: This class displays all the customers in the database in a list and also creates a view account button for each customer
 *
 *Method List: 
 *void actionPerformed(ActionEvent e)
 */
 
//importing necessary packages
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class ViewAccounts extends JFrame implements ActionListener 
{
    //declaring all instance variables
    private JLabel lblAccount[], lblTitle;
    private MyButton btnAccount[];
    private JPanel inputPanel, inputPanel2;
    private BankHomepage bh;
    private JButton btnHome;
    private CustomerAccountView ca;
    
    public ViewAccounts(BankHomepage BH, Customer cust[], Savings sav[], Chequing chq[])
    {
    	super("View Accounts");
    	
    	//setting layout of frame
    	setLayout(new GridLayout(1, 1));
    	
    	//initialzing inputPanel
    	inputPanel = new JPanel();
    	
    	//intializing all other components
    	lblAccount = new JLabel[AddAccount.getCounter()];
    	btnAccount = new MyButton [AddAccount.getCounter()];
    	btnHome = new JButton ("Home");
    	
 		//for loop iterating for all the customers in the database
    	for (int i=0; i<AddAccount.getCounter(); i++)
    	{
    		//making a label for each element in the label array
    		lblAccount[i] = new JLabel ("Name: " + cust[i].getName() + " " + "Acc#: " + sav[i].getCardNumber());
    		
    		//making a MyButton that takes in customer, savings and chequing objects
    		btnAccount[i] = new MyButton (cust[i], sav[i], chq[i], "View Account");
    		//changing the inputPanel layout each time by adding 1
    		inputPanel.setLayout(new GridLayout (i+2, 2));
    		//adding the the button and label to the inputPanel
    		inputPanel.add(lblAccount[i]);
    		inputPanel.add(btnAccount[i]);
  			
  			//adding an actionListener for the added button
  			btnAccount[i].addActionListener(this);
    	}
    	//adding the homeButton to inputPanel
    	inputPanel.add(btnHome);
    	//adding action listener for home button
    	btnHome.addActionListener(this);
    	
    	//making a scrollpane that takes in the inputPanel
    	JScrollPane scrollPane = new JScrollPane(inputPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 500, 600);
        
        //adding the scrollPane to frame
        add(scrollPane);
 
    	setSize(500, 600);
		setVisible(true);
		setLocation(100, 100);
		
		bh = BH;
    }
    
    //this method runs everytime an object with a listener is pressed
    public void actionPerformed (ActionEvent e)
    {
    	//runs through the size of the customer database
    	for (int i = 0; i<AddAccount.getCounter(); i++)
    	{
    	
	    	//if the specific button has been pressed
	    	if (e.getSource() == btnAccount[i])
	    	{
	    		//set current window to invisible 
	    		setVisible (false);
				
				//make a new instance of the CustomerAccountView class
				ca = new CustomerAccountView(this.bh, btnAccount[i].getCustomerObject(), 
		    			btnAccount[i].getSavingsObject(), btnAccount[i].getChequingObject(), i);
		    			
		    	//set this instance of the class to visible
		    	ca.setVisible(true);
		    	
	    	}
    	}
    	
    	//if home is pressed
    	if (e.getSource() == btnHome)
    	{
    		//set current window invisible
    		setVisible (false);
    		
    		//open up bankHomepage
    		
    		bh.setVisible (true);
    	}
    }
    public static void main(String[] args) 
    {
        
    }
}
