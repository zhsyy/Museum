package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "emails", schema = "2019summervacation")
public class EmailsEntity {
    private int emailId;
    private String senderName;
    private String receiverName;
    private String title;
    private String content;
    private Timestamp time;

    public EmailsEntity() {
    }

    public EmailsEntity(String senderName, String receiverName, String title, String content) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.title = title;
        this.content = content;
    }

    @Id
    @Column(name = "emailId")
    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    @Basic
    @Column(name = "senderName")
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Basic
    @Column(name = "receiverName")
    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailsEntity that = (EmailsEntity) o;
        return emailId == that.emailId &&
                Objects.equals(senderName, that.senderName) &&
                Objects.equals(receiverName, that.receiverName) &&
                Objects.equals(title, that.title) &&
                Objects.equals(content, that.content) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, senderName, receiverName, title, content, time);
    }
}
