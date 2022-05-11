package com.outstandingteam.palette.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.outstandingteam.palette.controller.util.ret.DetailAuction;
import com.outstandingteam.palette.controller.util.ret.SimpleAuction;
import com.outstandingteam.palette.entity.Auction;
import com.outstandingteam.palette.mapper.AuctionMapper;
import com.outstandingteam.palette.service.AuctionAddpriceService;
import com.outstandingteam.palette.service.AuctionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 拍卖表 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class AuctionServiceImpl extends ServiceImpl<AuctionMapper, Auction> implements AuctionService {
    @Autowired
    AuctionMapper auctionMapper;
    @Autowired
    AuctionAddpriceService auctionAddpriceService;

    @Override
    public SimpleAuction getSimpleAuctionById(Long auctionId) {
        return auctionMapper.getSimpleAuctionById(auctionId);
    }

    @Override
    public ArrayList<SimpleAuction> getAuctions(Date auctionStartTime, Date auctionEndTime, Double topPrice, Double lowPrice, Integer type) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("auctionStartTime", auctionStartTime);
        map.put("auctionEndTime", auctionEndTime);
        map.put("topPrice", topPrice);
        map.put("lowPrice", lowPrice);
        map.put("type", type);
        ArrayList<SimpleAuction> auctions = auctionMapper.getAuctions(map);
        return auctions;

    }

    @Override
    public ArrayList<SimpleAuction> getAuctionBySearch(String search) {
        return auctionMapper.getAuctionBySearch(search);

    }

    @Override
    public DetailAuction getAuctionDetails(Long auctionId) {
        return auctionMapper.getAuctionDetails(auctionId);
    }

    @Override
    @Transactional
    public Long addPrice(Long token, Long auctionId, Double price) {
        return auctionAddpriceService.addPrice(token, auctionId, price);
    }

    @Override
    public Boolean haveRoomId(Integer roomId) {
        List<Auction> auction_room_id = auctionMapper.selectList(new QueryWrapper<Auction>()
                .eq("auction_room_id", roomId).le("end_time",new Date()));
        return auction_room_id.size() > 0;

    }
}
