package com.reddoor.framework.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.shiro.SecurityUtils;

/**
 * 
 */
public class ShiroPermissionExtTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	
	private String permission;
	
	@Override 
	public int doStartTag() throws JspException{
//		HttpSession session = pageContext.getSession();  // 获取session
		String[] arrPermissions = permission.split(",");
		if(SecurityUtils.getSubject().isAuthenticated()){
			SecurityUtils.getSubject().isPermitted(arrPermissions);
			for(String permission:arrPermissions){
				if(SecurityUtils.getSubject().isPermitted(permission)){
					return TagSupport.EVAL_BODY_INCLUDE;
				}
			}
			return TagSupport.SKIP_BODY;
		}
		else{
			return TagSupport.SKIP_BODY;
		}
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}
