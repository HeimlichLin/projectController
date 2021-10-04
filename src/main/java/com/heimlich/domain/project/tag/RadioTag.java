package com.heimlich.domain.project.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.heimlich.domain.common.codes.briefcode.BriefCodeDefine;

public class RadioTag extends AbstractBriefCodeTag {
	final static String TEMPLATE = "<input type='radio' id='%s' name='%s' value='%s' %s %s > %s &nbsp;&nbsp;";

	@Override
	public void doTag() throws JspException, IOException {

		final List<? extends BriefCodeDefine> briefCodes = this.getCodes();
		final JspWriter jspContext = this.getJspContext().getOut();
		for (final BriefCodeDefine briefCodeDefine : briefCodes) {
			String keyString = "";
			if (briefCodeDefine.toCode().equals(key)) {
				keyString = "checked";
			}
			final StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format(RadioTag.TEMPLATE,//
					this.getGenerateId(briefCodeDefine),//
					this.name,//
					briefCodeDefine.toCode(), //
					this.toDynamicAttribute(),//
					keyString, this.showText(briefCodeDefine)));//
			jspContext.write(stringBuffer.toString());
		}

	}

}
