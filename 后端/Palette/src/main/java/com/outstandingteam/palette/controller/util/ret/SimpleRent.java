package com.outstandingteam.palette.controller.util.ret;

import lombok.Data;

import java.util.Date;

@Data
public class SimpleRent {
    private Long rentId;
    private SimpleUser leaseholder;
    private SimpleUser tenant;
    private Double charter;
    private Date time;
    private Date startTime;
    private Date endTime;

}
