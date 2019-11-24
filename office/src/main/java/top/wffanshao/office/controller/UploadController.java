package top.wffanshao.office.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.wffanshao.office.enums.ExceptionEnum;
import top.wffanshao.office.exception.MyException;
import top.wffanshao.office.service.impl.UploadServiceImpl;
import top.wffanshao.office.vo.ResponseResult;

/**
 * 描述：上传Controller
 *
 * @author 杨炜帆
 * @date 2019/11/24
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private UploadServiceImpl uploadServiceImpl;

    /**
     * 描述：图片上传
     *
     * @param file
     * @return
     */
    @PostMapping("image")
    public ResponseEntity<ResponseResult<String>> uploadImage(@RequestParam("file") MultipartFile file) {
        String url = this.uploadServiceImpl.upload(file);
        if (StringUtils.isBlank(url)) {
            // url为空，证明上传失败
            throw new MyException(ExceptionEnum.UPLOAD_FAIL);
        }
        return ResponseEntity.ok(new ResponseResult<>(200, "上传成功", url));
    }
}
