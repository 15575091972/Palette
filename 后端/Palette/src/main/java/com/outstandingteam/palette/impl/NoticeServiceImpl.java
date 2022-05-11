package com.outstandingteam.palette.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outstandingteam.palette.controller.util.ret.SimpleNotice;
import com.outstandingteam.palette.entity.Notice;
import com.outstandingteam.palette.mapper.NoticeMapper;
import com.outstandingteam.palette.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通知，只包含通知其本身内容（前端将整个标记语言以文字形式发送到数据库保存） 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public SimpleNotice getNoticeById(Long noticeId) {
        Notice notice = noticeMapper.selectById(noticeId);
        if (notice == null) {
            return null;
        }
        SimpleNotice simpleNotice = new SimpleNotice();
        simpleNotice.setNoticeId(notice.getNoticeId());
        simpleNotice.setNoticeTitle(notice.getNoticeTitle());
        simpleNotice.setNoticeContent(notice.getNoticeContent());
        simpleNotice.setPublishTime(notice.getPublishTime());
        return simpleNotice;
    }
}
