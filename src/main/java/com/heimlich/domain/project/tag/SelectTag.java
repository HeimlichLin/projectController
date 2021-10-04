package com.heimlich.domain.project.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.heimlich.domain.common.codes.briefcode.BriefCodeDefine;

public class SelectTag extends AbstractBriefCodeTag {
	// ================================================
	// == [Enumeration constants] Block Start
	// == [Enumeration constants] Block End
	// ================================================
	// == [static variables] Block Start
	final static String TEMPLATE_SELECT_BEGIN = "<select name='%s' %s>";
	final static String TEMPLATE_SELECT_END = "</select>";
	final static String TEMPLATE_DEFAULT_OPTION = "<option value=''>請選擇</option>";
	final static String TEMPLATE_OPTION = "<option value='%s'>%s</option>";

	// == [static variables] Block Stop
	// ================================================
	// == [instance variables] Block Start

	// == [instance variables] Block Stop
	// ================================================
	// == [static Constructor] Block Start
	// == [static Constructor] Block Stop
	// ================================================
	// == [Constructors] Block Start

	// == [Constructors] Block Stop
	// ================================================
	// == [Static Method] Block Start
	// == [Static Method] Block Stop
	// ================================================
	// == [Accessor] Block Start
	// == [Accessor] Block Stop
	// ================================================
	// == [Overrided JDK Method] Block Start (Ex. toString / equals+hashCode)
	@Override
	public void doTag() throws JspException, IOException {
		final List<? extends BriefCodeDefine> briefCodes = this.getCodes();
		final JspWriter jspContext = this.getJspContext().getOut();
		jspContext.write(String.format(SelectTag.TEMPLATE_SELECT_BEGIN,
				this.name,
				this.toDynamicAttribute()));
		jspContext.write(SelectTag.TEMPLATE_DEFAULT_OPTION);
		for (final BriefCodeDefine briefCodeDefine : briefCodes) {
			final StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(String.format(SelectTag.TEMPLATE_OPTION,//
					briefCodeDefine.toCode(), //
					this.showText(briefCodeDefine)));//
			jspContext.write(stringBuffer.toString());
		}
		jspContext.write(SelectTag.TEMPLATE_SELECT_END);
	}

	// == [Overrided JDK Method] Block Stop
	// ================================================
	// == [Method] Block Start
	// ####################################################################
	// ## [Method] sub-block :
	// ###################################################################
	// == [Method] Block Stop
	// ================================================
	// == [Inner Class] Block Start
	// == [Inner Class] Block Stop
	// ================================================

}
