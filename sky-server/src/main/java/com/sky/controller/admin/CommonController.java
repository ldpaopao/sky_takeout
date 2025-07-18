package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@Api(tags = "通用接口")
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {
    private final AliOssUtil aliOssUtil;

    public CommonController(AliOssUtil aliOssUtil) {
        this.aliOssUtil = aliOssUtil;
    }

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile  file) {
        log.info("文件上传：{}", file);
        try {
            //原始文件名
            String orignalFilename = file.getOriginalFilename();
            //截取原始文件名的后缀
            String extension =orignalFilename.substring(orignalFilename.lastIndexOf("."));
            //生成新的文件名称
            String objectName =UUID.randomUUID().toString() + extension;
            //文件请求的路径
           String filePath= aliOssUtil.upload(file.getBytes(), file.getOriginalFilename());
           return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败:{}",e);
        }

        return null;
    }

}
