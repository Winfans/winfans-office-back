package top.wffanshao.office.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "office_db_main_menu", schema = "office_db", catalog = "")
public class OfficeDbMainMenu {
    private int mainMenuId;
    private String mainMenuName;

    @Id
    @Column(name = "main_menu_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getMainMenuId() {
        return mainMenuId;
    }

    public void setMainMenuId(int mainMenuId) {
        this.mainMenuId = mainMenuId;
    }

    @Basic
    @Column(name = "main_menu_name", nullable = false, length = 20)
    public String getMainMenuName() {
        return mainMenuName;
    }

    public void setMainMenuName(String mainMenuName) {
        this.mainMenuName = mainMenuName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeDbMainMenu that = (OfficeDbMainMenu) o;
        return mainMenuId == that.mainMenuId &&
                Objects.equals(mainMenuName, that.mainMenuName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainMenuId, mainMenuName);
    }
}
