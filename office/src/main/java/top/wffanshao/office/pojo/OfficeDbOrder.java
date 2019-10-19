package top.wffanshao.office.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "office_db_order", schema = "office_db", catalog = "")
@IdClass(OfficeDbOrderPK.class)
public class OfficeDbOrder {
    private int userId;
    private int customerId;
    private Double money;
    private String detail;

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
    @Column(name = "money", nullable = true, precision = 0)
    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Basic
    @Column(name = "detail", nullable = true, length = 255)
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
        OfficeDbOrder that = (OfficeDbOrder) o;
        return userId == that.userId &&
                customerId == that.customerId &&
                Objects.equals(money, that.money) &&
                Objects.equals(detail, that.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, customerId, money, detail);
    }
}
