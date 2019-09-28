package com.jobgo.order.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "jobgo_jg_db_order_status", schema = "jobgo_jg_db", catalog = "")
public class JobgoJgDbOrderStatus {
    private long orderId;
    private int status;
    private Timestamp createdTime;
    private Timestamp paymentTime;
    private Timestamp endTime;
    private Timestamp closeTime;

    @Id
    @Column(name = "order_id", nullable = false)
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "created_time", nullable = true)
    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Basic
    @Column(name = "payment_time", nullable = true)
    public Timestamp getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Timestamp paymentTime) {
        this.paymentTime = paymentTime;
    }

    @Basic
    @Column(name = "end_time", nullable = true)
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "close_time", nullable = true)
    public Timestamp getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Timestamp closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobgoJgDbOrderStatus that = (JobgoJgDbOrderStatus) o;
        return orderId == that.orderId &&
                status == that.status &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(paymentTime, that.paymentTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(closeTime, that.closeTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, status, createdTime, paymentTime, endTime, closeTime);
    }
}
