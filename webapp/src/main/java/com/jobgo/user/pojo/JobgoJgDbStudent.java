package com.jobgo.user.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "jobgo_jg_db_student", schema = "jobgo_jg_db", catalog = "")
public class JobgoJgDbStudent {
    private String stuId;
    private int userId;
    private String stuName;
    private String stuMajor;
    private Integer stuSex;
    private String stuPerExp;
    private String stuClass;
    private Integer stuAge;
    private Timestamp authPassTime;
    private String stuCardFront;
    private String stuCardBack;
    private String stuApartment;

    @Id
    @Column(name = "stu_id", nullable = false, length = 10)
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "stu_name", nullable = false, length = 20)
    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @Basic
    @Column(name = "stu_major", nullable = true, length = 50)
    public String getStuMajor() {
        return stuMajor;
    }

    public void setStuMajor(String stuMajor) {
        this.stuMajor = stuMajor;
    }

    @Basic
    @Column(name = "stu_sex", nullable = true)
    public Integer getStuSex() {
        return stuSex;
    }

    public void setStuSex(Integer stuSex) {
        this.stuSex = stuSex;
    }

    @Basic
    @Column(name = "stu_per_exp", nullable = true, length = 255)
    public String getStuPerExp() {
        return stuPerExp;
    }

    public void setStuPerExp(String stuPerExp) {
        this.stuPerExp = stuPerExp;
    }

    @Basic
    @Column(name = "stu_class", nullable = true, length = 50)
    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    @Basic
    @Column(name = "stu_age", nullable = true)
    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    @Basic
    @Column(name = "auth_pass_time", nullable = true)
    public Timestamp getAuthPassTime() {
        return authPassTime;
    }

    public void setAuthPassTime(Timestamp authPassTime) {
        this.authPassTime = authPassTime;
    }

    @Basic
    @Column(name = "stu_card_front", nullable = true, length = 255)
    public String getStuCardFront() {
        return stuCardFront;
    }

    public void setStuCardFront(String stuCardFront) {
        this.stuCardFront = stuCardFront;
    }

    @Basic
    @Column(name = "stu_card_back", nullable = true, length = 255)
    public String getStuCardBack() {
        return stuCardBack;
    }

    public void setStuCardBack(String stuCardBack) {
        this.stuCardBack = stuCardBack;
    }

    @Basic
    @Column(name = "stu_apartment", nullable = true, length = 50)
    public String getStuApartment() {
        return stuApartment;
    }

    public void setStuApartment(String stuApartment) {
        this.stuApartment = stuApartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobgoJgDbStudent that = (JobgoJgDbStudent) o;
        return userId == that.userId &&
                Objects.equals(stuId, that.stuId) &&
                Objects.equals(stuName, that.stuName) &&
                Objects.equals(stuMajor, that.stuMajor) &&
                Objects.equals(stuSex, that.stuSex) &&
                Objects.equals(stuPerExp, that.stuPerExp) &&
                Objects.equals(stuClass, that.stuClass) &&
                Objects.equals(stuAge, that.stuAge) &&
                Objects.equals(authPassTime, that.authPassTime) &&
                Objects.equals(stuCardFront, that.stuCardFront) &&
                Objects.equals(stuCardBack, that.stuCardBack) &&
                Objects.equals(stuApartment, that.stuApartment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, userId, stuName, stuMajor, stuSex, stuPerExp, stuClass, stuAge, authPassTime, stuCardFront, stuCardBack, stuApartment);
    }
}
