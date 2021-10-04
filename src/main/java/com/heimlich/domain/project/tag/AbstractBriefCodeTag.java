package com.heimlich.domain.project.tag;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.heimlich.domain.common.codes.briefcode.BriefCodeComponent;
import com.heimlich.domain.common.codes.briefcode.BriefCodeDefine;
import com.heimlich.domain.common.codes.briefcode.BriefCodesCache;
import com.heimlich.domain.common.codes.filter.CommonFilter;

public abstract class AbstractBriefCodeTag extends BaseTag {

	final String SPLIT_CHAR = ",";

	// ================================================
	// == [Enumeration constants] Block Start
	// == [Enumeration constants] Block End
	// ================================================
	// == [static variables] Block Start
	// == [static variables] Block Stop
	// ================================================
	// == [instance variables] Block Start
	protected String key;
	protected String base1;
	protected String base2;
	protected String base3;
	protected String base4;
	protected String category;

	protected int size;
	protected String name;

	// filter需要
	protected String base = "";
	protected String includedKeys = "";
	protected String excludedKeys = "";

	//
	protected String showLabel = "Y";
	protected String showKey = "N";

	// == [instance variables] Block Stop
	// ================================================
	// == [static Constructor] Block Start
	// == [static Constructor] Block Stop
	// ================================================
	// == [Constructors] Block Stop
	// ================================================
	// == [Static Method] Block Start
	// == [Static Method] Block Stop
	// ================================================
	// == [Accessor] Block Start
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShowLabel() {
		return showLabel;
	}

	public void setShowLabel(String showLabel) {
		this.showLabel = showLabel;
	}

	public String getShowKey() {
		return showKey;
	}

	public void setShowKey(String showKey) {
		this.showKey = showKey;
	}

	public String showText(BriefCodeDefine briefCodeDefine) {
		if (briefCodeDefine == null) {
			return "";
		}
		final List<String> texts = new ArrayList<String>();
		if (StringUtils.equals(this.getShowKey(), "Y")) {
			texts.add(briefCodeDefine.toLastKey());
		}
		if (StringUtils.equals(this.getShowLabel(), "Y")) {
			texts.add(briefCodeDefine.toText());
		}
		return StringUtils.join(texts, "_");
	}

	// == [Accessor] Block Stop
	// ================================================
	// == [Overrided JDK Method] Block Start (Ex. toString / equals+hashCode)
	// == [Overrided JDK Method] Block Stop
	// ================================================
	// == [Method] Block Start
	// ####################################################################
	// ## [Method] sub-block :
	// ###################################################################
	protected String[] toKey() {

		if (this.base4 != null) {
			return new String[] { this.base4, this.base3, this.base2, this.base1, this.key };
		} else if (this.base3 != null) {
			return new String[] { this.base3, this.base2, this.base1, this.key };
		} else if (this.base2 != null) {
			return new String[] { this.base2, this.base1, this.key };
		} else if (this.base1 != null) {
			return new String[] { this.base1, this.key };
		} else if (this.key != null) {
			return new String[] { this.key };
		}
		throw new RuntimeException("設定錯誤");
	}

	public BriefCodeComponent getBriefCodeComponent() {
		return BriefCodesCache.Instance.getBridfCodeComponent();
	}

	public String getIncludedKeys() {
		return includedKeys;
	}

	public void setIncludedKeys(String includedKeys) {
		this.includedKeys = includedKeys;
	}

	public String getExcludedKeys() {
		return excludedKeys;
	}

	public void setExcludedKeys(String excludedKeys) {
		this.excludedKeys = excludedKeys;
	}

	protected List<? extends BriefCodeDefine> getCodes() {
		return BriefCodesCache.Instance.getCodeTable(new CommonFilter(category, base, includedKeys, excludedKeys));
	}

	final String getGenerateId(BriefCodeDefine briefCodeDefine) {
		final String id = this.getName() + "_" + briefCodeDefine.toCode();
		return id.replace(".", "_");
	}
	// ####################################################################
	// ## [Method] sub-block :
	// ###################################################################

	// == [Method] Block Stop
	// ================================================
	// == [Inner Class] Block Start
	// == [Inner Class] Block Stop
	// ================================================
}
