/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package poepart2;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author RC_Student_Lab
 */
public class RegistrationNGTest {
    
    public RegistrationNGTest() {
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
     * Test of userNameCheck method, of class Registration.
     */
    @Test
    public void testUserNameCheck() {
        System.out.println("userNameCheck");
        String username = "kyl_1";
        Registration instance = new Registration();
        boolean expResult = true;
        boolean result = instance.userNameCheck(username);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of passwordCheck method, of class Registration.
     */
    @Test
    public void testPasswordCheck() {
        System.out.println("passwordCheck");
        String password = "Ch&&sec@ke99!";
        Registration instance = new Registration();
        boolean expResult = true;
        boolean result = instance.passwordCheck(password);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of cellNumberCheck method, of class Registration.
     */
    @Test
    public void testCellNumberCheck() {
        System.out.println("cellNumberCheck");
        String cellphone = "+27838968976";
        Registration instance = new Registration();
        boolean expResult = true;
        boolean result = instance.cellNumberCheck(cellphone);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of signUp method, of class Registration.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        Registration instance = new Registration();
        instance.registerUser();
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of signIn method, of class Registration.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        Registration instance = new Registration();
        instance.loginUser();
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of sendMessages method, of class Registration.
     */
    @Test
    public void testSendMessages() {
        System.out.println("sendMessages");
        Registration instance = new Registration();
        instance.sendMessages();
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of checkMessageId method, of class Registration.
     */
    @Test
    public void testCheckMessageId() {
        System.out.println("checkMessageId");
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.checkMessageId();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of createMessageHash method, of class Registration.
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");
        String messageId = "";
        int messageNum = 0;
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.createMessageHash(messageId, messageNum);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of returnTotalMessages method, of class Registration.
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        Registration instance = new Registration();
        int expResult = 0;
        int result = instance.returnTotalMessages();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of printMessages method, of class Registration.
     */
    @Test
    public void testPrintMessages() {
        System.out.println("printMessages");
        Registration instance = new Registration();
        instance.printMessages();
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
