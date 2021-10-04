package com.heimlich.domain.project.controller.fospm2;

import org.apache.commons.lang.StringUtils;

import com.heimlich.domain.project.dto.fospm2.Fospm202DTO;
import com.heimlich.domain.project.service.fospm2.Fospm202Service;
import com.heimlich.domain.project.service.fospm2.impl.Fospm202ServiceImpl;
import com.heimlich.domain.project.validate.SampleMessage;
import com.heimlich.domain.project.validate.SampleMessageBuider;
import com.heimlich.domain.project.validate.ValidateActionSubject;
import com.heimlich.domain.project.validate.ValidateObserver;
import com.opensymphony.xwork2.Action;

/**
 * 資料 匯入檔案作業
 * 
*/
public class Fospm202Controller extends Fospm2Controller {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Fospm202DTO dto = new Fospm202DTO();
	private Fospm202Service service = new Fospm202ServiceImpl();
	
	public static ValidateObserver<Fospm202DTO> VALIDATE = new ValidateObserver<Fospm202DTO>() {

		public SampleMessage isValidate(final Fospm202DTO dto) {
			if (dto.getFile1() == null) {
				return SampleMessageBuider.newErrorMessage("請選擇檔案");
			}
			if (StringUtils.isBlank(dto.getYear())) {
				return SampleMessageBuider.newErrorMessage("請輸入年分");
			}
			return SampleMessageBuider.newNoneMessage();

		}

	};
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	public Fospm202DTO getDto() {
		return dto;
	}

	public void setDto(Fospm202DTO dto) {
		this.dto = dto;
	}
	
	public String doUpload() {
		if (ValidateActionSubject.validate(Fospm202Controller.VALIDATE, this.dto, this)) {
			this.service.insertData(dto);
//			this.addActionMessage(SampleMessageBuider.newOkMessage("處理成功"));
			return Action.SUCCESS;
		} else {
			return Action.INPUT;
		}

	}

}
