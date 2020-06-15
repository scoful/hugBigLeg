package com.scoful.mybatis.dao;

import com.scoful.mybatis.impl.UserDaoImpl;
import com.scoful.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author scoful
 * @date 2020/5/22 19:35
 */
public class UserDaoTest {
    public UserDao userDao;
    public SqlSession sqlSession;

    @Before
    public void setUp() throws Exception {
        // 指定全局配置文件
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        // 构建sqlSessionFactory
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获取sqlSession
        sqlSession = build.openSession();
        userDao = new UserDaoImpl(sqlSession);
    }


    @Test
    public void queryUserById() {
        System.out.println(userDao.queryUserById("111"));
    }

    @Test
    public void queryUserAll() {
        List<User> users = userDao.queryUserAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void insertUser() {
//        User user = new User();
//        user.setId("333");
//        user.setAge(18);
//        user.setBirthday(new Date("1988/08/24"));
//        user.setName("大虎");
//        user.setUserName("dh");
//        userDao.insertUser(user);
//        sqlSession.commit();
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setUserName("dj");
        user.setId("222");
        userDao.updateUser(user);
        sqlSession.commit();
    }

    @Test
    public void deleteUser() {
        userDao.deleteUser("333");
    }
}