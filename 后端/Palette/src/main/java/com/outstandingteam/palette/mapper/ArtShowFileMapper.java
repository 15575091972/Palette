package com.outstandingteam.palette.mapper;

import com.outstandingteam.palette.entity.ArtShowFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * <p>
 * 艺术品展示文件，用于实体艺术品的展示文件 Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface ArtShowFileMapper extends BaseMapper<ArtShowFile> {

    // 通过artId获取对应的所有展示文件
    ArrayList<String> getArtAllShowFileById(Long artId);

    // 通过artId获取对应的一个展示文件
    String getArtOneShowFileById(Long artId);
}
