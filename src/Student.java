
public class Student extends Person
{
	private double balanceOutstanding;
	
	public Student(String username, String password, double balanceOutstanding)
	{
		// call to the super constructor of the Person class
		super(username, password);
		
		this.balanceOutstanding = balanceOutstanding;
	}
	// pay the balance outstanding
	public void payBalance()
	{
		this.balanceOutstanding = 0;
	}
	// check if you have an outstanding late balance
	public double checkBalance()
	{
		return balanceOutstanding;
	}
	// returns the balance
	public double getBalanceOutstanding()
	{
		return balanceOutstanding;
	}
	// sets the balance
	public void setBalanceOutstanding(double balanceOutstanding)
	{
		this.balanceOutstanding = balanceOutstanding;
	}
}
