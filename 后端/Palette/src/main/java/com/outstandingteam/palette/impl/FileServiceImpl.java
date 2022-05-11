package com.outstandingteam.palette.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outstandingteam.palette.entity.File;
import com.outstandingteam.palette.mapper.FileMapper;
import com.outstandingteam.palette.service.FileService;
import com.outstandingteam.palette.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 文件表（保存各类文件，如艺术品的文件，上传的头像的文件，其他文件等） 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Autowired
    FileMapper fileMapper;

    @Override
    @Transactional
    public File addFile(MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();

        // 基础地址
        String basePath = "D:\\Workspace\\IDEA\\picture";
        Date now = new Date();
        String addPath = "/" + now.getYear() + now.getMonth() + now.getDate() + "/";
        // 文件名称包括文件类型后缀
        String fileName = multipartFile.getOriginalFilename();


        java.io.File file = new java.io.File(basePath + addPath);
        // 如果路径文件夹不存在，则创造该文件夹
        if (!file.exists()) {
            file.mkdirs();
        }

        file = new java.io.File(basePath + addPath + fileName);
//            System.out.println(file);


        // 同时还需要新建一个file的字段，并设置数据库文件字段的值
        File dbFile = new File();

        dbFile.setFilePath(addPath + fileName);
        dbFile.setFileName(multipartFile.getName() + UUID.randomUUID());
        dbFile.setFileFormat(multipartFile.getOriginalFilename());


        // 将文件存储到磁盘
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes)) != -1) {
            bufferedOutputStream.write(bytes, 0, len);
        }
        // MD5Util.md5HashCode 内将资源关闭了
        dbFile.setFileHash(MD5Util.md5HashCode(inputStream));
        // 关闭资源
        bufferedOutputStream.close();

        fileMapper.insert(dbFile);

        return dbFile;

    }
}
