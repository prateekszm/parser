package parser.dao;

import java.util.List;

import parser.entity.UploadedDataDetails;

public interface UploadedDataDetailsRepo {
	public void saveUploadedDataDetails(UploadedDataDetails uploadedDataDetails);
	public List<UploadedDataDetails> getUploadedDataDetailsByAccountId(Integer id);
	public void setProcessingStatusParsing(UploadedDataDetails uploadedDataDetails);
	public void setProcessingStatusParsed(UploadedDataDetails uploadedDataDetails);
	public void getUploadedDataDetailsByAccountIdAndProcessingStatus(Integer accountId,Integer processingStatus);
}
