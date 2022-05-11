package com.outstandingteam.palette.impl;

import com.outstandingteam.palette.entity.UserIdentity;
import com.outstandingteam.palette.mapper.UserIdentityMapper;
import com.outstandingteam.palette.service.UserIdentityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 身份表 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class UserIdentityServiceImpl extends ServiceImpl<UserIdentityMapper, UserIdentity> implements UserIdentityService {

}
