package parser.payload;

import parser.dao.AccountRepo;
import parser.dao.FileFormatParserRepo;
import parser.dao.FileFormatRepo;
import parser.dao.UploadedDataDetailsRepo;
import parser.dao.impl.AccountRepoImpl;
import parser.dao.impl.FileFormatParserRepoImpl;
import parser.dao.impl.FileFormatRepoImpl;
import parser.dao.impl.UploadedDataDetailsRepoImpl;
import parser.entity.*;

import java.util.ArrayList;
import java.util.List;

public class ParserMetaData {
    private Account account;


    private final FileFormatParser fileFormatParser = new FileFormatParser();
    private final List<FileAttachment> fileAttachmentList = new ArrayList<FileAttachment>();
    private List<UploadedDataDetails> uploadedDataDetailsList;
    private List<FileFormat> fileFormatList = new ArrayList<FileFormat>();
    private List<FileFormatParser> fileFormatParserList = new ArrayList<>();



    public ParserMetaData(Integer accountId) {
        getParserMetaData(accountId);
    }

    public void getParserMetaData(Integer accountId) {
        AccountRepo accountRepo = new AccountRepoImpl();
        UploadedDataDetailsRepo uploadedDataDetailsRepo = new UploadedDataDetailsRepoImpl();
        FileFormatRepo fileFormatRepo = new FileFormatRepoImpl();
        FileFormatParserRepo fileFormatParserRepo = new FileFormatParserRepoImpl();
        this.account = accountRepo.getAccount(accountId);
        this.uploadedDataDetailsList = uploadedDataDetailsRepo.getUploadedDataDetailsByAccountId(accountId);
        for (UploadedDataDetails uploadedDataDetail : uploadedDataDetailsList) {
            fileAttachmentList.addAll(uploadedDataDetail.getFileAttachment());
        }
        this.fileFormatList = fileFormatRepo.getFileFormatRepoByAccountIdV3(accountId);
        this.fileFormatParserList = fileFormatParserRepo.getFileFormatParserByAccountId(accountId);
        System.out.println("Completed getting data");

    }

    public Account getAccount() {
        return account;
    }

    public FileFormatParser getFileFormatParser() {
        return fileFormatParser;
    }
    public List<UploadedDataDetails> getUploadedDataDetailsList() {
        return uploadedDataDetailsList;
    }
    public List<FileFormat> getFileFormatList() {
        return fileFormatList;
    }
    public List<FileAttachment> getFileAttachmentList() {
        return fileAttachmentList;
    }
    public List<FileFormatParser> getFileFormatParserList() {
        return fileFormatParserList;
    }

}
