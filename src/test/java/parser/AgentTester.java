package parser;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import parser.payload.FileFormatMetaData;
import parser.payload.ParserMetaData;
import parser.pdfparser.helper.LayoutTextStripper;
import parser.pdfparser.helper.PdfToTextConverter;
import parser.service.mailclient.MailReceiveClient;
import parser.service.mailclient.MailSendClient;
import parser.utils.ReflectionUtils;

public class AgentTester {



    public static void main(String[] args) throws Exception {



        MailReceiveClient mailReceiveClient =  new MailReceiveClient("lastmanstandingzone@gmail.com","cegvulwqdxtsjihx");
        mailReceiveClient.downloadMail("C:\\Users\\KIIT\\Desktop\\TnP Pdf","panf","gay");

        // ParserMetaData parserMetaData = new ParserMetaData(1);

        /*File file = new File("C:/Users/KIIT/Downloads/Ajio_FN6903302200_1680714517727.pdf");
	        PDFParser pdfParser = new PDFParser(new RandomAccessFile( file,"r"));
	        pdfParser.parse();
	        PDDocument pdDocument = new PDDocument(pdfParser.getDocument());
	        PDFTextStripper pdfTextStripper = new LayoutTextStripper();
	        String string = pdfTextStripper.getText(pdDocument);
        //System.out.println(string);
//        PdfToTextConverter pdfToTextConverter = new PdfToTextConverter();
//        String dtr = pdfToTextConverter.pdfToTextConverter("C:/Users/KIIT/Downloads/Ajio_FN6903302200_1680714517727.pdf");
        System.out.println(string);*/

        //ParserMetaData metaData = new ParserMetaData(1);
        //FileFormatMetaData parserMetaData = new FileFormatMetaData(1,metaData);

        Properties prop  = new Properties();
        String propFileName = "C:\\Users\\KIIT\\Desktop\\Project\\parser\\application.properties";
       // prop.load(new FileInputStream("parser\\application.properties"));
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        System.out.println(prop.getProperty("email"));
        System.out.println(prop.getProperty("password"));


        final String user = "lastmanstandingzone@gmail.com";//change accordingly
//        final String password = "#Neeraj913";//change accordingly
        final String password = "cegvulwqdxtsjihx";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");


        String to = "1905945@kiit.ac.in";

        Session session = Session.getDefaultInstance(properties,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });
        sendEmail(session);
        try {


            //send the message
            for (int i = 0; i > 100; i++) {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(user));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                byte[] array = new byte[10]; // length is bounded by 7
                new Random().nextBytes(array);
                String generatedString = new String(array, Charset.forName("UTF-8"));

                message.setSubject(generatedString);
                message.setText("This is simple program of sending email using Java jthough JavaMail Api");
               // Transport.send(message);
                System.out.println(i + " number of mail send");
            }

            System.out.println("message sent successfully...");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

//        read email from gmail

        try {
            Session emailSession = Session.getDefaultInstance(properties, new jakarta.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });
            Store store = emailSession.getStore("pop3s");
            store.connect("pop.gmail.com", user, password);
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);
            System.out.println(messages.length);
            int i = 0;
            for (i = messages.length ; i >= 0; i--) {
                Message message = messages[i-1];
                System.out.println("this is " + i);

                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + Arrays.toString(message.getFrom()));
               // System.out.println("Text: " + message.getContent().toString());
                System.out.println("time sent date " + message.getSentDate());
                //System.out.println(" folder " + message.getFolder());

                // downloadAttachment(message);
            }
            emailFolder.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendEmail(Session session) throws Exception{
        Properties prop  = new Properties();
        String propFileName = "C:\\Users\\KIIT\\Desktop\\Project\\parser\\application.properties";
        prop.load(Files.newInputStream(Paths.get(propFileName)));
        String email = prop.getProperty("email");
        String password = prop.getProperty("password");
        String to = "prateek.kushwaha@highradius.com";
//        MimeMessage message = new MimeMessage(session);
        MailSendClient mailSendClient = new MailSendClient(email,password);
        File file = new File("C:\\Users\\KIIT\\Desktop\\Project\\parser\\src\\test\\java\\parser\\AgentTester.java");
        File file2 = new File("C:\\Users\\KIIT\\Desktop\\Project\\parser\\src\\test\\java\\parser\\Encapsulation.java");
        List<File> fileList = new ArrayList<File>();
        fileList.add(file);
        fileList.add(file2);
        boolean boo = mailSendClient.sendMailWithAttachments(to,"Subject","body",fileList);
        System.out.println(boo);

    }

    private static void downloadAttachment(Message message) throws Exception {
        Multipart multipart = (Multipart) message.getContent();
        int numberOfParts = multipart.getCount();
        System.out.println(numberOfParts);
        for (int ij = 0; ij < multipart.getCount(); ij++) {
            BodyPart bodyPart = multipart.getBodyPart(ij);

            System.out.println(bodyPart.getFileName());
            InputStream stream = bodyPart.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            if (true) {

                System.out.println(bodyPart.getFileName());
                File file = new File("C:\\Users\\KIIT\\Desktop\\test.html");
                try (OutputStream outputStream = Files.newOutputStream(file.toPath())) {
                    byte[] buffer = new byte[4096]; // Adjust the buffer size as needed
                    int bytesRead;
                    while ((bytesRead = stream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }


        }
        System.out.println();
    }
}

























