package com.outstandingteam.palette.controller.util.ret;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class DetailTestPaper {
    private Long testPaperId;
    private String testPaperName;
    private String testPaperIntroduction;
    private Date testPaperStartTime;
    private Date testPaperEndTime;
    private Integer hadAdvance;
    private Integer questionsNum;
    private Integer maxResults;
    private ArrayList<SimpleProblem> problems;
}
