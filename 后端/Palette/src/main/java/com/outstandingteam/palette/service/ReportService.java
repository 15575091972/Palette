package com.outstandingteam.palette.service;

import com.outstandingteam.palette.controller.util.ret.DetailReport;
import com.outstandingteam.palette.entity.Report;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 举报记录列表 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface ReportService extends IService<Report> {
    // 获取举报详情
    DetailReport getReport(Long reportId, Long token);
}
