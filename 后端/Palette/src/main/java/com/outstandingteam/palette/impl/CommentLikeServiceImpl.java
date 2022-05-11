package com.outstandingteam.palette.impl;

import com.outstandingteam.palette.entity.CommentLike;
import com.outstandingteam.palette.mapper.CommentLikeMapper;
import com.outstandingteam.palette.service.CommentLikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论点赞记录 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class CommentLikeServiceImpl extends ServiceImpl<CommentLikeMapper, CommentLike> implements CommentLikeService {

}
