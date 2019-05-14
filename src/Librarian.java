import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Librarian extends Person
{
	public Librarian(String username, String password)
	{
		// super constructor of the Person 
		super(username, password);
	}
	// adds a book to the library
	public void addBook(Book newBook) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File("Books.csv"));
		String oldInformation = "";
		String information =	newBook.getAuthorFirstName()+","+
								newBook.getAuthorLastName()+","+
								newBook.getTitle()+","+
								newBook.getIsbnCode()+","+
								newBook.getUniqueCode()+","+
								newBook.getAvailability();
		
		while(input.hasNextLine())
		{
			oldInformation += input.nextLine() + "\n";
		}
		
		try
		{
			BufferedWriter textWriter = new BufferedWriter(new FileWriter(new File("Books.csv")));
			textWriter.write(oldInformation);
			textWriter.write(information);
			textWriter.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	// updates book info by creating a new book object and replacing the old one with it
	public void updateBook(Book targetBook, Book updatedBook) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File("Books.csv"));
		String oldInformation = "";
		String oldBook =		targetBook.getAuthorFirstName()+","+
								targetBook.getAuthorLastName()+","+
								targetBook.getTitle()+","+
								targetBook.getIsbnCode()+","+
								targetBook.getUniqueCode()+","+
								targetBook.getAvailability();
		String newBook =		updatedBook.getAuthorFirstName()+","+
								updatedBook.getAuthorLastName()+","+
								updatedBook.getTitle()+","+
								updatedBook.getIsbnCode()+","+
								updatedBook.getUniqueCode()+","+
								updatedBook.getAvailability(); 
		
		while(input.hasNextLine())
		{
			String currentLine = input.nextLine();
			
			if(currentLine.equals(oldBook))
			{
				oldInformation += newBook + "\n";
			}
			else
			{
				oldInformation += currentLine + "\n";
			}
		}
		
		try
		{
			BufferedWriter textWriter = new BufferedWriter(new FileWriter(new File("Books.csv")));
			textWriter.write(oldInformation);
			textWriter.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	// removes the book from the library
	public void deleteBook(Book newBook) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File("Books.csv"));
		String oldInformation = "";
		String information =	newBook.getAuthorFirstName()+","+
								newBook.getAuthorLastName()+","+
								newBook.getTitle()+","+
								newBook.getIsbnCode()+","+
								newBook.getUniqueCode()+","+
								newBook.getAvailability();
		
		while(input.hasNextLine())
		{
			String currentLine = input.nextLine();
			
			if(!currentLine.equals(information))
			{
				oldInformation += currentLine;
				if(input.hasNextLine())
				{
					oldInformation += "\n";	// Annoying bug where it will delete the line but still leave behind an empty row
				}
			}
		}
		
		try
		{
			BufferedWriter textWriter = new BufferedWriter(new FileWriter(new File("Books.csv")));
			textWriter.write(oldInformation);
			textWriter.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	// main for testing
	public static void main(String[] args) throws FileNotFoundException
	{
		Librarian test = new Librarian("MyUser", "MyPass");
		
//		test.addBook(new Book("John", "Smith", "The Book", 97831614, 12, true));
//		test.deleteBook(new Book("John", "Smith", "The Book", 97831614, 12, true));
//		test.updateBook(new Book("John", "Smith", "The Book", 97831614, 12, true), new Book("John", "Smith", "Fake Book", 97831614, 12, true));
	}
}
