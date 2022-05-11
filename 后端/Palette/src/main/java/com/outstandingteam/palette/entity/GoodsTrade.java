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
 * 商品交易记录表
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="GoodsTrade对象", description="商品交易记录表")
public class GoodsTrade implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "交易记录ID")
    @TableId(value = "trade_id", type = IdType.ASSIGN_ID)
    private Long tradeId;

    @ApiModelProperty(value = "商品的ID")
    private Long goodsId;

    @ApiModelProperty(value = "卖方ID")
    private Long sellerId;

    @ApiModelProperty(value = "买方ID")
    private Long buyerId;

    @ApiModelProperty(value = "涉及金额")
    private BigDecimal coin;

    @ApiModelProperty(value = "手续费")
    private BigDecimal serviceCharge;

    @ApiModelProperty(value = "交易方式（1:零售 2:拍卖 3:盲盒）")
    private Integer tradeType;

    @ApiModelProperty(value = "如果为非零售，存储其方式ID(拍卖、盲盒)，如果为零售，存储其商品ID")
    private Long typeId;

    @ApiModelProperty(value = "公益")
    private Long publicWelfareId;

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
