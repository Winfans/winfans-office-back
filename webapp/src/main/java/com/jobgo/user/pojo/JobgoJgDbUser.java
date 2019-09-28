package com.jobgo.user.pojo;

import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@ToString
@Table(name = "jobgo_jg_db_user", schema = "jobgo_jg_db", catalog = "")
public class JobgoJgDbUser {
    private int userId;
    private String userPhone;

    private String userPassword;

    private Integer userIdentified;
    @Length(max = 20, message = "用户名不能超过20个字符")
    private String userName;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private Integer isAdmin;
    private String heading;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_phone", nullable = false, length = 11)
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Basic
    @Column(name = "user_password", nullable = false, length = 60)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "user_identified", nullable = false)
    public Integer getUserIdentified() {
        return userIdentified;
    }

    public void setUserIdentified(int userIdentified) {
        this.userIdentified = userIdentified;
    }

    public void setUserIdentified(Integer userIdentified) {
        this.userIdentified = userIdentified;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 20)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobgoJgDbUser that = (JobgoJgDbUser) o;
        return userId == that.userId &&
                Objects.equals(userPhone, that.userPhone) &&
                Objects.equals(userPassword, that.userPassword) &&
                Objects.equals(userIdentified, that.userIdentified) &&
                Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userPhone, userPassword, userIdentified, userName);
    }

    @Basic
    @Column(name = "created_time", nullable = false)
    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Basic
    @Column(name = "updated_time", nullable = true)
    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Basic
    @Column(name = "is_admin", nullable = false)
    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Basic
    @Column(name = "heading", nullable = true, length = 255)
    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }
}
