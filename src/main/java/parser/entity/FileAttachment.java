package parser.entity;
/*
 * this class is for the main all the attachement saved bug the uploadedDataDetails
 */

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="file_attachment")
public class FileAttachment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="file_attachment_id")
	private Integer fileAttachmentId;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_account_id")
	private Account accountId;
	
	@ManyToOne
	@JoinColumn(name="fk_uploaded_data_details")
	private UploadedDataDetails uploadedDataDetails;
	
	@Column(name="processing_status")
	private Integer processingStatus;
	@Column(name="file_path")
	private String filePath;
	@Column(name="attachment_file_name")
	private String attachmentFileName;
	@Column(name="page_count")
	private Integer pageCount;
	@Column(name="file_size")
	private String fileSize;
	@Column(name="password")
	private String password;
	@Column(name = "created_date")
	private transient Date createdDate;
	@Column(name = "created_user")
	private transient String createdUser;
	@Column(name = "updated_date")
	private transient Date updatedDate;
	@Column(name = "updated_user")
	private transient  String updatedUser;
	@Column(name = "is_deleted" ,columnDefinition = "BOOLEAN")
	private Boolean isDeleted;
	
	
	public Integer getFileAttachmentId() {
		return fileAttachmentId;
	}
	public void setFileAttachmentId(Integer fileAttachmentId) {
		this.fileAttachmentId = fileAttachmentId;
	}
	public Account getAccountId() {
		return accountId;
	}
	public void setAccountId(Account accountId) {
		this.accountId = accountId;
	}
	public UploadedDataDetails getUploadedDataDetails() {
		return uploadedDataDetails;
	}
	public void setUploadedDataDetails(UploadedDataDetails uploadedDataDetails) {
		this.uploadedDataDetails = uploadedDataDetails;
	}
	public Integer getProcessingStatus() {
		return processingStatus;
	}
	public void setProcessingStatus(Integer processingStatus) {
		this.processingStatus = processingStatus;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getAttachmentFileName() {
		return attachmentFileName;
	}
	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Override
	public String toString() {
		return "FileAttachment [fileAttachmentId=" + fileAttachmentId + ", accountId=" + accountId
				+ ", uploadedDataDetails=" + uploadedDataDetails + ", processingStatus=" + processingStatus
				+ ", filePath=" + filePath + ", attachmentFileName=" + attachmentFileName + ", pageCount=" + pageCount
				+ ", fileSize=" + fileSize + ", password=" + password + ", createdDate=" + createdDate
				+ ", createdUser=" + createdUser + ", updatedDate=" + updatedDate + ", updatedUser=" + updatedUser
				+ ", isDeleted=" + isDeleted + "]";
	}
	
}
