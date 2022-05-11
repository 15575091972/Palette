package com.outstandingteam.palette.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.outstandingteam.palette.controller.util.ret.DetailLocation;
import com.outstandingteam.palette.controller.util.ret.DetailsUser;
import com.outstandingteam.palette.controller.util.ret.SimpleUser;
import com.outstandingteam.palette.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface UserService extends IService<User> {
    // 登录验证
    Long login(String account, String password);

    // 注册验证
    Boolean registered(String account, String password, String payKey,String userPhone, String userName) throws Exception;

    // 检查账号是否存在
    Boolean accountIsBe(String account);

    // 获取其余用户的简略信息
    SimpleUser getOtherUser(Long userId);

    // 获取自己的详细基本信息
    DetailsUser getUser(Long token);

    // 修改自己的基本信息
    Boolean setUser(Long token, String userName, String userAccount, String userPhone, String userPassword);

    // 获取自己的邮寄保存信息
    ArrayList<DetailLocation> getHomeLocation(Long token);


    // 获取本人的订单，即自己的交易记录
    Map<String, Object> getOrder(Long token);

    // 获取本人的订单，即自己的交易记录
    Boolean setHeadImage(Long token, MultipartFile multipartFile) throws IOException;

    // 获取自己的余额
    Double getBalance(Long token) throws Exception;

    // 通过artToken 获取其所有者的信息
    ArrayList<SimpleUser> getSimpleUserByArtToken(String artToken);

    // 通过address获取简易用户信息
    SimpleUser getSimpleUserByAddress(String ethAddress);

    // 通过token获取该用户的UserId
    Long getUserIdByToken(Long token);
    // 充值MTB（喵凸币）
    Boolean chargeMTB(Long token, Integer MTBAmount, String chargeEvidence) throws Exception;
}
