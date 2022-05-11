package com.outstandingteam.palette.controller.util.ret;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class DetailAuction {
    private Long auctionId;
    private Integer auctionRoomId;
    private Double basePrice;
    private Double minAddprice;
    private Date startTime;
    private Date endTime;
    private Double topPrice;
    private Integer haveStay;
    private ArrayList<SimpleAddPrice> addPrices;
    private SimpleArt art;

}
