package top.wffanshao.office.service;


import top.wffanshao.office.dto.TeamDTO;
import top.wffanshao.office.pojo.OfficeDbTeam;
import top.wffanshao.office.pojo.OfficeDbUserTeam;

import java.util.List;

/**
 * 描述：团队Service
 */
public interface TeamService {

    /**
     * 描述：创建团队
     *
     * @param team
     * @param token
     * @return
     */
    Boolean createTeam(OfficeDbTeam team, String token);

    /**
     * 描述：查找一个用户的所有团队
     *
     * @param token
     * @return
     */
    List<TeamDTO> findAllTeam(String token);


    /**
     * 描述：根据团队id查找相对应的团队信息
     *
     * @return
     */
    OfficeDbTeam findTeamByTeamId(Integer teamId);
}
