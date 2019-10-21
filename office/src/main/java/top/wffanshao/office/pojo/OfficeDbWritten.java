package top.wffanshao.office.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "office_db_written", schema = "office_db", catalog = "")
public class OfficeDbWritten {
    private int customerId;
    private int userId;
    private String detail;
    private Timestamp createTime;
    private Double money;

    private int writtenId;

    @Basic
    @Column(name = "customer_id", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "detail", nullable = true, length = 255)
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "money", nullable = true, precision = 0)
    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeDbWritten that = (OfficeDbWritten) o;
        return customerId == that.customerId &&
                userId == that.userId &&
                Objects.equals(detail, that.detail) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(money, that.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, userId, detail, createTime, money);
    }

    @Id
    @Column(name = "written_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getWrittenId() {
        return writtenId;
    }

    public void setWrittenId(int writtenId) {
        this.writtenId = writtenId;
    }
}
