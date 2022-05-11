package com.outstandingteam.palette.impl;

import com.outstandingteam.palette.entity.TestQuestions;
import com.outstandingteam.palette.mapper.TestQuestionsMapper;
import com.outstandingteam.palette.service.TestQuestionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outstandingteam.palette.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 审核员考察问题 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class TestQuestionsServiceImpl extends ServiceImpl<TestQuestionsMapper, TestQuestions> implements TestQuestionsService {

    @Autowired
    TestQuestionsMapper testQuestionsMapper;
    @Autowired
    TokenService tokenService;

    @Override
    public Long uploadQuestion(Long token, String problem_title, String disturb_a,
                                  String disturb_b, String disturb_c, String answer) {
        Long userId = tokenService.transformUserId(token);
        TestQuestions questions = new TestQuestions();
        questions.setAuthorId(userId);
        questions.setProblemTitle(problem_title);
        questions.setDisturbA(disturb_a);
        questions.setDisturbB(disturb_b);
        questions.setDisturbC(disturb_c);
        questions.setAnswer(answer);
        testQuestionsMapper.insert(questions);
        return questions.getAuthorId();
    }
}
