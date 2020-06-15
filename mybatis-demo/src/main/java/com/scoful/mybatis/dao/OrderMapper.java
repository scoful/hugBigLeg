package com.scoful.mybatis.dao;

import com.scoful.mybatis.pojo.Order;
import com.scoful.mybatis.pojo.OrderUserDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author scoful
 * @date 2020/5/23 17:20
 */
public interface OrderMapper {
    /**
     * 根据订单号查询订单用户的信息
     *
     * @param number
     * @return
     */
    public Order queryOrderWithUserByOrderNumber(@Param("number") String number);

    /**
     * 根据订单号查询订单用户的信息及订单详情
     *
     * @param number
     * @return
     */
    Order queryOrderWithUserAndDetailByOrderNumber(@Param("number") String number);

    /**
     * 根据订单号查询订单用户的信息及订单详情及订单详情对应的商品信息
     *
     * @param number
     * @return
     */
    List<OrderUserDetail> queryOrderWithUserAndDetailItemByOrderNumber(@Param("number") String number);
}
