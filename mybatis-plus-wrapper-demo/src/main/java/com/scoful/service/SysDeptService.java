package com.scoful.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scoful.entity.SysDept;

/**
 * @author scoful
 * @date 2020/6/6 17:39
 */

public interface SysDeptService extends IService<SysDept> {

    IPage<SysDept> selectPage(Page<SysDept> page, String state);
}
