package com.microservice.cloud.common.domain.role;


import java.io.Serializable;
import java.util.Date;

/**
 * TODO: 角色映射实体类
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/11/15 9:23
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 883497913311673979L;

    /**
     * 角色ID
     */
    private Long id;


    /**
     * 角色名称
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色状态 1:可用 0:不可用
     */
    private String roleStates;

    /**
     * 角色备注
     */
    private String roleRemark;

    /**
     * 创建者id
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleStates() {
        return roleStates;
    }

    public void setRoleStates(String roleStates) {
        this.roleStates = roleStates;
    }

    public String getRoleRemark() {
        return roleRemark;
    }

    public void setRoleRemark(String roleRemark) {
        this.roleRemark = roleRemark;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
