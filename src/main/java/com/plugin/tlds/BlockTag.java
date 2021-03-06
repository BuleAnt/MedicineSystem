package com.plugin.tlds;


import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * 自定义标签，用于在Jsp模板中占位
 *
 * @author 逆风之羽
 *
 */
public class BlockTag extends BodyTagSupport {
    /**
     * 占位模块名称
     */
    private String name;

    private static final long serialVersionUID = 1425068108614007667L;


    @Override
    public int doStartTag() throws JspException {
        return super.doStartTag();
    }

    @Override
    public int doEndTag() throws JspException {
        ServletRequest request = pageContext.getRequest();
        //block标签中的默认值
        String defaultContent = (getBodyContent() == null)?null:getBodyContent().getString();
        String bodyContent = (String) request.getAttribute(OverrideTag.PREFIX+ name);
        //如果页面没有重写该模块则显示默认内容
        bodyContent = StringUtils.isEmpty(bodyContent)?defaultContent:bodyContent;
        if(bodyContent != null){
            try {
                pageContext.getOut().write(bodyContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.doEndTag();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
