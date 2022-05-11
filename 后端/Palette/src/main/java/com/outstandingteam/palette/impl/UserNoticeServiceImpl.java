package com.outstandingteam.palette.impl;

import com.outstandingteam.palette.controller.util.ret.SimpleNotice;
import com.outstandingteam.palette.entity.UserNotice;
import com.outstandingteam.palette.mapper.UserNoticeMapper;
import com.outstandingteam.palette.service.UserNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-14
 */
@Service
public class UserNoticeServiceImpl extends ServiceImpl<UserNoticeMapper, UserNotice> implements UserNoticeService {

    @Autowired
    UserNoticeMapper userNoticeMapper;

    @Override
    public ArrayList<SimpleNotice> getNoticeByToken(Long token) {
        return userNoticeMapper.getNoticeByToken(token);

    }
}
