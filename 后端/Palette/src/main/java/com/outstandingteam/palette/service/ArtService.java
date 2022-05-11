package com.outstandingteam.palette.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.outstandingteam.palette.controller.util.ret.DetailsArt;
import com.outstandingteam.palette.controller.util.ret.SimpleArt;
import com.outstandingteam.palette.controller.util.ret.SimpleGoods;
import com.outstandingteam.palette.entity.Art;
import com.outstandingteam.palette.util.Pager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * <p>
 * 电子艺术品表 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface ArtService extends IService<Art> {
    // 主页推荐的艺术品
    ArrayList<SimpleArt> getHotRecommendArt(Integer type, Integer num);

    // 获取其详细信息
    DetailsArt getDetailsById(Long token, Long artId);


    // 获取一个作品后面同类型的推荐艺术品
    ArrayList<SimpleArt> getArtRecommend(Long artId, Integer num);

    // 获取一个作品的现在存在的商品状态
    ArrayList<SimpleGoods> getArtGoods(Long artId);


    // 获取上传的信息
    HashMap<String, Object> getUpload(Long artId);

    // 获取用户本人的艺术品
    ArrayList<SimpleArt> getOwnerAllArt(Long token, Integer type, Integer sort);

    // 修改艺术品的一些基本信息
    Boolean setArt(Long token, Long artId, String artName, String artIntroduction);

    // 转赠艺术品
    Boolean givingArt(Long token, Long artId, Double artQuantity, Long receiverId);

    // 将艺术品设置为出售状态
    Long setSell(Long token, Long artId, Double artQuantity, Integer isRent, Date rentStartTime,
                 Date rentEndTime, Double price, String payKey) throws Exception;

    // 设置买卖的信息
    Long setAuction(Long token, Long artId, Double artQuantity, Double lowPrice,
                    Double minAddPrice, Integer isRent, Date rentStartTime, Date rentEndTime,
                    Date auctionStartTime, Date auctionEndTime, String payKey) throws Exception;

    // 上传一个艺术品
    Long uploadArt(Long token, String artName, String artIntroduction, Integer artType,
                   MultipartFile multipartFile, String payKey, MultipartFile[] showFile, String artAuthor) throws IOException;

    // 获取某个类型的所有的艺术品，
    Pager<SimpleArt> getAllArt(Integer type, Integer start, Integer limit);
}
