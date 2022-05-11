package com.outstandingteam.palette.mapper;

import com.outstandingteam.palette.entity.CommentLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 评论点赞记录 Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface CommentLikeMapper extends BaseMapper<CommentLike> {

}
