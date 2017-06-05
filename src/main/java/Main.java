import org.json.simple.JSONObject;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        JSONConfig jsonConfig = new JSONConfig("config.json");
        JSONObject data = jsonConfig.getObject();
        String user = (String) data.get("smtp.username");
        String userPassword = (String) data.get("smtp.password");

        System.out.print("Hello " + user + ". ");
        System.out.println("Please choose an option");
        System.out.println("0 - Send email");
        System.out.println("1 - Display inbox");
        System.out.println("2 - Exit");

        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        int i = scanner.nextInt();

        while(run){
            switch (i) {
                case 0:
                    scanner.nextLine();  // Consume newline left-over
                    System.out.print("Enter sender email: ");
                    String recieverEmail = scanner.nextLine();
                    System.out.print("Enter subject: ");
                    String subject = scanner.nextLine();
                    System.out.print("Enter message: ");
                    String mailMessage = scanner.nextLine();

                    TLSMailing mailingService = new TLSMailing(user, userPassword );
                    Session  session = mailingService.getSession();

                    try {
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(user));
                        message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(recieverEmail));
                        message.setSubject(subject);
                        message.setText(mailMessage);

                        Transport.send(message);

                        System.out.println("Done");

                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                case 1:
                    CheckMailBox mailBox = new CheckMailBox();
                    String host = (String) data.get("pop.host");// change accordingly
                    String mailStoreType = "pop3";
                    String username = user ;// change accordingly
                    String password = userPassword ;// change accordingly
                    mailBox.check(host, mailStoreType, username, password);

                case 2:
                    run = false;
            }
        }


    }
}