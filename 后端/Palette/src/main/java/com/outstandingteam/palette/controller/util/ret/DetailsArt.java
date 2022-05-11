package com.outstandingteam.palette.controller.util.ret;

import lombok.Data;

import java.util.ArrayList;

@Data
public class DetailsArt {
    private Long artId;
    private String artName;
    private Integer artType;
    private String artAuthor;
    private ArrayList<SimpleUser> owner;
    private String artIntroduction;
    private String showImage;
    private ArrayList<String> labels;
    private ArrayList<String> showFile;


}
