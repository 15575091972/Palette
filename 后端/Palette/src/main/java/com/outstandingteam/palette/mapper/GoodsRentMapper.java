package com.outstandingteam.palette.mapper;

import com.outstandingteam.palette.controller.util.ret.SimpleRent;
import com.outstandingteam.palette.entity.GoodsRent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * <p>
 * 商品租赁 Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface GoodsRentMapper extends BaseMapper<GoodsRent> {

    ArrayList<SimpleRent> getOrder(Long token);

    ArrayList<SimpleRent> getRentsByArtId(Long artId);
}
