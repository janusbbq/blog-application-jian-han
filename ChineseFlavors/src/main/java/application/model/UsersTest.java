package application.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class UsersTest {
	@Test
	public void getLastName() {
		Users u = new Users("abc");
		String expected = "abc";
		String actual = u.getLastName();

		assertEquals(expected, actual);
	}
}
