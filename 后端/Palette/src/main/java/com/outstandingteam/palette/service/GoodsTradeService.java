package com.outstandingteam.palette.service;

import com.outstandingteam.palette.controller.util.ret.SimpleTrade;
import com.outstandingteam.palette.entity.GoodsTrade;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 * 商品交易记录表 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface GoodsTradeService extends IService<GoodsTrade> {
    // 查询GoodsTrade部分的订单
    ArrayList<SimpleTrade> getOrder(Long token);
    // 查询艺术品的交易部分记录
    ArrayList<SimpleTrade> getTradesByArtId(Long artId);
}
