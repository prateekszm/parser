package parser.entity;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/*
 * this class is for the file format that is being seted up for user like pdf format 1 2 and 3 this class will contains name of the format 
 * and which class is going to be used for parsing the text
 * id,tempelete name,tempelet regex,conveter class and conveter method
 * 
 * this class regex will match all data from the pdf
*/
@Entity
@Table(name="file_format")
public class FileFormat {
	@Id
	@GeneratedValue
	@Column(name="file_format_id")
	private Integer fileFormatId;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_account_id")
	private Account account;
	@Column(name="tempelete_name")
	private String tempeleteName;
	@Column(name = "tempelete_regex" ,columnDefinition="TEXT")
	private String tempeletRegex;
	@Column(name="text_extractor_class")
	private String textExtractorClass;
	@Column(name="text_extractor_method")
	private String textExtractormethod;
	@Column(name = "created_date")
	private transient Date createdDate;
	@Column(name = "created_user")
	private transient String createdUser;
	@Column(name = "updated_date")
	private transient Date updatedDate;
	@Column(name = "updated_user")
	private transient String updatedUser;
	@Column(name = "is_deleted",columnDefinition = "BOOLEAN")
	private Boolean isDeleted;


	//Add header  add item Line pattern and add item Line Position
	@OneToMany(mappedBy = "fileFormat", fetch = FetchType.EAGER)
	private Set<HeaderFieldParser> headerFieldParserSet = new HashSet<HeaderFieldParser>();
	@OneToMany(mappedBy = "fileFormat", fetch = FetchType.EAGER)
	private Set<ItemLinePattern> itemLinePatternSet = new HashSet<ItemLinePattern>();
//	@OneToOne(mappedBy = "fileFormatParser")
//	private FileFormatParser fileFormatParserId;
	

	public Integer getFileFormatId() {
		return fileFormatId;
	}
	public void setFileFormatId(Integer fileFormatId) {
		this.fileFormatId = fileFormatId;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getTempeleteName() {
		return tempeleteName;
	}
	public void setTempeleteName(String tempeleteName) {
		this.tempeleteName = tempeleteName;
	}
	public String getTempeletRegex() {
		return tempeletRegex;
	}
	public void setTempeletRegex(String tempeletRegex) {
		this.tempeletRegex = tempeletRegex;
	}
	public String getTextExtractorClass() {
		return textExtractorClass;
	}
	public void setTextExtractorClass(String textExtractorClass) {
		this.textExtractorClass = textExtractorClass;
	}
	public String getTextExtractormethod() {
		return textExtractormethod;
	}
	public void setTextExtractormethod(String textExtractormethod) {
		this.textExtractormethod = textExtractormethod;
	}
	@Transient
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
	public Set<HeaderFieldParser> getHeaderFieldParserSet() {
		return headerFieldParserSet;
	}

	public void setHeaderFieldParserSet(Set<HeaderFieldParser> headerFieldParserSet) {
		this.headerFieldParserSet = headerFieldParserSet;
	}

	public Set<ItemLinePattern> getItemLinePatternSet() {
		return itemLinePatternSet;
	}

	public void setItemLinePatternSet(Set<ItemLinePattern> itemLinePatternSet) {
		this.itemLinePatternSet = itemLinePatternSet;
	}

	@Override
	public String toString() {
		return "FileFormat [fileFormatId=" + fileFormatId + ", account=" + account + ", tempeleteName=" + tempeleteName
				+ ", tempeletRegex=" + tempeletRegex + ", textExtractorClass=" + textExtractorClass
				+ ", textExtractormethod=" + textExtractormethod + ", createdDate=" + createdDate + ", createdUser="
				+ createdUser + ", updatedDate=" + updatedDate + ", updatedUser=" + updatedUser + ", isDeleted="
				+ isDeleted + ", headerFieldParserSet=" + headerFieldParserSet + ", itemLinePatternSet="
				+ itemLinePatternSet + "]";
	}	
	
}
