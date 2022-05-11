package com.outstandingteam.palette.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.outstandingteam.palette.controller.util.ret.SimpleAddPrice;
import com.outstandingteam.palette.entity.AuctionAddprice;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * <p>
 * 拍卖的加价表 Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface AuctionAddpriceMapper extends BaseMapper<AuctionAddprice> {
    ArrayList<SimpleAddPrice> getSimpleByAuctionId(Long auctionId);

}
