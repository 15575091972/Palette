package com.outstandingteam.palette.service;

import com.outstandingteam.palette.controller.util.ret.SimpleUser;
import com.outstandingteam.palette.entity.UserFollow;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 * 关注者 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface UserFollowService extends IService<UserFollow> {
    // 获取某个用户所关注用户
    ArrayList<SimpleUser> getFollow(Long userId);
}
