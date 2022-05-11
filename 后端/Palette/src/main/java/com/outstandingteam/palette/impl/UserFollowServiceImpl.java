package com.outstandingteam.palette.impl;

import com.outstandingteam.palette.controller.util.ret.SimpleUser;
import com.outstandingteam.palette.entity.UserFollow;
import com.outstandingteam.palette.mapper.UserFollowMapper;
import com.outstandingteam.palette.service.UserFollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * <p>
 * 关注者 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class UserFollowServiceImpl extends ServiceImpl<UserFollowMapper, UserFollow> implements UserFollowService {

    @Autowired
    UserFollowMapper userFollowMapper;

    @Override
    @Transactional
    public ArrayList<SimpleUser> getFollow(Long userId) {
        return userFollowMapper.getFollow(userId);

    }
}
