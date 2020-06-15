package com.scoful.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scoful.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author  scoful
 * @date 2020/6/8 16:37
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}