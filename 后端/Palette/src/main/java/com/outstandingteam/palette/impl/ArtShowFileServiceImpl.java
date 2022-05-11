package com.outstandingteam.palette.impl;

import com.outstandingteam.palette.entity.ArtShowFile;
import com.outstandingteam.palette.entity.File;
import com.outstandingteam.palette.mapper.ArtShowFileMapper;
import com.outstandingteam.palette.service.ArtShowFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outstandingteam.palette.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 艺术品展示文件，用于实体艺术品的展示文件 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class ArtShowFileServiceImpl extends ServiceImpl<ArtShowFileMapper, ArtShowFile> implements ArtShowFileService {

    @Autowired
    ArtShowFileMapper artShowFileMapper;

    @Autowired
    FileService fileService;

    @Override
    public Boolean addShowFile(Long artId, MultipartFile[] showFile) throws IOException {
        for (MultipartFile multipartFile : showFile) {
            File file = fileService.addFile(multipartFile);
            ArtShowFile artShowFile = new ArtShowFile();
            artShowFile.setArtId(artId);
            artShowFile.setFileId(file.getFileId());
            artShowFileMapper.insert(artShowFile);
        }
        return true;
    }
}
