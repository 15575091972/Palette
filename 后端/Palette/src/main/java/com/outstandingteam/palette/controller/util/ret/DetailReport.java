package com.outstandingteam.palette.controller.util.ret;

import lombok.Data;

import java.util.ArrayList;

@Data
public class DetailReport {
    private Long reportId;
    private Integer reportObjectType;
    private Long reportObject;
    private String  reportContent;
    private String reportResult;
    private ArrayList<String> evidence;
}
