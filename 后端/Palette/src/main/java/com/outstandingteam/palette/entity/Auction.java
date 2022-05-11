package com.outstandingteam.palette.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 拍卖表
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Auction对象", description="拍卖表")
public class Auction implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "拍卖ID")
    @TableId(value = "auction_id", type = IdType.ASSIGN_ID)
    private Long auctionId;

    @ApiModelProperty(value = "拍卖房间号码")
    private Integer auctionRoomId;

    @ApiModelProperty(value = "拍卖商品")
    private Long auctionGoodsId;

    @ApiModelProperty(value = "拍卖基础价")
    private BigDecimal basePrice;

    @ApiModelProperty(value = "每次最少加价")
    private BigDecimal minAddprice;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "当前价格")
    private BigDecimal topPrice;

    @ApiModelProperty(value = "是否留拍")
    private Boolean haveStay;

    @ApiModelProperty(value = "乐观锁")
    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "创造时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
