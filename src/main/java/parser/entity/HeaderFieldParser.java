package parser.entity;

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

/*
 * this class is for the header data that file format have
*/
@Entity
@Table(name="header_field_parser")
public class HeaderFieldParser {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="header_field_parser_id")
	private Integer HeaderFieldParserId;
	@ManyToOne(fetch =  FetchType.EAGER)
	@JoinColumn(name = "fk_file_format")
	private FileFormat fileFormat;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_account_id")
	private  Account account;
	@Column(name="key_identifier",columnDefinition = "BOOLEAN")
	private Boolean keyIdentifier;
	@Column(name = "regex_pattern",columnDefinition="TEXT")
	private String regexPattern;
	@Column(name="field_name")
	private String fieldName;
	@Column(name = "created_date")
	private transient Date createdDate;
	@Column(name = "created_user")
	private transient String createdUser;
	@Column(name = "updated_date")
	private transient Date updatedDate;
	@Column(name = "updated_user")
	private transient String updatedUser;
	@Column(name = "is_deleted" ,columnDefinition = "BOOLEAN")
	private Boolean isDeleted;
	public Integer getHeaderFieldParserId() {
		return HeaderFieldParserId;
	}
	public void setHeaderFieldParserId(Integer headerFieldParserId) {
		HeaderFieldParserId = headerFieldParserId;
	}
	public FileFormat getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(FileFormat fileFormat) {
		this.fileFormat = fileFormat;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Boolean getKeyIdentifier() {
		return keyIdentifier;
	}
	public void setKeyIdentifier(Boolean keyIdentifier) {
		this.keyIdentifier = keyIdentifier;
	}
	public String getRegexPattern() {
		return regexPattern;
	}
	public void setRegexPattern(String regexPattern) {
		this.regexPattern = regexPattern;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
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
