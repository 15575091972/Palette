package com.outstandingteam.palette.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.outstandingteam.palette.controller.util.ret.DetailComment;
import com.outstandingteam.palette.entity.ArtComment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 * 艺术品评论区 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface ArtCommentService extends IService<ArtComment> {
    // 通过作品的ID获取其下评论
    ArrayList<DetailComment> getCommentsById(Long artId, Long token);
    // 发表一条评论
    Boolean addComment(Long token, String commentContent, Long commentReply, Long artId);
}
