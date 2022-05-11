package com.outstandingteam.palette.service;

import com.outstandingteam.palette.entity.UserArtView;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户浏览作品次数 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface UserArtViewService extends IService<UserArtView> {
    // 添加用户查看记录一次
    Boolean addViewNum(Long token, Long artId);

}
