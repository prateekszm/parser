package parser.dao;

import java.util.List;

import parser.entity.FileAttachment;

public interface FileAttachmentRepo {
	//void getFileAttachmentByUploadedData(Integer id);
	void saveFileAttachment(FileAttachment fielAttachement);
	void saveFileAttachment(List<FileAttachment> fileAttachmentList);
	void updateFileAttachment(FileAttachment fielAttachement);
	void updateFileAttachment(List<FileAttachment> fileAttachmentList);
	List<FileAttachment> getFileAttachment(Integer accountId ,Integer uploadedDataDetailsId);
	
}
