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
 * 审核员考察问题
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TestQuestions对象", description="审核员考察问题")
public class TestQuestions implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "问题ID")
    @TableId(value = "problem_id", type = IdType.ASSIGN_ID)
    private Long problemId;

    @ApiModelProperty(value = "问题描述")
    private String problemTitle;

    @ApiModelProperty(value = "干扰选项A")
    private String disturbA;

    @ApiModelProperty(value = "干扰选项B")
    private String disturbB;

    @ApiModelProperty(value = "干扰选项C")
    private String disturbC;

    @ApiModelProperty(value = "答案")
    private String answer;

    private Long authorId;

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
