/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package poepart1;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author RC_Student_lab
 */
public class RegisterationNGTest {
    
    public RegisterationNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of checkUserName method, of class Registeration.
     */
    @Test
    public void testCheckUserName() {
        System.out.println("checkUserName");
        String username = "kyl_1";
        Registeration instance = new Registeration();
        boolean expResult = true;
        boolean result = instance.checkUserName(username);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of checkPasswordComplexity method, of class Registeration.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        String password = "Ch&&sec@ke99!";
        Registeration instance = new Registeration();
        boolean expResult = true;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of checkCellPhoneNumber method, of class Registeration.
     */
    @Test
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        String cellphone = "+27838968976";
        Registeration instance = new Registeration();
        boolean expResult = true;
        boolean result = instance.checkCellPhoneNumber(cellphone);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of registerUser method, of class Registeration.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        Registeration instance = new Registeration();
        instance.registerUser();
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of loginUser method, of class Registeration.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        Registeration instance = new Registeration();
        instance.loginUser();
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
