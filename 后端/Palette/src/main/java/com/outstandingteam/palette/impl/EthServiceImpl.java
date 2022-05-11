package com.outstandingteam.palette.impl;

import com.outstandingteam.palette.service.EthService;
import com.outstandingteam.palette.util.EthUtil;
import com.outstandingteam.palette.util.IdWorker;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.Bip39Wallet;
import org.web3j.crypto.Bip44WalletUtils;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class EthServiceImpl implements EthService {

    private static Web3j web3j;

    static {
        web3j = EthUtil.getInstance().getWeb3j();
    }

    @Override
    @Transactional
    public String setEthAddress(String payKey) throws Exception {
        // 生成 Keystore
        String walletFilePath = "D:\\Workspace\\GETH\\keystore";
        File file = new File(walletFilePath);
        if (!file.exists()) {
            file.mkdirs();
        }

        // 在区块链上创造用户，并生成文件
        Bip39Wallet bip39Wallet = Bip44WalletUtils.generateBip44Wallet(payKey, file);

        Credentials credentials = WalletUtils.loadCredentials(payKey, walletFilePath + "\\" + bip39Wallet.getFilename());

        // 获取其生成的地址
        String address = credentials.getAddress();


        //转账 10ETH
        EthUtil.getInstance().sendETH(address, 10);

        // 将生成的该用户设置为合约的用户
        TransactionReceipt transactionReceipt = EthUtil.getInstance().getPalette()
                .setPower(address)
                .send();
        if (transactionReceipt.getStatus().equals("0x1")) {

            return new JSONObject()
                    .put("address", address)
                    .put("fileName", bip39Wallet.getFilename())
                    .toString();
        }
        return null;
    }

    @Override
    public Double getBalance(String ethAddress) throws Exception {
        BigInteger send = EthUtil.getInstance().getPalette().getBalance(ethAddress).send();
        if (send != null) {
            return send.doubleValue();
        }
        return null;
    }


    @Override
    public ArrayList<String> getAddressByTokenId(String artToken) {
        try {
            List list = EthUtil.getInstance().getPalette().getAddressByTokenId(new BigInteger(artToken)).send();
            return (ArrayList<String>) list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Tuple2<String, BigInteger> getUpload(String artToken) {
        try {
            return EthUtil.getInstance().getPalette().getUpload(new BigInteger(artToken)).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getOwnerAllArt(String ethAddress) {
        try {
            List send = EthUtil.getInstance().getPalette().getArtsByAddress(ethAddress).send();
            return (ArrayList<String>) send;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean haveSetArtPower(String ethAddress, String artToken) {
        List send = null;
        try {
            send = EthUtil.getInstance().getPalette().getAddressByTokenId(new BigInteger(artToken)).send();
            for (Object o : send) {
                if (o.equals(ethAddress)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Boolean givingArt(String sender, String receiver, String artToken, Double artQuantity) {
        TransactionReceipt send = null;
        try {
            int quantity = (int) (artQuantity * 100);
            send = EthUtil.getInstance().getPalette().transactionArt(receiver, new BigInteger(artToken), BigInteger.valueOf(quantity)).send();
            System.out.println(send);
            return send.getStatus().equals("0x1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean setSell(String artToken, Double artQuantity, String payKey, String keySrc) throws Exception {
        // 将需要设置的数量由小数形式转为整数形势
        TransactionReceipt send = null;
        int quantity = (int) (artQuantity * 100);

        send = EthUtil.getInstance()
                .getPalette(payKey, keySrc)
                .asGoods(new BigInteger(artToken), BigInteger.valueOf(quantity))
                .send();
        if (send.getStatus().equals("0x1")) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean Sell(String ethAddress, String artToken, BigDecimal goodsQuantity,
                        BigDecimal resalePrice, String payKey, String keySrc) throws Exception {
        // 将数量和价格进行转换
        int quantity = (int) (goodsQuantity.doubleValue() * 100);
        int price = (int) resalePrice.doubleValue();

        // 发起交易
        TransactionReceipt send = EthUtil.getInstance().getPalette(payKey, keySrc)
                .transactionArtAndMTB(ethAddress, new BigInteger(artToken),
                        BigInteger.valueOf(quantity), BigInteger.valueOf(price))
                .send();
        return send.getStatus().equals("0x1");
    }

    @Override
    public String uploadArt(String fileHash, String payKey, String keySrc) {
        BigInteger artToken = BigInteger.valueOf(new IdWorker().nextId());
        try {
            TransactionReceipt send = EthUtil.getInstance()
                    .getPalette(payKey, keySrc)
                    .uploadNFT(artToken,
                            new BigInteger(fileHash, 16))
                    .send();
            if (send.getStatus().equals("0x1")) {
                return artToken.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Boolean payRent(String payKey, String keySrc, String artToken, BigDecimal resalePrice) throws Exception {
        ArrayList<String> address = (ArrayList<String>) EthUtil.getInstance().getPalette()
                .getAddressByTokenId(new BigInteger(artToken)).send();
        if (address.size() == 0) {
            return false;
        }
        int v = (int) resalePrice.doubleValue() * 100;
        for (String s : address) {
            TransactionReceipt send = EthUtil.getInstance()
                    .getPalette(payKey, keySrc)
                    .transactionMTB(s, BigInteger.valueOf(v))
                    .send();
        }
        return null;
    }

    @Override
    public Boolean chargeMTB(String ethAddress, Integer MTBAmount) throws Exception {
        TransactionReceipt send = EthUtil.getInstance().getPalette()
                .topUpMTB(ethAddress,BigInteger.valueOf(MTBAmount)).send();
        return send.getStatus().equals("0x1");
    }

}
