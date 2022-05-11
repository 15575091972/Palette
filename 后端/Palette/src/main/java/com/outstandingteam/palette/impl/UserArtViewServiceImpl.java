package com.outstandingteam.palette.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outstandingteam.palette.entity.UserArtView;
import com.outstandingteam.palette.mapper.UserArtViewMapper;
import com.outstandingteam.palette.service.UserArtViewService;
import com.outstandingteam.palette.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户浏览作品次数 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class UserArtViewServiceImpl extends ServiceImpl<UserArtViewMapper, UserArtView> implements UserArtViewService {

    @Autowired
    UserArtViewMapper userArtViewMapper;
    @Autowired
    UserService userService;

    @Override
    @Transactional
    public Boolean addViewNum(Long token, Long artId) {
        Long userId = userService.getUserIdByToken(token);
        QueryWrapper<UserArtView> wrapper = new QueryWrapper<UserArtView>()
                .eq("user_id", userId).eq("art_id", artId);
        UserArtView userArtView = userArtViewMapper.selectOne(wrapper);
        if (userArtView != null) {
            userArtView.setViewNum(userArtView.getViewNum() + 1);
            int update = userArtViewMapper.updateById(userArtView);
            return update > 0;
        } else {
            userArtView = new UserArtView();
            userArtView.setUserId(userId);
            userArtView.setArtId(artId);
            userArtView.setViewNum(1);
            int insert = userArtViewMapper.insert(userArtView);
            return insert > 0;
        }
    }
}
