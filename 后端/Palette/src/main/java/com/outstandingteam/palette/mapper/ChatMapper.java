package com.outstandingteam.palette.mapper;

import com.outstandingteam.palette.entity.Chat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 聊天内容表，聊天内容可以为文字，也可能为文件 Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface ChatMapper extends BaseMapper<Chat> {

}
