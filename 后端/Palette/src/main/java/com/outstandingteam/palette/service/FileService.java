package com.outstandingteam.palette.service;

import com.outstandingteam.palette.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 文件表（保存各类文件，如艺术品的文件，上传的头像的文件，其他文件等） 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface FileService extends IService<File> {
    // 添加一个文件
    File addFile(MultipartFile multipartFile) throws IOException;
}
