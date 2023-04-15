package parser.pdfparser;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import parser.config.ParsingStatusAndConstants;
import parser.dao.AccountRepo;
import parser.dao.FileAttachmentRepo;
import parser.dao.UploadedDataDetailsRepo;
import parser.dao.impl.AccountRepoImpl;
import parser.dao.impl.FileAttachmentRepoImpl;
import parser.dao.impl.UploadedDataDetailsRepoImpl;
import parser.entity.Account;
import parser.entity.FileAttachment;
import parser.entity.UploadedDataDetails;

//right now i have file i have to put in uplaoded Data details with its account as fk and after putting i have to put this in file attatchment
@Deprecated
public class ParserByRegex {
	FileAttachmentRepo fileAttachmentRepo = new FileAttachmentRepoImpl();
	public void run() {
		UploadedDataDetailsRepo uploadedDataDetails = new UploadedDataDetailsRepoImpl();
		List<UploadedDataDetails> UploadedDataDetailsList = new ArrayList<UploadedDataDetails>();
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Upload in uploadDataDetails and put the value in fileAttachment
	 */
	public void uploadDetails(Integer accountId, String path) {
		UploadedDataDetailsRepo uploadedDataDetailsrepo = new UploadedDataDetailsRepoImpl();
		AccountRepo accountRepo = new AccountRepoImpl();
		File folder = new File(path);

		UploadedDataDetails uploadedDataDetails = new UploadedDataDetails();
		uploadedDataDetails.setAccount(accountRepo.getAccount(accountId));
		uploadedDataDetails.setAccount(new Account());
		uploadedDataDetails.getAccount().setAccountId(accountId);
		uploadedDataDetails.setFileCount(folder.listFiles().length);
		uploadedDataDetails.setProcessingStatus(ParsingStatusAndConstants.UNPARSED);
		uploadedDataDetails.setFilePath(path);
		uploadedDataDetailsrepo.saveUploadedDataDetails(uploadedDataDetails);
		List<UploadedDataDetails> uploadedDataDetailsList = uploadedDataDetailsrepo
				.getUploadedDataDetailsByAccountId(accountId);

		saveDataInFileAttatchement(uploadedDataDetailsList, accountId);

	}

	private void saveDataInFileAttatchement(List<UploadedDataDetails> uploadedDataDetailsList, Integer accountId) {
		List<FileAttachment> fileAttatchementList = new ArrayList<FileAttachment>();
		for (UploadedDataDetails uploadedDataDetails : uploadedDataDetailsList) {
			File folder = new File(uploadedDataDetails.getFilePath());
			File[] listOfFiles = folder.listFiles();

			for (File file : listOfFiles) {
				if (file.isFile()) {
					FileAttachment fileAttachment = new FileAttachment();
					Account account = new Account();
					account.setAccountId(accountId);
					UploadedDataDetails upladDataDetails = new UploadedDataDetails();
					upladDataDetails.setUploadedDeatilsId(uploadedDataDetails.getUploadedDeatilsId());
					fileAttachment.setAttachmentFileName(file.getName());
					fileAttachment.setAccountId(account);
					fileAttachment.setFilePath(file.getAbsolutePath());
					fileAttachment.setUploadedDataDetails(uploadedDataDetails);
					Long fileSizeInBytes = file.length();
					Long fileSizeInMegabytes = (Long) (fileSizeInBytes / (1024 * 1024));
					fileAttachment.setFileSize(fileSizeInMegabytes.toString());
					fileAttachment.setProcessingStatus(ParsingStatusAndConstants.UNPARSED);
					fileAttachment.setCreatedUser(ParsingStatusAndConstants.SYSTEM);
					fileAttachment.setCreatedDate(new Date());
					fileAttachment.setIsDeleted(ParsingStatusAndConstants.FALSE);
					fileAttatchementList.add(fileAttachment);

				}
			}

		}
		fileAttachmentRepo.saveFileAttachment(fileAttatchementList);
	}

}
