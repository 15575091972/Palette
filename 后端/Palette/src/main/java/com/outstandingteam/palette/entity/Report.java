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
 * 举报记录列表
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Report对象", description="举报记录列表")
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "举报记录ID")
    @TableId(value = "report_id", type = IdType.ASSIGN_ID)
    private Long reportId;

    @ApiModelProperty(value = "举报对象类型（1:用户  2:聊天记录 3:商品 4:交易记录 5:测试问题 6:拍卖 7:盲盒 8:其他）")
    private Integer reportObjectType;

    @ApiModelProperty(value = "举报对象")
    private Long reportObject;

    @ApiModelProperty(value = "举报内容")
    private String reportContent;

    @ApiModelProperty(value = "举报者")
    private Long reporter;

    @ApiModelProperty(value = "举报结果")
    private String reportResult;

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
