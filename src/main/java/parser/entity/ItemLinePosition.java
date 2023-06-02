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

import org.hibernate.annotations.Fetch;


@Entity
@Table(name = "item_line_position")
public class ItemLinePosition {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "item_line_position_id")
	private Integer itemLinePositionId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_item_line_pattern")
	private ItemLinePattern itemLinePattern;
	@Column(name = "regex_pattern",columnDefinition="TEXT")
	private String regexPattern;
	@Column(name="field_name")
	private String fieldName;
	@Column(name="item_line_position")
	private Integer itemLinePosition;
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
	public Integer getItemLinePositionId() {
		return itemLinePositionId;
	}
	public void setItemLinePositionId(Integer itemLinePositionId) {
		this.itemLinePositionId = itemLinePositionId;
	}
	public ItemLinePattern getItemLinePattern() {
		return itemLinePattern;
	}
	public void setItemLinePattern(ItemLinePattern itemLinePattern) {
		this.itemLinePattern = itemLinePattern;
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
	public Integer getItemLinePosition() {
		return itemLinePosition;
	}
	public void setItemLinePosition(Integer itemLinePosition) {
		this.itemLinePosition = itemLinePosition;
	}
	@Override
	public String toString() {
		return "ItemLinePosition [itemLinePositionId=" + itemLinePositionId + ", itemLinePattern=" + itemLinePattern
				+ ", regexPattern=" + regexPattern + ", fieldName=" + fieldName + ", createdDate=" + createdDate
				+ ", createdUser=" + createdUser + ", updatedDate=" + updatedDate + ", updatedUser=" + updatedUser
				+ ", isDeleted=" + isDeleted + "]";
	}
}
