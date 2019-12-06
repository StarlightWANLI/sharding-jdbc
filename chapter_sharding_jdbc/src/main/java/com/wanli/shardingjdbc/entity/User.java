package com.wanli.shardingjdbc.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 *
 * @since 2018-11-23
 */
@Data
public class User implements Serializable{

    private static final long serialVersionUID = -2359336982098859444L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 真实名称
     */
    private String realName;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 修改日期
     */
    private Date updateTime;
    /**
     * 删除标记 1:删除;0:未删除
     */
    private String delFlag;

}
