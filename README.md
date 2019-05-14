# Library
Final project for CSCI C-212, Introduction to Software Systems class. 
This system is designed to implement an online registrar for the library. Using CSV files to store and read information. Simulates 
the actions of a regular library. e.g. checking out books, returning books, outstanding fees and charges, etc. Uses a command line tool to 
navigate 

# What I learned 
- Data storage and retrieval
- Parsing CSV files 
- OOP concepts: inheritance, polymorphism 
- OOP design pattern
- JUnit testing


 
# Instructions to run
Before running the program, first make sure that all the files are in the same folder as your project. This is particularly important when it comes to the CSV files containing the underlying database. 

You will notice that there are three CSV files: Books.csv, Transactions.csv and People.csv. These are the files that contain all the information on the books in the library, the people involved (such as the librarians and the customers, further referred to as Students), and the transactions that take place. 

To run the program, select the Login.java file and run it. From there, you will be guided by a series of text fields, displayed to you through the console. You will be able to navigate through the program by following the prompts with the keys displayed. 

Test cases are included in the tests package. It includes the tests of individual functions, separated by classes. They are all JUnit tests, so executing them is as simple as running them from your preferred Java IDE.
