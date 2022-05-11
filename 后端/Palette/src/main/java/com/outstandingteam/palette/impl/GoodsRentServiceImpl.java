package com.outstandingteam.palette.impl;

import com.outstandingteam.palette.controller.util.ret.SimpleRent;
import com.outstandingteam.palette.entity.GoodsRent;
import com.outstandingteam.palette.mapper.GoodsRentMapper;
import com.outstandingteam.palette.service.GoodsRentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * <p>
 * 商品租赁 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class GoodsRentServiceImpl extends ServiceImpl<GoodsRentMapper, GoodsRent> implements GoodsRentService {

    @Autowired
    GoodsRentMapper goodsRentMapper;

    @Override
    @Transactional
    public ArrayList<SimpleRent> getOrder(Long token) {
        return goodsRentMapper.getOrder(token);
    }

    @Override
    public ArrayList<SimpleRent> getRentsByArtId(Long artId) {
        return goodsRentMapper.getRentsByArtId(artId);
    }
}
