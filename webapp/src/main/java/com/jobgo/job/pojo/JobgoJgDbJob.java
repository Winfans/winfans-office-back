package com.jobgo.job.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "jobgo_jg_db_job", schema = "jobgo_jg_db", catalog = "")
public class JobgoJgDbJob {
    private int jobId;
    private String jobTitle;
    private String jobAddr;
    private String jobTime;
    private Integer jobSalary;
    private String jobTreatment;
    private String jobRequirement;
    private int jobType;
    private Integer jobEmployeeNum;
    private Timestamp createdTime;
    private String entId;

    @Id
    @Column(name = "job_id", nullable = false)
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
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
    @Column(name = "job_addr", nullable = true, length = 255)
    public String getJobAddr() {
        return jobAddr;
    }

    public void setJobAddr(String jobAddr) {
        this.jobAddr = jobAddr;
    }

    @Basic
    @Column(name = "job_time", nullable = true, length = 255)
    public String getJobTime() {
        return jobTime;
    }

    public void setJobTime(String jobTime) {
        this.jobTime = jobTime;
    }

    @Basic
    @Column(name = "job_salary", nullable = true)
    public Integer getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(Integer jobSalary) {
        this.jobSalary = jobSalary;
    }

    @Basic
    @Column(name = "job_treatment", nullable = true, length = 255)
    public String getJobTreatment() {
        return jobTreatment;
    }

    public void setJobTreatment(String jobTreatment) {
        this.jobTreatment = jobTreatment;
    }

    @Basic
    @Column(name = "job_requirement", nullable = true, length = 255)
    public String getJobRequirement() {
        return jobRequirement;
    }

    public void setJobRequirement(String jobRequirement) {
        this.jobRequirement = jobRequirement;
    }

    @Basic
    @Column(name = "job_type", nullable = false)
    public int getJobType() {
        return jobType;
    }

    public void setJobType(int jobType) {
        this.jobType = jobType;
    }

    @Basic
    @Column(name = "job_employee_num", nullable = true)
    public Integer getJobEmployeeNum() {
        return jobEmployeeNum;
    }

    public void setJobEmployeeNum(Integer jobEmployeeNum) {
        this.jobEmployeeNum = jobEmployeeNum;
    }

    @Basic
    @Column(name = "created_time", nullable = true)
    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobgoJgDbJob that = (JobgoJgDbJob) o;
        return jobId == that.jobId &&
                jobType == that.jobType &&
                Objects.equals(jobTitle, that.jobTitle) &&
                Objects.equals(jobAddr, that.jobAddr) &&
                Objects.equals(jobTime, that.jobTime) &&
                Objects.equals(jobSalary, that.jobSalary) &&
                Objects.equals(jobTreatment, that.jobTreatment) &&
                Objects.equals(jobRequirement, that.jobRequirement) &&
                Objects.equals(jobEmployeeNum, that.jobEmployeeNum) &&
                Objects.equals(createdTime, that.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, jobTitle, jobAddr, jobTime, jobSalary, jobTreatment, jobRequirement, jobType, jobEmployeeNum, createdTime);
    }

    @Basic
    @Column(name = "ent_id", nullable = false, length = 18)
    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }
}
