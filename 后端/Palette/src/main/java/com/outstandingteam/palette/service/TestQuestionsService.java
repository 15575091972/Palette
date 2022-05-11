package com.outstandingteam.palette.service;

import com.outstandingteam.palette.entity.TestQuestions;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 审核员考察问题 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface TestQuestionsService extends IService<TestQuestions> {
    // 添加一个题目
    Long uploadQuestion(Long token, String problem_title, String disturb_a, String disturb_b, String disturb_c, String answer);
}
