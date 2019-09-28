package com.jobgo.job.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "jobgo_jg_db_job_status", schema = "jobgo_jg_db", catalog = "")
public class JobgoJgDbJobStatus {
    private long jobId;
    private int status;
    private Timestamp submitTime;
    private Timestamp passTime;
    private Timestamp lastFailTime;

    @Id
    @Column(name = "job_id", nullable = false)
    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
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
        JobgoJgDbJobStatus that = (JobgoJgDbJobStatus) o;
        return jobId == that.jobId &&
                status == that.status &&
                Objects.equals(submitTime, that.submitTime) &&
                Objects.equals(passTime, that.passTime) &&
                Objects.equals(lastFailTime, that.lastFailTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, status, submitTime, passTime, lastFailTime);
    }
}
