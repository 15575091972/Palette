package com.outstandingteam.palette.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outstandingteam.palette.entity.AuctionAddprice;
import com.outstandingteam.palette.entity.Token;
import com.outstandingteam.palette.mapper.AuctionAddpriceMapper;
import com.outstandingteam.palette.service.AuctionAddpriceService;
import com.outstandingteam.palette.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 * 拍卖的加价表 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class AuctionAddpriceServiceImpl extends ServiceImpl<AuctionAddpriceMapper, AuctionAddprice> implements AuctionAddpriceService {

    @Autowired
    AuctionAddpriceMapper auctionAddpriceMapper;
    @Autowired
    TokenService tokenService;

    @Override
    @Transactional
    public Long addPrice(Long token, Long auctionId, Double price) {
        Token byId = tokenService.getById(token);
        AuctionAddprice auctionAddprice = new AuctionAddprice();
        auctionAddprice.setAutionId(auctionId);
        auctionAddprice.setBidPrice(new BigDecimal(price));
        auctionAddprice.setUserId(byId.getUserId());
        int insert = auctionAddpriceMapper.insert(auctionAddprice);
        return auctionAddprice.getAutionAddpriceId();
    }
}
