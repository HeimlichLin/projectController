package com.heimlich.domain.project.define;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.heimlich.domain.common.CommonlStyle;
import com.heimlich.domain.common.grid.ALIGN;
import com.heimlich.domain.common.utils.TransUtils;
import com.heimlich.domain.project.component.TextSupport;
import com.heimlich.domain.project.dto.fospm1.impl.Fospm101QueryDTO;
import com.heimlich.domain.project.dto.fospm1.impl.Fospm101ResultDTO;

public enum Fospm101StyleDefine implements CommonlStyle {	
	BONDNO(ALIGN.LEFT, 30, false, Contract.LABEL_FTZNAME), //
	REPORTCUSTID(ALIGN.CENTER, 20, false, Contract.LABEL_CUSTCODE)
	;

	private Fospm101StyleDefine(final ALIGN align,//
			final int size, //
			final boolean sortable, //
			final Contract contract//
			) {//
		this.name = this.name();
		this.align = align.name();
		this.size = size;
		this.sortable = sortable;
		this.label = contract.getCode();
		this.key = name();
	}
	final String name;
	final String key;
	final String align;
	final int size;
	final boolean sortable;
	final String label;
	final static String STYLE_FORMATE = "{name:'%s',index:'%s', align:'%s',width:'%s',sortable:%s}";

	public String getStyle() {
		return String.format(Fospm101StyleDefine.STYLE_FORMATE,//
				this.name, this.name, this.align, String.valueOf(this.size), this.sortable ? "true" : "false");
	}

	public String getLabelName(final TextSupport textSupport) {
		return textSupport.getText(this.label);
	}

	public static List<CommonlStyle> getCommonlStyles(final Fospm101QueryDTO dto) {
		List<CommonlStyle> styles = new ArrayList<CommonlStyle>();
		styles.add(REPORTCUSTID);
		styles.add(BONDNO);
		return styles;
	}

	public static List<Map<String, String>> getGridList(final List<Fospm101ResultDTO> resultDTOs) {
		return TransUtils.transBean2MapList(resultDTOs);
	}

	public String getKey() {
		return name;
	}
	
}
