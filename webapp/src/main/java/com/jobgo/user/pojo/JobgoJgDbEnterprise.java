package com.jobgo.user.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "jobgo_jg_db_enterprise", schema = "jobgo_jg_db", catalog = "")
public class JobgoJgDbEnterprise {
    private String entId;
    private int userId;
    private String entName;
    private String entAddr;
    private String entIntr;
    private String entHon;
    private String entLogo;
    private String entTraCer;
    private int type;

    @Id
    @Column(name = "ent_id", nullable = false, length = 18)
    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
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
    @Column(name = "ent_name", nullable = false, length = 50)
    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    @Basic
    @Column(name = "ent_addr", nullable = false, length = 255)
    public String getEntAddr() {
        return entAddr;
    }

    public void setEntAddr(String entAddr) {
        this.entAddr = entAddr;
    }

    @Basic
    @Column(name = "ent_intr", nullable = true, length = 255)
    public String getEntIntr() {
        return entIntr;
    }

    public void setEntIntr(String entIntr) {
        this.entIntr = entIntr;
    }

    @Basic
    @Column(name = "ent_hon", nullable = true, length = 255)
    public String getEntHon() {
        return entHon;
    }

    public void setEntHon(String entHon) {
        this.entHon = entHon;
    }

    @Basic
    @Column(name = "ent_logo", nullable = true, length = 255)
    public String getEntLogo() {
        return entLogo;
    }

    public void setEntLogo(String entLogo) {
        this.entLogo = entLogo;
    }

    @Basic
    @Column(name = "ent_tra_cer", nullable = false, length = 255)
    public String getEntTraCer() {
        return entTraCer;
    }

    public void setEntTraCer(String entTraCer) {
        this.entTraCer = entTraCer;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobgoJgDbEnterprise that = (JobgoJgDbEnterprise) o;
        return userId == that.userId &&
                type == that.type &&
                Objects.equals(entId, that.entId) &&
                Objects.equals(entName, that.entName) &&
                Objects.equals(entAddr, that.entAddr) &&
                Objects.equals(entIntr, that.entIntr) &&
                Objects.equals(entHon, that.entHon) &&
                Objects.equals(entLogo, that.entLogo) &&
                Objects.equals(entTraCer, that.entTraCer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entId, userId, entName, entAddr, entIntr, entHon, entLogo, entTraCer, type);
    }
}
