package top.wffanshao.office.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "office_db_contact", schema = "office_db", catalog = "")
@IdClass(OfficeDbContactPK.class)
public class OfficeDbContact {
    private int userId;
    private int customerId;
    private Timestamp contactTime;
    private String detail;
    private String content;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "customer_id", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "contact_time", nullable = false)
    public Timestamp getContactTime() {
        return contactTime;
    }

    public void setContactTime(Timestamp contactTime) {
        this.contactTime = contactTime;
    }

    @Basic
    @Column(name = "detail", nullable = false, length = 255)
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeDbContact that = (OfficeDbContact) o;
        return userId == that.userId &&
                customerId == that.customerId &&
                Objects.equals(contactTime, that.contactTime) &&
                Objects.equals(detail, that.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, customerId, contactTime, detail);
    }

    @Basic
    @Column(name = "content", nullable = false, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
