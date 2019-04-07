package com.baomidou.ant.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("userName")
    private String userName;

    private String password;

    /**
     * 用户真实姓名或昵称
     */
    @TableField("trueName")
    private String trueName;

    @TableField("roleId")
    private String roleId;

    @TableField("roleName")
    private String roleName;

    private String auth;

    @TableField("authName")
    private String authName;

    @TableField("createTime")
    private LocalDateTime createTime;

    private Integer status;

    /**
     * 排序，越大越靠前
     */
    private Long sequence;

    /**
     * 用户类型：1普通用户，100：管理员
     */
    private Integer type;

    private String email;

    /**
     * 用户头像
     */
    @TableField("avatarUrl")
    private String avatarUrl;

    /**
     * 0：账号登陆，1：github登陆，2：码云
     */
    @TableField("loginType")
    private Integer loginType;

    /**
     * 第三方唯一ID
     */
    @TableField("thirdlyId")
    private String thirdlyId;

    /**
     * 密码MD5盐
     */
    @TableField("passwordSalt")
    private String passwordSalt;


}
