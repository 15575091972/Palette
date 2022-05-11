package com.outstandingteam.palette.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outstandingteam.palette.controller.util.ret.DetailComment;
import com.outstandingteam.palette.entity.ArtComment;
import com.outstandingteam.palette.entity.Comment;
import com.outstandingteam.palette.mapper.ArtCommentMapper;
import com.outstandingteam.palette.service.ArtCommentService;
import com.outstandingteam.palette.service.CommentService;
import com.outstandingteam.palette.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 艺术品评论区 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class ArtCommentServiceImpl extends ServiceImpl<ArtCommentMapper, ArtComment> implements ArtCommentService {

    @Autowired
    ArtCommentMapper artCommentMapper;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;

    @Override
    public ArrayList<DetailComment> getCommentsById(Long artId, Long token) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", token == null ? null : userService.getUserIdByToken(token));
        map.put("artId", artId);
        return artCommentMapper.getCommentsById(map);

    }

    @Override
    @Transactional
    public Boolean addComment(Long token, String commentContent, Long commentReply,Long artId) {
        Long userId = userService.getUserIdByToken(token);
        Comment comment = new Comment();
        comment.setCommentContent(commentContent);
        comment.setCommentReply(commentReply);
        comment.setCommnetatorId(userId);
        commentService.save(comment);

        ArtComment artComment = new ArtComment();
        artComment.setCommentId(comment.getCommentId());
        artComment.setArtId(artId);
        return artCommentMapper.insert(artComment) > 0;


    }
}
