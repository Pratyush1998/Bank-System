/*Name: Pratyush Bhandari
 *Date: 5/18/2016
 *Description: This class is the main page of the bank account program.
 *             it opens up all thoe other classes in the program
 *
 *Method List:
 *void actionPerformed(ActionEvent e) 
 *void sortAddAccObjects(int size)
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class BankHomepage extends JFrame implements ActionListener 
{
    
    //declaring private instance variables 
    private JButton createCust, editAccounts, accTrans, btnExit;
    private JPanel inputPanel;
    private int counter;
    private int counter2;
    private AddAccount ac;
    private ViewAccounts va;
    private EditAccounts ea;
    private static BankHomepage bh;
    
    public BankHomepage() 
    {
    	super("Bank HomePage");
    	
    	//setting layout of frame
    	setLayout(new GridLayout(1, 1));
    	
    	//initializing and setting layout of inputPanel
    	inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout (3, 1));
    	
    	//initializing all of the buttons in the program
    	//giving them all labels
    	createCust = new JButton ("Create New Customer");
    	editAccounts = new JButton ("Edit Accounts");
    	accTrans = new JButton ("View Accounts");
    	btnExit = new JButton ("Exit");

    	//adding all buttons to input panel in specific order
    	inputPanel.add(createCust);
    	inputPanel.add(editAccounts);
    	inputPanel.add(accTrans);
    	inputPanel.add(btnExit);
    	
    	//adding input panel to frame
    	add(inputPanel);
    	
    	//setting size of frame
    	setSize(400, 600);
		
		setVisible(true);
		
		//setting location of frame
		setLocation(100, 100);
		
    	//adding action listeners for all buttons
    	createCust.addActionListener(this);
    	editAccounts.addActionListener(this);
    	accTrans.addActionListener(this);
    	btnExit.addActionListener(this);
    	
    	//initializing the counters
    	counter = 0;
    	counter2 = 0;	
    }
    
    //the action performed method carries out the specified action
    //when an object with a listener is pressed
    public void actionPerformed(ActionEvent e)
    {
    	//if create customer btn is pressed
    	if (e.getSource() == createCust)
    	{
    		
    		//make homepage invisible
    		setVisible (false);
    		
    		//add 1 to counter for
    		//counter is for how many times the create customer 
    		//button is clicked
    		counter++;
	    	
	    	//if the create customer button was clicked once and
	    	//the view accounts button was not clicked
	    	if (counter == 1 && counter2!=1)
	    	{
	    		//make new isntance of AddAccount class
	    		ac = new AddAccount(bh);
	    		
	    		//open the customer database using ArrayLibrary
	    		ArrayLibrary.openFile();
    			int size = ArrayLibrary.size;
    			
    			//set the counter in add account to the size of the 
    			//opened database
	    		AddAccount.setCounter(size);
	    		
	    		//run through the entire add account arrays of saving 
	    		//chequing and customer objects
	    		sortAddAccObjects(size);
	    		
	    		//make instance of add account visible
	    		ac.setVisible (true);
	    		
	    	}
	    	//if the add customer button is being pressed a second time
	    	else
	    	{
	    		//make the same instance of AddAccount visible 
	    		ac.setVisible (true);
	    	}
	    }
    	
    	//if viewAccounts button is clicked
    	if (e.getSource() == accTrans)
    	{
    		
    		//open customer database
    		ArrayLibrary.openFile();
    		
    		//find size of database
    		int size = ArrayLibrary.size;
    		
    		//set AddAccount counter to size of database 
    		AddAccount.setCounter(size);
    		
    		
    		counter2++;
    		
    		//if view account is pressed for the first time and 
    		//addcustomer has not been pressed
    		if (counter2==1 && counter==0)
    		{
    			//make an instance of the AddAccount class
    			//and set it to visible
    			ac = new AddAccount(bh);
    			ac.setVisible(false);
    		}
    		
    		sortAddAccObjects(size);
    		
    		setVisible(false);
    		//sending objects from AddAccount class to ViewAccount class
    		va = new ViewAccounts(bh, AddAccount.cust, AddAccount.sav, AddAccount.chq);
    		va.setVisible(true);
	    }
    	
    	if(e.getSource() == editAccounts)
    	{
    		
    		//open databae file
    		ArrayLibrary.openFile();
    		int size = ArrayLibrary.size;
    		AddAccount.setCounter(size);
    		counter2++;
    		
    		if (counter2==1 && counter==0)
    		{
    			ac = new AddAccount(bh);
    			ac.setVisible(false);
    		}
    		
    		sortAddAccObjects(size);
    		
    		setVisible(false);
    		ea = new EditAccounts(bh, AddAccount.cust);
    		ea.setVisible(true);
    	}
    	
    	//if exit is pressed
    	if(e.getSource() == btnExit)
    	{
    		//making confirm dialog box for exiting
    		int confirmed = JOptionPane.showConfirmDialog(null, "Exit Program?","EXIT",JOptionPane.YES_NO_OPTION);
	        
	        //if yes is pressed
	        if(confirmed == JOptionPane.YES_OPTION)
	        {
	            //exit
	            System.exit(0);
	        }
    	}
    	
    }
    
    //this method sorts the objects in the AddAccount class
    //using ArrayLibrary
    public void sortAddAccObjects(int size)
    {
    	//run through the entire add account arrays of saving 
	    //chequing and customer objects
		int i;
		for (i=0; i<size; i++)
		{
			//replace each element with the sorted element from
			//array library
			AddAccount.sav[i] = ArrayLibrary.savings[i];
			AddAccount.cust[i]= ArrayLibrary.customer[i];
			AddAccount.chq[i] = ArrayLibrary.chequing[i];
		}	
    }
    
    public static void main(String[] args) 
    {
       //making instance of BankHomepage
       bh = new BankHomepage();
       
       JOptionPane.showMessageDialog(null, "Hello, welcome to VPS Banking System!" + "\n" + 
       	"All of the Accounts from the Customer Database Have Already Been Loaded!" + "\n" + 
       	"Feel Free to Start Banking, You Can Add a New Customer or View the Current Ones Already in the Database.");
    }
}
