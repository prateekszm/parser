package parser.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Integer accountId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    @Lob
    private String description;
    @Column(name = "created_date")
    private transient Date createdDate;
    @Column(name = "created_user")
    private transient String createdUser;
    @Column(name = "updated_date")
    private Date updatedDate;
    @Column(name = "updated_user")
    private transient String updatedUser;
    @Column(name = "is_deleted", columnDefinition = "BOOLEAN")
    private Boolean isDeleted;
    @Column(name = "email")
    private String email;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "Account [accountId=" + accountId + ", name=" + name + ", description=" + description + ", createdDate="
                + createdDate + ", createdUser=" + createdUser + ", updatedDate=" + updatedDate + ", updatedUser="
                + updatedUser + ", isDeleted=" + isDeleted + "]";
    }
}
