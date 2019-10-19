package top.wffanshao.office.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OfficeDbWrittenPK implements Serializable {
    private int customerId;
    private int userId;

    @Column(name = "customer_id", nullable = false)
    @Id
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Column(name = "user_id", nullable = false)
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeDbWrittenPK that = (OfficeDbWrittenPK) o;
        return customerId == that.customerId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, userId);
    }
}
