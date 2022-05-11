package com.outstandingteam.palette.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.outstandingteam.palette.controller.util.ret.*;
import com.outstandingteam.palette.entity.File;
import com.outstandingteam.palette.entity.User;
import com.outstandingteam.palette.mapper.UserMapper;
import com.outstandingteam.palette.service.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    EthService ethService;
    @Autowired
    LocationService locationService;
    @Autowired
    ShoppingCarService shoppingCarService;
    @Autowired
    GoodsRentService goodsRentService;
    @Autowired
    GoodsTradeService goodsTradeService;
    @Autowired
    FileService fileService;
    @Autowired
    TokenService tokenService;

    @Override
    @Transactional
    public Long login(String account, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .eq("user_account", account)
                .eq("user_password", password);
        // 如果查询得到相关账户，则获取token，否则返回null
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            return 0l;
        }
        return tokenService.getToken(user.getUserId());
    }

    @Override
    @Transactional
    public Boolean registered(String account, String password, String payKey, String userPhone, String userName) throws Exception {
        if (accountIsBe(account)) {
            return false;
        }
        User user = new User();
        user.setUserAccount(account);
        user.setUserPassword(password);
        if (userPhone != null) {
            user.setUserPhone(userPhone);
        }
        if (userName != null) {
            user.setUserName(userName);
        }
        user.setSkipCourse(false);
        // 生成一个该用户对应的区块链上的账户
        JSONObject jsonObject = new JSONObject(ethService.setEthAddress(payKey));
        user.setEthAddress((String) jsonObject.get("address"));
        user.setKeySrc((String) jsonObject.get("fileName"));


        // 插入数据
        return userMapper.insert(user) > 0;
    }

    @Override
    public Boolean accountIsBe(String account) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("user_account", account)) != null;
    }

    @Override
    public SimpleUser getOtherUser(Long userId) {
        return userMapper.getOtherUser(userId);
    }

    @Override
    public DetailsUser getUser(Long token) {
        return userMapper.getUser(token);
    }

    @Override
    @Transactional
    public Boolean setUser(Long token, String userName, String userAccount, String userPhone, String userPassword) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("userName", userName);
        map.put("userAccount", userAccount);
        map.put("userPhone", userPhone);
        map.put("userPassword", userPassword);
        return userMapper.setUser(map);
    }

    @Override
    public ArrayList<DetailLocation> getHomeLocation(Long token) {
        return locationService.getHomeLocation(token);
    }


    @Override
    public Map<String, Object> getOrder(Long token) {
        ArrayList<SimpleTrade> trades = goodsTradeService.getOrder(token);
        ArrayList<SimpleRent> rents = goodsRentService.getOrder(token);
        if (trades == null && rents == null) {
            return null;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("trades", trades);
        map.put("rents", rents);
        return map;
    }

    @Override
    @Transactional
    public Boolean setHeadImage(Long token, MultipartFile multipartFile) throws IOException {
        File file = fileService.addFile(multipartFile);
        if (file != null) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("userId", getUserIdByToken(token));
            map.put("userHeadImageId", file.getFileId());
            return userMapper.setUser(map);
        }
        return false;
    }

    @Override
    public Double getBalance(Long token) throws Exception {
        String ethAddress = getById(getUserIdByToken(token)).getEthAddress();
        return ethService.getBalance(ethAddress);
    }

    @Override
    public ArrayList<SimpleUser> getSimpleUserByArtToken(String artToken) {
        // 通过 artToken 获取用户的 address
        ArrayList<String> addressByTokenId = ethService.getAddressByTokenId(artToken);
        if (addressByTokenId.isEmpty()) {
            return null;
        }
        return userMapper.getSimpleUserByAddress(addressByTokenId);
    }

    @Override
    public SimpleUser getSimpleUserByAddress(String ethAddress) {
        ArrayList<String> list = new ArrayList<>();
        list.add(ethAddress);
        return userMapper.getSimpleUserByAddress(list).get(0);
    }

    @Override
    public Long getUserIdByToken(Long token) {
        return tokenService.getById(token).getUserId();
    }

    @Override
    public Boolean chargeMTB(Long token, Integer MTBAmount, String chargeEvidence) throws Exception {
        // 获取本人的信息
        User user = userMapper.selectById(getUserIdByToken(token));
        if (user == null) {
            return false;
        }

        // 验证充值凭证



        // 充值
        return ethService.chargeMTB(user.getEthAddress(),MTBAmount);
    }
}
