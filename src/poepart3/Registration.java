/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepart3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_Lab
 */
public class Registration {

    String registeredUser;
    String registeredPassword;
    String registeredCellphoneNumber;

    ArrayList<String> disregardMessage = new ArrayList<>();
    ArrayList<String> storeMessage = new ArrayList<>();
    ArrayList<String> sentMessage = new ArrayList<>();
    ArrayList<String> reciepientPhone = new ArrayList<>();
    ArrayList<String> hashID = new ArrayList<>();
    ArrayList<String> uniqueMessageID = new ArrayList<>();

    Random random = new Random();

    public boolean checkUserName(String username) {//username validation
        if (username != null && username.contains("_") && username.length() >= 5) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                "Username is not correctly formatted. It must contain an underscore and be at least 5 characters long.",
                "Username Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    public boolean checkPasswordComplexity(String password) {//password validation
        if (password != null && password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_+=-])(?=\\S+$).{8,}$")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                "Password must be at least 8 characters and contain:\n- Uppercase\n- Lowercase\n- Number\n- Special character",
                "Password Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    public boolean checkCellphoneNumber(String cellphone) {//cellphone validation
        if (cellphone != null && cellphone.matches("^(\\+27|0)[6-8][0-9]{8}$")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                "Cellphone incorrect. Must start with +27 or 0 and be a valid SA mobile number.",
                "Cellphone Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    public void registerUser() {//registeration validation

        JOptionPane.showInputDialog(null, "Please enter your first name:", "First Name", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showInputDialog(null, "Please enter your last name:", "Last Name", JOptionPane.INFORMATION_MESSAGE);

        String userName;
        do {
            userName = JOptionPane.showInputDialog(null, "Please enter your username:", "Username", JOptionPane.INFORMATION_MESSAGE);
            if (userName == null) return; // user cancelled
        } while (!checkUserName(userName));

        JOptionPane.showMessageDialog(null, "Username successfully added.");

        String userPassword;
        do {
            userPassword = JOptionPane.showInputDialog(null, "Enter your password:", "Password", JOptionPane.INFORMATION_MESSAGE);
            if (userPassword == null) return;
        } while (!checkPasswordComplexity(userPassword));

        JOptionPane.showMessageDialog(null, "Password successfully captured.");

        String userCellphone;
        do {
            userCellphone = JOptionPane.showInputDialog(null, "Enter your cellphone number:", "Cellphone", JOptionPane.INFORMATION_MESSAGE);
            if (userCellphone == null) return;
        } while (!checkCellphoneNumber(userCellphone));

        JOptionPane.showMessageDialog(null, "Cellphone added successfully.");

        registeredUser = userName;
        registeredPassword = userPassword;
        registeredCellphoneNumber = userCellphone;

        JOptionPane.showMessageDialog(null, "Registration completed successfully!");
    }

    public void loginUser() {//login validation

        if (registeredUser == null) {
            JOptionPane.showMessageDialog(null,
                "You must register before you can log in. ",
                "Error ", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String userName = JOptionPane.showInputDialog(null, "Enter username: ", " Login ", JOptionPane.INFORMATION_MESSAGE);
        if (userName == null) return;
        String userPassword = JOptionPane.showInputDialog(null, "Enter password: ", " Login ", JOptionPane.INFORMATION_MESSAGE);
        if (userPassword == null) return;

        if (userName.equals(registeredUser) && userPassword.equals(registeredPassword)) {

            boolean exit = false;
            while (!exit) {
                String menu =
                    "Choose an option:\n" +
                    "1) Send Messages\n" +
                    "2) Show Recently Sent Messages\n" +
                    "3) Quit\n" +
                    "4) View sender + recipient\n" +
                    "5) Longest message\n" +
                    "6) Search by Message ID\n" +
                    "7) Search by recipient\n" +
                    "8) Delete message by hash\n" +
                    "9) Full Message Report\n" +
                    "10) Load Stored Messages (JSON)\n\n" +
                    "Enter option number (1-10):";

                String input = JOptionPane.showInputDialog(null, menu, "Input", JOptionPane.QUESTION_MESSAGE);
                if (input == null) { // user cancelled
                    exit = true;
                    continue;
                }

                input = input.trim();
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 10. ");
                    continue;
                }

                int option;
                try {
                    option = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid number. Please enter 1 - 10. ");
                    continue;
                }

                switch (option) {
                    case 1 -> sentMessage();
                    case 2 -> printMessages(); // Show Recently Sent Messages: call your existing printMessages()
                    case 3 -> {
                        JOptionPane.showMessageDialog(null, "Goodbye! ");
                        System.exit(0);
                    }
                    case 4 -> displaySendersAndRecipients();
                    case 5 -> displayLongestSentMessage();
                    case 6 -> searchByMessageID();
                    case 7 -> searchMessagesByRecipient();
                    case 8 -> deleteMessageByHash();
                    case 9 -> displayFullReport();
                    case 10 -> loadStoredMessagesFromJSON("storedMessages.json"); // same folder as .java
                    default -> JOptionPane.showMessageDialog(null, "Option must be between 1 and 10.");
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Incorrect login credentials.");
        }
    }

    public void sentMessage() {

        String recipientCell;
        do {
            recipientCell = JOptionPane.showInputDialog(null, "Enter recipient cellphone number:", "Recipient", JOptionPane.INFORMATION_MESSAGE);

            if (recipientCell == null) return;

            if (!checkCellphoneNumber(recipientCell)) {
                JOptionPane.showMessageDialog(null, "Invalid recipient number.");
            }

        } while (!checkCellphoneNumber(recipientCell));

        String messageNumber = JOptionPane.showInputDialog(null, "How many messages do you want to send?", "Message Count", JOptionPane.INFORMATION_MESSAGE);

        if (messageNumber == null) return;

        try {
            int messageCount = Integer.parseInt(messageNumber);

            for (int i = 0; i < messageCount; i++) {

                String message = JOptionPane.showInputDialog(
                        null,
                        "Enter message (less than 250 characters):\nMessage " + (i + 1) + " of " + messageCount,
                        "Message Input",
                        JOptionPane.INFORMATION_MESSAGE
                );

                if (message == null) return;

                String[] options = {"Send", "Store", "Disregard"};
                int action = JOptionPane.showOptionDialog(null, "Choose action for this message:",
                        "Message Action", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[1]);

                String id = checkMessageId();

                switch (action) {
                    case 0 -> { // send
                        sentMessage.add(message);
                        uniqueMessageID.add(id);
                        reciepientPhone.add(recipientCell);
                        String hash = createMessageHash(id, messageCount);
                        hashID.add(hash);

                        JOptionPane.showMessageDialog(null,
                            "Message sent!\nMessage ID: " + id +
                            "\nHash: " + hash +
                            "\nContent: " + message.toUpperCase());
                    }
                    case 1 -> { // store
                        storeMessage.add(message);
                        JOptionPane.showMessageDialog(null, "Message stored successfully.");
                    }
                    case 2 -> { // disregard
                        disregardMessage.add(message);
                        JOptionPane.showMessageDialog(null, "Message disregarded.");
                    }
                    default -> {
                        // quit
                    }
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(null, "Total messages sent: " + returnTotalMessages());
    }

    //message helpers

    public String checkMessageId() {
        String id;

        do {
            int firstNum = 1 + random.nextInt(9);
            int remaining = random.nextInt(1_000_000_000);
            id = firstNum + String.format("%09d", remaining);
        } while (uniqueMessageID.contains(id));

        return id;
    }

    public String createMessageHash(String messageId, int messageNum) {
        return messageId.substring(0, 2) + ":" + messageNum;
    }

    public int returnTotalMessages() {
        return sentMessage.size();
    }

    public String printMessages() {

        if (sentMessage.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages were sent.");
            return "No messages.";
        }

        StringBuilder display = new StringBuilder("\n*** SENT MESSAGES ***\n");

        for (int i = 0; i < sentMessage.size(); i++) {
            display.append("Message ").append(i + 1).append(":\n")
                    .append("Hash ID: ").append(hashID.size() > i ? hashID.get(i) : "N/A").append("\n")
                    .append("Message ID: ").append(uniqueMessageID.size() > i ? uniqueMessageID.get(i) : "N/A").append("\n")
                    .append("Content: ").append(sentMessage.get(i)).append("\n")
                    .append("Recipient: ").append(reciepientPhone.size() > i ? reciepientPhone.get(i) : "N/A")
                    .append("\n\n");
        }

        JOptionPane.showMessageDialog(null, display.toString());
        return display.toString();
    }

    // a. Display the sender and recipient of all sent messages.
    public void displaySendersAndRecipients() {
        if (sentMessage.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No sent messages to display.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentMessage.size(); i++) {
            sb.append("Message ").append(i + 1).append(":\n");
            sb.append("Sender: ").append(registeredUser).append("\n");
            sb.append("Recipient: ").append(reciepientPhone.get(i)).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Senders & Recipients", JOptionPane.INFORMATION_MESSAGE);
    }

    // b. Display the longest sent message.
    public void displayLongestSentMessage() {
        if (sentMessage.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No sent messages.");
            return;
        }
        String longest = sentMessage.get(0);
        int index = 0;
        for (int i = 1; i < sentMessage.size(); i++) {
            if (sentMessage.get(i).length() > longest.length()) {
                longest = sentMessage.get(i);
                index = i;
            }
        }
        String msg = "Longest message:\n" + longest + "\nRecipient: " + reciepientPhone.get(index) + "\nMessage ID: " + uniqueMessageID.get(index);
        JOptionPane.showMessageDialog(null, msg, "Longest Message", JOptionPane.INFORMATION_MESSAGE);
    }

    // c. Search for a message ID and display recipient and message.
    public void searchByMessageID() {
        if (uniqueMessageID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages to search.");
            return;
        }
        String id = JOptionPane.showInputDialog(null, "Enter Message ID to search:", "Search by ID", JOptionPane.INFORMATION_MESSAGE);
        if (id == null) return;
        int idx = uniqueMessageID.indexOf(id);
        if (idx >= 0) {
            String result = "Message ID: " + id + "\nRecipient: " + reciepientPhone.get(idx) + "\nMessage: " + sentMessage.get(idx);
            JOptionPane.showMessageDialog(null, result, "Search Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Message ID not found.");
        }
    }

    // d. Search for all messages sent to a particular recipient.
    public void searchMessagesByRecipient() {
        if (reciepientPhone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages to search.");
            return;
        }
        String recipient = JOptionPane.showInputDialog(null, "Enter recipient number to search:", "Search by Recipient", JOptionPane.INFORMATION_MESSAGE);
        if (recipient == null) return;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < reciepientPhone.size(); i++) {
            if (reciepientPhone.get(i).equals(recipient)) {
                sb.append("Message ID: ").append(uniqueMessageID.get(i)).append("\n");
                sb.append("Hash: ").append(hashID.get(i)).append("\n");
                sb.append("Message: ").append(sentMessage.get(i)).append("\n\n");
            }
        }
        if (sb.length() == 0) {
            JOptionPane.showMessageDialog(null, "No messages found for recipient: " + recipient);
        } else {
            JOptionPane.showMessageDialog(null, sb.toString(), "Messages for " + recipient, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // e. Delete a message using the message hash.
    public void deleteMessageByHash() {
        if (hashID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages to delete.");
            return;
        }
        String hash = JOptionPane.showInputDialog(null, "Enter Message Hash to delete (format: XX:NN):", "Delete by Hash", JOptionPane.INFORMATION_MESSAGE);
        if (hash == null) return;
        int idx = hashID.indexOf(hash);
        if (idx >= 0) {
            String removedMsg = sentMessage.remove(idx);
            uniqueMessageID.remove(idx);
            reciepientPhone.remove(idx);
            hashID.remove(idx);
            JOptionPane.showMessageDialog(null, "Message deleted.\nContent removed: " + removedMsg);
        } else {
            JOptionPane.showMessageDialog(null, "Hash not found.");
        }
    }

    // f. Display a report that lists the full details of all the sent messages.
    public void displayFullReport() {
        if (sentMessage.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No sent messages.");
            return;
        }

        StringBuilder display = new StringBuilder("*** FULL SENT MESSAGES REPORT ***\n\n");
        for (int i = 0; i < sentMessage.size(); i++) {
            display.append("Sender: ").append(registeredUser).append("\n");
            display.append("Recipient: ").append(reciepientPhone.get(i)).append("\n");
            display.append("Message ID: ").append(uniqueMessageID.get(i)).append("\n");
            display.append("Hash ID: ").append(hashID.get(i)).append("\n");
            display.append("Content: ").append(sentMessage.get(i)).append("\n");
            display.append("--------------------------------------------------\n");
        }

        JOptionPane.showMessageDialog(null, display.toString(), "Full Report", JOptionPane.INFORMATION_MESSAGE);
    }

    //JSON
    public void loadStoredMessagesFromJSON(String filePath) {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                JOptionPane.showMessageDialog(null, "JSON file not found at: " + filePath);
                return;
            }
            StringBuilder sb = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append(" ");
                }
            }

            String content = sb.toString();

            // A very small regex-based parser to pick up simple objects with Recipient/Message/Flag
            Pattern p = Pattern.compile("\\{[^}]*\\}");
            Matcher m = p.matcher(content);

            int count = 0;
            while (m.find()) {
                String obj = m.group();
                String recipient = extractJsonField(obj, "recipient");
                String message = extractJsonField(obj, "message");
                // String flag = extractJsonField(obj, "flag"); // not used right now

                if (message != null) {
                    storeMessage.add(message);
                    count++;
                }
            }

            JOptionPane.showMessageDialog(null, "Loaded " + count + " stored messages from JSON.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading JSON: " + e.getMessage());
        }
    }

    private String extractJsonField(String jsonObj, String fieldName) {
        Pattern p = Pattern.compile("(?i)\"" + Pattern.quote(fieldName) + "\"\\s*:\\s*\"([^\"]*)\"");
        Matcher m = p.matcher(jsonObj);
        if (m.find()) return m.group(1);
        return null;
    }
}  

