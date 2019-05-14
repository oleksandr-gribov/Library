

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class TransactionTester {

	@Test
	// display transaction tester
	void test() throws FileNotFoundException {
		Transaction test = new Transaction();
		test.addBookToCart(new Book("FirstName2", "LastName2", "Title2", 22222, 2, true));
		test.addBookToCart(new Book("FirstName3", "LastName3", "Title3", 33333, 3, true));
		String displayString = "[Code = 1, Checkout Date = 04/27/2018, Return Date = null, Due Date = 05/18/2018, Status = false]"; 
		assertEquals(displayString, test.toString());
	}

}
