package com.scoful.mybatis.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author scoful
 * @date 2020/5/23 17:17
 */
@Data
public class Order {
    private Integer id;
    private Long userId;
    private String orderNumber;
    private Date created;
    private Date updated;
    private User user;
    private List<OrderDetail> orderDetails;
}
