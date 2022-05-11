package com.outstandingteam.palette.mapper;

import com.outstandingteam.palette.controller.util.ret.SimpleNotice;
import com.outstandingteam.palette.entity.UserNotice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-14 ${time}
 */
@Mapper
public interface UserNoticeMapper extends BaseMapper<UserNotice> {

    // 获取我现在应该收到的通知
    ArrayList<SimpleNotice> getNoticeByToken(Long token);
}
