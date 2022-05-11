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
 * 公益记录表
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PublicWelfare对象", description="公益记录表")
public class PublicWelfare implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公益ID")
    @TableId(value = "publicwelfare_id", type = IdType.ASSIGN_ID)
    private Long publicwelfareId;

    @ApiModelProperty(value = "捐赠金额（人民币）")
    private BigDecimal publicwelfareCoin;

    @ApiModelProperty(value = "公益对象")
    private String publicwelfareObject;

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
