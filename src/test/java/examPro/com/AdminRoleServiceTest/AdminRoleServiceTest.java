package examPro.com.AdminRoleServiceTest;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import examPro.com.services.AdminRoleService;

public class AdminRoleServiceTest {

	AdminRoleService adminRoleService = new AdminRoleService();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createNewUserTest() {
		boolean result = adminRoleService.createNewUser("mdkashem88@gmail.com", "password", "Md", "Kashem", "11/2/1989",
				"333-333-3333", new Date(System.currentTimeMillis()));
		assertEquals(false, result); // return false if the user already exist in the database 
	}

}
