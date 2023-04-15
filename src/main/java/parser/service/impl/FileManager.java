package parser.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import parser.config.ParsingStatusAndConstants;
import parser.config.StringConstants;
import parser.dao.FileAttachmentRepo;
import parser.dao.UploadedDataDetailsRepo;
import parser.dao.impl.FileAttachmentRepoImpl;
import parser.dao.impl.UploadedDataDetailsRepoImpl;
import parser.entity.Account;
import parser.entity.FileAttachment;
import parser.entity.UploadedDataDetails;
import parser.payload.ParserMetaData;

public class FileManager {
    UploadedDataDetailsRepo uploadedDataDetailsRepo = new UploadedDataDetailsRepoImpl();
    FileAttachmentRepo fileAttachmentRepo = new FileAttachmentRepoImpl();
    boolean folderParsed = false;

    public void run(HashMap<String, String> config) {
        ArrayList<String> filePathList = new ArrayList<>();
        ArrayList<FileAttachment> fileAttachmentList = new ArrayList<>();

        System.out.println("Inside fileManager run");
        System.out.println(config);
        try {
            File folder = new File(config.get(StringConstants.FOLDERPATH));
            UploadedDataDetails uploadedDataDetails = new UploadedDataDetails();

            if (config.get("goDeep").equals("false")) {
                getAllFilePaths(config.get(StringConstants.FOLDERPATH), filePathList);
            } else {
                File[] listFiles = folder.listFiles();
                if (listFiles != null) {
                    for (File file : listFiles) {
                        if (file.isFile()) {
                            filePathList.add(file.getAbsolutePath());
                        }
                    }
                }
            }
            System.out.println(filePathList);
            Account account = new Account();
            account.setAccountId(Integer.parseInt(config.get("accountId")));
            uploadedDataDetails.setAccount(account);
            uploadedDataDetails.setFilePath(folder.getAbsolutePath());
            uploadedDataDetails.setProcessingStatus(ParsingStatusAndConstants.PARSING);
            uploadedDataDetails.setFileCount(Integer.parseInt(Integer.toString(filePathList.size())));
            uploadedDataDetailsRepo.saveUploadedDataDetails(uploadedDataDetails);
            for (String filePath : filePathList) {
                FileAttachment fileAttachment = new FileAttachment();
                fileAttachment.setAccountId(account);
                fileAttachment.setUploadedDataDetails(uploadedDataDetails);
                fileAttachment.setFilePath(filePath);
                fileAttachment.setProcessingStatus(ParsingStatusAndConstants.PARSING);
                fileAttachmentList.add(fileAttachment);
            }
            fileAttachmentRepo.saveFileAttachment(fileAttachmentList);

            //all file saved start for go to another file class and write all logic to parse header and child level data
            boolean status = false;
            ParserMetaData parserMetaData = new ParserMetaData(account.getAccountId());
            AttachmentParser attachmentParser = new AttachmentParser();
            folderParsed = attachmentParser.run(filePathList, parserMetaData, account.getAccountId());
            if (folderParsed) {
                uploadedDataDetailsRepo.setProcessingStatusParsed(uploadedDataDetails);
                for (FileAttachment fileAttachment :fileAttachmentList) {
                    fileAttachment.setProcessingStatus(ParsingStatusAndConstants.PARSED);
                }
                fileAttachmentRepo.updateFileAttachment(fileAttachmentList);
            } else {
                System.out.println("Error happened + FileManager after calling attachmentParserRun ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllFilePaths(String dir, ArrayList<String> fileName) {
        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    fileName.add(listOfFile.getAbsolutePath());
                } else if (listOfFile.isDirectory()) {
                    getAllFilePaths(listOfFile.getAbsolutePath(), fileName);

                }
            }
        }
    }

}
