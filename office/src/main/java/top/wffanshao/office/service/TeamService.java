package top.wffanshao.office.service;


import top.wffanshao.office.pojo.OfficeDbTeam;

/**
 * 描述：团队Service
 */
public interface TeamService {

    /**
     * 描述：创建团队
     *
     * @param team
     * @return
     */
    Boolean createTeam(OfficeDbTeam team);

}
