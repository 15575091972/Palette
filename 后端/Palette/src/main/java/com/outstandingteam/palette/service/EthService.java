package com.outstandingteam.palette.service;

import org.springframework.stereotype.Service;
import org.web3j.tuples.generated.Tuple2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

@Service
public interface EthService {

    // 创造以太坊地址
    String setEthAddress(String payKey) throws Exception;

    // 前往以太坊获取余额
    Double getBalance(String ethAddress) throws Exception;

    // 查询一个艺术品的拥有者
    ArrayList<String> getAddressByTokenId(String artToken);

    // 获取艺术品的上传信息
    Tuple2<String, BigInteger> getUpload(String artToken);

    // 获取用户的艺术品
    ArrayList<String> getOwnerAllArt(String ethAddress);

    // 获取是否有该艺术品的试用权
    Boolean haveSetArtPower(String ethAddress, String artToken);

    // 转赠
    Boolean givingArt(String sender, String receiver, String artToken, Double artQuantity);

    // 设置为出售状态
    Boolean setSell(String artToken, Double artQuantity, String payKey, String keySrc) throws Exception;

    // 出售艺术品
    Boolean Sell(String ethAddress, String artToken, BigDecimal goodsQuantity,
                 BigDecimal resalePrice, String payKey, String keySrc) throws Exception;

    // 上传艺术品
    String uploadArt(String fileHash, String payKey, String keySrc);

    // 租赁交易
    Boolean payRent(String payKey, String keySrc, String artToken, BigDecimal resalePrice) throws Exception;

    // 充值MTB（喵凸币）
    Boolean chargeMTB(String ethAddress, Integer MTBAmount) throws Exception;
}
