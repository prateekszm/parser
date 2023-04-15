package parser.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;

import com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type;

/*
 * this class will have details about the data
*/
@Entity
@Table(name = "claim")
public class Claim {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "claim_id")
	private Integer claimId;
	@OneToOne
	@JoinColumn(name = "fk_account_id")
	private Account account;
	@OneToOne
	@JoinColumn(name = "fk_file_format")  //In future change this into string with name
	private FileFormat fileFormat;
	@Column(name = "data_claim_key")
	private String dataClaimKey;
	@Column(name = "header_level_data",columnDefinition="TEXT")
	private String headerLevelData;
	@Column(name = "item_level_data",columnDefinition="TEXT")
	private String ItemLevelData;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "created_user")
	private String createdUser;
	@Column(name = "updated_date")
	private Date updatedDate;
	@Column(name = "updated_user")
	private String updatedUser;
	@Column(name = "is_deleted" ,columnDefinition = "BOOLEAN")
	private Boolean isDeleted;
	
	public Integer getClaimId() {
		return claimId;
	}
	public void setClaimId(Integer claimId) {
		this.claimId = claimId;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getDataClaimKey() {
		return dataClaimKey;
	}
	public void setDataClaimKey(String dataClaimKey) {
		this.dataClaimKey = dataClaimKey;
	}
	public FileFormat getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(FileFormat fileFormat) {
		this.fileFormat = fileFormat;
	}
	public String getHeaderLevelData() {
		return headerLevelData;
	}
	public void setHeaderLevelData(String headerLevelData) {
		this.headerLevelData = headerLevelData;
	}
	public String getItemLevelData() {
		return ItemLevelData;
	}
	public void setItemLevelData(String itemLevelData) {
		ItemLevelData = itemLevelData;
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
}
