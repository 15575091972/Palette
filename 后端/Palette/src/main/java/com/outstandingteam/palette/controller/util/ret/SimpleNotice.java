package com.outstandingteam.palette.controller.util.ret;

import lombok.Data;

import java.util.Date;

@Data
public class SimpleNotice {
    private Long noticeId;
    private String noticeTitle;
    private String noticeContent;
    private Date publishTime;
}
