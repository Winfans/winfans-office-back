package top.wffanshao.office.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "office_db_user_team", schema = "office_db", catalog = "")
@IdClass(OfficeDbUserTeamPK.class)
public class OfficeDbUserTeam {
    private int userId;
    private int teamId;
    private int teamAdmin;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "team_id", nullable = false)
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "team_admin", nullable = false)
    public int getTeamAdmin() {
        return teamAdmin;
    }

    public void setTeamAdmin(int teamAdmin) {
        this.teamAdmin = teamAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeDbUserTeam that = (OfficeDbUserTeam) o;
        return userId == that.userId &&
                teamId == that.teamId &&
                teamAdmin == that.teamAdmin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, teamId, teamAdmin);
    }
}
