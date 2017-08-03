/*Name: Pratyush Bhandari
 *Date: 5/18/2016
 *Description: This class displays all the customers in the database in a list and also creates an edit button for each customer
 *
 *Method List: 
 *void actionPerformed(ActionEvent e)
 */
 
//importing necessary packages
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class EditAccountView extends JFrame implements ActionListener {
        
    //declaring all instance variables
    private JButton btnHome, btnEdit;
    private JTextField txtNewName, txtNewPhone;
    private BankHomepage bh;
    private JPanel inputPanel;
    private Customer cust;
    private int arrayLocation;
    
    public EditAccountView(BankHomepage BH, Customer customer, int i) 
    {
    	super("Edit Account View");
    	
    	cust = customer;
    	
    	//setting layout of frame
    	setLayout(new GridLayout(1, 1));
    	
    	//initializing inputPanel and setting layout
    	inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout (4, 1));
    	
    	//intializing the buttons and textfields
    	btnHome = new JButton ("Home");
    	btnEdit = new JButton ("Edit");
    	txtNewName = new JTextField (cust.getName());
    	txtNewPhone = new JTextField (cust.getPhone());
    	
    	//adding all the components to the inputPanel
    	inputPanel.add(txtNewName);
    	inputPanel.add(txtNewPhone);
    	inputPanel.add(btnEdit);
    	inputPanel.add(btnHome);
    	
    	//adding inputPanel to frame
    	add(inputPanel);
    	
    	setSize(400, 600);
		setVisible(true);
		setLocation(100, 100);
		
		//adding action listener for both home and edit buttons
		btnHome.addActionListener(this);
		btnEdit.addActionListener(this);
    	
    	bh = BH;
    	arrayLocation = i;
    		
    }
    
    //this method runs everytime an object with a lsitner is pressed to carry out 
    //a commanded task
    public void actionPerformed(ActionEvent e)
    {
    	//if the home button is pressed
    	if (e.getSource() == btnHome)
    	{
    		//close current window
    		setVisible (false);
    		
	    	//set bank homepage to visible
	    	bh.setVisible (true);	
    			
	    }
	    
	    if (e.getSource()==btnEdit)
	    {
	    	//set name and phone of specific element of cust object array to the text in the textfield
	    	AddAccount.cust[arrayLocation].setName(txtNewName.getText());
	    	AddAccount.cust[arrayLocation].setPhone(txtNewPhone.getText());
	    	
	    	//making temporary customer, savings and chequing object arrays
	    	Customer tempCust[] = new Customer[AddAccount.getCounter()];
        	for (int i = 0; i<AddAccount.getCounter(); i++)
        	{
        		tempCust[i] = AddAccount.cust[i];
        	}
        	
        	Savings tempSav[] = new Savings[AddAccount.getCounter()];
        	for (int i = 0; i<AddAccount.getCounter(); i++)
        	{
        		tempSav[i] = AddAccount.sav[i];
        	}
        	
        	Chequing tempChq[] = new Chequing[AddAccount.getCounter()];
        	
        	for (int i = 0; i<AddAccount.getCounter(); i++)
        	{
        		tempChq[i] = AddAccount.chq[i];
        	}
	    	
	    	//saving temporary object arrays to the customer database
	    	ArrayLibrary.save(tempCust, tempSav, tempChq);
	    	
	    	
	    	JOptionPane.showMessageDialog(null, "Your Changes Have Been Made!");
	    }
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
