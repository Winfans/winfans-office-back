package top.wffanshao.office.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "office_db_user_customer", schema = "office_db", catalog = "")
@IdClass(OfficeDbUserCustomerPK.class)
public class OfficeDbUserCustomer {
    private int userId;
    private int customerId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeDbUserCustomer that = (OfficeDbUserCustomer) o;
        return userId == that.userId &&
                customerId == that.customerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, customerId);
    }
}
