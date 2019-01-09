package com.microservice.cloud.common.domain.permission;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO: 权限实体
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/12/1 15:15
 */
public class Permission implements Serializable {

    private static final long serialVersionUID = 2180289127097828592L;
    /**
     * 权限ID
     */
    private Long id;

    /**
     * 权限Code
     */
    private String permissionCode;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限状态 1:启用 0:禁用
     */
    private String permissionState;

    /**
     * 权限备注
     */
    private String permissionRemark;

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

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionState() {
        return permissionState;
    }

    public void setPermissionState(String permissionState) {
        this.permissionState = permissionState;
    }

    public String getPermissionRemark() {
        return permissionRemark;
    }

    public void setPermissionRemark(String permissionRemark) {
        this.permissionRemark = permissionRemark;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", permissionCode=").append(permissionCode);
        sb.append(", permissionName=").append(permissionName);
        sb.append(", permissionState=").append(permissionState);
        sb.append(", permissionRemark=").append(permissionRemark);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}