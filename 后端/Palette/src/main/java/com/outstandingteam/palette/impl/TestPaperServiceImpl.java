package com.outstandingteam.palette.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outstandingteam.palette.controller.util.ret.DetailTestPaper;
import com.outstandingteam.palette.entity.TestPaper;
import com.outstandingteam.palette.mapper.TestPaperMapper;
import com.outstandingteam.palette.service.TestPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 试卷表 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class TestPaperServiceImpl extends ServiceImpl<TestPaperMapper, TestPaper> implements TestPaperService {

    @Autowired
    TestPaperMapper testPaperMapper;

    @Override
    public DetailTestPaper getTestPaper(Long testPaperId) {
        return testPaperMapper.getTestPaper(testPaperId);
    }

}
