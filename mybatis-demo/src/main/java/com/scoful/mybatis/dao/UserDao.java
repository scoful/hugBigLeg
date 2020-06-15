package com.scoful.mybatis.dao;

import com.scoful.mybatis.pojo.User;

import java.util.List;

/**
 * 非常原生的用法，不推荐
 *
 * @author scoful
 * @date 2020/5/22 19:25
 */
public interface UserDao {
    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    public User queryUserById(String id);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    public List<User> queryUserAll();

    /**
     * 新增用户
     *
     * @param user
     */
    public void insertUser(User user);

    /**
     * 更新用户信息
     *
     * @param user
     */
    public void updateUser(User user);

    /**
     * 根据id删除用户信息
     *
     * @param id
     */
    public void deleteUser(String id);
}
