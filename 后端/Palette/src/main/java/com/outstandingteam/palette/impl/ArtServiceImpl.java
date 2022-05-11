package com.outstandingteam.palette.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outstandingteam.palette.controller.util.ret.*;
import com.outstandingteam.palette.entity.*;
import com.outstandingteam.palette.mapper.ArtMapper;
import com.outstandingteam.palette.service.*;
import com.outstandingteam.palette.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.tuples.generated.Tuple2;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;


/**
 * <p>
 * 电子艺术品表 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class ArtServiceImpl extends ServiceImpl<ArtMapper, Art> implements ArtService {

    @Autowired
    TokenService tokenService;
    @Autowired
    ArtMapper artMapper;
    @Autowired
    UserArtViewService userArtViewService;
    @Autowired
    ArtCommentService artCommentService;
    @Autowired
    GoodsTradeService goodsTradeService;
    @Autowired
    GoodsRentService goodsRentService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    UserService userService;
    @Autowired
    EthService ethService;
    @Autowired
    AuctionService auctionService;
    @Autowired
    PublicWelfareService publicWelfareService;
    @Autowired
    FileService fileService;
    @Autowired
    ArtLabelService artLabelService;
    @Autowired
    ArtShowFileService artShowFileService;


    @Override
    @Transactional
    public ArrayList<SimpleArt> getHotRecommendArt(Integer type, Integer num) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("num", num);
        return artMapper.getHotRecommendArt(map);
    }

    @Override
    @Transactional
    public DetailsArt getDetailsById(Long token, Long artId) {
        if (token != null) {
            // 添加用户查看记录
            userArtViewService.addViewNum(token, artId);
        }

        // 查询详细的信息  缺少作品的所有者
        DetailsArt detailsArt = artMapper.getDetailsById(artId);

        // 获取作品的token，前往区块链上查询所有者的信息
        String artToken = artMapper.selectById(artId).getArtToken();
        ArrayList<SimpleUser> simpleUserByArtToken = userService.getSimpleUserByArtToken(artToken);

        // 获取其信息放入其对象中
        detailsArt.setOwner(simpleUserByArtToken);
        return detailsArt;
    }


    @Override
    public ArrayList<SimpleArt> getArtRecommend(Long artId, Integer num) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("artId", artId);
        map.put("num", num);
        return artMapper.getArtRecommend(map);
    }

    @Override
    public ArrayList<SimpleGoods> getArtGoods(Long artId) {
        return goodsService.getArtGoods(artId);
    }


    @Override
    public HashMap<String, Object> getUpload(Long artId) {
        Art art = artMapper.selectById(artId);
        if (art == null) {
            return null;
        }
        String artToken = art.getArtToken();

        Tuple2<String, BigInteger> upload = ethService.getUpload(artToken);
        HashMap<String, Object> hashMap = new HashMap<>();
        if (upload == null) {
            return hashMap;
        }
        hashMap.put("time", new Date(upload.getValue2().longValue()));
        SimpleUser simpleUser = userService.getSimpleUserByAddress(upload.getValue1());
        hashMap.put("upload", simpleUser);

        return hashMap;

    }

    @Override
    public ArrayList<SimpleArt> getOwnerAllArt(Long token, Integer type, Integer sort) {
        User user = userService.getById(userService.getUserIdByToken(token));
        if (user == null) {
            return null;
        }
        ArrayList<String> ownerAllArt = ethService.getOwnerAllArt(user.getEthAddress());
        if (ownerAllArt == null) {
            return null;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("ownerAllArt", ownerAllArt);
        hashMap.put("type", type);
        hashMap.put("sort", sort);
        return artMapper.getArtByToken(hashMap);

    }

    @Override
    @Transactional
    public Boolean setArt(Long token, Long artId, String artName, String artIntroduction) {
        User user = userService.getById(userService.getUserIdByToken(token));
        if (user == null) {
            return null;
        }
        Art art = artMapper.selectById(artId);
        if (art == null) {
            return null;
        }

        if (ethService.haveSetArtPower(user.getEthAddress(), art.getArtToken())) {
            Art tempArt = new Art();
            tempArt.setArtId(artId);
            tempArt.setArtName(artName);
            tempArt.setArtIntroduction(artIntroduction);

            int i = artMapper.updateById(tempArt);
            return i > 0;
        }
        return null;

    }

    @Override
    @Transactional
    public Boolean givingArt(Long token, Long artId, Double artQuantity, Long receiverId) {
        DetailsUser sender = userService.getUser(token);
        if (sender == null) {
            return null;
        }
        User receiver = userService.getBaseMapper().selectById(receiverId);
        if (receiver == null) {
            return null;
        }

        Art art = artMapper.selectById(artId);
        if (art == null) {
            return null;
        }

        return ethService.givingArt(sender.getEthAddress(), receiver.getEthAddress(), art.getArtToken(), artQuantity);

    }

    @Override
    @Transactional
    public Long setSell(Long token, Long artId, Double artQuantity, Integer isRent,
                        Date rentStartTime, Date rentEndTime, Double price, String payKey) throws Exception {
        User user = userService.getById(userService.getUserIdByToken(token));
        if (user == null) {
            return null;
        }
        Art art = artMapper.selectById(artId);
        if (art == null) {
            return null;
        }


        Goods goods = new Goods();
        goods.setArtId(artId);
        goods.setTradeMode(isRent == 0 ? 1 : 2);
        goods.setGoodsQuantity(new BigDecimal(artQuantity));
        goods.setGoodsSellerId(user.getUserId());
        goods.setSelled(false);
        if (isRent == 1) {
            goods.setRentStartTime(rentStartTime);
            goods.setRentEndTime(rentEndTime);
        }
        goods.setResalePrice(new BigDecimal(price));

        if (goodsService.getBaseMapper().insert(goods) == 0) {
            return null;
        }

        if (isRent == 0 && !ethService.setSell(art.getArtToken(), artQuantity, payKey, user.getKeySrc())) {
            return null;
        }
        return goods.getGoodsId();
    }

    @Override
    @Transactional
    public Long setAuction(Long token, Long artId, Double artQuantity, Double lowPrice,
                           Double minAddPrice, Integer isRent, Date rentStartTime,
                           Date rentEndTime, Date auctionStartTime, Date auctionEndTime, String payKey) throws Exception {
        User user = userService.getById(userService.getUserIdByToken(token));
        if (user == null) {
            return null;
        }
        Art art = artMapper.selectById(artId);
        if (art == null) {
            return null;
        }

        Goods goods = new Goods();
        goods.setArtId(artId);
        goods.setTradeMode(isRent == 0 ? 1 : 2);
        goods.setGoodsQuantity(new BigDecimal(artQuantity));
        goods.setGoodsSellerId(user.getUserId());
        goods.setSelled(false);
        if (isRent == 1) {
            goods.setRentStartTime(rentStartTime);
            goods.setRentEndTime(rentEndTime);
        }

        if (goodsService.getBaseMapper().insert(goods) == 0) {
            return null;
        }

        Auction auction = new Auction();

        Random random = new Random();
        Integer roomId = Math.abs(random.nextInt());
        while (auctionService.haveRoomId(roomId)) {
            roomId = Math.abs(random.nextInt());
        }
        auction.setAuctionRoomId(roomId);
        auction.setBasePrice(new BigDecimal(lowPrice));
        auction.setAuctionGoodsId(goods.getGoodsId());
        auction.setMinAddprice(new BigDecimal(minAddPrice));
        auction.setStartTime(auctionStartTime);
        auction.setEndTime(auctionEndTime);
        auction.setTopPrice(new BigDecimal(lowPrice));
        auction.setHaveStay(false);
        if (auctionService.getBaseMapper().insert(auction) == 0) {
            return null;
        }
        if (isRent == 0 && !ethService.setSell(art.getArtToken(), artQuantity, payKey, user.getKeySrc())) {
            return null;
        }

        return auction.getAuctionId();
    }


    @Override
    @Transactional
    public Long uploadArt(Long token, String artName, String artIntroduction, Integer artType,
                          MultipartFile multipartFile, String payKey, MultipartFile[] showFile, String artAuthor) throws IOException {
        // 通过token获取用户的address
        User user = userService.getById(userService.getUserIdByToken(token));
        if (user == null) {
            return null;
        }

        // 需要新建一个艺术品的字段
        Art art = new Art();
        art.setArtName(artName);
        art.setArtIntroduction(artIntroduction);
        art.setArtType(artType);

        // 设置艺术品作者，否者为    “佚名”
        art.setArtAuthor(artAuthor);


        File file = fileService.addFile(multipartFile);
        System.out.println(file.getFileHash());
        // 同时将信息上传到区块链中
        String artToken = ethService.uploadArt(file.getFileHash(), payKey, user.getKeySrc());
        art.setArtToken(artToken);

        art.setArtFile(file.getFileId());

        // 将艺术品类存储到数据库中
        if (artMapper.insert(art) == 0) {
            return null;
        }

        // 添加观看次数
        UserArtView userArtView = new UserArtView();
        userArtView.setArtId(art.getArtId());
        userArtView.setUserId(user.getUserId());
        userArtView.setViewNum(1);
        if (!userArtViewService.save(userArtView)) {
            return null;
        }
        // 增加标签
        ArtLabel artLabel = new ArtLabel();
        artLabel.setArtId(art.getArtId());
        artLabel.setArtLabel(artName);
        if (!artLabelService.save(artLabel)) {
            return null;
        }

        if (showFile != null) {
            artShowFileService.addShowFile(art.getArtId(), showFile);
        }

        return art.getArtId();
    }

    @Override
    public Pager<SimpleArt> getAllArt(Integer type, Integer start, Integer limit) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("type", type);
        hashMap.put("start", start);
        hashMap.put("limit", limit);
        Pager<SimpleArt> simpleArtPager = new Pager<>();
        simpleArtPager.setData(artMapper.getAllArt(hashMap));
        simpleArtPager.setStartNum(start);
        simpleArtPager.setLimitNum(limit);
        return simpleArtPager;

    }


}
