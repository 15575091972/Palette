package com.outstandingteam.palette.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outstandingteam.palette.controller.util.ret.SimpleGoods;
import com.outstandingteam.palette.entity.*;
import com.outstandingteam.palette.mapper.GoodsMapper;
import com.outstandingteam.palette.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    ArtService artService;
    @Autowired
    PublicWelfareService publicWelfareService;
    @Autowired
    GoodsRentService goodsRentService;
    @Autowired
    GoodsTradeService goodsTradeService;
    @Autowired
    EthService ethService;


    @Override
    public ArrayList<SimpleGoods> getArtGoods(Long artId) {
        return goodsMapper.getArtGoods(artId);
    }

    @Override
    public ArrayList<SimpleGoods> getMarkGoods(double topPrice, double lowPrice, Integer type) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("topPrice", topPrice);
        hashMap.put("lowPrice", lowPrice);
        hashMap.put("type", type);
        return goodsMapper.getMarkGoods(hashMap);

    }

    @Override
    @Transactional
    public HashMap<String, Object> buyGoodsById(Long token, Long goodsId, String payKey) throws Exception {
        // 获取用户
        User user = userService.getById(tokenService.transformUserId(token));
        if (user == null) {
            return null;
        }
        // 获取商品
        Goods goods = goodsService.getById(goodsId);
        // 如果查询不到该商品或者商品不为零售，则
        if (goods == null || goods.getTradeFun() != 1) {
            return null;
        }
        // 获取艺术品记录
        Art art = artService.getById(goods.getArtId());
        if (art == null) {
            return null;
        }

        // 创造公益记录
        PublicWelfare publicWelfare = new PublicWelfare();
        publicWelfare.setPublicwelfareCoin(new BigDecimal(0));
        publicWelfare.setPublicwelfareObject("");
        if (!publicWelfareService.save(publicWelfare)) {
            return null;
        }


        HashMap<String, Object> hashMap = new HashMap<>();
        if (goods.getTradeMode() == 2) {
            // 如果时租赁，则创造租赁记录
            GoodsRent goodsRent = new GoodsRent();
            goodsRent.setGoodsId(goodsId);
            goodsRent.setLeaseholderId(user.getUserId());
            goodsRent.setTenantId(goods.getGoodsSellerId());
            goodsRent.setCharterMoney(goods.getResalePrice());
            goodsRent.setServiceCharge(new BigDecimal(0));          // 手续费
            goodsRent.setTradeType(1);
            goodsRent.setTypeId(goodsId);
            goodsRent.setPublicWelfareId(publicWelfare.getPublicwelfareId());
            goodsRent.setRentStartTime(goods.getRentStartTime());
            goodsRent.setRentEndTime(goods.getRentEndTime());

            if (!goodsRentService.save(goodsRent)) {
                return null;
            }
            // 将租赁记录返回
            hashMap.put("rent", goodsRent);

            // 将金额发给所有者
            if (!ethService.payRent(payKey, user.getKeySrc(), art.getArtToken(),goods.getResalePrice())) {
                return null;
            }
        } else {
            // 如果时所有权变更，则创造所有权变更交易
            GoodsTrade goodsTrade = new GoodsTrade();
            goodsTrade.setGoodsId(goodsId);
            goodsTrade.setServiceCharge(new BigDecimal(0));         // 手续费
            goodsTrade.setSellerId(goods.getGoodsSellerId());
            goodsTrade.setBuyerId(user.getUserId());
            goodsTrade.setCoin(goods.getResalePrice());
            goodsTrade.setTradeType(1);
            goodsTrade.setTypeId(goodsId);
            goodsTrade.setPublicWelfareId(publicWelfare.getPublicwelfareId());

            if (!goodsTradeService.save(goodsTrade)) {
                return null;
            }
            // 将交易记录放回
            hashMap.put("trade", goodsTrade);

            // 更改艺术品在区块链上的所有权
            if (!ethService.Sell(user.getEthAddress(), art.getArtToken(),
                    goods.getGoodsQuantity(), goods.getResalePrice(), payKey, user.getKeySrc())) {
                return null;
            }

            // 修改商品的状态，只有所有权交易需要修改该状态
            UpdateWrapper<Goods> wrapper = new UpdateWrapper<Goods>()
                    .set("selled", true)
                    .eq("goods_id", goods.getGoodsId());
            goodsService.update(wrapper);
        }

        return hashMap;

    }


    @Override
    public ArrayList<SimpleGoods> getArtFuzzy(String fuzzyStatement) {

        return goodsMapper.getArtFuzzy(fuzzyStatement);

    }
}
