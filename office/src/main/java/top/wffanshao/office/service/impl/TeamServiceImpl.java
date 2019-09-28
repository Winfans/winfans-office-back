package top.wffanshao.office.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wffanshao.office.dao.TeamDAO;
import top.wffanshao.office.pojo.OfficeDbTeam;
import top.wffanshao.office.service.TeamService;

import java.sql.Timestamp;

/**
 * 描述：团队Service
 */
@Service
public final class TeamServiceImpl implements TeamService {


    @Autowired
    private TeamDAO teamDAO;

    /**
     * 描述：创建团队
     *
     * @param team
     * @return
     */
    @Override
    public Boolean createTeam(OfficeDbTeam team) {
        team.setFirstCreatedTime(new Timestamp(System.currentTimeMillis()));
        team.setLastUpdatedTime(new Timestamp(System.currentTimeMillis()));
        OfficeDbTeam savedTeam = teamDAO.save(team);
        if (savedTeam.getTeamId() == 0) {
            return false;
        }
        return true;
    }
}
