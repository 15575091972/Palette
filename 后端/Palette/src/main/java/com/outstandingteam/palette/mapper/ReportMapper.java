package com.outstandingteam.palette.mapper;

import com.outstandingteam.palette.controller.util.ret.DetailReport;
import com.outstandingteam.palette.entity.Report;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

/**
 * <p>
 * 举报记录列表 Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface ReportMapper extends BaseMapper<Report> {

    // 获取举报详情
    DetailReport getReport(HashMap<String, Object> map);
}
