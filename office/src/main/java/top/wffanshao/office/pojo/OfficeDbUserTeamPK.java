package top.wffanshao.office.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OfficeDbUserTeamPK implements Serializable {
    private int userId;
    private int teamId;

    @Column(name = "user_id", nullable = false)
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "team_id", nullable = false)
    @Id
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeDbUserTeamPK that = (OfficeDbUserTeamPK) o;
        return userId == that.userId &&
                teamId == that.teamId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, teamId);
    }
}
