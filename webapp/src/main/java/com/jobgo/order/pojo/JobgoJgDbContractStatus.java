package com.jobgo.order.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "jobgo_jg_db_contract_status", schema = "jobgo_jg_db", catalog = "")
public class JobgoJgDbContractStatus {
    private long conId;
    private int status;
    private Timestamp startTime;
    private Timestamp failTime;
    private Timestamp endTime;

    @Id
    @Column(name = "con_id", nullable = false)
    public long getConId() {
        return conId;
    }

    public void setConId(long conId) {
        this.conId = conId;
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
    @Column(name = "start_time", nullable = true)
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "fail_time", nullable = true)
    public Timestamp getFailTime() {
        return failTime;
    }

    public void setFailTime(Timestamp failTime) {
        this.failTime = failTime;
    }

    @Basic
    @Column(name = "end_time", nullable = true)
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobgoJgDbContractStatus that = (JobgoJgDbContractStatus) o;
        return conId == that.conId &&
                status == that.status &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(failTime, that.failTime) &&
                Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conId, status, startTime, failTime, endTime);
    }
}
