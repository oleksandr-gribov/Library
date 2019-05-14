import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PersonTest {

	@Test
	void test() throws FileNotFoundException {
		ArrayList<Book> bookLst = new ArrayList();
		
		Book book = new Book("Don","Sabo","The Magic of Tennis",12345,1,true);
		Book book2 = new Book("FirstName2","LastName2","Title2",22222,2,true);
		Book book3 = new Book("FirstName3","LastName3","Title3",33333,3,true);
		Book book4 = new Book("FirstName4","LastName4","Title4",44444,4,true);
		Book book5 = new Book("FirstName5","LastName5","Title5",55555,5,true);
		Book book6 = new Book("FirstName6","LastName6","Title6",66666,6,true);
		Book book7 = new Book("FirstName7","LastName7","Title7",77777,7,true);
		Book book8 = new Book("FirstName8","LastName8","Title1",88888,8,false);
		Book book9 = new Book("Maurice","Sendak","Where the Wild Things Are",97800,9,true);
		Book book10 = new Book("Joanne","Rowling","Harry Potter and the Philosopher's Stone",978074,10,true);
		Book book11 = new Book("FirstName1","LastName1","The Bible",1234,11,true);
		
		bookLst.add(book);
		bookLst.add(book2);
		bookLst.add(book3);
		bookLst.add(book4);
		bookLst.add(book5);
		bookLst.add(book6);
		bookLst.add(book7);
		bookLst.add(book8);
		bookLst.add(book9);
		bookLst.add(book10);
		bookLst.add(book11);
		Person person = new Person("ajc", "password");
		
		assertEquals(bookLst.toString(), person.search().toString()); //checks search() method		
		ArrayList<Book> bookLst2 = new ArrayList();
		bookLst2.add(book2);
		
		
		assertEquals(bookLst2.toString(), person.search("Title2").toString()); //checks search(String title) method
		
		assertEquals(bookLst2.toString(), person.search("FirstName2","LastName2").toString()); //checks search(String firstName, String lastName) method
		
		assertEquals(bookLst2.toString(), person.search(22222).toString()); //checks search(int isbn) method
		
		
		
		
		
	}

}
