package com.outstandingteam.palette.mapper;

import com.outstandingteam.palette.controller.util.ret.DetailAuction;
import com.outstandingteam.palette.controller.util.ret.SimpleAuction;
import com.outstandingteam.palette.entity.Auction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 拍卖表 Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface AuctionMapper extends BaseMapper<Auction> {
    // 通过ID获取拍卖的简单信息
    SimpleAuction getSimpleAuctionById(Long auctionId);

    // 筛选某类的拍卖
    ArrayList<SimpleAuction> getAuctions(HashMap<String, Object> map);

    // 搜索拍卖场次
    ArrayList<SimpleAuction> getAuctionBySearch(String search);

    // 进入某个拍卖场次，获取详细下拍卖信息
    DetailAuction getAuctionDetails(Long auctionId);
}
