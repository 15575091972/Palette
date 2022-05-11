package com.outstandingteam.palette.mapper;

import com.outstandingteam.palette.controller.util.ret.DetailLocation;
import com.outstandingteam.palette.entity.Location;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * <p>
 * 收货地址（主要是实体艺术品的交易） Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface LocationMapper extends BaseMapper<Location> {
    ArrayList<DetailLocation> getHomeLocation(Long token);
}
