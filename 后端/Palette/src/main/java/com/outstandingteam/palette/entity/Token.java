package com.outstandingteam.palette.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * token表
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Token对象", description="token表")
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "token", type = IdType.ASSIGN_ID)
    private Long token;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private Date deadlineTime;


}
