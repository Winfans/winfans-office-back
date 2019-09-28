package com.jobgo.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 描述：上传Service
 * 创建时间: 2019-09-5 15:01
 * 修改时间: 2019-09-5 15:01
 */
public interface UploadService {
    /**
     * 文件上传
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
