package parser.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * this class will contains item line that will separate out with parent and child Regex style
*/
@Entity
@Table(name="item_line_pattern")
public class ItemLinePattern {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "item_line_pattern_id")
	private Integer itemLinePatternId;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_account_id")
	private Account account;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_file_format")
	private FileFormat fileFormat;
	@Column(name = "regex_pattern",columnDefinition="TEXT")
	private String regexPattern;
	@Column(name="sequence")
	private Integer sequence;
	@Column(name="fk_item_line_pattern_parrent_id")
	private Integer itemLinePatternParrentId;
	@Column(name = "created_date")
	private transient Date createdDate;
	@Column(name = "created_user")
	private transient String createdUser;
	@Column(name = "updated_date")
	private transient Date updatedDate;
	@Column(name = "updated_user")
	private  transient String updatedUser;
	@Column(name = "is_deleted" ,columnDefinition = "BOOLEAN")
	private Boolean isDeleted;
	
	@OneToMany(mappedBy = "itemLinePattern", fetch = FetchType.LAZY)
	Set<ItemLinePosition> itemLinePositionSet = new HashSet<ItemLinePosition>(); 
	
	public Integer getItemLinePatternId() {
		return itemLinePatternId;
	}
	public void setItemLinePatternId(Integer itemLinePatternId) {
		this.itemLinePatternId = itemLinePatternId;
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
	public String getRegexPattern() {
		return regexPattern;
	}
	public void setRegexPattern(String regexPattern) {
		this.regexPattern = regexPattern;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public Integer getItemLinePatternParrentId() {
		return itemLinePatternParrentId;
	}
	public void setItemLinePatternParrentId(Integer itemLinePatternParrentId) {
		this.itemLinePatternParrentId = itemLinePatternParrentId;
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
	
	public Set<ItemLinePosition> getItemLinePositionSet() {
		return itemLinePositionSet;
	}
	public void setItemLinePositionSet(Set<ItemLinePosition> itemLinePositionSet) {
		this.itemLinePositionSet = itemLinePositionSet;
	}
	@Override
	public String toString() {
		return "ItemLinePattern [itemLinePatternId=" + itemLinePatternId + ", account=" + account + ", fileFormat="
				+ fileFormat + ", regexPattern=" + regexPattern + ", sequence=" + sequence
				+ ", itemLinePatternParrentId=" + itemLinePatternParrentId + ", createdDate=" + createdDate
				+ ", createdUser=" + createdUser + ", updatedDate=" + updatedDate + ", updatedUser=" + updatedUser
				+ ", isDeleted=" + isDeleted + ", itemLinePositionSet=" + itemLinePositionSet + "]";
	}
}
