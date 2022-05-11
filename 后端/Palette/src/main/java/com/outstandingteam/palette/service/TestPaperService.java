package com.outstandingteam.palette.service;

import com.outstandingteam.palette.controller.util.ret.DetailTestPaper;
import com.outstandingteam.palette.entity.TestPaper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 试卷表 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface TestPaperService extends IService<TestPaper> {

    // 获取一套特定试卷
    DetailTestPaper getTestPaper(Long testPaperId);

}
