package com.outstandingteam.palette.impl;

import com.outstandingteam.palette.controller.util.ret.DetailReport;
import com.outstandingteam.palette.entity.Report;
import com.outstandingteam.palette.mapper.ReportMapper;
import com.outstandingteam.palette.service.ReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 举报记录列表 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {

    @Autowired
    ReportMapper reportMapper;

    @Override
    public DetailReport getReport(Long reportId, Long token) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("reportId",reportId);
        map.put("token",token);
        return reportMapper.getReport(map);
    }
}
