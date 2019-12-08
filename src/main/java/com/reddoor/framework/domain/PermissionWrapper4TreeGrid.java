package com.reddoor.framework.domain;

import java.util.ArrayList;
import java.util.List;

public class PermissionWrapper4TreeGrid {
	private Integer total;
	private List<Permission> rows;
	private List<Permission> footer = new ArrayList<Permission>();
	
	public PermissionWrapper4TreeGrid(List<Permission> menus){
		for(Permission each:menus){
			each.set_parentId(each.getPid());
			each.setIconCls(each.getIcon());
		}
		this.rows = menus;
		this.total = menus.size();
	}

	public Integer getTotal() {
		return total;
	}

	public List<Permission> getRows() {
		return rows;
	}

	public List<Permission> getFooter() {
		return footer;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public void setRows(List<Permission> rows) {
		this.rows = rows;
	}

	public void setFooter(List<Permission> footer) {
		this.footer = footer;
	}
}
