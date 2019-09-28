package top.wffanshao.office.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "office_db_user", schema = "office_db", catalog = "")
public class OfficeDbUser {
    private int userId;
    private String userName;
    private String userPasswd;
    private int admin;
    private Timestamp registerTime;
    private Timestamp firstLoginTime;
    private Timestamp lastLoginTime;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_passwd", nullable = false, length = 60)
    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    @Basic
    @Column(name = "admin", nullable = false)
    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeDbUser that = (OfficeDbUser) o;
        return userId == that.userId &&
                admin == that.admin &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userPasswd, that.userPasswd) &&
                Objects.equals(registerTime, that.registerTime) &&
                Objects.equals(firstLoginTime, that.firstLoginTime) &&
                Objects.equals(lastLoginTime, that.lastLoginTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userPasswd, admin, registerTime, firstLoginTime, lastLoginTime);
    }

    @Basic
    @Column(name = "register_time", nullable = false)
    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    @Basic
    @Column(name = "first_login_time", nullable = true)
    public Timestamp getFirstLoginTime() {
        return firstLoginTime;
    }

    public void setFirstLoginTime(Timestamp firstLoginTime) {
        this.firstLoginTime = firstLoginTime;
    }

    @Basic
    @Column(name = "last_login_time", nullable = true)
    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
