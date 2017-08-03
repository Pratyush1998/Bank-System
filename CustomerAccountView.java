/*Name: Pratyush Bhandari
 *Date: 5/18/2016
 *Description: This class displays all the customers in the database in a list and also creates an edit button for each customer
 *
 *Method List: 
 *void actionPerformed(ActionEvent e)
 *void makeTempAcc()
 */
 
//importing needed packages
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class CustomerAccountView extends JFrame implements ActionListener
{
    //declaring instance variables
    private JPanel inputPanel;
    private JLabel lblName, lblAccountNumber, lblPhone, lblSavingBal, lblChequingBal;
    private BankHomepage bh;
    private JButton btnHome, btnWithdraw, btnDeposit;
    private Savings sav;
    private Chequing chq;
    private Customer cust;
    private JRadioButton radSavings, radChequing;
    private static int counter;
    private int arrayLocation;
    
    public CustomerAccountView(BankHomepage BH, Customer customer, Savings savings, Chequing cheq, int i) 
    {
    	super("Customer Account");
    	
    	//setting all of the input paramaters of the contructor to the corresponding variables of the program 
    	arrayLocation = i;
    	sav = savings;
    	chq = cheq;
    	cust = customer;
    	
    	//setting layout of the frame
    	setLayout(new GridLayout(1, 1));
    	
    	//initializing inputPanel and setting layout
    	inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout (10, 1));
		
		//intializing all of the other components 
    	lblName = new JLabel ("Name: " + cust.getName());
    	lblPhone = new JLabel ("Phone Number: " + cust.getPhone());
    	lblAccountNumber = new JLabel ("Account Number: " + Integer.toString(sav.getCardNumber()));
    	lblSavingBal = new JLabel("Saving Balance: " + String.valueOf(sav.getBalance()));
    	lblChequingBal = new JLabel("Chequing Balance: " + String.valueOf(chq.getBalance()));
    	
    	radSavings = new JRadioButton ("Savings");
    	radChequing = new JRadioButton ("Chequing");
    	
    	
    	btnHome = new JButton ("Home");
    	btnWithdraw = new JButton ("Withdraw");
    	btnDeposit = new JButton ("Deposit");
    	
    	//adding all of the components to the inputPanel
    	inputPanel.add(lblName);
    	inputPanel.add(lblPhone);
    	inputPanel.add(lblAccountNumber);
    	inputPanel.add(lblSavingBal);
    	inputPanel.add(lblChequingBal);
    	inputPanel.add(btnWithdraw);
    	inputPanel.add(btnDeposit);
    	inputPanel.add(radSavings);
    	inputPanel.add(radChequing);
    	inputPanel.add(btnHome);
    	
    	//adding inputPanel to frame
    	add(inputPanel);
    	
    	setSize(400, 600);
		setVisible(true);
		setLocation(100, 100);
		
		bh = BH;
		
		//adding an action listener for each button
		btnHome.addActionListener(this);
		btnWithdraw.addActionListener(this);
		btnDeposit.addActionListener(this);
		
    }
    
    //this method is called everytime an object with a listener is pressed
    public void actionPerformed (ActionEvent e)
    {
    	//if the home button is pressed
    	if (e.getSource() == btnHome)
    	{
    		setVisible (false);
    		bh.setVisible (true);	
        }
        
        //if withdraw is pressed and savings radio button is pressed
        if (e.getSource() == btnWithdraw && radSavings.isSelected()==true)
        {
        	int withdrawalAmt = Integer.parseInt (JOptionPane.showInputDialog("How much would you like to withdraw"));
        	
        	//witdrawalAmt is withdrawn from an element in the savings array of the add account class
        	AddAccount.sav[arrayLocation].withdraw(withdrawalAmt);
        	
  			//method is called
  			makeTempAcc();
        	
        	//savings balance textfield is set to the balance of a specific element of the savings object array in the AddAccount class
        	lblSavingBal.setText("Savings Balance: " + String.valueOf(AddAccount.sav[arrayLocation].getBalance()));
        	
        }
        //if withdraw is pressed and chequing is selected
        if (e.getSource() == btnWithdraw && radChequing.isSelected()==true && radSavings.isSelected()==false)
        {
        	int withdrawalAmt = Integer.parseInt (JOptionPane.showInputDialog("How much would you like to withdraw"));
        	
        	//witdrawalAmt is withdrawn from an element in the chequing array of the add account class
        	AddAccount.chq[arrayLocation].withdraw(withdrawalAmt);
        	
        	//method is called
        	makeTempAcc();
        	
        	//chequing balance textfield is set to the balance of a specific element of the chequing object array in the AddAccount class
        	lblChequingBal.setText("Chequing Balance: " + String.valueOf(AddAccount.chq[arrayLocation].getBalance()));
        }
        
        //if deposit is pressed and Savings is selected
        if (e.getSource() == btnDeposit && radSavings.isSelected()==true && radChequing.isSelected()==false)
        {
        	int depositAmt = Integer.parseInt (JOptionPane.showInputDialog("How much would you like to deposit"));
        	//depositAmt is deposited to an element in the savings array of the AddAccount class
        	AddAccount.sav[arrayLocation].deposit(depositAmt);
        	
        	makeTempAcc();
        	
        	//savings balance textfield is set to the balance of a specific element of the savings object array in the AddAccount class
        	lblSavingBal.setText("Savings Balance: " + String.valueOf(AddAccount.sav[arrayLocation].getBalance()));
        }
        
        //if deposit is pressed and chequing is selected
        if (e.getSource() == btnDeposit && radChequing.isSelected()==true && radSavings.isSelected()==false)
        {
        	int depositAmt = Integer.parseInt (JOptionPane.showInputDialog("How much would you like to deposit"));
        	
        	//depositAmt is deposited to an element in the chequing array of the AddAccount class
        	AddAccount.chq[arrayLocation].deposit(depositAmt);
        	
        	makeTempAcc();
        	
        	//chequing balance textfield is set to the balance of a specific element of the chequing object array in the AddAccount class
        	lblChequingBal.setText("Chequing Balance: " + String.valueOf(AddAccount.chq[arrayLocation].getBalance()));
        }
        
        //if deposit is pressed and both radio buttons are selected
        if (e.getSource() == btnDeposit && radChequing.isSelected()==true && radSavings.isSelected()==true)
        {
        	JOptionPane.showMessageDialog(null, "Can only click on either chequing or savings toggle button");
        }
        
        //if withdraw is pressed and both radio buttons are selected
        if (e.getSource() == btnWithdraw && radChequing.isSelected()==true && radSavings.isSelected()==true)
        {
        	JOptionPane.showMessageDialog(null, "Can only click on either chequing or savings toggle button");
        }
    }
    
    //this method makes temporary customer, savings and chequing objects
    public void makeTempAcc()
    {
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
    	
    	ArrayLibrary.save(tempCust, tempSav, tempChq);
    }
    public static void main(String[] args) 
    {
        
    }
}
