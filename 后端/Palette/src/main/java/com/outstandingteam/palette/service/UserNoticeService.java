package com.outstandingteam.palette.service;

import com.outstandingteam.palette.controller.util.ret.SimpleNotice;
import com.outstandingteam.palette.entity.UserNotice;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-14 ${time}
 */
@Service
public interface UserNoticeService extends IService<UserNotice> {
    // 获取我现在应该收到的通知
    ArrayList<SimpleNotice> getNoticeByToken(Long token);
}
