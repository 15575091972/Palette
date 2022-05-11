package com.outstandingteam.palette.mapper;

import com.outstandingteam.palette.entity.UserIdentity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * <p>
 * 身份表 Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface UserIdentityMapper extends BaseMapper<UserIdentity> {
    // 通过ID获取身份的集合
    ArrayList<String> getIdentityByUserId(Long userId);
}
