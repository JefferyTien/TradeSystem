package com.reddoor.framework.common.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PermissionTag extends SimpleTagSupport {
	
	private static final long serialVersionUID = 1L;
	
	private String permission;
	
	@Override 
	public void doTag() {
		PageContext pageContext = (PageContext) this.getJspContext();  
		HttpSession session = pageContext.getSession();
		List<String> permissions = (List<String>)session.getAttribute("permissions");
		
		JspWriter out = getJspContext().getOut();
		try {
			if(permissions.contains(permission)){
//				out.write("");
				getJspBody().invoke(null);   // 如果有标签有body内容,则处理
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (JspException e) {
			e.printStackTrace();
		}
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}
