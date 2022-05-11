package com.outstandingteam.palette.controller;

import com.outstandingteam.palette.controller.util.Result;
import com.outstandingteam.palette.controller.util.ResultCode;
import com.outstandingteam.palette.controller.util.ret.DetailReport;
import com.outstandingteam.palette.controller.util.ret.DetailTestPaper;
import com.outstandingteam.palette.controller.util.ret.SimpleNotice;
import com.outstandingteam.palette.service.*;
import com.outstandingteam.palette.util.PaletteLog;
import com.outstandingteam.palette.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * <p>
 * 系统信息接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@RestController
@RequestMapping("/palette/system")
public class SystemController {

    @Autowired
    NoticeService noticeService;
    @Autowired
    ReportService reportService;
    @Autowired
    TestPaperService testPaperService;
    @Autowired
    TakeExamService takeExamService;
    @Autowired
    TestQuestionsService testQuestionsService;
    @Autowired
    UserNoticeService userNoticeService;

    /**
     * 获取通知详情
     *
     * @param map
     * @return 通知详情
     */
    @PostMapping(value = "/getNoticeById", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getNoticeById(@RequestBody Map<String, Object> map) {
        Long noticeId = ParamUtil.tradeToLong(map.get("noticeId"));        //

        if (noticeId == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }
        SimpleNotice simpleNotice = noticeService.getNoticeById(noticeId);
        if (simpleNotice == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        return Result.ok("notice", simpleNotice);
    }

    /**
     * 获取举报详情
     *
     * @param map
     * @return 通知详情
     */
    @PostMapping(value = "/getReport", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getReport(@RequestBody Map<String, Object> map) {
        Long reportId = ParamUtil.tradeToLong(map.get("reportId"));        // 举报记录ID
        Long token = ParamUtil.tradeToLong(map.get("token"));        // token

        if (reportId == null || token == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }
        DetailReport detailReport = reportService.getReport(reportId, token);
        if (detailReport == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }

        return Result.ok("report", detailReport);
    }

    /**
     * 获取一套特定试卷
     *
     * @param map
     * @return 试卷详情
     */
    @PostMapping(value = "/getTestPaper", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getTestPaper(@RequestBody Map<String, Object> map) {
        Long testPaperId = ParamUtil.tradeToLong(map.get("testPaperId"));        // 试卷ID
        Long token = ParamUtil.tradeToLong(map.get("token"));        // token
        if (testPaperId == null || token == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        // 判断该用户是否有权限获取该试卷的资格
        Boolean isSuccess = takeExamService.havePermissions(testPaperId, token);
        if (isSuccess == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }
        if (!isSuccess) {
            return Result.error(ResultCode.INSUFFICIENT_PERMISSIONS);
        }

        DetailTestPaper detailTestPaper = testPaperService.getTestPaper(testPaperId);
        if (detailTestPaper == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }

        return Result.ok("testPaper", detailTestPaper);
    }

    /**
     * 上传添加一个题目
     *
     * @param map
     * @return 问题ID
     */
    @PostMapping(value = "/uploadQuestion", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result uploadQuestion(@RequestBody Map<String, Object> map) {
        Long token = ParamUtil.tradeToLong(map.get("token"));        // token
        String problem_title = (String) map.get("problem_title");
        String disturb_a = (String) map.get("disturb_a");
        String disturb_b = (String) map.get("disturb_b");
        String disturb_c = (String) map.get("disturb_c");
        String answer = (String) map.get("answer");
        if (problem_title == null || disturb_a == null || disturb_b == null || disturb_c == null || answer == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        Long problemId = testQuestionsService.uploadQuestion(token, problem_title, disturb_a, disturb_b, disturb_c, answer);
        if (problemId == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }

        return Result.ok("problemId", problemId);
    }

    /**
     * 生成一套新的试卷
     *
     * @param map
     * @return 试卷详情
     */
    @PostMapping(value = "/generateTestPaper", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result generateTestPaper(@RequestBody Map<String, Object> map) {
        Long token = ParamUtil.tradeToLong(map.get("token"));        // token
        if (token == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        return null;
    }

    /**
     * 获取我现在应该收到的通知
     *
     * @param map
     * @return 试卷详情
     */
    @PostMapping(value = "/getNoticeByToken", produces = "application/json; charset=UTF-8")
    @ResponseBody
    @PaletteLog("Post")
    public Result getNoticeByToken(@RequestBody Map<String, Object> map) {
        Long token = ParamUtil.tradeToLong(map.get("token"));        // token

        if (token == null) {
            return Result.error(ResultCode.PARAMETER_UNQUALIFIED);
        }

        ArrayList<SimpleNotice> notices = userNoticeService.getNoticeByToken(token);
        if (notices == null) {
            return Result.error(ResultCode.SERVER_ERROR);
        }

        return Result.ok("notices", notices);
    }


}
