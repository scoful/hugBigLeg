package com.scoful.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author scoful
 * @date 2020/6/6 17:39
 */

@Data
@TableName(value = "sys_dept")
public class SysDept implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 部门编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 父级部门编号
     */
    @TableField(value = "pid")
    private Integer pid;
    /**
     * 父级部门名称
     */
    @TableField(value = "title")
    private String title;
    /**
     * 是否展开(0-展开,1-不展开)
     */
    @TableField(value = "open")
    private Integer open;
    /**
     * 部门地址
     */
    @TableField(value = "address")
    private String address;
    /**
     * 创建时间
     */
    @TableField(value = "createtime")
    private Date createtime;
    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
}