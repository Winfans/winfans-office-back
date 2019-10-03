package top.wffanshao.office.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import top.wffanshao.office.dto.TeamDTO;
import top.wffanshao.office.enums.ExceptionEnum;
import top.wffanshao.office.exception.MyException;
import top.wffanshao.office.pojo.OfficeDbTeam;
import top.wffanshao.office.pojo.OfficeDbUserTeam;
import top.wffanshao.office.service.TeamService;
import top.wffanshao.office.vo.ResponseResult;

import java.util.List;


/**
 * 描述：团队Controller
 */
@RestController
@RequestMapping("team")
public final class TeamController {

    @Autowired
    private TeamService teamService;

    /**
     * 描述：创建团队
     *
     * @return
     */
    @PostMapping("createTeam")
    public ResponseEntity<ResponseResult<Void>> createTeam(OfficeDbTeam team, @CookieValue("OFFICE_TOKEN") String token) {
        teamService.createTeam(team, token);
        return ResponseEntity.ok(new ResponseResult<>(201, "创建成功"));
    }

    /**
     * 描述：查找一个用户的所有团队
     *
     * @return
     */
    @GetMapping("findAllTeam")
    public ResponseEntity<ResponseResult<List<TeamDTO>>> findAllTeam(@CookieValue("OFFICE_TOKEN") String token) {
        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", teamService.findAllTeam(token)));
    }


    /**
     * 描述：根据团队id查找相对应的团队信息
     *
     * @return
     */
    @GetMapping("findTeamByTeamId/{teamId}")
    public ResponseEntity<ResponseResult<OfficeDbTeam>> findTeamByTeamId(@PathVariable("teamId") Integer teamId) {
        OfficeDbTeam team = teamService.findTeamByTeamId(teamId);
        if (team == null) {
            throw new MyException(ExceptionEnum.TEAM_NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", team));
    }
}
