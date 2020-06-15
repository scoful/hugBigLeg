package com.scoful.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scoful.mapper.UserMapper;
import com.scoful.entity.User;
import com.scoful.service.UserService;
/**
 * @author  scoful
 * @date 2020/6/8 16:37
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}
