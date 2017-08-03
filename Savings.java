public class Savings extends Account 
{
	public Savings(Customer cust) 
	{
		super(cust);
		fee = setFee();
	}

	public void withdraw(double withAmt) 
	{
		super.withdraw(withAmt); 
		fee = setFee();
	}
	
	public double setFee() 
	{
		if (this.balance < 2000) 
		{
			fee = 1; 
			return fee;
		}
		
		fee = 0; 
		
		return fee;
	}

	public static void main(String[] args) 
	{ 
		Customer c = new Customer("Vaibhav", "905123456");
		Savings sav = new Savings(c);
		sav.deposit(2000);
		System.out.println(sav.balance);
		sav.withdraw(10000);
		System.out.println(sav.balance);
		sav.withdraw(100);
		System.out.println(sav.balance);
	}
}
