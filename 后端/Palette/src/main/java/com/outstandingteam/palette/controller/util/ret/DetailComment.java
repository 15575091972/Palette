package com.outstandingteam.palette.controller.util.ret;

import lombok.Data;

import java.util.Date;

@Data
public class DetailComment {
    private Long commentId;
    private String commentContent;
    private SimpleUser commnetator;
    private Long commentReply;
    private Integer commentLike;
    private Date createTime;
    private Boolean isLike;

}
