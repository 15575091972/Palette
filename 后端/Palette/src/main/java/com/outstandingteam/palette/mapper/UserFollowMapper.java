package com.outstandingteam.palette.mapper;

import com.outstandingteam.palette.controller.util.ret.SimpleUser;
import com.outstandingteam.palette.entity.UserFollow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * <p>
 * 关注者 Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface UserFollowMapper extends BaseMapper<UserFollow> {

    ArrayList<SimpleUser> getFollow(Long userId);
}
