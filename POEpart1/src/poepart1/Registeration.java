/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepart1;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_lab
 */
public class Registeration {//start of class

    String registeredUser;
    String registeredPassword;
    String registeredPhone;
    String firstName;
    String lastName;
    
    ArrayList<String> disregardMessage = new ArrayList<>();
    ArrayList<String> storeMessage = new ArrayList<>();
    ArrayList<String> sentMessage = new ArrayList<>();
    ArrayList<String> uniqueMessageID = new ArrayList<>();
    ArrayList<String> messageIDs = new ArrayList<>();
    ArrayList<String> hashID= new ArrayList<>();
    ArrayList<String> recipientPhone= new ArrayList<>();
    ArrayList<String> recipientPerMessage = new ArrayList<>();
    Random random = new Random();
    
            
    public boolean checkUserName(String username){//username method
        if(username.contains("_")&& username.length() >= 5){
            JOptionPane.showMessageDialog(null, "Username successfully captured. ");
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length. ");
            return false;
        }
    }
    
    public boolean checkPasswordComplexity(String password){//password method
        if(password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_+=-])(?=\\S+$).{8,}$")) {
            JOptionPane.showMessageDialog(null, "Password successfully captured. ");
            return true;
        }else{
            JOptionPane.showMessageDialog(null,"Password is not correctly formatted; please ensure that the password contains at least eight characters, a captial letter, a number, and a special character. ");
            return false;
        }
    }
    
    public boolean checkCellPhoneNumber(String cellphone){//cellphone method
        String phoneNumber = JOptionPane.showInputDialog("Enter your international cellphone number (10 digits, starts with 0):");
       
       if(phoneNumber.matches("0[0-9]{9}")){
           JOptionPane.showMessageDialog(null,"Cellphone number successfully captured. ");
       }else{
           JOptionPane.showMessageDialog(null,"Cellphone number incorrectly formatted or does not contain international code, please correct the number and try again. ");
       }
        
        return cellphone.matches("^\\+\\d{1,3}\\d{1,7}$") && cellphone .length() <= 11;
        }
   
    
    public void registerUser() {//register method
        
        String userName = JOptionPane.showInputDialog("Enter your username ");
        String userPassword = JOptionPane.showInputDialog("Enter your password ");
        String userCellphone = JOptionPane.showInputDialog("Enter your cellphone number ");
        
        if(checkUserName(userName) && checkPasswordComplexity(userPassword) && checkCellPhoneNumber(userCellphone) ){
            registeredUser = userName;
            registeredPassword = userPassword;
            registeredPhone = userCellphone;
            JOptionPane.showMessageDialog(null,"Registered successfully ");
        }else{
            JOptionPane.showMessageDialog(null,"Registeration failed ");
        }
    }
    
    
    public void loginUser() {//login method
        if(registeredUser == null && registeredPassword == null && registeredPhone == null){
            JOptionPane.showMessageDialog(null,"Login failed ");
            return;
            
        }
        
        firstName = JOptionPane.showInputDialog("Please enter your first name ");
        lastName = JOptionPane.showInputDialog("Please enter your last name ");
        String userName = JOptionPane.showInputDialog("Enter your username to login ");
        String userPassword = JOptionPane.showInputDialog("Enter your password to login ");
        
        
        if(userName.equals(registeredUser) && userPassword.equals(registeredPassword)){
            JOptionPane.showMessageDialog(null, "Welcome " + firstName + " " + lastName +
                    " it is great to see you again. ");
        }else{
            JOptionPane.showMessageDialog(null, "Username or password incorrect please try again. ");
        }
} 
    public void sendMessage(){
         
       String recipientNumber;
    do{
       recipientNumber = JOptionPane.showInputDialog(null,"Please enter recipient number ");
       if (recipientNumber == null){
            return;
       }
       if (!checkCellPhoneNumber(recipientNumber)){
       JOptionPane.showMessageDialog(null,"Invalid recipient number");
       }

       }while (!checkCellPhoneNumber(recipientNumber));
       
       String messageNumber = JOptionPane.showInputDialog("How many messages do you want to send? ");
       if(messageNumber == null){
           return;
       }

       try {
           int messageCount = Integer.parseInt(messageNumber);
           for (int i = 0; i < messageCount; i++){
               String message = JOptionPane.showInputDialog(String.format("Please enter your message (%d of %d): ", i + 1, messageCount));
               if (message == null){
                   return;
               }  
 
           String[] options = {"Send","Store","Disregard"};
               int actions = JOptionPane.showOptionDialog(null,"What do you want to do with this message.?","Message Action",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
               
               String id = checkMessageId();
                
               switch(actions){
                   case 0 -> {
                   sentMessage.add(message);
                   uniqueMessageID.add(id);
                   int number = sentMessage.size();
                   String hash = createMessageHash(id, number);
                   hashID.add(hash);
                   JOptionPane.showMessageDialog(null,"Message SENT " + id + "\n Hash Id: " + hash + "\n Message: " + message.toUpperCase());
                   
                }
                   case 1 -> {
                   storeMessage.add(message);
                   JOptionPane.showMessageDialog(null,"Message STORED " );
                   }
                   case 2 -> {
                   disregardMessage.add(message);
                   JOptionPane.showMessageDialog(null,"Message disregarded" );
                   }
                      
                   default -> {
                       return;
                   }
               }
               
           }
        }catch(NumberFormatException e){
            JOptionPane.showInputDialog(null, "Please enter the valid number ", "Error",JOptionPane.ERROR_MESSAGE);
        }   
      
      printMessages();
       
      JOptionPane.showInputDialog(null,returnTotalMessages());
     }//end of method
     
    public String checkMessageId(){
        String id;
        do{
               int firstNum = 1 + random.nextInt(9);
               int remainingNum = random.nextInt(250);
               id = firstNum + String.format("%09d", remainingNum);
               
        }while(uniqueMessageID.contains(id));
                return id;
        
    }//end of method
    public String createMessageHash(String messageId, int messageNum){
        return messageId.substring(0,2) + ":" + messageNum;
    }
    
    public int returnTotalMessages(){
        return sentMessage.size();
    }
    public String printMessages(){
        if(sentMessage.isEmpty()){
           JOptionPane.showInputDialog(null,"No message was sent ");
           return "No message was sent ";
       }
        
       StringBuilder display = new StringBuilder("\n*** Sent messages ***\n");
        for(int i = 0; i < sentMessage.size(); i++){
          display .append("Message:").append(i + 1).append(":\n")
                                .append("Message HASHID: ").append(hashID.get(i)).append("\n")
                                .append("Message ID: ").append(uniqueMessageID.get(i)).append("\n")
                                .append("Message content: ").append(sentMessage.get(i)).append("\n")
                                .append("Recipient: ").append(recipientPhone.get(i));
          
        }
        JOptionPane.showMessageDialog(null, display.toString());
        return null;
    }
    
}//end of class
