package com.outstandingteam.palette.mapper;

import com.outstandingteam.palette.controller.util.ret.SimpleTrade;
import com.outstandingteam.palette.entity.GoodsTrade;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * <p>
 * 商品交易记录表 Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface GoodsTradeMapper extends BaseMapper<GoodsTrade> {
    // 查询GoodsTrade部分的订单
    ArrayList<SimpleTrade> getOrder(Long token);

    ArrayList<SimpleTrade> getTradesByArtId(Long artId);
}
