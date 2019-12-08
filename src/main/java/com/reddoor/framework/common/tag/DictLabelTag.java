package com.reddoor.framework.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.reddoor.framework.domain.Dict;

public class DictLabelTag extends TagSupport{
	private String dictName="";
	private String value="";
	
	@Override
	public int doEndTag() throws JspException{
		Dict dic = new Dict();
		JspWriter out = pageContext.getOut();
		
		try {
			out.write("DictLabelTag Test");
		}
		catch (IOException e) {
			throw new JspException(e);
		}
		return TagSupport.EVAL_PAGE;
	}
	
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
