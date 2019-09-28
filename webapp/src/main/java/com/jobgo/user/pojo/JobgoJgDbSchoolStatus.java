package com.jobgo.user.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "jobgo_jg_db_school_status", schema = "jobgo_jg_db", catalog = "")
public class JobgoJgDbSchoolStatus {
    private String schId;
    private int status;
    private Timestamp submitTime;
    private Timestamp passTime;
    private Timestamp logOutTime;
    private Timestamp lastFailTime;

    @Id
    @Column(name = "sch_id", nullable = false, length = 18)
    public String getSchId() {
        return schId;
    }

    public void setSchId(String schId) {
        this.schId = schId;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public int getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "submit_time", nullable = true)
    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    @Basic
    @Column(name = "pass_time", nullable = true)
    public Timestamp getPassTime() {
        return passTime;
    }

    public void setPassTime(Timestamp passTime) {
        this.passTime = passTime;
    }

    @Basic
    @Column(name = "log_out_time", nullable = true)
    public Timestamp getLogOutTime() {
        return logOutTime;
    }

    public void setLogOutTime(Timestamp logOutTime) {
        this.logOutTime = logOutTime;
    }

    @Basic
    @Column(name = "last_fail_time", nullable = true)
    public Timestamp getLastFailTime() {
        return lastFailTime;
    }

    public void setLastFailTime(Timestamp lastFailTime) {
        this.lastFailTime = lastFailTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobgoJgDbSchoolStatus that = (JobgoJgDbSchoolStatus) o;
        return schId == that.schId &&
                status == that.status &&
                Objects.equals(submitTime, that.submitTime) &&
                Objects.equals(passTime, that.passTime) &&
                Objects.equals(logOutTime, that.logOutTime) &&
                Objects.equals(lastFailTime, that.lastFailTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schId, status, submitTime, passTime, logOutTime, lastFailTime);
    }
}
