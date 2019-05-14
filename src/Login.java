import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Login
{
	private int state = 0;	// (0 is Menu), (1 is Login), (2 is Create Account), (3 is Student Interface), (4 is Student Interface)
	private String username = "";
	private Person user;
	private String role = "";
	
	public void menu() throws FileNotFoundException // overview menu
	{
		Scanner input = new Scanner(System.in);
		String menu = "";
		
		loopOne:
		while(true)
		{
			if(state == 0)	// Menu
			{
				loopTwo:
				while(true)
				{
					System.out.println("--{Main Menu}--"); 
					System.out.println("Enter [x] to Exit");
					System.out.println("[1] Login");
					System.out.println("[2] Create New Student Account");
					System.out.print("Input : ");
					menu = input.next();
					
					if(menu.equals("1")) // if user presses 1, it addresses user to the login page
					{
						System.out.println("\n===Account Login===\n");
						state = 1;
						break loopTwo;
					}
					else if(menu.equals("2")) // if user presses 2, it addresses user to create a new student account
					{
						System.out.println("\n===Creating New Student Account===\n");
						state = 2;
						break loopTwo;
					}
					else if(menu.equals("x")) // press x to exit
					{
						state = 5;
						break;
					}
					else
					{
						System.out.println("\n<Must Enter [x] or [1] or [2]>\n"); break;
					}
				}
			}
			else if(state == 1) // mentioned above
			{
				login();
			}
			else if(state == 2) // mentioned above
			{
				newAccount();
			}
			else if(state == 3)	// Student Login Success
			{
				System.out.println("\n===Student Account " + user.getUsername() + "===\n");
				studentAccount();
				break;
			}
			else if(state == 4)	// Librarian Login Success
			{
				System.out.println("\n===Librarian Account " + user.getUsername() + "===\n");
				librarianAccount();
			}
			else
			{
				System.out.println("\n***Program Terminated***");
				break loopOne;
			}
		}
	}
	
	public void login() throws FileNotFoundException // logging in using the giving usernames and passwords
	{
		Scanner accounts = new Scanner(new File("People.csv"));
		Scanner input = new Scanner(System.in);
		Boolean valid = false;
		String password = "";
		String[] information = null;
		
		System.out.println("--{Login Menu}--");
		System.out.println("Enter [x] to Exit");
		System.out.println("Enter [z] to Go Back");
		System.out.print("Enter Username : ");
		username = input.next();
		if(username.equals("z")) // pressing z to go back
		{
			System.out.println("\n===Exiting Account Login===\n");
			username = "";
			state = 0;	// Switch to menu state
		}
		if(username.equals("x")) // pressing x to exit the program
		{
			username = "";
			state = 5;	// Terminate
		}
		
		if(!username.equals("")) // username being a string
		{
			System.out.print("Enter Password : ");
			password = input.next();
			if(password.equals("x"))
			{
				password = "";
				state = 5;	// Terminate
			}
			if(password.equals("z")) // z to exit account login
			{
				System.out.println("\n===Exiting Account Login===\n");
				password = "";
				state = 0;	// Switch to menu state
			}
			
			if(!password.equals("")) // password being a string
			{
				while(accounts.hasNextLine())
				{
					information = accounts.nextLine().split(",");
					role = information[0];
					if(username.equals(information[1]) && password.equals(information[2]))	// Valid login, boolean = true, break
					{
						valid = true;
						break;
					}
					else	// Invalid login, boolean = false
					{
						valid = false;
					}
				}
				if(valid == false) // if input invalid information, it will print out this statement
				{
					System.out.println("\n===Couldn't Find Your Account===\n");
				}
				else
				{
					if(role.equals("librarian"))	// If role is librarian
					{
						user = new Librarian(information[1], information[2]);
						state = 4;	// Switch to librarian state
					}
					else	// Else
					{
						user = new Student(information[1], information[2], Integer.parseInt(information[3]));
						state = 3;	// Switch to student state
					}
				}
			}
		}
	}
	
	public void newAccount() throws FileNotFoundException // when creating a new account 
	{
		Scanner file = new Scanner(new File("People.csv"));
		Scanner input = new Scanner(System.in);
		ArrayList<String> usernames = new ArrayList<>();
		String oldInformation = "";
		String password = "";
		String confirm = "";
		
		while(file.hasNextLine()) // takes in the users input to make a new account
		{
			String currentLine = file.nextLine();
			String[] information = currentLine.split(",");
			
			oldInformation += currentLine + "\n";
			usernames.add(information[1]);
		}
		
		loopOne:
		while(true)
		{
			while(true)
			{
				System.out.println("--{Account Creation Menu}--"); // it allows user to make a new username and password
				System.out.println("Enter [x] to Exit");
				System.out.println("Enter [z] to Go Back");
				System.out.print("Enter New Username : ");
				username = input.next();
				if(username.equals("x"))
				{
					state = 5;	// Terminate
					username = "";
					break loopOne;
				}
				if(username.equals("z")) // exiting the creating phase
				{
					System.out.println("\n===Exiting Account Creation===\n");
					state = 0;	// Switch to menu state
					username = "";
					break loopOne;
				}
				if(usernames.contains(username))
				{
					System.out.println("\n<Username Has Been Taken>\n");
				}
				else
				{
					break;
				}
			}
			System.out.print("Enter New Password : "); // new password
			password = input.next();
			if(password.equals("x"))
			{
				state = 5;	// Terminate
				username = "";
				break loopOne;
			}
			else if(password.equals("z")) // exiting 
			{
				System.out.println("\n===Exiting Account Creation===\n");
				state = 0;	// Switch to menu state
				username = "";
				break loopOne;
			}
			else
			{
				while(true)
				{
					System.out.print("Confirm [y] [n] : "); // yes or no if you want to create the account
					confirm = input.next();
					if(confirm.equals("y"))
					{
						System.out.println("\n===Created New Account===\n");
						state = 0;	// Switch to menu state
						break loopOne;
					}
					else if(!confirm.equals("n"))
					{
						System.out.println("\n<Must Enter [y] or [n]>\n");
					}
					else
					{
						System.out.println("\n===Cancelled===\n");
						break;
					}
				}
			}
		}
		
		if(!username.equals(""))
		{
			try
			{
				BufferedWriter textWriter = new BufferedWriter(new FileWriter(new File("People.csv")));
				textWriter.write(oldInformation);
				textWriter.write("student," + username + "," + password + "," + 0);
				textWriter.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void studentAccount() throws FileNotFoundException // the student account
	{
		Scanner books = new Scanner(new File("Books.csv"));
		Scanner input = new Scanner(System.in);
		ArrayList <String> search = new ArrayList<>();
		String key = "";
		
		while(true) 
		{
			System.out.println("--{Student Menu}--"); // all the possible options for the student account
			System.out.println("Enter [x] to Exit");
			System.out.println("[1] Search Book");
			System.out.println("[2] Check Out Book");
			System.out.println("[3] Return Book");
			System.out.println("[4] Pay Dues");
			System.out.println("Input : ");
			key = input.next();
			Transaction transaction1 = new Transaction();
			transaction1.setCheckOutStatus(false);
			Scanner t = new Scanner(new File("Transaction.csv"));
			while(t.hasNextLine())
			{
				String[] info = t.nextLine().split(",");
				if(info[1].equals(user.getUsername()))
				{
					transaction1.setTransactionCode(Integer.parseInt(info[0]));
					transaction1.setTransactionDate(info[2]);
					transaction1.setDueDate(info[3]);
				}
			}
			
			Scanner b = new Scanner(new File("Books.csv"));
			while(b.hasNextLine())
			{
				String[] info = b.nextLine().split(",");
				if(Integer.parseInt(info[6]) == transaction1.getTransactionCode())
				{
					transaction1.addBookToCart(new Book(info[0], 
														info[1], 
														info[2], 
														Integer.parseInt(info[3]), 
														Integer.parseInt(info[4]), 
														true));
				}
			}
			
			if(key.equals("1"))
			{
				System.out.println("\n===Book Search===\n");
				bookSearch();
			}
			else if(key.equals("2"))
			{
				ArrayList<Book> addBook = new ArrayList<>();
				
				System.out.println("\n===Check Out Book===\n");
				String key1 = "";
				while(true)
				{
					books = new Scanner(new File("Books.csv"));
					System.out.println("--{Check Out Menu}--");
					System.out.println("Enter [z] to Go Back");
					System.out.println("[1] Add Book To Cart");
					System.out.println("[2] Remove Book From Cart");
					System.out.println("[3] Display Cart");
					System.out.println("[4] CheckOut");
					System.out.println("Input : ");
					key1 = input.next();
					if(key1.equals("1"))
					{
						System.out.println("Enter the book unique ID: ");
						String value = input.next();
						
						while(books.hasNextLine())
						{
							String[] information = books.nextLine().split(",");
							if(Integer.parseInt(information[4]) == Integer.parseInt(value))
							{
								transaction1.addBookToCart(new Book(information[0], information[1], information[2], Integer.parseInt(information[3]), Integer.parseInt(information[4]), Boolean.parseBoolean(information[5])));
							}
						}
					}
					else if(key1.equals("2"))
					{
						System.out.println("Enter the book unique ID: ");
						String value = input.next();
						
						ArrayList<Book> currentCart = new ArrayList<>();
						for(Book a : transaction1.getBooksInCart())
						{
							currentCart.add(a);
						}
						
						for(int a = 0; a < currentCart.size(); a++)
						{
							if(currentCart.get(a).getUniqueCode() == Integer.parseInt(value))
							{
								transaction1.removeBookFromCart(currentCart.get(a));
							}
						}
					}
					else if(key1.equals("3"))
					{
						transaction1.displayCart();
					}
					else if(key1.equals("4"))
					{
						Scanner transactions = new Scanner(new File("Transaction.csv"));
						transaction1.updateBookStatus();
						transaction1.updateCheckOutStatus();
						
						String oldInformation = "";
						
						while(transactions.hasNextLine())
						{
							oldInformation += transactions.nextLine() + "\n";
						}
						try
						{
							BufferedWriter textWriter = new BufferedWriter(new FileWriter(new File("Transaction.csv")));
							textWriter.write(oldInformation);
							textWriter.write(transaction1.getTransactionCode() + "," + username + "," + transaction1.getTransactionDate() + "," + transaction1.getDueDate());
							textWriter.close();
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}
					else if(key1.equals("z"))
					{
						break;
					}
				}
			}
			else if(key.equals("3"))
			{
				transaction1.returnBooks();
			}
			else if(key.equals("4"))
			{
				((Student)user).payBalance();
				System.out.println("\nThank you for paying your dues!\n");
			}
			else if(key.equals("x"))
			{
				System.out.println("\n===Logging Out===\n");
				state = 0;	// Switch to menu state
				break;
			}
			else
			{
				System.out.println("\n<Must Enter [x] or [1] or [2] or [3] or [4]>\n");
			}
		}
	}
	
		
	public void librarianAccount() throws FileNotFoundException // the librarian account
	{
		Scanner input = new Scanner(System.in);
		ArrayList<String> information = new ArrayList<>();
		String inputKey = "";
		
		loopOne:
		while(true)
		{
			System.out.println("--{Librarian Menu}--");
			System.out.println("Enter [x] to Exit");
			System.out.println("Enter [z] to Logout");
			System.out.println("[1] Book Search");
			System.out.println("[2] Add Book");
			System.out.println("[3] Remove Book");
			System.out.println("[4] Replace Book");
			System.out.println("[5] Check Out");
			System.out.print("Input : ");
			inputKey = input.next();
			
			if(inputKey.equals("1"))
			{
				System.out.println("\n===Book Search===\n");
				bookSearch();
			}
			else if(inputKey.equals("2"))
			{
				Scanner books = new Scanner(new File("Books.csv"));
				System.out.println("\n===Book Add===\n");
				System.out.println("--{Add Book}--");
				System.out.println("Enter [z] to Go Back");
				System.out.print("Author Firstname : ");
				String fName = input.next();
				if(fName.equals("z"))
				{
					System.out.println("\n===Exiting Add Book===\n");
					continue;
				}
				System.out.print("Author Lastname : ");
				String lName = input.next();
				if(lName.equals("z"))
				{
					System.out.println("\n===Exiting Add Book===\n");
					continue;
				}
				System.out.print("Book Title : ");
				String title = "";
				while(true)
				{
					title = input.nextLine();
					if(title.equals("z"))
					{
						System.out.println("\n===Exiting Add Book===\n");
						continue loopOne;
					}
					if(!title.isEmpty())
					{
						break;
					}
				}
				String ISBN = "";
				while(true)
				{
					System.out.print("Book ISBN : ");
					ISBN = input.next();
					
					if(ISBN.equals("z"))
					{
						System.out.println("\n===Exiting Add Book===\n");
						continue loopOne;
					}
					
					try
					{
						Integer.parseInt(ISBN);
						break;
					}
					catch(NumberFormatException nfe)
					{
						System.out.println("\n<Must Enter Number>\n");
					}
				}
				int counter = 0;
				String oldInformation = "";
				while(books.hasNextLine())
				{
					counter++;
					String currentLine = books.nextLine();
					oldInformation += currentLine + "\n";
				}
				
				try
				{
					BufferedWriter textWriter = new BufferedWriter(new FileWriter(new File("Books.csv")));
					textWriter.write(oldInformation);
					textWriter.write(fName + "," + lName + "," + title + "," + ISBN + "," + (counter+1) + "," + true + "," + 0);
					textWriter.close();
					System.out.println("\n===Book Added===\n");
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			else if(inputKey.equals("3"))
			{
				Scanner books = new Scanner(new File("Books.csv"));
				String code = "";
				System.out.println("\n===Book Remove===\n");
				
				while(true)
				{
					System.out.println("--{Remove Book}--");
					System.out.println("Enter [z] to Go Back");
					System.out.print("Enter Book Unique Code To Remove : ");
					code = input.next();
					
					if(code.equals("z"))
					{
						System.out.println("\n===Exiting Remove Book===\n");
						code = "";
						break;
					}
					
					try
					{
						Integer.parseInt(code);
						break;
					}
					catch(NumberFormatException nfe)
					{
						System.out.println("\n<Must Enter Number>\n");
					}
				}
				
				if(!code.isEmpty())
				{
					int counter = 0;
					String oldInformation = "";
					while(books.hasNextLine())
					{
						String[] bookInformation = books.nextLine().split(",");
						
						if(!bookInformation[4].equals(code))
						{
							counter++;
							oldInformation +=	bookInformation[0] + "," + 
												bookInformation[1] + "," + 
												bookInformation[2] + "," + 
												bookInformation[3] + "," + 
												counter + "," +
												bookInformation[5] + "," +
												bookInformation[6] + "\n";
						}
					}
					
					try
					{
						BufferedWriter textWriter = new BufferedWriter(new FileWriter(new File("Books.csv")));
						textWriter.write(oldInformation);
						textWriter.close();
						System.out.println("\n===Book Removed===\n");
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
			else if(inputKey.equals("4"))
			{
				Scanner books = new Scanner(new File("Books.csv"));
				String code = "";
				System.out.println("\n===Book Replace===\n");
				
				while(true)
				{
					System.out.println("--{Replace Book}--");
					System.out.println("Enter [z] to Go Back");
					System.out.print("Enter Book Unique Code To Replace : ");
					code = input.next();
					
					if(code.equals("z"))
					{
						System.out.println("\n===Exiting Replace Book===\n");
						code = "";
						break;
					}
					
					try
					{
						Integer.parseInt(code);
						break;
					}
					catch(NumberFormatException nfe)
					{
						System.out.println("\n<Must Enter Number>\n");
					}
				}
				
				if(!code.isEmpty())
				{
					System.out.println("\nEnter Book to Replace With");
					System.out.print("Author Firstname : ");
					String fName = input.next();
					if(fName.equals("z"))
					{
						System.out.println("\n===Exiting Replace Book===\n");
						continue;
					}
					System.out.print("Author Lastname : ");
					String lName = input.next();
					if(lName.equals("z"))
					{
						System.out.println("\n===Exiting Replace Book===\n");
						continue;
					}
					System.out.print("Book Title : ");
					String title = "";
					while(true)
					{
						title = input.nextLine();
						if(title.equals("z"))
						{
							System.out.println("\n===Exiting Replace Book===\n");
							continue;
						}
						if(!title.isEmpty())
						{
							break;
						}
					}
					String ISBN = "";
					while(true)
					{
						System.out.print("Book ISBN : ");
						ISBN = input.next();
						if(ISBN.equals("z"))
						{
							System.out.println("\n===Exiting Replace Book===\n");
							ISBN = "";
							break;
						}
						try
						{
							Integer.parseInt(ISBN);
							break;
						}
						catch(NumberFormatException nfe)
						{
							System.out.println("\n<Must Enter Number>\n");
						}
					}
					
					if(!ISBN.isEmpty())
					{
						int counter = 0;
						String oldInformation = "";
						while(books.hasNextLine())
						{
							counter++;
							String[] bookInformation = books.nextLine().split(",");
							
							if(!bookInformation[4].equals(code))
							{
								oldInformation +=	bookInformation[0] + "," + 
													bookInformation[1] + "," + 
													bookInformation[2] + "," + 
													bookInformation[3] + "," + 
													counter + "," + 
													bookInformation[5] + "," + 
													bookInformation[6] + "\n";
							}
							else
							{
								oldInformation +=	fName + "," + lName + "," + title + "," + ISBN + "," + counter + "," + true + "," + 0 + "\n";
							}
						}
						
						try
						{
							BufferedWriter textWriter = new BufferedWriter(new FileWriter(new File("Books.csv")));
							textWriter.write(oldInformation);
							textWriter.close();
							System.out.println("\n===Book Replaced===\n");
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}
				}
			}
			
			else if(inputKey.equals("x"))
			{
				state = 5;	// Terminate
				break;
			}
			else if(inputKey.equals("z"))
			{
				System.out.println("\n===Logging Out===\n");
				state = 0;	// Switch to menu state
				break;
			}
			else
			{
				System.out.println("\n<Must Enter [x] or [z] or [1] or [2] or [3] or [4]>\n");
			}
		}
	}
	
	public void bookSearch() throws FileNotFoundException // searching for a book by the student and librarian
	{
		Scanner input = new Scanner(System.in);
		String inputKey = "";
		
		while(true)
		{
			System.out.println("--{Book Search Menu}--");
			System.out.println("Enter [x] to Exit");
			System.out.println("Enter [z] to Go Back");
			System.out.println("[1] List all Books");
			System.out.println("[2] Search by Title");
			System.out.println("[3] Search by Author");
			System.out.println("[4] Search by ISBN");
			System.out.print("Input : ");
			inputKey = input.nextLine();
			
			if(inputKey.equals("1"))
			{
				Scanner books = new Scanner(new File("Books.csv"));
				System.out.println("\n===List all Books===\n");
				for(Book a : user.search())
				{
					String[] bookInfo = books.nextLine().split(",");
					System.out.println(a + " Transaction Code = " + bookInfo[6]);
				}
				System.out.println();
			}
			else if(inputKey.equals("2"))
			{
				Scanner books = new Scanner(new File("Books.csv"));
				System.out.println("\n===Search by Title===\n");
				System.out.println("--{Title Search}--");
				System.out.print("Title : ");
				String title = input.nextLine();
				
				if(user.search(title).isEmpty())
				{
					System.out.println("\n===No Books Found===\n");
				}
				else
				{
					System.out.println();
					for(Book a : user.search(title))
					{
						String[] bookInfo = books.nextLine().split(",");
						System.out.println(a + " Transaction Code = " + bookInfo[6]);
					}
					System.out.println();
				}
				inputKey = null;
			}
			else if(inputKey.equals("3"))
			{
				Scanner books = new Scanner(new File("Books.csv"));
				String firstName = "";
				String lastName = "";
				System.out.println("\n===Search by Author===\n");
				System.out.println("--{Author Search}--");
				System.out.print("Firstname : ");
				firstName = input.nextLine();
				System.out.print("Lastname  : ");
				lastName = input.nextLine();
				
				if(user.search(firstName, lastName).isEmpty())
				{
					System.out.println("\n===No Books Found===\n");
				}
				else
				{
					System.out.println();
					for(Book a : user.search(firstName, lastName))
					{
						String[] bookInfo = books.nextLine().split(",");
						System.out.println(a + " Transaction Code = " + bookInfo[6]);
					}
					System.out.println();
				}
			}
			else if(inputKey.equals("4"))
			{
				Scanner books = new Scanner(new File("Books.csv"));
				String ISBN = "";
				System.out.println("\n===Search by ISBN===\n");
				System.out.println("--{ISBN Search}--");
				System.out.print("ISBN : ");
				ISBN = input.nextLine();
				
				try
				{
					if(user.search(Integer.parseInt(ISBN)).isEmpty())
					{
						System.out.println("\n===No Books Found===\n");
					}
					else
					{
						System.out.println();
						for(Book a : user.search(Integer.parseInt(ISBN)))
						{
							String[] bookInfo = books.nextLine().split(",");
							System.out.println(a + " Transaction Code = " + bookInfo[6]);
						}
						System.out.println();
					}
				}
				catch(NumberFormatException nfe)
				{
					System.out.println("\n<Must Enter Number>\n");
				}
			}
			else if(inputKey.equals("x"))
			{
				state = 5;	// Terminate
				break;
			}
			else if(inputKey.equals("z"))
			{
				System.out.println("\n===Exiting Book Search===\n");
				state = 0;	// Switch to menu state
				break;
			}
			else
			{
				System.out.println("\n<Must Enter [x] or [z] or [1] or [2] or [3] or [4]>\n");
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException // driver function / testing 
	{
		Login test = new Login();
		test.menu();
	}
}
