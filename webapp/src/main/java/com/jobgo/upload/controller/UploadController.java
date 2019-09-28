package com.jobgo.upload.controller;

import com.jobgo.commom.enums.ExceptionEnum;
import com.jobgo.commom.exception.MyException;
import com.jobgo.commom.vo.ResponseResult;
import com.jobgo.upload.service.impl.UploadServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 描述：上传Controller
 * 创建时间: 2019-09-5 15:01
 * 修改时间: 2019-09-5 15:01
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
