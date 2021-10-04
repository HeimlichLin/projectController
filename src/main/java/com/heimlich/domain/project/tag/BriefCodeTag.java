package com.heimlich.domain.project.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.heimlich.domain.common.codes.briefcode.BriefCodeDefine;

public class BriefCodeTag extends AbstractBriefCodeTag {

	@Override
	public void doTag() throws JspException, IOException {
		final JspWriter jspContext = this.getJspContext().getOut();
		final BriefCodeDefine briefCodeDefine	=this.getBriefCodeComponent().getCode(category, this.toKey());
		jspContext.write(this.showText(briefCodeDefine));
	}

}
