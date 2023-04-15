package parser.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "upladed_data_details")
public class UploadedDataDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "upladed_data_details_id")
	private Integer uploadedDeatilsId;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_account_id")
	private Account account;
	@Column(name = "processing_status")
	private Integer processingStatus;
	@Column(name = "data_details")
	private Integer dataDetails;
	@Column(name = "file_path")
	private String filePath;
	@Column(name = "file_count")
	private Integer fileCount;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "created_user")
	private String createdUser;
	@Column(name = "updated_date")
	private Date updatedDate;
	@Column(name = "updated_user")
	private String updatedUser;
	@Column(name = "is_deleted", columnDefinition = "BOOLEAN")
	private Boolean isDeleted;

	// One uploadedDataDetails have many file Attachment
	@OneToMany(mappedBy = "uploadedDataDetails", fetch = FetchType.EAGER)
	private Set<FileAttachment> fileAttachment = new HashSet<FileAttachment>();



	public void setUploadedDeatilsId(Integer uploadedDeatilsId) {
		this.uploadedDeatilsId = uploadedDeatilsId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Integer getProcessingStatus() {
		return processingStatus;
	}

	public void setProcessingStatus(Integer processingStatus) {
		this.processingStatus = processingStatus;
	}

	public Integer getDataDetails() {
		return dataDetails;
	}

	public void setDataDetails(Integer dataDetails) {
		this.dataDetails = dataDetails;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getFileCount() {
		return fileCount;
	}

	public void setFileCount(Integer fileCount) {
		this.fileCount = fileCount;
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
	public Set<FileAttachment> getFileAttachment() {
		return fileAttachment;
	}

	public void setFileAttachment(Set<FileAttachment> fileAttachment) {
		this.fileAttachment = fileAttachment;
	}

	public Integer getUploadedDeatilsId() {
		return uploadedDeatilsId;
	}

//	public List<FileAttachment> getFileAttachment() {
//		return fileAttachment;
//	}
//
//	public void setFileAttachment(ArrayList<FileAttachment> fileAttachment) {
//		this.fileAttachment = fileAttachment;
//	}

	@Override
	public String toString() {
		return "UploadedDataDetails [uploadedDeatilsId=" + uploadedDeatilsId + ", account=" + account
				+ ", processingStatus=" + processingStatus + ", dataDetails=" + dataDetails + ", filePath=" + filePath
				+ ", fileCount=" + fileCount + ", createdDate=" + createdDate + ", createdUser=" + createdUser
				+ ", updatedDate=" + updatedDate + ", updatedUser=" + updatedUser + ", isDeleted=" + isDeleted
				+ ", fileAttachment=" + fileAttachment + "]";
	}

}
