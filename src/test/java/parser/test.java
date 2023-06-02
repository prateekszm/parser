package parser;

import parser.service.mailclient.MailClient;
import parser.service.mailclient.MailReceiveClient;

public class test {
    @org.junit.jupiter.api.Test
    void test() {
        MailReceiveClient mailReceiveClient =  new MailReceiveClient("lastmanstandingzone@gmail.com","cegvulwqdxtsjihx");
        mailReceiveClient.downloadMail("doenlwoa","panf","gay");

    }
}
