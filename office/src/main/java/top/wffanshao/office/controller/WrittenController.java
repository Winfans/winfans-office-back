package top.wffanshao.office.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.wffanshao.office.dto.WrittenDTO;
import top.wffanshao.office.enums.ExceptionEnum;
import top.wffanshao.office.exception.MyException;
import top.wffanshao.office.pojo.OfficeDbWritten;
import top.wffanshao.office.service.WrittenService;
import top.wffanshao.office.vo.ResponsePage;
import top.wffanshao.office.vo.ResponseResult;


/**
 * 描述：签单记录Controller
 *
 * @author 杨炜帆
 * @date 2019/10/12
 */
@RestController
@RequestMapping("written")
public class WrittenController {

    @Autowired
    private WrittenService writtenService;

    /**
     * 描述：分页查询团队下所有的签单记录
     *
     * @param teamId
     * @param page   当前页数
     * @param size   总叶数
     * @return
     */
    @GetMapping("findAllWrittenByPage/{teamId}/{page}/{size}")
    public ResponseEntity<ResponseResult<ResponsePage<WrittenDTO>>> findAllWrittenByPage(@PathVariable("teamId") int teamId,
                                                                                         @PathVariable("page") int page,
                                                                                         @PathVariable("size") int size) {
        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", writtenService.findAllWrittenByPage(teamId, page, size)));
    }

    /**
     * 描述：根据writtenId查询签单记录
     *
     * @param writtenId
     * @return
     */
    @GetMapping("findWrittenByWrittenId/{writtenId}")
    public ResponseEntity<ResponseResult<WrittenDTO>> findWrittenByTeamId(@PathVariable("writtenId") int writtenId) {
        WrittenDTO writtenDTO = writtenService.findWrittenByTeamId(writtenId);
        if (writtenDTO == null) {
            throw new MyException(ExceptionEnum.WRITTEN_NOT_FOUND);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", writtenDTO));
    }


    /**
     * 描述：添加签单记录
     *
     * @param token
     * @param writtenDTO
     * @return
     */
    @PostMapping("addWritten")
    public ResponseEntity<ResponseResult<Void>> addWritten(
            @CookieValue("OFFICE_TOKEN") String token,
            WrittenDTO writtenDTO
    ) {
        boolean result = writtenService.addWritten(token,writtenDTO);

        if (!result) {
            throw new MyException(ExceptionEnum.WRITTEN_ADD_FAIL);
        }
        return ResponseEntity.ok(new ResponseResult<>(201, "添加成功"));
    }

    /**
     * 描述：删除签单记录
     *
     * @param token
     * @param writtenId
     * @return
     */
    @DeleteMapping("deleteWrittenByWrittenId/{writtenId}")
    public ResponseEntity<ResponseResult<Void>> deleteWrittenByWrittenId(
            @CookieValue("OFFICE_TOKEN") String token,
            @PathVariable("writtenId") Integer writtenId
    ) {

        boolean result = writtenService.deleteWrittenByWrittenId(token, writtenId);

        if (!result) {
            throw new MyException(ExceptionEnum.WRITTEN_DELETE_FAIL);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "删除成功"));
    }


    /**
     * 描述：根据id修改相对应的签单记录
     *
     * @param token
     * @param writtenId
     * @param writtenDTO
     * @return
     */
    @PostMapping("updateWrittenByWrittenId/{writtenId}")
    public ResponseEntity<ResponseResult<Void>> updateWrittenByWrittenId(
            @CookieValue("OFFICE_TOKEN") String token,
            @PathVariable("writtenId") Integer writtenId,
            WrittenDTO writtenDTO
    ) {
        Boolean result = writtenService.updateWrittenByWrittenId(token, writtenId, writtenDTO);
        if (!result) {
            throw new MyException(ExceptionEnum.TEAM_UPDATE_FAIL);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "修改成功"));
    }

}
