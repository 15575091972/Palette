package com.outstandingteam.palette.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.outstandingteam.palette.controller.util.ret.DetailComment;
import com.outstandingteam.palette.entity.ArtComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Map;

/**
 * <p>
 * 艺术品评论区 Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface ArtCommentMapper extends BaseMapper<ArtComment> {
    // 通过作品的ID获取其下评论
    ArrayList<DetailComment> getCommentsById(Map<String,Object> map);
}
