package top.wffanshao.office.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OfficeDbOrderPK implements Serializable {
    private int userId;
    private int customerId;

    @Column(name = "user_id", nullable = false)
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "customer_id", nullable = false)
    @Id
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeDbOrderPK that = (OfficeDbOrderPK) o;
        return userId == that.userId &&
                customerId == that.customerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, customerId);
    }
}
