/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package poepart3;

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
     * Test of checkUserName method, of class Registration.
     */
    @Test
    public void testCheckUserName() {
        System.out.println("checkUserName");
        String username = "kyl_1";
        Registration instance = new Registration();
        boolean expResult = true;
        boolean result = instance.checkUserName(username);
        assertEquals(result, expResult);

    }

    /**
     * Test of checkPasswordComplexity method, of class Registration.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        String password = "Ch&&sec@ke99!";
        Registration instance = new Registration();
        boolean expResult = true;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(result, expResult);
        
    }

    /**
     * Test of checkCellphoneNumber method, of class Registration.
     */
    @Test
    public void testCheckCellphoneNumber() {
        System.out.println("checkCellphoneNumber");
        String cellphone = "+27838968976";
        Registration instance = new Registration();
        boolean expResult = true;
        boolean result = instance.checkCellphoneNumber(cellphone);
        assertEquals(result, expResult);

    }

    /**
     * Test of registerUser method, of class Registration.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        Registration instance = new Registration();
        instance.registerUser();

    }

    /**
     * Test of loginUser method, of class Registration.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        Registration instance = new Registration();
        instance.loginUser();
        
    }

    /**
     * Test of sentMessage method, of class Registration.
     */
    @Test
    public void testSentMessage() {
        System.out.println("sentMessage");
        Registration instance = new Registration();
        instance.sentMessage();

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

    }

    /**
     * Test of printMessages method, of class Registration.
     */
    @Test
    public void testPrintMessages() {
        System.out.println("printMessages");
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.printMessages();
        assertEquals(result, expResult);

    }

    /**
     * Test of displaySendersAndRecipients method, of class Registration.
     */
    @Test
    public void testDisplaySendersAndRecipients() {
        System.out.println("displaySendersAndRecipients");
        Registration instance = new Registration();
        instance.displaySendersAndRecipients();

    }

    /**
     * Test of displayLongestSentMessage method, of class Registration.
     */
    @Test
    public void testDisplayLongestSentMessage() {
        System.out.println("displayLongestSentMessage");
        Registration instance = new Registration();
        instance.displayLongestSentMessage();
        
    }

    /**
     * Test of searchByMessageID method, of class Registration.
     */
    @Test
    public void testSearchByMessageID() {
        System.out.println("searchByMessageID");
        Registration instance = new Registration();
        instance.searchByMessageID();

    }

    /**
     * Test of searchMessagesByRecipient method, of class Registration.
     */
    @Test
    public void testSearchMessagesByRecipient() {
        System.out.println("searchMessagesByRecipient");
        Registration instance = new Registration();
        instance.searchMessagesByRecipient();

    }

    /**
     * Test of deleteMessageByHash method, of class Registration.
     */
    @Test
    public void testDeleteMessageByHash() {
        System.out.println("deleteMessageByHash");
        Registration instance = new Registration();
        instance.deleteMessageByHash();

    }

    /**
     * Test of displayFullReport method, of class Registration.
     */
    @Test
    public void testDisplayFullReport() {
        System.out.println("displayFullReport");
        Registration instance = new Registration();
        instance.displayFullReport();

    }

    /**
     * Test of loadStoredMessagesFromJSON method, of class Registration.
     */
    @Test
    public void testLoadStoredMessagesFromJSON() {
        System.out.println("loadStoredMessagesFromJSON");
        String filePath = "";
        Registration instance = new Registration();
        instance.loadStoredMessagesFromJSON(filePath);

    }
    
}
