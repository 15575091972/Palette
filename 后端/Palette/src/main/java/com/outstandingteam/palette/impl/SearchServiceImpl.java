package com.outstandingteam.palette.impl;

import com.outstandingteam.palette.entity.Search;
import com.outstandingteam.palette.mapper.SearchMapper;
import com.outstandingteam.palette.service.SearchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户搜索记录 服务实现类
 * </p>
 *
 * @author chenjintao
 * @since 2022-03-05
 */
@Service
public class SearchServiceImpl extends ServiceImpl<SearchMapper, Search> implements SearchService {

}
