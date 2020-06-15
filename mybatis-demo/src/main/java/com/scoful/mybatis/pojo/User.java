package com.scoful.mybatis.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author scoful
 * @date 2020/5/22 18:51
 */
@Data
public class User {
    private String id;
    private String userName;
    private String password;
    private String name;
    private Integer age;
    private Integer sex;
    private Date birthday;
    private String created;
    private String updated;


}
