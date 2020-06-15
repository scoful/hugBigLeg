package com.scoful.mybatis.dao;

import com.scoful.mybatis.pojo.Order;
import com.scoful.mybatis.pojo.OrderUserDetail;
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
 * @date 2020/5/23 17:30
 */
public class OrderMapperTest {

    private OrderMapper orderMapper;

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
        orderMapper = sqlSession.getMapper(OrderMapper.class);
    }

    @Test
    public void queryOrderWithUserByOrderNumber() {
        Order order = orderMapper.queryOrderWithUserByOrderNumber("201807010001");
        System.out.println(order);
    }

    @Test
    public void queryOrderWithUserAndDetailByOrderNumber() {
        Order orders = orderMapper.queryOrderWithUserAndDetailByOrderNumber("201807010001");
        System.out.println(orders);
    }

    @Test
    public void queryOrderWithUserAndDetailItemByOrderNumber() {
        List<OrderUserDetail> orderUserDetails = orderMapper.queryOrderWithUserAndDetailItemByOrderNumber("201807010001");
        for (OrderUserDetail orderUserDetail : orderUserDetails) {
            System.out.println(orderUserDetail);
        }
    }
}