package com.outstandingteam.palette.mapper;

import com.outstandingteam.palette.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 通知，只包含通知其本身内容（前端将整个标记语言以文字形式发送到数据库保存） Mapper 接口
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05 ${time}
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

}
