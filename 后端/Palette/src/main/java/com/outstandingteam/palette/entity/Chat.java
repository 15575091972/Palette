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
 * 聊天内容表，聊天内容可以为文字，也可能为文件
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Chat对象", description="聊天内容表，聊天内容可以为文字，也可能为文件")
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "聊天记录ID")
    @TableId(value = "chat_id", type = IdType.ASSIGN_ID)
    private Long chatId;

    @ApiModelProperty(value = "是否为文件")
    private Boolean isFile;

    @ApiModelProperty(value = "聊天发送者")
    private Long sender;

    @ApiModelProperty(value = "聊天接收者")
    private Long recipient;

    @ApiModelProperty(value = "聊天内容，如果时文件，内容保存问文件ID")
    private String chatContent;

    @ApiModelProperty(value = "如果发送为文件是，拥有文件值")
    private Long fileId;

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
