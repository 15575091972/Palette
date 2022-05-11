package com.outstandingteam.palette.impl;

import com.outstandingteam.palette.controller.util.ret.SimpleTrade;
import com.outstandingteam.palette.entity.GoodsTrade;
import com.outstandingteam.palette.mapper.GoodsTradeMapper;
import com.outstandingteam.palette.service.GoodsTradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * <p>
 * 商品交易记录表 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class GoodsTradeServiceImpl extends ServiceImpl<GoodsTradeMapper, GoodsTrade> implements GoodsTradeService {

    @Autowired
    GoodsTradeMapper goodsTradeMapper;

    @Override
    @Transactional
    public ArrayList<SimpleTrade> getOrder(Long token) {
        return goodsTradeMapper.getOrder(token);
    }

    @Override
    public ArrayList<SimpleTrade> getTradesByArtId(Long artId) {
        return goodsTradeMapper.getTradesByArtId(artId);
    }
}
