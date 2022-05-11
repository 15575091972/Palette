package com.outstandingteam.palette.service;

import com.outstandingteam.palette.controller.util.ret.DetailAuction;
import com.outstandingteam.palette.controller.util.ret.SimpleAuction;
import com.outstandingteam.palette.entity.Auction;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

/**
 * <p>
 * 拍卖表 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface AuctionService extends IService<Auction> {
    // 通过ID获取拍卖的简单信息
    SimpleAuction getSimpleAuctionById(Long auctionId);

    // 筛选某类的拍卖
    ArrayList<SimpleAuction> getAuctions(Date auctionStartTime, Date auctionEndTime, Double topPrice, Double lowPrice, Integer type);

    // 搜索拍卖场次
    ArrayList<SimpleAuction> getAuctionBySearch(String search);

    // 进入某个拍卖场次，获取详细下拍卖信息
    DetailAuction getAuctionDetails(Long auctionId);

    // 加价
    Long addPrice(Long token, Long auctionId, Double price);

    // 是否存在房间号
    Boolean haveRoomId(Integer roomId);
}
