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
 * 拍卖的加价表
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AuctionAddprice对象", description="拍卖的加价表")
public class AuctionAddprice implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "加价记录ID")
    @TableId(value = "aution_addprice_id", type = IdType.ASSIGN_ID)
    private Long autionAddpriceId;

    @ApiModelProperty(value = "参与的拍卖场次")
    private Long autionId;

    @ApiModelProperty(value = "喊价，即当前价格")
    private BigDecimal bidPrice;

    @ApiModelProperty(value = "喊价人")
    private Long userId;

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
