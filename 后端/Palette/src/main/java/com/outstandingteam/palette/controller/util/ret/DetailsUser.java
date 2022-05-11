package com.outstandingteam.palette.controller.util.ret;

import lombok.Data;

import java.util.ArrayList;

@Data
public class DetailsUser {
    private Long userId;
    private String userName;
    private String userAccount;
    private String  userHeadImage;
    private String userPhone;
    private String ethAddress;
    private ArrayList<String> identity;
    private Integer followNum;
}
