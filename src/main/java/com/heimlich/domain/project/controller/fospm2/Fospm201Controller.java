package com.heimlich.domain.project.controller.fospm2;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.heimlich.domain.common.codes.define.BriefCodes;
import com.heimlich.domain.project.controller.fospm1.Fospm1Controller;
import com.heimlich.domain.project.dto.fospm2.Fospm201DTO;
import com.heimlich.domain.project.service.fospm2.Fospm201Service;
import com.heimlich.domain.project.service.fospm2.impl.Fospm201ServiceImpl;
import com.heimlich.domain.project.validate.SampleMessage;
import com.heimlich.domain.project.validate.SampleMessageBuider;
import com.heimlich.domain.project.validate.ValidateActionSubject;
import com.heimlich.domain.project.validate.ValidateObserver;
import com.opensymphony.xwork2.Action;


/**
 * 代碼檔重置作業
 * 
  */	
public class Fospm201Controller extends Fospm2Controller {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static Map<String, String> categorys = new HashMap<String, String>();
	private transient Fospm201Service service = new Fospm201ServiceImpl();
	private Fospm201DTO dto = new Fospm201DTO();

	static {
		final EnumSet<BriefCodes> briefCodesSet = EnumSet.allOf(BriefCodes.class);
		for (final BriefCodes briefCodes : briefCodesSet) {
			Fospm201Controller.categorys
					.put(briefCodes.name(), briefCodes.name() + "(" + briefCodes.getDescription() + ")");
		}
//		final EnumSet<EnumCodes> EnumCodesSet = EnumSet.allOf(EnumCodes.class);
//		for (final EnumCodes code : EnumCodesSet) {
//			Fospm201Controller.categorys.put(code.name(), code.name() + "(" + code.getDescription() + ")");
//		}
	}

	public static ValidateObserver<Fospm201DTO> VALIDATE = new ValidateObserver<Fospm201DTO>() {

		public SampleMessage isValidate(final Fospm201DTO dto) {
			if (StringUtils.isBlank(dto.getCategory())) {
				return SampleMessageBuider.newErrorMessage("代碼檔");
			} else if (!this.isBriefCodes(dto.getCategory())) {
				return SampleMessageBuider.newErrorMessage("代碼檔");
			}
			return SampleMessageBuider.newNoneMessage();

		}

		private boolean isBriefCodes(final String category) {
			return categorys.containsKey(category);
		}

	};

	/**
	 * 清除暫存
	 * 
	 * @return
	 */
	public String doClearCache() {
		if (ValidateActionSubject.validate(Fospm201Controller.VALIDATE, this.dto, this)) {
			this.dto = this.service.clearCache(this.dto);
//			this.addActionMessage(SampleMessageBuider.newOkMessage("處理成功"));
			return Action.SUCCESS;
		} else {
			return Action.INPUT;
		}

	}

	public String doQuery() {
		if (ValidateActionSubject.validate(Fospm201Controller.VALIDATE, this.dto, this)) {
			this.dto = this.service.query(this.dto);
//			this.addActionMessage(SampleMessageBuider.newOkMessage("處理成功"));
			return Action.SUCCESS;
		} else {
			return Action.INPUT;
		}
	}

	public Fospm201DTO getDto() {
		return this.dto;
	}

	public void setDto(final Fospm201DTO dto) {
		this.dto = dto;
	}

	public static Map<String, String> getCategorys() {
		return categorys;
	}

}
