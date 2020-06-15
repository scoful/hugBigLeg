package com.scoful.mybatis.dao;

import com.scoful.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author scoful
 * @date 2020/5/22 20:15
 */
public class UserMapperTest {

    public UserMapper userMapper;

    @Before
    public void setUp() throws Exception {
        // 指定全局配置文件
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        // 构建sqlSessionFactory
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获取sqlSession
        SqlSession sqlSession = build.openSession(true);
        // 1. 映射文件的命名空间（namespace）必须是mapper接口的全路径
        // 2. 映射文件的statement的id必须和mapper接口的方法名保持一致
        // 3. Statement的resultType必须和mapper接口方法的返回类型一致
        // 4. statement的parameterType必须和mapper接口方法的参数类型一致（不一定）
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void login() {
        System.out.println(userMapper.login("dj", "123456"));
        System.out.println(userMapper.login("dj", "123456"));
    }

    @Test
    public void queryUserByTableName() {
        List<User> tb_user = userMapper.queryUserByTableName("tb_user");
        for (User user : tb_user) {
            System.out.println(user);
        }
    }


    @Test
    public void queryUserById() {
        System.out.println(userMapper.queryUserById("111"));
    }

    @Test
    public void queryUserAll() {
        List<User> users = userMapper.queryUserAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setId("555");
        user.setAge(18);
        user.setBirthday(new Date("1988/08/24"));
        user.setName("小胡");
        user.setUserName("dh");
        userMapper.insertUser(user);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setUserName("dj");
        user.setId("333");
        userMapper.updateUser(user);
    }

    @Test
    public void deleteUserById() {
        userMapper.deleteUserById("333");
    }

    @Test
    public void queryUserList() {
        List<User> users = userMapper.queryUserList("静静");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void queryUserListByNameOrAge() {
        List<User> users = userMapper.queryUserListByNameOrAge(null, 18);
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void queryUserListByNameAndAge() {
        List<User> users = userMapper.queryUserListByNameAndAge(null, 18);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void queryUserListByIds() {
        List<User> users = userMapper.queryUserListByIds(new String[]{"111", "222"});
        for (User user : users) {
            System.out.println(user);
        }
    }
}