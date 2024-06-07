package poe;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    @Before
    public void setup() {
        // Reset the Login class before each test
        Login.setFname(null);
        Login.setLname(null);
        Login.setUserName(null);
        Login.setPassword(null);
        Login.setUserName2(null);
        Login.setPassword2(null);
    }

    @Test
    public void testCheckUserNameCorrectFormat() {
        assertTrue(Login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserNameIncorrectFormat() {
        assertFalse(Login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testCheckPasswordComplexityValid() {
        assertTrue(Login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexityInvalid() {
        assertFalse(Login.checkPasswordComplexity("password*"));
    }

    @Test
    public void testLoginSuccessful() {
        // Simulate successful login
        Login.setFname("John");
        Login.setLname("Doe");
        Login.setUserName("username");
        Login.setPassword("password");

        assertTrue(Login.returnLoginStatus("username", "password"));
    }

    @Test
    public void testLoginFailed() {
        // Simulate failed login
        Login.setFname("John");
        Login.setLname("Doe");
        Login.setUserName("username");
        Login.setPassword("password");

        assertFalse(Login.returnLoginStatus("incorrect_username", "incorrect_password"));
    }
}

 






