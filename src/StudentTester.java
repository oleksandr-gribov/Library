

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;  



import junit.framework.Assert;

class StudentTester {

	@Test
	// check balance tester
	void checkBalance() {
		Student myStudent = new Student("gorgi", "password", 0); 
		Student myStudent1 = new Student("Patrick", "code", 20); 
		StudentTester myTester = new StudentTester(); 
		assertEquals(0, myStudent.checkBalance());
		assertEquals(20, myStudent1.checkBalance());
	}
	
}
