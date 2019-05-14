import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Person
{
	private String username;
	private String password;
	
	public Person(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	// looks up a book with no search parameter
	// displays a list of all books in the library
	public ArrayList<Book> search() throws FileNotFoundException
	{
		Scanner input = new Scanner(new File("Books.csv"));
		ArrayList<Book> books = new ArrayList<Book>();
		
		while(input.hasNextLine())
		{
			String[] currentRow = input.nextLine().split(",");
			
			books.add(new Book(currentRow[0], currentRow[1], currentRow[2], Integer.parseInt(currentRow[3]), Integer.parseInt(currentRow[4]), Boolean.parseBoolean(currentRow[5])));
		}
		
		return books;
	}
	// looks up a book by the title
	public ArrayList<Book> search(String title) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File("Books.csv"));
		ArrayList<Book> matched = new ArrayList<Book>();
		
		while(input.hasNextLine())
		{
			String[] currentRow = input.nextLine().split(",");
			
			if(currentRow[2].equals(title))
			{
				matched.add(new Book(currentRow[0], currentRow[1], currentRow[2], Integer.parseInt(currentRow[3]), Integer.parseInt(currentRow[4]), Boolean.parseBoolean(currentRow[5])));
			}
		}
		
		return matched;
	}
	// looks up a book by the books ISBN
	public ArrayList<Book> search(int ISBN) throws FileNotFoundException
	// It says ISBN on the UML Diagram but you also said that ISBN should be a string???
	// Currently returning the Unique Code and not the ISBN. Fix this?
	{
		Scanner input = new Scanner(new File("Books.csv"));
		ArrayList<Book> matched = new ArrayList<Book>();
		
		while(input.hasNextLine())
		{
			String[] currentRow = input.nextLine().split(",");
			
			if(Integer.parseInt(currentRow[3]) == ISBN)
			{
				matched.add(new Book(currentRow[0], currentRow[1], currentRow[2], Integer.parseInt(currentRow[3]), Integer.parseInt(currentRow[4]), Boolean.parseBoolean(currentRow[5])));
			}
		}
		
		return matched;
	}
	// looks up a book by author's first and last name
	public ArrayList<Book> search(String firstName, String lastName) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File("Books.csv"));
		ArrayList<Book> matched = new ArrayList<Book>();
		
		while(input.hasNextLine())
		{
			String[] currentRow = input.nextLine().split(",");
			
			if(currentRow[0].equals(firstName) && currentRow[1].equals(lastName))
			{
				matched.add(new Book(currentRow[0], currentRow[1], currentRow[2], Integer.parseInt(currentRow[3]), Integer.parseInt(currentRow[4]), Boolean.parseBoolean(currentRow[5])));
			}
		}
		
		return matched;
	}
	

	// getter username
	public String getUsername()
	{
		return username;
	}
	// setter username
	public void setUsername(String username)
	{
		this.username = username;
	}
	// getter for password
	public String getPassword()
	{
		return password;
	}
	// setter for password
	public void setPassword(String password)
	{
		this.password = password;
	}
	// main with testers
	public static void main(String[] args) throws FileNotFoundException
	{
		Person test = new Person("MyUser", "MyPass");
//		System.out.println(test.search("Title1"));
		System.out.println(test.search(2));
//		System.out.println(test.search("Maurice", "Sendak"));
//		System.out.println(test.search());
//		System.out.println(test.search("Where the Wild Things Are"));
	}
}
