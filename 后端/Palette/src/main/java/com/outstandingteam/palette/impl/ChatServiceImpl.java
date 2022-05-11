package com.outstandingteam.palette.impl;

import com.outstandingteam.palette.entity.Chat;
import com.outstandingteam.palette.mapper.ChatMapper;
import com.outstandingteam.palette.service.ChatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 聊天内容表，聊天内容可以为文字，也可能为文件 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {

}
