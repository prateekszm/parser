package parser.entity;
/*
 * this class is for the details of file format  and which class is responsible for parsing the pdf or excel
 * 
*/

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="file_format_parser")
public class FileFormatParser {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="file_format_parser_id")
	private Integer fileFormatParserId;
	@OneToOne
	@JoinColumn(name="fk_account_id")
	private Account account;
	@OneToOne
	@JoinColumn(name="file_format_id")
	private FileFormat fileFormat;
	@Column(name="file_type")
	private String fileType;
	@Column(name="parser_class")
	private String parserClass;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "created_user")
	private String createdUser;
	@Column(name = "updated_date")
	private transient Date updatedDate;
	@Column(name = "updated_user")
	private String updatedUser;
	@Column(name = "is_deleted" ,columnDefinition = "BOOLEAN")
	private Boolean isDeleted;
	public Integer getFileFormatParserId() {
		return fileFormatParserId;
	}
	public void setFileFormatParserId(Integer fileFormatParserId) {
		this.fileFormatParserId = fileFormatParserId;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public FileFormat getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(FileFormat fileFormat) {
		this.fileFormat = fileFormat;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getParserClass() {
		return parserClass;
	}
	public void setParserClass(String parserClass) {
		this.parserClass = parserClass;
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
