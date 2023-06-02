package parser.service.mailclient;

import parser.payload.Env;

import java.util.Properties;

public class EmailDownloadManager {
    String email = Env.getProperties("email");
    String password = Env.getProperties("password");
    private static final String smtpProtocol = "smtp";
    private static final String popProtocol = "pop3";
    private static final String imapProtocol = "imap";



}
