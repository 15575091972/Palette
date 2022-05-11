package com.outstandingteam.palette.service;

import com.outstandingteam.palette.controller.util.ret.SimpleRent;
import com.outstandingteam.palette.entity.GoodsRent;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 * 商品租赁 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface GoodsRentService extends IService<GoodsRent> {
    // 查询GoodsRent部分的订单
    ArrayList<SimpleRent> getOrder(Long token);
    // 查询艺术品的租赁部分记录
    ArrayList<SimpleRent> getRentsByArtId(Long artId);
}
