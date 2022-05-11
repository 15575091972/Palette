package com.outstandingteam.palette.mapper;

import com.outstandingteam.palette.controller.util.ret.SimpleProblem;
import com.outstandingteam.palette.entity.TestQuestions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 审核员考察问题 Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface TestQuestionsMapper extends BaseMapper<TestQuestions> {
    // 通过ID查询问题
    SimpleProblem getSimpleById(Long problemId);
}
