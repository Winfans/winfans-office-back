package com.jobgo.upload.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.jobgo.upload.properties.UploadProperties;
import com.jobgo.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;


/**
 * 描述：上传Service实现类
 * 创建时间: 2019-09-5 15:01
 * 修改时间: 2019-09-5 15:01
 */
@Service
@Slf4j
@EnableConfigurationProperties
public final class UploadServiceImpl implements UploadService {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private UploadProperties uploadProperties;

    // 支持上传的文件类型
    private static final List<String> suffixes = Arrays.asList("image/png", "image/jpeg", "image/jpg");

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @Override
    public String upload(MultipartFile file) {
        try {
            String type = file.getContentType();

            // 校验文件类型
            if (!suffixes.contains(type)) {
                log.info("[上传服务] 上传文件失败，文件类型不匹配：{}", type);
                return null;
            }

            // 校验图片内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                log.info("[上传服务] 上传失败，文件内容不符合要求");
                return null;
            }
            // 生成保存目录、保存图片、拼接图片地址
            StorePath storePath = this.storageClient.uploadFile(
                    file.getInputStream(), file.getSize(), getExtension(file.getOriginalFilename()), null);

            return uploadProperties.getBaseUrl() + "/" + storePath.getFullPath();
        } catch (Exception e) {
            log.info("[上传服务] 上传失败，发生异常");
            return null;
        }
    }

    /**
     * 描述：获取文件拓展名
     *
     * @param fileName
     * @return
     */
    public String getExtension(String fileName) {
        return StringUtils.substringAfterLast(fileName, ".");
    }


}
