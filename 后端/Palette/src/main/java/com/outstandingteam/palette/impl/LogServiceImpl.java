package com.outstandingteam.palette.impl;

import com.outstandingteam.palette.entity.Log;
import com.outstandingteam.palette.mapper.LogMapper;
import com.outstandingteam.palette.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
