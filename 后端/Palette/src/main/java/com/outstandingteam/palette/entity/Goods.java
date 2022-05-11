package com.outstandingteam.palette.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Goods对象", description="商品表")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品ID")
    @TableId(value = "goods_id", type = IdType.ASSIGN_ID)
    private Long goodsId;

    @ApiModelProperty(value = "商品艺术品ID")
    private Long artId;

    @ApiModelProperty(value = "交易模式（1:所有权买卖 2:租赁）")
    private Integer tradeMode;

    @ApiModelProperty(value = "商品数量（所有权：0.01-1.00 租赁 1）")
    private BigDecimal goodsQuantity;

    @ApiModelProperty(value = "商品卖家ID")
    private Long goodsSellerId;

    @ApiModelProperty(value = "如果trade_mode为2，记录租赁开始时间")
    private Date rentStartTime;

    @ApiModelProperty(value = "如果trade_mode为2，记录租赁结束时间")
    private Date rentEndTime;

    @ApiModelProperty(value = "交易方法 1：零售 2: 拍卖 3：盲盒")
    private Integer tradeFun;

    @ApiModelProperty(value = "零售价")
    private BigDecimal resalePrice;

    @ApiModelProperty(value = "是否被售出")
    private Boolean selled;

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
