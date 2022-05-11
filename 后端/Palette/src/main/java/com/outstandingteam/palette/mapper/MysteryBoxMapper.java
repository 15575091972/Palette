package com.outstandingteam.palette.mapper;

import com.outstandingteam.palette.controller.util.ret.SimpleMysteryBox;
import com.outstandingteam.palette.entity.MysteryBox;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 盲盒 Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface MysteryBoxMapper extends BaseMapper<MysteryBox> {
    // 获取简单盲盒的信息
    SimpleMysteryBox getSimpleById(Long boxId);
}
