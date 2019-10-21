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
     * 描述：添加成员
     *
     * @param token
     * @param written
     * @return
     */
    @PostMapping("addWritten")
    public ResponseEntity<ResponseResult<Void>> addWritten(
            @CookieValue("OFFICE_TOKEN") String token,
            OfficeDbWritten written
    ) {
        boolean result = writtenService.addWritten(token,written);

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
     * @param userId
     * @return
     */
    @DeleteMapping("deleteWrittenWrittenId/{writtenId}")
    public ResponseEntity<ResponseResult<Void>> deleteWrittenWrittenId(
            @CookieValue("OFFICE_TOKEN") String token,
            @PathVariable("writtenId") Integer writtenId,
            @RequestParam("userId") Integer userId
    ) {

        boolean result = writtenService.deleteWrittenWrittenId(token, writtenId, userId);

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
     * @param written
     * @return
     */
    @PutMapping("updateWrittenByWrittenId/{writtenId}")
    public ResponseEntity<ResponseResult<Void>> updateWrittenByWrittenId(
            @CookieValue("OFFICE_TOKEN") String token,
            @PathVariable("writtenId") Integer writtenId,
            OfficeDbWritten written
    ) {
        Boolean result = writtenService.updateWrittenByWrittenId(token, writtenId, written);
        if (!result) {
            throw new MyException(ExceptionEnum.TEAM_UPDATE_FAIL);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "修改成功"));
    }

}
