package com.outstandingteam.palette.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.outstandingteam.palette.controller.util.ret.SimpleNotice;
import com.outstandingteam.palette.entity.Notice;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通知，只包含通知其本身内容（前端将整个标记语言以文字形式发送到数据库保存） 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface NoticeService extends IService<Notice> {

    // 获取通知详情
    SimpleNotice getNoticeById(Long noticeId);
}
