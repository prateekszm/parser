package parser.service.mailclient;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

import java.util.Properties;

public class MailClient {
    private final String email;
    private final String password;
    private final Properties properties = new Properties();

    Session session = Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(email,password);
        }
    });
    public MailClient(String email, String password) {
        this.properties.put("mail.smtp.auth", true);
        this.properties.put("mail.smtp.starttls.enable", true);
        this.properties.put("mail.smtp.port", "587");
        this.properties.put("mail.transfer.protocol","smtp");
        this.properties.put("mail.smtp.host", "smtp.gmail.com");
        this.email = email;
        this.password = password;
        System.out.println("I invoked");
    }
     void buildSession(){
         System.out.println("i am here ");
    }

    public String getUser() {
        System.out.println("get email");
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Properties getProperties() {
        return properties;
    }

    public Session getSession() {
        return session;
    }

}
