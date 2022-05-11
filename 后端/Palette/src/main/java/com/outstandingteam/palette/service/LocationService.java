package com.outstandingteam.palette.service;

import com.outstandingteam.palette.controller.util.ret.DetailLocation;
import com.outstandingteam.palette.entity.Location;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 * 收货地址（主要是实体艺术品的交易） 服务类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Service
public interface LocationService extends IService<Location> {
    ArrayList<DetailLocation> getHomeLocation(Long token);
}
