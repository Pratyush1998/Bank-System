public class Chequing extends Account 
{
	public Chequing(Customer cust) 
	{ 
		super(cust);
		fee = 1; 
	}
	
	public static void main(String[] args) 
	{ 
		Customer c = new Customer("Vaibhav", "905123456");
		Chequing ca = new Chequing(c);
		ca.deposit(2000);
		System.out.println(ca.balance);
		ca.withdraw(400);
		System.out.println(ca.balance);
		ca.withdraw(5000);
		System.out.println(ca.balance);
	}
}
