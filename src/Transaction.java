
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Transaction
{
	private int transactionCode;
	public int getTransactionCode() {
		return transactionCode;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public String getDueDate() {
		return dueDate;
	}
	private String transactionDate;	// Need to somehow incorporate the actual time?
	private String returnDate;
	private String dueDate;
	private boolean checkOutStatus; // If false, then user is still choosing. set it to true once he is done
							   // to avoid any changes made after the checkout
	
	private ArrayList<Book> booksInCart = new ArrayList<>(); 
	public ArrayList<Book> getBooksInCart() {
		return booksInCart;
	}

	// constructor
	public Transaction() throws FileNotFoundException
	{	
		int counter = 0;
		Scanner transactions = new Scanner(new File("Transaction.csv"));
		while(transactions.hasNextLine())
		{
			String[] info = transactions.nextLine().split(",");
			if(!transactions.hasNextLine())
			{
				counter = Integer.parseInt((info[0] + 1));
			}
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		this.transactionCode = counter+1;
		c.setTime(date);
		this.transactionDate = sdf.format(c.getTime()); 
		c.add(Calendar.DATE, 21);
		this.dueDate = sdf.format(c.getTime());  // current date + 3 weeks
		
		this.checkOutStatus = false;
	}
	
	// aggregate a list of books
	// check if book is overdue 
	// display a receipt after checking out 
	// update the availability of the book
	// remove a book
	
	// adds a book to the cart
	public void addBookToCart (Book aBook) {
		if (!checkOutStatus) {
			if (aBook.getAvailability() == true) {
				booksInCart.add(aBook); 
			}
		}
	}
	// removes a book from the cart
	public void removeBookFromCart (Book aBook) {
		if (booksInCart.isEmpty()) {
			System.out.println("Your cart is currently empty");
		}
		if (!checkOutStatus) {
			booksInCart.remove(aBook); 
		}
	}
	// displays all the books in cart
	public void displayCart() {
		if (booksInCart.isEmpty()) {
			System.out.println("Your cart is currently empty");
		}else { 
			for (int i=0; i<booksInCart.size(); i++) {
				System.out.println(booksInCart.get(i).toString()); 
			}
		}	
	}
	// updates the status of the checkout so no more changes can be made after the checkout has been complete
	// true if the checkout is complete
	public void updateCheckOutStatus () {
		if (checkOutStatus == false) {
			this.checkOutStatus = true; 
		}
	}
	// updates the availability of the books
	public void updateBookStatus () throws FileNotFoundException {
//		booksInCart
		Scanner books = new Scanner(new File("Books.csv"));
		String updatedInformation = "";
		
		while(books.hasNextLine())
		{
			Boolean contains = false;
			String[] currentBook = books.nextLine().split(",");
			Book myBook = new Book(	currentBook[0], 
									currentBook[1], 
									currentBook[2], 
									Integer.parseInt(currentBook[3]), 
									Integer.parseInt(currentBook[4]), 
									Boolean.parseBoolean(currentBook[5]));
			for(Book a : booksInCart)
			{
				if(a.getUniqueCode() == myBook.getUniqueCode())
				{
					contains = true;
					break;
				}
				else
				{
					contains = false;
				}
			}
			if(contains)
			{
				updatedInformation +=	currentBook[0] + "," +
										currentBook[1] + "," +
										currentBook[2] + "," +
										currentBook[3] + "," +
										currentBook[4] + "," +
										false + "," +
										transactionCode + "\n";
			}
			else
			{
				updatedInformation +=	currentBook[0] + "," +
										currentBook[1] + "," +
										currentBook[2] + "," +
										currentBook[3] + "," +
										currentBook[4] + "," +
										currentBook[5] + "," +
										currentBook[6] + "\n";
			}
		}
		try
		{
			BufferedWriter textWriter = new BufferedWriter(new FileWriter(new File("Books.csv")));
			textWriter.write(updatedInformation);
			textWriter.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void returnBooks() throws FileNotFoundException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		returnDate = sdf.format(c.getTime());
		
		Scanner books = new Scanner(new File("Books.csv"));
		String updatedInformation = "";
		
		while(books.hasNextLine())
		{
			Boolean contains = false;
			String[] currentBook = books.nextLine().split(",");
			Book myBook = new Book(	currentBook[0], 
									currentBook[1], 
									currentBook[2], 
									Integer.parseInt(currentBook[3]), 
									Integer.parseInt(currentBook[4]), 
									Boolean.parseBoolean(currentBook[5]));
			for(Book a : booksInCart)
			{
				if(a.getUniqueCode() == myBook.getUniqueCode())
				{
					contains = true;
					break;
				}
				else
				{
					contains = false;
				}
			}
			if(contains)
			{
				updatedInformation +=	currentBook[0] + "," +
										currentBook[1] + "," +
										currentBook[2] + "," +
										currentBook[3] + "," +
										currentBook[4] + "," +
										true + "," +
										0 + "\n";
			}
			else
			{
				updatedInformation +=	currentBook[0] + "," +
										currentBook[1] + "," +
										currentBook[2] + "," +
										currentBook[3] + "," +
										currentBook[4] + "," +
										currentBook[5] + "," +
										currentBook[6] + "\n";
			}
		}
		
		Scanner transactions = new Scanner(new File("Transaction.csv"));
		String deleteInfo = "";
		
		while(transactions.hasNextLine())
		{
			String currentRow = transactions.nextLine();
			String[] info = currentRow.split(",");
			if(Integer.parseInt(info[0]) != this.getTransactionCode())
			{
				deleteInfo += currentRow + "\n";
			}
		}
		
		try
		{
			BufferedWriter textWriter = new BufferedWriter(new FileWriter(new File("Books.csv")));
			textWriter.write(updatedInformation);
			textWriter.close();
			
			BufferedWriter textWriter2 = new BufferedWriter(new FileWriter(new File("Transaction.csv")));
			textWriter2.write(deleteInfo);
			textWriter2.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isCheckOutStatus() {
		return checkOutStatus;
	}

	public void setCheckOutStatus(boolean checkOutStatus) {
		this.checkOutStatus = checkOutStatus;
	}

	public void setTransactionCode(int transactionCode) {
		this.transactionCode = transactionCode;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public void setBooksInCart(ArrayList<Book> booksInCart) {
		this.booksInCart = booksInCart;
	}

	// add the transaction ID to the Books.csv
	public void addTransactionID () {
		for (int i=0; i<booksInCart.size(); i++) {
			
		}
	}
	// checks if the book being returned is late 
	public boolean isOverdue () throws ParseException {
		String[] returnDay = returnDate.split("/");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		Date date = sdf.parse(returnDate);
		c.setTime(date);
		
		Calendar c1 = Calendar.getInstance();
		Date date1 = sdf.parse(dueDate);
		c1.setTime(date1);
		if(c.compareTo(c1) > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	// prints the 'receipt' of the transaction
	public void displayTransaction() {
		if(checkOutStatus) {
			System.out.println(toString());
		}
	}
	public String toString()	// Technically is displayTransaction
	{
		return "[" + "Code = " + transactionCode + ", Checkout Date = " + transactionDate + 
				", Return Date = " + returnDate + ", Due Date = " + dueDate + 
				", Status = " + checkOutStatus + "]";
	}
	public static void main (String[] args) throws FileNotFoundException, ParseException {
		Transaction test = new Transaction();
		test.addBookToCart(new Book("FirstName2", "LastName2", "Title2", 22222, 2, true));
		test.addBookToCart(new Book("FirstName3", "LastName3", "Title3", 33333, 3, true));
//		test.updateBookStatus();
//		System.out.println(test.transactionCode);
//		Transaction test2 = new Transaction();
//		System.out.println(test2.transactionCode);
		
//		test.returnBooks();
//		test.dueDate = "04/26/2018";
//		System.out.println(test.isOverdue());
	}
}

