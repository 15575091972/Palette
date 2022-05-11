package com.outstandingteam.palette.service;

import com.outstandingteam.palette.entity.ArtShowFile;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 艺术品展示文件，用于实体艺术品的展示文件 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface ArtShowFileService extends IService<ArtShowFile> {
    //  增加shou文件
    Boolean addShowFile(Long artId, MultipartFile[] showFile) throws IOException;
}
