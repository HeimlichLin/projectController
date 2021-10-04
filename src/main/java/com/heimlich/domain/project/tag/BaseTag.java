package com.heimlich.domain.project.tag;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class BaseTag extends SimpleTagSupport implements DynamicAttributes {

	// ================================================
	// == [Enumeration constants] Block Start
	// == [Enumeration constants] Block End
	// ================================================
	// == [static variables] Block Start
	final static String DYNAMIC_FORMAT = " %s='%s' ";
	// == [static variables] Block Stop
	// ================================================
	// == [instance variables] Block Start
	private final Map<String, String> dynamicAttribute = new HashMap<String, String>();

	// == [instance variables] Block Stop
	// ================================================
	// == [static Constructor] Block Start
	// == [static Constructor] Block Stop
	// ================================================
	// == [Constructors] Block Stop
	// == [Constructors] Block Stop
	// ================================================
	// == [Static Method] Block Start
	// == [Static Method] Block Stop
	// ================================================
	// == [Accessor] Block Start
	// == [Accessor] Block Stop
	// ================================================
	// == [Overrided JDK Method] Block Start (Ex. toString / equals+hashCode)
	// == [Overrided JDK Method] Block Stop
	// ================================================
	// == [Method] Block Start
	// ####################################################################
	// ## [Method] sub-block :
	// ###################################################################
	public void setDynamicAttribute(String arg0, String key, Object value)
			throws JspException {
		this.dynamicAttribute.put(key, value.toString());
	}

	public Map<String, String> getDynamicAttribute() {
		return this.dynamicAttribute;
	}

	public String toDynamicAttribute() {
		final StringBuffer dynamicAttribute = new StringBuffer();
		for (final Map.Entry<String, String> entry : this.getDynamicAttribute()
				.entrySet()) {
			dynamicAttribute.append(String.format(BaseTag.DYNAMIC_FORMAT,
					entry.getKey(), entry.getValue()));
		}
		return dynamicAttribute.toString();

	}
	// == [Method] Block Stop
	// ================================================
	// == [Inner Class] Block Start
	// == [Inner Class] Block Stop
	// ================================================
}