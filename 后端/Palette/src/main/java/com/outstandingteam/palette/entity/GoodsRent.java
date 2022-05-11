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
 * 商品租赁
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="GoodsRent对象", description="商品租赁")
public class GoodsRent implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "租赁ID")
    @TableId(value = "rent_id", type = IdType.ASSIGN_ID)
    private Long rentId;

    private Long goodsId;

    @ApiModelProperty(value = "租赁人 收钱的一方")
    private Long leaseholderId;

    @ApiModelProperty(value = "租客 出钱的一方")
    private Long tenantId;

    @ApiModelProperty(value = "租金")
    private BigDecimal charterMoney;

    @ApiModelProperty(value = "手续费")
    private BigDecimal serviceCharge;

    @ApiModelProperty(value = "交易方式（1:零售 2:拍卖 3:盲盒）")
    private Integer tradeType;

    @ApiModelProperty(value = "如果为非零售，存储其方式ID，如果为零售，存储其商品ID")
    private Long typeId;

    @ApiModelProperty(value = "公益")
    private Long publicWelfareId;

    @ApiModelProperty(value = "租期开始时间")
    private Date rentStartTime;

    @ApiModelProperty(value = "租期结束时间")
    private Date rentEndTime;

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
