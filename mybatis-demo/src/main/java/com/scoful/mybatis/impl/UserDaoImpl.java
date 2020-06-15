package com.scoful.mybatis.impl;

import com.scoful.mybatis.dao.UserDao;
import com.scoful.mybatis.pojo.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 非常原生的用法，还写死了字符串
 *
 * @author scoful
 * @date 2020/5/22 19:25
 */
public class UserDaoImpl implements UserDao {

    private SqlSession sqlSession;

    public UserDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public User queryUserById(String id) {
        return sqlSession.selectOne("UserDao.queryUserById", id);
    }

    public List<User> queryUserAll() {
        return sqlSession.selectList("UserDao.queryUserAll");
    }

    public void insertUser(User user) {
        sqlSession.insert("UserDao.insertUser", user);
    }

    public void updateUser(User user) {
        sqlSession.update("UserDao.updateUser", user);
    }

    public void deleteUser(String id) {
        sqlSession.delete("UserDao.deleteUser", id);
    }
}
