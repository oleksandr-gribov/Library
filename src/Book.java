public class Book
{
	private String authorFirstName;
	private String authorLastName;
	private String title;
	private int isbnCode;
	private int uniqueCode;
	private Boolean availability;
	
	public Book(String authorFirstName, String authorLastName, String title, int isbnCode, int uniqueCode, Boolean availability)
	{
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.title = title;
		this.isbnCode = isbnCode;
		this.uniqueCode = uniqueCode;
		this.availability = availability;
	}
	// This is the display info
	public String toString() 
	{
		String status;
		
		if(availability == true)
		{
			status = "Available";
		}
		else
		{
			status = "Not Available";
		}
		
		return "[Title = " + title + ", Author = " + authorFirstName + " " + authorLastName + ", ISBN = " + isbnCode + ", Unique Code = " + uniqueCode + ", Status = " + status + "]";
	}
	// getter for author first name
	public String getAuthorFirstName()
	{
		return authorFirstName;
	}
	// setter for author first name
	public void setAuthorFirstName(String authorFirstName)
	{
		this.authorFirstName = authorFirstName;
	}
	// getter for author last name
	public String getAuthorLastName()
	{
		return authorLastName;
	}
	// setter for author last name
	public void setAuthorLastName(String authorLastName)
	{
		this.authorLastName = authorLastName;
	}
	// getter for the title
	public String getTitle()
	{
		return title;
	}
	// setter for the title
	public void setTitle(String title)
	{
		this.title = title;
	}
	// getter for the isbn
	public int getIsbnCode()
	{
		return isbnCode;
	}
	// setter for the isbn code
	public void setIsbnCode(int isbnCode)
	{
		this.isbnCode = isbnCode;
	}
	// getter for the unique code
	public int getUniqueCode()
	{
		return uniqueCode;
	}
	// setter for the unique code
	public void setUniqueCode(int uniqueCode)
	{
		this.uniqueCode = uniqueCode;
	}
	// getter for availability 
	public Boolean getAvailability()
	{
		return availability;
	}
	// setter for availability 
	public void setAvailability(Boolean availability)
	{
		this.availability = availability;
	}
	// main with tester
	public static void main(String[] args)
	// Test
	{
		Book test = new Book("John", "Smith", "The Book", 97831614, 12, true);
		System.out.println(test.toString());
	}
}