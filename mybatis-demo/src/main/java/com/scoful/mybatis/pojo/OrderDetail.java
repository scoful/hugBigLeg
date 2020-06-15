package com.scoful.mybatis.pojo;

import lombok.Data;

/**
 * @author scoful
 * @date 2020/5/23 17:35
 */
@Data
public class OrderDetail {
    private Integer id;
    private Integer orderId;
    private Double totalPrice;
    private Integer status;
}
