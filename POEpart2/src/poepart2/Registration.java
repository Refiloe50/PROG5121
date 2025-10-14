/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepart2;

import java.util.ArrayList;
import java.util.Random;          
import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_Lab
 */
class Registration {
    
    
    String registeredUser;
    String registeredPassword;
    String registeredPhone;
    String firstName;
    String lastName;
    
    ArrayList<String> sentMessage = new ArrayList<>();
    ArrayList<String> storeMessage = new ArrayList<>();
    ArrayList<String> disregardMessage = new ArrayList<>();
    ArrayList<String> uniqueMessageID = new ArrayList<>();
    
    ArrayList<String> messageIDs = new ArrayList<>();
    ArrayList<String> hashIDs = new ArrayList<>();
    ArrayList<String> recipientPerMessage = new ArrayList<>();
    Random random = new Random();

    
    
    public boolean userNameCheck(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean passwordCheck(String password) {
        return password != null && password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_+=-])(?=\\S+$).{8,}$");
    }

    public boolean cellNumberCheck(String cellphone) {
        return cellphone != null && cellphone.matches("^(\\+27|0)[6-8][0-9]{8}$");
    }

    public void registerUser() {
        String userName;
        String userPassword;
        String userCellNumber;

        firstName = JOptionPane.showInputDialog(null, "Please enter your first name", "First name, ", JOptionPane.INFORMATION_MESSAGE);
        lastName = JOptionPane.showInputDialog(null, "Please enter your last name", "Last name, " , JOptionPane.INFORMATION_MESSAGE);
        
        do {
            userName = JOptionPane.showInputDialog(null, "Enter your username (must include '_' and be <= 5 characters)", "Username, ", JOptionPane.INFORMATION_MESSAGE );
            if (userName == null) return;

            if (!userNameCheck(userName)) {
                JOptionPane.showMessageDialog(null, "Invalid username ", "Invalid username ", JOptionPane.INFORMATION_MESSAGE);
            }
        } while (!userNameCheck(userName));
        registeredUser = userName;

        do {
            userPassword = JOptionPane.showInputDialog("Enter password (must include uppercase, lowercase, number, and special char)");
            
            if (!passwordCheck(userPassword)) {
                JOptionPane.showMessageDialog(null, "Invalid password. Please try again ");
                
            if (userPassword == null) 
                return;
            }
        } while (!passwordCheck(userPassword));
        registeredPassword = userPassword;
        
        do {
            userCellNumber = JOptionPane.showInputDialog(null,"Enter cellphone number ", "Cellphone", JOptionPane.INFORMATION_MESSAGE );
            if (userCellNumber == null) return;

            if (!cellNumberCheck(userCellNumber)) {
                JOptionPane.showMessageDialog(null, "Invalid cellphone number. Please try again ","Cellphone", JOptionPane.INFORMATION_MESSAGE);
            }
        } while (!cellNumberCheck(userCellNumber));{

        JOptionPane.showMessageDialog(null, "Registration successful ");
    }
        
        registeredPhone = userCellNumber;
    }
    public void loginUser() {
        if (registeredUser == null || registeredPassword == null) {
            JOptionPane.showMessageDialog(null, "You need to sign up before you can log in ");
            return;
        }

        String userName = JOptionPane.showInputDialog("Please enter your username ");
        String userPassword = JOptionPane.showInputDialog("Please enter your password ");

        if (userName == null || userPassword == null) {
            JOptionPane.showMessageDialog(null, "Login cancelled. ");
            return;
        }

        if (userName.equals(registeredUser) && userPassword.equals(registeredPassword)) {
            String[] options = {"Send message", "Coming soon", "Exit"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Welcome to QuickChat. ",
                    "Options",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (choice) {
                case 0 -> sendMessages();
                case 1 -> JOptionPane.showMessageDialog(null, "Coming soon ");
                case 2 -> System.exit(0);
                default -> JOptionPane.showMessageDialog(null, "Quit ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Your credentials are incorrect ");
        }
    }

    public void sendMessages() {
        String recipientNumber;
        do {
            recipientNumber = JOptionPane.showInputDialog(null, "Enter recipient number starting with 0 or +27 " , "Recipient number", JOptionPane.INFORMATION_MESSAGE);
            if (recipientNumber == null) 
                return;

            if (!cellNumberCheck(recipientNumber)) {
                JOptionPane.showMessageDialog(null, "Invalid, please try again ");
            }
        } while (!cellNumberCheck(recipientNumber));
        
        String messageNumber = JOptionPane.showInputDialog("How many messages do you want to send? ");
        if (messageNumber == null) 
            return;

        try {
            int messageCount = Integer.parseInt(messageNumber);
            for (int i = 0; i < messageCount; i++) {
                String message = JOptionPane.showInputDialog(null, String.format("Please enter your message of less than 250 characters in length(%d of %d): ", i + 1, messageCount),"Message(s)", JOptionPane.INFORMATION_MESSAGE);
                if (message == null) {
                    return;
                }

                String[] options = {"Send message ", "Store message", "Disregard message"};
                int actions = JOptionPane.showOptionDialog(
                        null,
                        "What do you want to do with this message? ",
                        "Message Action",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                switch (actions) {
                    case 0 -> { 
                        String id = checkMessageId();
                        sentMessage.add(message);
                        uniqueMessageID.add(id);
                        messageIDs.add(id);
                        recipientPerMessage.add(recipientNumber);

                        int number = sentMessage.size();
                        String hash = createMessageHash(id, number);
                        hashIDs.add(hash);

                        JOptionPane.showMessageDialog(null,
                                "Message successfully sent \nMessage ID: " + id +
                                        "\nHash ID: " + hash +
                                        "\nMessage: " + message.toUpperCase());
                    }
                    case 1 -> {
                        storeMessage.add(message);
                        JOptionPane.showMessageDialog(null, "Message successfully stored ");
                    }
                    case 2 -> {
                        disregardMessage.add(message);
                        JOptionPane.showMessageDialog(null, "Message disregarded ");
                    }
                    default -> { 
                        JOptionPane.showMessageDialog(null, "No action selected, message skipped ");
                    }
                }
            }

            printMessages();
            JOptionPane.showMessageDialog(null, "Total sent messages: " + returnTotalMessages(),"Total messages ", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number ", "Error ", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public String checkMessageId() {
        String id;
        do {
            int firstNum = 1 + random.nextInt(9); 
            int remainingNum = random.nextInt(1_000_000_000); 
            id = firstNum + String.format("%09d", remainingNum);
        } while (uniqueMessageID.contains(id));
        return id;
    }

    public String createMessageHash(String messageId, int messageNum) {
        String prefix = messageId.length() >= 2 ? messageId.substring(0, 2) : messageId;
        return prefix + ":" + messageNum;
    }

    public int returnTotalMessages() {
        return sentMessage.size();
    }

    public void printMessages() {
        if (sentMessage.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No message was sent ");
            return;
        }

        StringBuilder display = new StringBuilder("\n*** SENT MESSAGES ***\n");
        for (int i = 0; i < sentMessage.size(); i++) {
            display.append("Message No: ").append(i + 1).append("\n")
                    .append("Hash ID: ").append(i < hashIDs.size() ? hashIDs.get(i) : "N/A").append("\n")
                    .append("Message ID: ").append(i < messageIDs.size() ? messageIDs.get(i) : "N/A").append("\n")
                    .append("Message content: ").append(sentMessage.get(i)).append("\n")
                    .append("Recipient: ").append(i < recipientPerMessage.size() ? recipientPerMessage.get(i) : "N/A").append("\n\n");
        }

        JOptionPane.showMessageDialog(null, display.toString());
    }
}
