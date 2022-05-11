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
 * 盲盒
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="MysteryBox对象", description="盲盒")
public class MysteryBox implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "盲盒ID")
    @TableId(value = "box_id", type = IdType.ASSIGN_ID)
    private Long boxId;

    @ApiModelProperty(value = "盲盒名称")
    private String boxName;

    @ApiModelProperty(value = "盲盒价格")
    private BigDecimal boxPrice;

    @ApiModelProperty(value = "盲盒简介")
    private String boxIntroduction;

    @ApiModelProperty(value = "盲盒开盒时间")
    private Date openBoxTime;

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
