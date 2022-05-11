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
 * 试卷试题表
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TestPaperProblem对象", description="试卷试题表")
public class TestPaperProblem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "问题ID")
    @TableId(value = "problem_id", type = IdType.ASSIGN_ID)
    private Long problemId;

    @ApiModelProperty(value = "试卷号")
    private Long testPaperId;

    @ApiModelProperty(value = "分值")
    private Integer score;

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
