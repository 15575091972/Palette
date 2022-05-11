package com.outstandingteam.palette.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.outstandingteam.palette.controller.util.ret.DetailsUser;
import com.outstandingteam.palette.controller.util.ret.SimpleUser;
import com.outstandingteam.palette.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    // 获取其余人的的简易信息
    SimpleUser getOtherUser(Long userId);

    // 获取用户及其关注者的信息
    DetailsUser getUser(Long token);

    // userName、userAccount、userPhone、userPassword、userHeadImageId、userPassword必须有一个不为空
    Boolean setUser(Map<String, Object> map);

    // 通过地址获取 SimpleUser
    ArrayList<SimpleUser> getSimpleUserByAddress(ArrayList<String> list);
}
