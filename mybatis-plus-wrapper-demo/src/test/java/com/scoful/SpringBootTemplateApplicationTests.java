package com.scoful;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.scoful.entity.User;
import com.scoful.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * junit5的写法
 * 不需要写RunWith(SpringRunner.class),而且类不用public
 */
@SpringBootTest
class SpringBootTemplateApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void select() {
        List<User> userList = userMapper.selectList(null);
        assertEquals(7, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    void insert() {
        User user = new User();
//        user.setId(IdWorker.getId());// 如果实体类的id设置了type = IdType.INPUT,就需要手动
        user.setUserName("台湾5");// 如果实体类的id设置了type = IdType.ASSIGN_ID，就自动填充，或者在配置文件里全局配置
        user.setAge(30);
        user.setManagerId(1087982257332887553L);
        int rows = userMapper.insert(user);
        System.out.println("影响行数：" + rows);
    }

    @Test
    void selectById() {
        User user = userMapper.selectById(1087982257332887553L);
        System.out.println(user);
    }

    @Test
    void selectBatchIds() {
        List<Long> userIds = Arrays.asList(1088248166370832385L, 1094590409767661570L, 1269935187089727490L);
        List<User> userList = userMapper.selectBatchIds(userIds);
        userList.forEach(System.out::println);
    }

    @Test
    void selectByMap() {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("user_name", "王天风");
        stringObjectHashMap.put("age", 25);
        List<User> userList = userMapper.selectByMap(stringObjectHashMap);
        userList.forEach(System.out::println);

    }

    /**
     * 名字中包含雨并且年龄小于40
     * name like '%雨%' and age<40
     */
    @Test
    void selectByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name", "雨")
                    .lt("age", 40);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
        // lambda写法
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.like(User::getUserName, "雨")
                              .lt(User::getAge, 40);
        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 名字中包含雨年并且龄大于等于20且小于等于40并且email不为空
     * name like '%雨%' and age between 20 and 40 and email is not null
     */
    @Test
    void selectByWrapper2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("user_name", "雨")
                .between("age", 20, 40)
                .isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
        // lambda写法
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.like(User::getUserName, "雨")
                              .between(User::getAge, 20, 40)
                              .isNotNull(User::getEmail);
        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        users.forEach(System.out::println);

    }

    /**
     * 名字为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
     * name like '王%' or age>=25 order by age desc,id asc
     */
    @Test
    void selectByWrapper3() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .likeRight("user_name", "王")
                .or()
                .ge("age", 25)
                .orderByDesc("age")
                .orderByAsc("id");

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
        // lambda写法
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.likeRight(User::getUserName, "王")
                              .or()
                              .ge(User::getAge, 25)
                              .orderByDesc(User::getAge)
                              .orderByAsc(User::getId);
        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 创建日期为2019年2月14日并且直属上级为名字为王姓
     * date_format(create_time,'%Y-%m-%d')='2019-02-14' and manager_id in (select id from user where name like '王%')
     */
    @Test
    void selectByWrapper4() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-02-14")
                    .inSql("manager_id", "select id from tb_user where user_name like '王%'");

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
        // lambda写法
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-02-14")
                              .inSql(User::getManagerId, "select id from tb_user where user_name like '王%'");
        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 名字为王姓并且（年龄小于40或邮箱不为空）
     * name like '王%' and (age<40 or email is not null)
     */
    @Test
    void selectByWrapper5() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("user_name", "王")
                    .and(wq -> wq.lt("age", 40)
                                 .or()
                                 .isNotNull("email"));
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
        // lambda写法
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.likeRight(User::getUserName, "王")
                              .and(lqw -> lqw.lt(User::getAge, 40)
                                             .or()
                                             .isNotNull(User::getEmail));
        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
     * name like '王%' or (age<40 and age>20 and email is not null)
     */
    @Test
    void selectByWrapper6() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("user_name", "王")
                    .or(wq -> wq.lt("age", 40)
                                .gt("age", 20)
                                .isNotNull("email"));
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
        // lambda写法
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.likeRight(User::getUserName, "王")
                              .or(wq -> wq.lt(User::getAge, 40)
                                          .gt(User::getAge, 20)
                                          .isNotNull(User::getEmail));
        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * （年龄小于40或邮箱不为空）并且名字为王姓
     * (age<40 or email is not null) and name like '王%'
     */
    @Test
    void selectByWrapper7() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.nested(wq -> wq.lt("age", 40)
                                    .or()
                                    .isNotNull("email"))
                    .likeRight("user_name", "王");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
        // lambda写法
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.nested(wq -> wq.lt(User::getAge, 40)
                                              .or()
                                              .isNotNull(User::getEmail))
                              .likeRight(User::getUserName, "王");
        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 年龄为30、31、34、35
     * age in (30、31、34、35)
     */
    @Test
    void selectByWrapper8() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 35));
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
        // lambda写法
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.in(User::getAge, Arrays.asList(30, 31, 34, 35));
        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        users.forEach(System.out::println);

    }

    /**
     * 只返回满足条件的其中一条语句即可
     * limit 1
     */
    @Test
    void selectByWrapper9() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 35))
                    .last("limit 1");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
        // lambda写法
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.in(User::getAge, Arrays.asList(30, 31, 34, 35))
                              .last("limit 1");
        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 名字中包含雨并且年龄小于40(需求1加强版)通过选择来实现
     * 第一种情况：select id,name
     * from user
     * where name like '%雨%' and age<40
     */
    @Test
    void selectByWrapper10() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "user_name")
                    .like("user_name", "雨")
                    .lt("age", 40);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
        // lambda写法
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.select(User::getId, User::getUserName)
                              .like(User::getUserName, "雨")
                              .lt(User::getAge, 40);
        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 名字中包含雨并且年龄小于40(需求1加强版)通过排除来实现
     * 第二种情况：select id,name,age,email
     * from user
     * where name like '%雨%' and age<40
     */
    @Test
    void selectByWrapper11() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(User.class, info -> !info.getColumn()
                                                     .equals("create_time") && !info.getColumn()
                                                                                    .equals("manager_id"))
                    .like("user_name", "雨")
                    .lt("age", 40);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    void testCondition() {
        String name = "雨", email = "";
        selectByWrapper12(name, email);
    }

    /**
     * 后台管理的多项查询
     */
    void selectByWrapper12(String name, String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), "user_name", name)
                    .like(StringUtils.isNotBlank(email), "email", email);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
        // lambda写法
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.like(StringUtils.isNotBlank(name), User::getUserName, name)
                              .like(StringUtils.isNotBlank(email), User::getEmail, email);
        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        users.forEach(System.out::println);
    }


    /**
     * 按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄。
     * 并且只取年龄总和小于500的组。
     * select avg(age) avg_age,min(age) min_age,max(age) max_age
     * from user
     * group by manager_id
     * having sum(age) <500
     */
    @Test
    void selectByWrapper13() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("avg(age) avg_age", "min(age) min_age", "max(age) max_age")
                    .groupBy("manager_id")
                    .having("sum(age) <{0}", 500);
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    /**
     * 新的lambda查询
     */
    @Test
    void selectLambda() {
        List<User> userList = new LambdaQueryChainWrapper<User>(userMapper).like(User::getUserName, "雨")
                                                                           .ge(User::getAge, 20)
                                                                           .list();
        userList.forEach(System.out::println);
    }

    @Test
    void update() {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("user_name", "王天风");
        User user = new User();
        user.setAge(26);
        int update = userMapper.update(user, userUpdateWrapper);
        System.out.println(update);
        // lambda写法
        LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userLambdaUpdateWrapper.eq(User::getUserName, "王天风");
        user.setEmail("xxxx@qq.com");
        int update1 = userMapper.update(user, userLambdaUpdateWrapper);
        System.out.println(update1);
    }

    @Test
    void delete() {
        int i = userMapper.deleteById(1270258167518883841L);
        System.out.println(i);
    }
}
