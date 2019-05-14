import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BookTester {

	@Test
	//toString tester 
	void test() {
		Book myBook = new Book("John", "Smith", "The Book", 97831614, 12, true); 
		Book myBook1 = new Book("Stan", "Jones", "The Great Retreat", 756738, 85, false); 
		String myBookToString = "[Title = The Book, Author = John Smith, ISBN = 97831614, Unique Code = 12, Status = Available]"; 
		String myBookToString1 = "[Title = The Great Retreat, Author = Stan Jones, ISBN = 756738, Unique Code = 85, Status = Not Available]"; 
		assertEquals(myBookToString, myBook.toString());
		assertEquals(myBookToString1, myBook1.toString());
	}

}
