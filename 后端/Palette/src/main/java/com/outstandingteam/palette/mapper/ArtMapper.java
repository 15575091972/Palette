package com.outstandingteam.palette.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.outstandingteam.palette.controller.util.ret.DetailsArt;
import com.outstandingteam.palette.controller.util.ret.SimpleArt;
import com.outstandingteam.palette.entity.Art;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 电子艺术品表 Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface ArtMapper extends BaseMapper<Art> {
    // 根据artId获取SimpleArt
    SimpleArt getSimpleArtById(Long artId);

    ArrayList<SimpleArt> getHotRecommendArt(Map<String, Object> map);

    // 获取n条特定类型的SimpleArt记录
    ArrayList<SimpleArt> getSomeArt(Map<String, Object> map);

    // 获取其详细信息
    DetailsArt getDetailsById(Long artId);

    // 获取一个作品后面同类型的推荐艺术品
    ArrayList<SimpleArt> getArtRecommend(Map<String, Object> map);

    // 通过艺术品的token获取艺术品信息
    ArrayList<SimpleArt> getArtByToken(HashMap<String, Object> hashMap);

    // 获取某个类型的所有艺术品
    ArrayList<SimpleArt> getAllArt(HashMap<String,Object> map);
}
