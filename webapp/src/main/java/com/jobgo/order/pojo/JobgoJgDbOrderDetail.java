package com.jobgo.order.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "jobgo_jg_db_order_detail", schema = "jobgo_jg_db", catalog = "")
public class JobgoJgDbOrderDetail {

    private long id;
    private long orderId;
    private long jobId;
    private String jobTitle;
    private int totalPay;
    private String jobShowImg;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order_id", nullable = false)
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "job_id", nullable = false)
    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "job_title", nullable = false, length = 50)
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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
    @Column(name = "job_show_img", nullable = true, length = 255)
    public String getJobShowImg() {
        return jobShowImg;
    }

    public void setJobShowImg(String jobShowImg) {
        this.jobShowImg = jobShowImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobgoJgDbOrderDetail that = (JobgoJgDbOrderDetail) o;
        return id == that.id &&
                orderId == that.orderId &&
                jobId == that.jobId &&
                totalPay == that.totalPay &&
                Objects.equals(jobTitle, that.jobTitle) &&
                Objects.equals(jobShowImg, that.jobShowImg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, jobId, jobTitle, totalPay, jobShowImg);
    }
}
