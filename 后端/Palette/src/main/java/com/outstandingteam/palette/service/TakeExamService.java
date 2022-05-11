package com.outstandingteam.palette.service;

import com.outstandingteam.palette.entity.TakeExam;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 参加考试记录表 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface TakeExamService extends IService<TakeExam> {

    // 某人是否有某试卷考试的资格
    Boolean havePermissions(Long testPaperId, Long token);
}
