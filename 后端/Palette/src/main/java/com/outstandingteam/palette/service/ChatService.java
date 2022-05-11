package com.outstandingteam.palette.service;

import com.outstandingteam.palette.entity.Chat;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 聊天内容表，聊天内容可以为文字，也可能为文件 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface ChatService extends IService<Chat> {

}
