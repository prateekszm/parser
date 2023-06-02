package parser.entity;

import javax.persistence.*;
import java.util.Date;
/*Read the email and download the email and save the email in emailMetaData and put all the downloaded file in FileAttachment
* the path will be with starting like emailFrom/date/time/AllAttachment .pdf .xls .xlsV
* put the data in file attachment. The email manager will work seperately that file upload
* */
@Entity
@Table(name="email_meta_data")
public class EmailMetaData {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="email_meta_data_id")
    private Integer emailMetaDataId;
    @Column(name="email_from")
    private String emailFrom;
    @Column(name="numberOfAttachment")
    private Integer numberOfAttachment;
    @Column(name="subject",columnDefinition = "text")
    private String subject;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "is_deleted", columnDefinition = "BOOLEAN")
    private Boolean isDeleted;

}
