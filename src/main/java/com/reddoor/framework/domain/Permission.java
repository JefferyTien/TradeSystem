package com.reddoor.framework.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

public class Permission {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父节点名称
     */
    @Column(name = "PID")
    private Integer pid;

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 类型:菜单or功能
     */
    @Column(name = "TYPE")
    private String type;

    /**
     * 排序
     */
    @Column(name = "SORT")
    private Integer sort;

    @Column(name = "URL")
    private String url;

    /**
     * 菜单编码
     */
    @Column(name = "PERM_CODE")
    private String permCode;

    @Column(name = "ICON")
    private String icon;

    @Column(name = "STATE")
    private String state;

    @Column(name = "DESCRIPTION")
    private String description;
    
    
	// used for tree grid
    @Transient
	private Integer _parentId;
    @Transient
	private String iconCls;
    
    @Transient
	private Set<RolePermission> rolePermissions = new HashSet<RolePermission>(0);

    public Permission() {
	}

	/** minimal constructor */
	public Permission(String name) {
		this.name = name;
	}
	
	public Permission(Integer id) {
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
     * 获取父节点名称
     *
     * @return PID - 父节点名称
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父节点名称
     *
     * @param pid 父节点名称
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取名称
     *
     * @return NAME - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取类型:菜单or功能
     *
     * @return TYPE - 类型:菜单or功能
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型:菜单or功能
     *
     * @param type 类型:菜单or功能
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取排序
     *
     * @return SORT - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取菜单编码
     *
     * @return PERM_CODE - 菜单编码
     */
    public String getPermCode() {
        return permCode;
    }

    /**
     * 设置菜单编码
     *
     * @param permCode 菜单编码
     */
    public void setPermCode(String permCode) {
        this.permCode = permCode;
    }

    /**
     * @return ICON
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return STATE
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(String state) {
        this.state = state;
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

	public Integer get_parentId() {
		return _parentId;
	}

	public String getIconCls() {
		return iconCls;
	}

	public Set<RolePermission> getRolePermissions() {
		return rolePermissions;
	}

	public void set_parentId(Integer _parentId) {
		this._parentId = _parentId;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public void setRolePermissions(Set<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}
}