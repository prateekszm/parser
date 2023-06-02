package parser.service.mailclient;



import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.File;
import java.util.List;




public class MailSendClient extends MailClient {
    public MailSendClient(String email, String password) {
        super(email, password);
    }

    public boolean sendMail(String to, String subject,String body){
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(super.getUser()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean sendMailWithAttachments(String to, String subject, String body, List<File> fileList){
        try{
            MimeMessage message =  new MimeMessage(session);
            message.setSubject(subject);
            message.setFrom(new InternetAddress(super.getUser()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            MimeBodyPart bodyPartText = new MimeBodyPart();
            bodyPartText.setText(body);

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPartText);

            if( !(fileList == null) && !fileList.isEmpty()){
                addAttachments(multipart,fileList);
            }
            message.setContent(multipart);
            Transport.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private void addAttachments(MimeMultipart multipart, List<File> fileList) {
        try {
            for(File file:fileList){
                MimeBodyPart bodyPartAttachment = new MimeBodyPart();
                DataSource source = new FileDataSource(file);
                bodyPartAttachment.setDataHandler(new DataHandler(source));
                bodyPartAttachment.setFileName(file.getName());
                multipart.addBodyPart(bodyPartAttachment);
            }
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
