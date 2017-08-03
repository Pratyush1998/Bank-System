/*Name: Pratyush Bhandari
 *Date: 5/18/2016
 *Description: This class adds information to the customer database
 *
 *Method List: 
 *void actionPerformed(ActionEvent e)
 *int getCounter()
 *void setCounter(int count);
 */

//importing necessary packages
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class AddAccount extends JFrame implements ActionListener 
{
        
    //declaring all private instance variables
    private JTextField txtName, txtPhone, txtChqBal, txtSavBal;
    private JPanel inputPanel;
    private JButton btnHome, btnAdd;
    private static int counter3;
    private BankHomepage bh;
    public static Customer cust[];
    public static Chequing chq[];
    public static Savings sav[];
    
    //AddAccount constructor
    public AddAccount(BankHomepage BH) 
    {
    	super("Add Account");
    	
    	//setting layout of frame
    	setLayout(new GridLayout(1, 1));
    	
    	//intializing inputPanel and setting layout
    	inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout (6, 1));
    	
    	//initializing all textfields and buttons
    	txtName = new JTextField ("Enter Name");
    	txtPhone = new JTextField ("Enter Phone");
    	txtChqBal = new JTextField ("Enter Initial Chequing Balance");
    	txtSavBal = new JTextField ("Enter Initial Savings Balance");
    	btnHome = new JButton ("Home");
    	btnAdd = new JButton ("Add Account");
    	
    	//adding all components to inputPanel in specific order
    	inputPanel.add(txtName);
    	inputPanel.add(txtPhone);
    	inputPanel.add(txtChqBal);
    	inputPanel.add(txtSavBal);
    	inputPanel.add(btnAdd);
    	inputPanel.add(btnHome);
    	
    	//adding inputPanel to frame
    	add(inputPanel);
    	
    	//setting size of frame
    	setSize(400, 600);
		
		setVisible(true);
		//setting location of frame
		setLocation(100, 100);
		
		//adding action listeners for btnHome and btnAdd
		btnHome.addActionListener(this);
		btnAdd.addActionListener(this);
		
		//setting BankAccount instance variable to specific instance given by BankHomepage 
		bh = BH;
		
		//Setting size of customer savings and chequing object arrays
		cust = new Customer[100];
		sav = new Savings [100];
		chq = new Chequing [100];
    }
    
    public void actionPerformed(ActionEvent e)
    {
    	//if home is pressed
    	if (e.getSource() == btnHome)
    	{
    		//set current window to invisible
    		setVisible (false);
    		
	    	//set specfic instance of BankHomepage to visible
	    	bh.setVisible (true);	
    			
	    }
	    
	    //if btnAdd is pressed
	    if (e.getSource() == btnAdd)
	    {
	    	
			try
			{
				//setting specific element of each Customer object array to text inputed
				cust[counter3] = new Customer(txtName.getText(), txtPhone.getText());
				
				sav[counter3] = new Savings(cust[counter3]);
				chq[counter3] = new Chequing(cust[counter3]);
				
				
				int txtchqBalance = Integer.parseInt(txtChqBal.getText());
				
				//setting balance for element of chequing object array as the text inputed in chequing balance text field
				chq[counter3].setBalance(txtchqBalance);
			
				//setting balance for element of savings object array as the text inputed in savings balance text field
				int txtsavBalance = Integer.parseInt(txtSavBal.getText());
				sav[counter3].setBalance(txtsavBalance);
				
				JOptionPane.showMessageDialog(null, "New Customer Has Been Added to Database! " + "\n" + 
					"You Can Add Another Customer or Go Back Home to View Your Added Customer(s)");
				
				//adding 1 to counter 3
				counter3++;
				
				//resetting all textfields
				txtName.setText("Enter Name");
				txtPhone.setText("Enter Phone");
				txtChqBal.setText("Enter Initial Chequing Balance");
				txtSavBal.setText("Enter Initial Savings Balance");
				
				
	        	//making temporary customer, savings and chequing object arrays
	        	Customer tempCust[] = new Customer[counter3];
	        	for (int i = 0; i<counter3; i++)
	        	{
	        		tempCust[i] = cust[i];
	        	}
	        	
	        	Savings tempSav[] = new Savings[counter3];
	        	for (int i = 0; i<counter3; i++)
	        	{
	        		tempSav[i] = sav[i];
	        	}
	        	
	        	Chequing tempChq[] = new Chequing[counter3];
	        	for (int i = 0; i<counter3; i++)
	        	{
	        		tempChq[i] = chq[i];
	        	}
	        	
	        	//saving temporary object arrays into database
	            ArrayLibrary.save(tempCust, tempSav, tempChq);	
			}
			catch(Exception NumberFormatException)
			{
				JOptionPane.showMessageDialog(null, 
					"Make sure to fill in all fields and to enter a number value for savings, chequing and phone number");
			}
				
	    }
    }
    
    public static int getCounter()
    {
    	return counter3;
    }
    
    public static void setCounter(int count)
    {
    	counter3 = count;
    }
    		
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
