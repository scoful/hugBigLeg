package com.scoful.mybatis.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author scoful
 * @date 2020/5/23 19:07
 */
@Data
public class OrderUserDetail {
    private String id;
    private String name;
    private Integer age;
    private Integer sex;
    private Date birthday;
    private String orderNumber;
    private Double totalPrice;
    private Integer status;
    private String itemName;
    private Float itemPrice;
    private String itemDetail;
}
