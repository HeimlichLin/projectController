package com.heimlich.domain.project.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.heimlich.domain.common.codes.briefcode.BriefCodeDefine;

public class MultipleSelectTag extends AbstractBriefCodeTag {

	final static String TEMPLATE_SELECT_BEGIN = "<select %s size=%d multiple>";
	final static String TEMPLATE_SELECT_END = "</select>";
	final static String TEMPLATE_OPTION = "<option value='%s'>%s</option>";

	@Override
	public void doTag() throws JspException, IOException {
		final List<? extends BriefCodeDefine> briefCodes = this.getCodes();
		final JspWriter jspContext = this.getJspContext().getOut();
		jspContext.write(String.format(MultipleSelectTag.TEMPLATE_SELECT_BEGIN,
				this.toDynamicAttribute(), this.getSize()));

		for (final BriefCodeDefine briefCodeDefine : briefCodes) {
			final StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format(
					MultipleSelectTag.TEMPLATE_OPTION,//
					briefCodeDefine.toCode(), //

					this.showText(briefCodeDefine)));//
			jspContext.write(stringBuffer.toString());
		}
		jspContext.write(MultipleSelectTag.TEMPLATE_SELECT_END);
	}

}
