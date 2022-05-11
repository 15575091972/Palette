package com.outstandingteam.palette.entity;

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
 * 艺术品检查
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ArtCheck对象", description="艺术品检查")
public class ArtCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "检查ID")
    @TableId(value = "check_id", type = IdType.ASSIGN_ID)
    private Long checkId;

    @ApiModelProperty(value = "审查艺术品ID")
    private Long artId;

    @ApiModelProperty(value = "是否通过审查")
    private Boolean havaPass;

    @ApiModelProperty(value = "审查意见")
    private String checkResult;

    @ApiModelProperty(value = "审核人")
    private Long checker;

    @ApiModelProperty(value = "审核截止时间")
    private Date endTime;

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
