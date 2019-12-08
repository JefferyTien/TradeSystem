package com.reddoor.framework.common.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import com.reddoor.framework.domain.Dict;
import com.reddoor.framework.service.DictService;
import com.reddoor.framework.service.boot.SpringContextUtil;
import com.reddoor.framework.service.impl.DictServiceImpl;

public class DictSelectTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	
	private String dictName;
	private String id;
	private String name;
	private String value;
	private String cssClass;
	private String styleClass;
	private String multiple;
	private String onChange;
	private boolean defaultValue;
	
	// Autowired can't be used here, cause not in spring container 
//	@Autowired
//	DictService dictService;
	
	@Override
	public int doEndTag() throws JspException{
		List<Dict> dictList;
		try {
			DictService dictService = (DictService)SpringContextUtil.getBean("DictServiceImpl", DictServiceImpl.class);
			dictList = dictService.findAll();
		}
		catch (Exception e1) {
			throw new JspException(e1);
		}
		JspWriter out = pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		sb.append("<select class=\"easyui-combobox\" name='" + this.name + "' id='" + this.getId()+"'");
		if(!StringUtils.isEmpty(this.getCssClass())){
			sb.append("class=\""+this.getCssClass()+"\" ");
		}
		if(!StringUtils.isEmpty(this.getStyleClass())){
            sb.append("style=\"" + this.getStyleClass() + "\" ");
        }
        if(!StringUtils.isEmpty(this.getMultiple())){
            sb.append("multiple=\"" + this.getMultiple() + "\" ");
        }
        if(!StringUtils.isEmpty(this.getOnChange())){
            sb.append("onchange=\"" + this.getOnChange() + "\" ");
        }
        sb.append(">");
        if(this.isDefaultValue()){
        	sb.append("<option value=''>--请选择--</option>");
        }
		for(Dict dc:dictList){
			if(dc.getValue().equals(this.getValue())){
				sb.append("<option value='"+dc.getValue()+"' seleted='selected'>");
			}
			else{
				sb.append("<option value='"+dc.getValue()+"'>");
			}
			sb.append(dc.getLabel()+"</option>");
		}
        sb.append("</select>");
        
        try {
			out.write(sb.toString());
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getStyleClass() {
		return styleClass;
	}
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	public String getMultiple() {
		return multiple;
	}
	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}
	public String getOnChange() {
		return onChange;
	}
	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}
	public boolean isDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(boolean defaultValue) {
		this.defaultValue = defaultValue;
	}
}
