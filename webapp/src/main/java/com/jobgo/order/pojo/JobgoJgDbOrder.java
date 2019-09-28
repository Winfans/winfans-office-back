package com.jobgo.order.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "jobgo_jg_db_order", schema = "jobgo_jg_db", catalog = "")
public class JobgoJgDbOrder {
    private long orderId;
    private int totalPay;
    private String entId;
    private String entName;
    private long conId;

    @Id
    @Column(name = "order_id", nullable = false)
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "total_pay", nullable = false)
    public int getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(int totalPay) {
        this.totalPay = totalPay;
    }

    @Basic
    @Column(name = "ent_id", nullable = false, length = 18)
    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    @Basic
    @Column(name = "ent_name", nullable = false, length = 50)
    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    @Basic
    @Column(name = "con_id", nullable = true)
    public long getConId() {
        return conId;
    }

    public void setConId(long conId) {
        this.conId = conId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobgoJgDbOrder order = (JobgoJgDbOrder) o;
        return orderId == order.orderId &&
                totalPay == order.totalPay &&
                conId == order.conId &&
                Objects.equals(entId, order.entId) &&
                Objects.equals(entName, order.entName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, totalPay, entId, entName, conId);
    }
}
