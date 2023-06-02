package parser.service.mailclient;

import jakarta.mail.*;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.search.FlagTerm;
import parser.payload.CurrentDateTime;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public final class MailReceiveClient extends MailClient {

    public MailReceiveClient(String user, String password) {
        super(user, password);
        super.getProperties().put("mail.store.protocol", "imaps");
    }

    public Boolean downloadMail(String downloadFolderPath, String path, String extraParams) {
        //read email from last and save in email_data table and make a folder in C and enter path in it use smtp
        try {
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", super.getUser(), super.getPassword());
            Folder inbox = store.getFolder("inbox");
            Message[] arrayMessage = getUnSeenMessage(inbox);
            Date date = lastEmailPresent();
            for (int a = arrayMessage.length - 1; a >= 0; a--) {
                Message message = arrayMessage[a];
                if (message.getReceivedDate().equals(date.toString())) {
//                    TODO: check the date with last data present in data base and the new recived date should be > present database
                }
                if (message.getContent() instanceof Multipart) {
                    System.out.println(message.getSubject());
                    HashMap<InputStream, String> attachments = getAttachments(message);
                    if (!attachments.isEmpty()) {
                        saveAttachments(attachments, downloadFolderPath);
                    }
                }
                message.setFlag(Flags.Flag.SEEN, true);
            }
            inbox.close();
            store.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void saveAttachments(HashMap<InputStream, String> attachments, String downloadFolderPath) {
        String path = createDownloadPath(downloadFolderPath);
        for (Map.Entry<InputStream, String> entry : attachments.entrySet()) {
            File file = new File(path + entry.getValue());
            try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(file.toPath()));
                 InputStream in = entry.getKey()) {
                byte[] buf = new byte[8192];
                int len;
                while ((len = in.read(buf)) > 0)
                    out.write(buf, 0, len);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // close streams, but don't mask original exception, if any
        }
    }

    private String createDownloadPath(String downloadFolderPath) {
        CurrentDateTime currentDateTime = new CurrentDateTime();
        return downloadFolderPath + "//" + currentDateTime.getYear() + "//"
                + currentDateTime.getMonth() + "//" + currentDateTime.getDay();
    }

    private Message[] getUnSeenMessage(Folder inbox) throws Exception {
        inbox.open(Folder.READ_WRITE);
        Flags seen = new Flags(Flags.Flag.SEEN);
        FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
        return inbox.search(unseenFlagTerm);
    }

    private Date lastEmailPresent() {
        //TODO make a call to db and get the last received email and put it in system
        return new Date(System.currentTimeMillis());
    }

    private void downloadAttachment(String downloadFolderPath, Message message) {
        try {
            Multipart multiPart = (Multipart) message.getContent();
            int numberOfAttachment = multiPart.getCount();
            for (int partCount = 0; partCount < numberOfAttachment; partCount++) {
                MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
                if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                    // this part is attachment
                    String fileName = part.getFileName();
                    System.out.println(fileName);
                    part.saveFile(downloadFolderPath + File.separator + fileName);
                } else {
                    // this part may be the message content
                    System.out.println(part.getContent().toString());
                }
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HashMap<InputStream, String> getAttachments(Message message) {
        HashMap<InputStream, String> fileAttachments = new HashMap<>();
        try {
            Multipart part = (Multipart) message.getContent();
            for (int i = 0; i < part.getCount(); i++) {
                fileAttachments.putAll(getAttachments((MimeBodyPart) part.getBodyPart(i)));
            }
            return fileAttachments;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileAttachments;
    }

    private HashMap<InputStream, String> getAttachments(MimeBodyPart bodyPart) throws Exception {
        HashMap<InputStream, String> inputStreamMap = new HashMap<>();
        Object content = bodyPart.getContent();
        if (content instanceof String && bodyPart.getFileName().contains("txt")) {
            System.out.println("Text format not supported");
        }
        if (content instanceof InputStream &&
                (bodyPart.getFileName().contains(".pdf")
                        || bodyPart.getFileName().contains(".xls")
                        || bodyPart.getFileName().contains(".xlsv"))) {
            inputStreamMap.put(bodyPart.getInputStream(), bodyPart.getFileName());
            return inputStreamMap;
        }
        return inputStreamMap;
    }

    private void saveAttachments(Message[] messages) throws Exception {

        for (Message msg : messages) {
            if (msg.getContent() instanceof Multipart) {
                Multipart multipart = (Multipart) msg.getContent();
                for (int i = 0; i < multipart.getCount(); i++) {
                    Part part = multipart.getBodyPart(i);
                    String disposition = part.getDisposition();
                    if ((disposition != null) &&
                            ((disposition.equalsIgnoreCase(Part.ATTACHMENT) ||
                                    (disposition.equalsIgnoreCase(Part.INLINE))))) {
                        MimeBodyPart mimeBodyPart = (MimeBodyPart) part;
                        String fileName = mimeBodyPart.getFileName();
                        File fileToSave = new File(fileName);
                        mimeBodyPart.saveFile(fileToSave);
                    }
                }
            }
        }
    }
}
