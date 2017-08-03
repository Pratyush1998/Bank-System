/*Name: Pratyush Bhandari
 *Date: 5/18/2016
 *Description: This class is extends JButton and is meant to allow a button to take in customer, savings and chequing objects
 *
 *Method List:
 *Customer getCustomerObject()
 *Savings getSavingsObject()
 *Chequing getChequingObject() 
 *
 */
 
//importing needed package
import javax.swing.JButton;

public class MyButton extends JButton
{
	//declaring instance variables
	private Customer customerObject;
	private Savings savingsObject;
	private Chequing chequingObject;
	
	//primary constructor
	MyButton(Customer c, Savings s, Chequing ch, String str)
	{
		//calling JButton parent class
		super(str);
		
		//setting instance variables to input parameters of constuctor
		customerObject = c;
		savingsObject = s;
		chequingObject = ch;
		
	}
	
	//secondary constuctor
	MyButton (Customer c, String str)
	{
		//calling JButton parent class
		super(str);
		
		//setting instance customerObject instance variable to input
		//parameter of constructor
		customerObject = c;
	}
	
	public Customer getCustomerObject()
	{
		return customerObject;
	}
	
	public Savings getSavingsObject()
	{
		return savingsObject;
	}
	
	public Chequing getChequingObject()
	{
		return chequingObject;
	}
}

