package top.wffanshao.office.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "office_db_team", schema = "office_db", catalog = "")
public class OfficeDbTeam {
    private int teamId;
    private String teamName;
    private int submenuId;
    private Timestamp firstCreatedTime;
    private Timestamp lastUpdatedTime;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id", nullable = false)
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
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
        return teamId == that.teamId &&
                submenuId == that.submenuId &&
                Objects.equals(teamName, that.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, teamName, submenuId);
    }



    @Basic
    @Column(name = "first_created_time", nullable = false)
    public Timestamp getFirstCreatedTime() {
        return firstCreatedTime;
    }

    public void setFirstCreatedTime(Timestamp firstCreatedTime) {
        this.firstCreatedTime = firstCreatedTime;
    }

    @Basic
    @Column(name = "last_updated_time", nullable = false)
    public Timestamp getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
