package com.scoful.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scoful.entity.SysDept;
import com.scoful.mapper.SysDeptMapper;
import com.scoful.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author scoful
 * @date 2020/6/6 17:39
 */

@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public IPage<SysDept> selectPage(Page<SysDept> page, String state) {
        IPage<SysDept> result = sysDeptMapper.selectPageVo(page, state);
        return result;
    }
}
