package com.outstandingteam.palette.service;

import com.outstandingteam.palette.entity.AuctionAddprice;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 拍卖的加价表 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface AuctionAddpriceService extends IService<AuctionAddprice> {
    // 加价
    Long addPrice(Long token, Long auctionId, Double price);
}
