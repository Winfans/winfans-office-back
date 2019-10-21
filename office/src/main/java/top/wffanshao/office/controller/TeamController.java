package top.wffanshao.office.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import top.wffanshao.office.dto.TeamDTO;
import top.wffanshao.office.enums.ExceptionEnum;
import top.wffanshao.office.exception.MyException;
import top.wffanshao.office.pojo.OfficeDbTeam;
import top.wffanshao.office.pojo.OfficeDbUser;
import top.wffanshao.office.pojo.OfficeDbUserTeam;
import top.wffanshao.office.service.TeamService;
import top.wffanshao.office.vo.ResponseResult;

import java.util.List;


/**
 * 描述：团队Controller
 *
 * @author 杨炜帆
 * @date 2019/10/12
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
     * @param teamId
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

    /**
     * 描述：根据团队id修改相对应的团队信息
     *
     * @param token
     * @param teamId
     * @param team
     * @return
     */
    @PutMapping("updateTeamByTeamId/{teamId}")
    public ResponseEntity<ResponseResult<Void>> updateTeamByTeamId(
            @CookieValue("OFFICE_TOKEN") String token,
            @PathVariable("teamId") Integer teamId,
            OfficeDbTeam team) {
        Boolean result = teamService.updateTeamByTeamId(token, teamId, team);
        if (!result) {
            throw new MyException(ExceptionEnum.TEAM_UPDATE_FAIL);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "修改成功"));
    }

    /**
     * 描述：添加成员
     *
     * @return
     */
    @PostMapping("addTeamUserByTeamId/{teamId}")
    public ResponseEntity<ResponseResult<Void>> addTeamUserByTeamId(
            @CookieValue("OFFICE_TOKEN") String token,
            @PathVariable("teamId") Integer teamId,
            @RequestParam("userNmae") String userName

    ) {
        boolean result = teamService.addTeamUserByTeamId(token, teamId, userName);

        if (!result) {
            throw new MyException(ExceptionEnum.USER_TEAM_ADD_FAIL);
        }
        return ResponseEntity.ok(new ResponseResult<>(201, "添加成功"));
    }


    /**
     * 描述：删除成员
     *
     * @param token
     * @param teamId
     * @param userId
     * @return
     */
    @DeleteMapping("deleteTeamUserByUserIdAndTeamId/{teamId}")
    public ResponseEntity<ResponseResult<Void>> deleteTeamUserByUserIdAndTeamId(
            @CookieValue("OFFICE_TOKEN") String token,
            @PathVariable("teamId") Integer teamId,
            @RequestParam("userId") Integer userId
    ) {

        boolean result = teamService.deleteTeamUserByUserIdAndTeamId(token, teamId, userId);

        if (!result) {
            throw new MyException(ExceptionEnum.USER_TEAM_DELETE_FAIL);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "删除成功"));
    }

    /**
     * 描述：根据团队id查找相对应的所有成员
     *
     * @param teamId
     * @return
     */
    @GetMapping("findAllUserTeamByTeamId/{teamId}")
    public ResponseEntity<ResponseResult<List<OfficeDbUser>>> findAllUserTeamByTeamId(@PathVariable("teamId") Integer teamId) {

        List<OfficeDbUser> userList = teamService.findAllUserTeamByTeamId(teamId);

        if (CollectionUtils.isEmpty(userList)) {
            throw new MyException(ExceptionEnum.USER_NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", userList));
    }


}
