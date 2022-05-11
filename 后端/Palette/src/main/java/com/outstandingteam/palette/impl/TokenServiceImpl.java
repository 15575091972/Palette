package com.outstandingteam.palette.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outstandingteam.palette.entity.Token;
import com.outstandingteam.palette.mapper.TokenMapper;
import com.outstandingteam.palette.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * token表 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {

    @Autowired
    TokenMapper tokenMapper;

    @Override
    @Transactional
    public Long transformUserId(Long token) {
        Token select = tokenMapper.selectById(token);
        if (select == null) {
            return null;
        }
        return select.getUserId();
    }

    @Override
    public Long getToken(Long userId) {
        QueryWrapper<Token> wrapper = new QueryWrapper<Token>()
                .ge("deadline_time", new Date())
                .le("create_time", new Date())
                .eq("user_id", userId);
        Token token = tokenMapper.selectOne(wrapper);

        // 如果查询到现有token，则返回其token
        if (token != null) {
            return token.getToken();
        }

        // 如果查询不到现有有效的token，则新建一个 token
        token = new Token();
        token.setUserId(userId);

        // 为每个token设置15分钟的时限
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE,15 );//给时间坐标加上minutes分钟
        token.setDeadlineTime(cal.getTime());

        if (tokenMapper.insert(token) > 0){
            return token.getToken();
        }
        return null;

    }
}
