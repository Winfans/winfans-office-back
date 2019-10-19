package top.wffanshao.office.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "office_db_user_role", schema = "office_db", catalog = "")
@IdClass(OfficeDbUserRolePK.class)
public class OfficeDbUserRole {
    private int userId;
    private int roleId;
    private int status;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "role_id", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeDbUserRole that = (OfficeDbUserRole) o;
        return userId == that.userId &&
                roleId == that.roleId &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId, status);
    }
}
