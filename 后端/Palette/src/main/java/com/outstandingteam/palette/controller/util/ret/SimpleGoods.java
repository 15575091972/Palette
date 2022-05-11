package com.outstandingteam.palette.controller.util.ret;

import lombok.Data;

import java.util.Date;

@Data
public class SimpleGoods {

    private Long goodsId;
    private Integer tradeMode;
    private Double goodsQuantity;
    private SimpleUser goodsSeller;
    private Date rentStartTime;
    private Date rentEndTime;
    private Integer tradeFun;
    private Double resalePrice;
    private SimpleArt simpleArt;

}
