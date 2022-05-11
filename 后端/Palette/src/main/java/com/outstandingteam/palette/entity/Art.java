package com.outstandingteam.palette.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 电子艺术品表
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Art对象", description="电子艺术品表")
public class Art implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "艺术品ID")
    @TableId(value = "art_id", type = IdType.ASSIGN_ID)
    private Long artId;

    @ApiModelProperty(value = "艺术品名称")
    private String artName;

    @ApiModelProperty(value = "电子艺术品源文件")
    private Long artFile;

    @ApiModelProperty(value = "艺术品类型 0：电子图片艺术品 1：实体艺术品 2:音乐 3：影视 4：游戏 5：模型 ")
    private Integer artType;

    @ApiModelProperty(value = "艺术品简介")
    private String artIntroduction;

    @ApiModelProperty(value = "在区块链中的token")
    private String artToken;

    @ApiModelProperty(value = "艺术品作者")
    private String artAuthor;


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
