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
 * 试卷表
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TestPaper对象", description="试卷表")
public class TestPaper implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "试卷号")
    @TableId(value = "test_paper_id", type = IdType.ASSIGN_ID)
    private Long testPaperId;

    @ApiModelProperty(value = "试卷名称")
    private String testPaperName;

    @ApiModelProperty(value = "试卷简介")
    private String testPaperIntroduction;

    @ApiModelProperty(value = "试卷开始时间")
    private Date testPaperStartTime;

    @ApiModelProperty(value = "考试结束时间")
    private Date testPaperEndTime;

    @ApiModelProperty(value = "是否允许提前交卷")
    private Boolean hadAdvance;

    @ApiModelProperty(value = "试卷题目数")
    private Integer questionsNum;

    @ApiModelProperty(value = "满分值")
    private Integer maxResults;

    @ApiModelProperty(value = "答案，试卷发下去时生成")
    private String answer;

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
