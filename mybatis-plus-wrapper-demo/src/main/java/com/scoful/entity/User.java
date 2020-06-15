package com.scoful.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author scoful
 * @date 2020/6/8 16:37
 */

/**
 * 人员表
 */
@ApiModel(value = "com-scoful-entity-User")
@Data
@TableName(value = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "id")
    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 姓名
     */
    @TableField(value = "user_name")
    @ApiModelProperty(value = "姓名")
    private String userName;
    /**
     * 年龄
     */
    @TableField(value = "age")
    @ApiModelProperty(value = "年龄")
    private Integer age;
    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 直属上级id
     */
    @TableField(value = "manager_id")
    @ApiModelProperty(value = "直属上级id")
    private Long managerId;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    /**
     * 版本
     */
    @TableField(value = "version")
    @ApiModelProperty(value = "版本")
    private Integer version;
    /**
     * 逻辑删除标识(0.未删除,1.已删除)
     */
    @TableField(value = "deleted")
    @ApiModelProperty(value = "逻辑删除标识(0.未删除,1.已删除)")
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private String remark;
}