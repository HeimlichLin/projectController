package com.heimlich.domain.project.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.heimlich.domain.common.codes.briefcode.BriefCodeDefine;

public class CheckboxTag extends AbstractBriefCodeTag {

	final static String TEMPLATE = "<input type='checkbox' name='%s' %s value='%s'> %s &nbsp;&nbsp;";

	@Override
	public void doTag() throws JspException, IOException {

		final List<? extends BriefCodeDefine> briefCodes = this.getCodes();
		final JspWriter jspContext = this.getJspContext().getOut();
		for (final BriefCodeDefine briefCodeDefine : briefCodes) {
			final StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format(CheckboxTag.TEMPLATE,//
					this.name,
					this.toDynamicAttribute(),//
					briefCodeDefine.toCode(), //
					this.showText(briefCodeDefine)));//
			jspContext.write(stringBuffer.toString());
		}

	}

}
