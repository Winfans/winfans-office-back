package top.wffanshao.office.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "office_db_team", schema = "office_db", catalog = "")
public class OfficeDbTeam {
    private int teamIdteamId;
    private String teamName;
    private int submenuId;

    @Id
    @Column(name = "team_idteam_id", nullable = false)
    public int getTeamIdteamId() {
        return teamIdteamId;
    }

    public void setTeamIdteamId(int teamIdteamId) {
        this.teamIdteamId = teamIdteamId;
    }

    @Basic
    @Column(name = "team_name", nullable = false, length = 50)
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Basic
    @Column(name = "submenu_id", nullable = false)
    public int getSubmenuId() {
        return submenuId;
    }

    public void setSubmenuId(int submenuId) {
        this.submenuId = submenuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeDbTeam that = (OfficeDbTeam) o;
        return teamIdteamId == that.teamIdteamId &&
                submenuId == that.submenuId &&
                Objects.equals(teamName, that.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamIdteamId, teamName, submenuId);
    }
}
