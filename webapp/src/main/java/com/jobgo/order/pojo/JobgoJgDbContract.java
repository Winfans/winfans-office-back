package com.jobgo.order.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "jobgo_jg_db_contract", schema = "jobgo_jg_db", catalog = "")
public class JobgoJgDbContract {
    private long conId;
    private long jobId;
    private String stuId;
    private String entId;

    @Id
    @Column(name = "con_id", nullable = false)
    public long getConId() {
        return conId;
    }

    public void setConId(long conId) {
        this.conId = conId;
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
    @Column(name = "stu_id", nullable = false, length = 10)
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Basic
    @Column(name = "ent_id", nullable = false, length = 18)
    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobgoJgDbContract that = (JobgoJgDbContract) o;
        return conId == that.conId &&
                jobId == that.jobId &&
                Objects.equals(stuId, that.stuId) &&
                Objects.equals(entId, that.entId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conId, jobId, stuId, entId);
    }
}
