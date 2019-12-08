package com.reddoor.framework.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

public class Role {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ROLE_CODE")
    private String roleCode;

    @Column(name = "SORT")
    private Short sort;

    @Column(name = "DEL_FLAG")
    private String delFlag;

    @Column(name = "DESCRIPTION")
    private String description;
    
    @Transient
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);
    @Transient
	private Set<RolePermission> rolePermissions = new HashSet<RolePermission>(0);

    public Role() {
	}
	
	public Role(Integer id) {
		this.id=id;
	}
	
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
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return ROLE_CODE
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * @param roleCode
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * @return SORT
     */
    public Short getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Short sort) {
        this.sort = sort;
    }

    /**
     * @return DEL_FLAG
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * @return DESCRIPTION
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public Set<RolePermission> getRolePermissions() {
		return rolePermissions;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public void setRolePermissions(Set<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}
}