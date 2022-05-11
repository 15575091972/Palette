package com.outstandingteam.palette.controller.util.ret;

import lombok.Data;

import java.util.ArrayList;

@Data
public class SimpleMysteryBox {
    private Long boxId;
    private String boxName;
    private Double boxPrice;
    private ArrayList<String> goods;
}
