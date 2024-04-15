/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package poe;

import org.junit.Test;
import java.util.Scanner;
import static org.junit.Assert.*;

public class LoginTest {

    @Test
    public void testCheckUserNameCorrectFormat() {
        assertTrue(Login.checkUserName(new Scanner("kyl_1")));
    }

    @Test
    public void testCheckUserNameIncorrectFormat() {
        assertFalse(Login.checkUserName(new Scanner("kyle!!!!!!!")));
    }

    @Test
    public void testCheckPasswordComplexityValid() {
        assertTrue(Login.checkPasswordComplexity(new Scanner("Ch&&sec@ke99!")));
    }

    @Test
    public void testCheckPasswordComplexityInvalid() {
        assertFalse(Login.checkPasswordComplexity(new Scanner("password*")));
    }

      @Test
    public void testLoginSuccessful() {
        // Assuming the username and password match the stored values
        Login.setFname("John");
        Login.setLname("Doe");
        assertTrue(Login.returnLoginStatus("username", "password"));
    }

    @Test
    public void testLoginFailed() {
        // Assuming the username and password do not match the stored values
        Login.setFname("John");
        Login.setLname("Doe");
        assertFalse(Login.returnLoginStatus("incorrect_username", "incorrect_password"));
    }
}

