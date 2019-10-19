package top.wffanshao.office.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "office_db_submenu", schema = "office_db", catalog = "")
public class OfficeDbSubmenu {
    private int submenuId;
    private int mainMenuId;
    private String submenuIcon;
    private String submenuIconBgColor;
    private String submenuName;
    private String submenuDescription;
    private String homePath;
    private String subPath;

    @Id
    @Column(name = "submenu_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getSubmenuId() {
        return submenuId;
    }

    public void setSubmenuId(int submenuId) {
        this.submenuId = submenuId;
    }

    @Basic
    @Column(name = "main_menu_id", nullable = false)
    public int getMainMenuId() {
        return mainMenuId;
    }

    public void setMainMenuId(int mainMenuId) {
        this.mainMenuId = mainMenuId;
    }

    @Basic
    @Column(name = "submenu_icon", nullable = false, length = 50)
    public String getSubmenuIcon() {
        return submenuIcon;
    }

    public void setSubmenuIcon(String submenuIcon) {
        this.submenuIcon = submenuIcon;
    }

    @Basic
    @Column(name = "submenu_icon_bg_color", nullable = false, length = 20)
    public String getSubmenuIconBgColor() {
        return submenuIconBgColor;
    }

    public void setSubmenuIconBgColor(String submenuIconBgColor) {
        this.submenuIconBgColor = submenuIconBgColor;
    }

    @Basic
    @Column(name = "submenu_name", nullable = false, length = 20)
    public String getSubmenuName() {
        return submenuName;
    }

    public void setSubmenuName(String submenuName) {
        this.submenuName = submenuName;
    }

    @Basic
    @Column(name = "submenu_description", nullable = false, length = 50)
    public String getSubmenuDescription() {
        return submenuDescription;
    }

    public void setSubmenuDescription(String submenuDescription) {
        this.submenuDescription = submenuDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeDbSubmenu that = (OfficeDbSubmenu) o;
        return submenuId == that.submenuId &&
                mainMenuId == that.mainMenuId &&
                Objects.equals(submenuIcon, that.submenuIcon) &&
                Objects.equals(submenuIconBgColor, that.submenuIconBgColor) &&
                Objects.equals(submenuName, that.submenuName) &&
                Objects.equals(submenuDescription, that.submenuDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(submenuId, mainMenuId, submenuIcon, submenuIconBgColor, submenuName, submenuDescription);
    }

    @Basic
    @Column(name = "home_path", nullable = true, length = 50)
    public String getHomePath() {
        return homePath;
    }

    public void setHomePath(String homePath) {
        this.homePath = homePath;
    }

    @Basic
    @Column(name = "sub_path", nullable = true, length = 50)
    public String getSubPath() {
        return subPath;
    }

    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }
}
