package top.wffanshao.office.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.wffanshao.office.enums.ExceptionEnum;
import top.wffanshao.office.exception.MyException;
import top.wffanshao.office.pojo.OfficeDbTeam;
import top.wffanshao.office.service.TeamService;
import top.wffanshao.office.vo.ResponseResult;


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
    @GetMapping("createTeam")
    public ResponseEntity<ResponseResult<Void>> createTeam(OfficeDbTeam team) {

        Boolean result = teamService.createTeam(team);

        if (!result) {
            throw new MyException(ExceptionEnum.TEAM_CREATE_FAIL);
        }

        return ResponseEntity.ok(new ResponseResult<>(201, "创建成功"));
    }

}
