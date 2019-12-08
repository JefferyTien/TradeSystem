package com.reddoor.framework.domain;

import javax.persistence.*;

@Table(name = "role_permission")
public class RolePermission {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ROLE_ID")
    private Integer roleId;

    @Column(name = "PERMISSION_ID")
    private Integer permissionId;
    
    @Transient
	private Permission permission;
    @Transient
	private Role role;

    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return ROLE_ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return PERMISSION_ID
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * @param permissionId
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

	public Permission getPermission() {
		return permission;
	}

	public Role getRole() {
		return role;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}