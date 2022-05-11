package com.outstandingteam.palette.impl;

import com.outstandingteam.palette.controller.util.ret.DetailLocation;
import com.outstandingteam.palette.entity.Location;
import com.outstandingteam.palette.mapper.LocationMapper;
import com.outstandingteam.palette.service.LocationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 * 收货地址（主要是实体艺术品的交易） 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class LocationServiceImpl extends ServiceImpl<LocationMapper, Location> implements LocationService {

    @Autowired
    LocationMapper locationMapper;

    @Override
    public ArrayList<DetailLocation> getHomeLocation(Long token) {
        return locationMapper.getHomeLocation(token);
    }
}
