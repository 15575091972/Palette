package com.outstandingteam.palette.controller.util.ret;

import lombok.Data;

import java.util.Date;

@Data
public class SimpleAuction {
    private Long auctionId;
    private Integer auctionRoomId;
    private Double topPrice;
    private Date startTime;
    private Date endTime;
    private SimpleArt art;
}
