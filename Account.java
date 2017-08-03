/*
 Authors: Sam and Vaibhav
 Description: 
 */

import javax.swing.*;
public class Account 
{
	
	public double balance;
	public double fee; 
	public int cardNumber; 
	public Customer Cust; 
	 

	public Account() 
	{
		this.balance = 0; 
		this.Cust = null; 
		this.cardNumber = createCardNumber(); 
	}

	public Account(Customer currentCust) 
	{
		
		this.balance = 0; 
		this.Cust = currentCust; 
		this.cardNumber = createCardNumber(); 
	}


	public void withdraw(double withAmt) 
	{
		if (this.balance >= (withAmt + fee)) 
		{
			this.balance = this.balance-(fee + withAmt);
			JOptionPane.showMessageDialog(null, withAmt + " was withdrawn, and fee of " + fee + " was taken. Your new balance is " + this.balance);
		} 
		
		else 
		{
			JOptionPane.showMessageDialog(null,"Not Enough Money! Cannot withdraw " + withAmt + " from " + this.balance + ".");
		}
	}
	
	public void deposit(double depAmt) 
	{
		this.balance = this.balance + depAmt; 
		JOptionPane.showMessageDialog (null, depAmt + " was deposited." + "\n" + "New Balance: " + (this.balance));
	}

	private int createCardNumber() 
	{ 
		return (int) (Math.random() * 90000000 + 10000000);
	}

	public void setBalance(double bal) 
	{
		this.balance = bal; 
	}
	
	
	public int getCardNumber() 
	{
		return this.cardNumber;
	}
	
	public double getBalance() 
	{ 
		return this.balance;
	}

	
	public static void main(String[] args) 
	{ 
		
		Customer c = new Customer("Vaibhav", "9052155161");

		Account a1 = new Account();
		Account a2 = new Account(c);
		
		System.out.println("a1 Card Number: " + a1.getCardNumber());
		System.out.println("a2 Card Number: " + a2.getCardNumber());
		a1.setBalance(5000);
		a2.setBalance(5000);
		System.out.println("a1 Balance: " + a1.getBalance());
		System.out.println("a2 Balance: " + a2.getBalance());
		
	}
}
