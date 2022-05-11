package com.outstandingteam.palette.controller.util.ret;

import lombok.Data;

import java.util.Date;

@Data
public class SimpleTrade {
    private Long tradeId;
    private SimpleUser seller;
    private SimpleUser buyer;
    private Double coin;
    private Date time;


}
