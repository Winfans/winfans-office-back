package com.jobgo.user.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "jobgo_jg_db_school", schema = "jobgo_jg_db", catalog = "")
public class JobgoJgDbSchool {
    private String schId;
    private int userId;
    private String schName;
    private String schLogo;
    private String schTraCer;
    private String schIntr;
    private String schHon;
    private String schAddr;

    @Id
    @Column(name = "sch_id", nullable = false, length = 18)
    public String getSchId() {
        return schId;
    }

    public void setSchId(String schId) {
        this.schId = schId;
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
    @Column(name = "sch_name", nullable = false, length = 50)
    public String getSchName() {
        return schName;
    }

    public void setSchName(String schName) {
        this.schName = schName;
    }

    @Basic
    @Column(name = "sch_logo", nullable = false, length = 255)
    public String getSchLogo() {
        return schLogo;
    }

    public void setSchLogo(String schLogo) {
        this.schLogo = schLogo;
    }

    @Basic
    @Column(name = "sch_tra_cer", nullable = false, length = 255)
    public String getSchTraCer() {
        return schTraCer;
    }

    public void setSchTraCer(String schTraCer) {
        this.schTraCer = schTraCer;
    }

    @Basic
    @Column(name = "sch_intr", nullable = true, length = 255)
    public String getSchIntr() {
        return schIntr;
    }

    public void setSchIntr(String schIntr) {
        this.schIntr = schIntr;
    }

    @Basic
    @Column(name = "sch_hon", nullable = true, length = 255)
    public String getSchHon() {
        return schHon;
    }

    public void setSchHon(String schHon) {
        this.schHon = schHon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobgoJgDbSchool that = (JobgoJgDbSchool) o;
        return userId == that.userId &&
                Objects.equals(schId, that.schId) &&
                Objects.equals(schName, that.schName) &&
                Objects.equals(schLogo, that.schLogo) &&
                Objects.equals(schTraCer, that.schTraCer) &&
                Objects.equals(schIntr, that.schIntr) &&
                Objects.equals(schHon, that.schHon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schId, userId, schName, schLogo, schTraCer, schIntr, schHon);
    }

    @Basic
    @Column(name = "sch_addr", nullable = false, length = 255)
    public String getSchAddr() {
        return schAddr;
    }

    public void setSchAddr(String schAddr) {
        this.schAddr = schAddr;
    }
}
