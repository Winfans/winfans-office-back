package top.wffanshao.office.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 描述：上传Service
 *
 * @author 杨炜帆
 * @date 2019/11/24
 */
public interface UploadService {

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
