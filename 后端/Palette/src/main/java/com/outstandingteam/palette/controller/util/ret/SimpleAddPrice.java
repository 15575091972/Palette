package com.outstandingteam.palette.controller.util.ret;

import lombok.Data;

import java.util.Date;

@Data
public class SimpleAddPrice {
    private Double bidPrice;
    private SimpleUser user;
    private Date time;
}
