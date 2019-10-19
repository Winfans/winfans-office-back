package top.wffanshao.office.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.wffanshao.office.bo.UserInfo;
import top.wffanshao.office.dao.TeamDAO;
import top.wffanshao.office.dao.UserTeamDAO;
import top.wffanshao.office.dto.TeamDTO;
import top.wffanshao.office.enums.ExceptionEnum;
import top.wffanshao.office.exception.MyException;
import top.wffanshao.office.pojo.OfficeDbSubmenu;
import top.wffanshao.office.pojo.OfficeDbTeam;
import top.wffanshao.office.pojo.OfficeDbUserTeam;
import top.wffanshao.office.properties.JwtProperties;
import top.wffanshao.office.service.MenuService;
import top.wffanshao.office.service.TeamService;
import top.wffanshao.office.utils.JwtUtils;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/**
 * 描述：团队Service
 */
@Slf4j
@Service
public class TeamServiceImpl implements TeamService {


    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private UserTeamDAO userTeamDAO;

    @Autowired
    private MenuService menuService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 描述：创建团队
     *
     * @param team
     * @param token
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createTeam(OfficeDbTeam team, String token) {

        // 解析token获取用户id
        UserInfo userInfo = null;
        try {
            userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            log.error("[团队服务] 解析用户token失败{}", e);
            throw new MyException(ExceptionEnum.TEAM_CREATE_FAIL);
        }

        // 创建团队
        team.setFirstCreatedTime(new Timestamp(System.currentTimeMillis()));
        team.setLastUpdatedTime(new Timestamp(System.currentTimeMillis()));
        OfficeDbTeam savedTeam = teamDAO.saveAndFlush(team);
        if (savedTeam.getTeamId() == 0) {
            log.error("[团队服务] 团队创建失败");
            throw new MyException(ExceptionEnum.PARSE_USER_TOKEN_FAIL);
        }

        try {
            // 将团队和用户关联起来，并将用户设置为团队管理员
            OfficeDbUserTeam userTeam = new OfficeDbUserTeam();
            userTeam.setUserId(userInfo.getUserId());
            userTeam.setTeamId(team.getTeamId());
            userTeam.setTeamAdmin(1);
            userTeamDAO.saveAndFlush(userTeam);
        } catch (Exception e) {
            log.error("[团队服务] 团队和用户关联失败{}", e);
            throw new MyException(ExceptionEnum.USER_TEAM_UNION_FAIL);
        }

        return true;
    }

    /**
     * 描述：查找一个用户的所有团队
     *
     * @param token
     * @return
     */
    @Override
    public List<TeamDTO> findAllTeam(String token) {
        // 解析token获取用户id
        UserInfo userInfo = null;
        try {
            userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            log.error("[团队服务] 解析用户token失败{}", e);
            return null;
        }

        List<TeamDTO> teams = new LinkedList<>();


        List<OfficeDbUserTeam> userTeams = userTeamDAO.findAllByUserId(userInfo.getUserId());

        userTeams.forEach(userTeam -> {
            OfficeDbTeam team = this.findTeamByTeamId(userTeam.getTeamId());
            OfficeDbSubmenu submenu = menuService.findSubmenuBySubmenuId(team.getSubmenuId());
            TeamDTO teamDTO = new TeamDTO();
            teamDTO.setTeamId(userTeam.getTeamId());
            teamDTO.setTeamName(team.getTeamName());
            teamDTO.setSubmenuName(submenu.getSubmenuName());
            teamDTO.setSubmenuIconBgColor(submenu.getSubmenuIconBgColor());
            teamDTO.setHomePath(submenu.getHomePath());
            teamDTO.setSubPath(submenu.getSubPath());
            teams.add(teamDTO);
        });
        return teams;
    }

    /**
     * 描述：根据团队id查找相对应的团队信息
     *
     * @param teamId
     * @return
     */
    @Override
    public OfficeDbTeam findTeamByTeamId(Integer teamId) {
        return teamDAO.findById(teamId).orElse(null);
    }
}
