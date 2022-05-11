package com.outstandingteam.palette.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.outstandingteam.palette.entity.TakeExam;
import com.outstandingteam.palette.mapper.TakeExamMapper;
import com.outstandingteam.palette.service.TakeExamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 参加考试记录表 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class TakeExamServiceImpl extends ServiceImpl<TakeExamMapper, TakeExam> implements TakeExamService {

    @Autowired
    TakeExamMapper takeExamMapper;

    @Override
    public Boolean havePermissions(Long testPaperId, Long token) {
        QueryWrapper<TakeExam> wrapper = new QueryWrapper<TakeExam>()
                .eq("test_paper_id", testPaperId)
                .eq("user_id", token);
        TakeExam takeExam = takeExamMapper.selectOne(wrapper);
        return null != takeExam;
    }
}
