package com.outstandingteam.palette.service;

import com.outstandingteam.palette.entity.Token;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * token表 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface TokenService extends IService<Token> {
    // 将 userId 查询出 token
    Long transformUserId(Long token);
    // 通过userId 获取 token
    Long getToken(Long userId);
}
