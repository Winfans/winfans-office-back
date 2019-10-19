package top.wffanshao.office.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.wffanshao.office.dto.WrittenDTO;
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

    @GetMapping("findAllWrittenByPage/{page}/{size}")
    public ResponseEntity<ResponseResult<ResponsePage<WrittenDTO>>> findAllWrittenByPage(@PathVariable("page") int page,
                                                                                         @PathVariable("size") int size) {
        return ResponseEntity.ok(new ResponseResult<>(200, "查询成功", writtenService.findAllWrittenByPage(page,size)));
    }
}
