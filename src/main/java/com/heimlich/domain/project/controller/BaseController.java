package com.heimlich.domain.project.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.heimlich.domain.project.component.TextSupport;
import com.heimlich.domain.project.define.Contract;

public abstract class BaseController implements Serializable, TextSupport {	
	private static final long serialVersionUID = 1L;	
	public static final String SUCCESS = "success";
	
	private String successResultValue;

	final String menuId;

	public BaseController() {
		super();
		this.menuId = this.getClass().getSimpleName().replace("Action", "").toUpperCase();
	}
	
	public final String getMenuId() {
		return this.menuId;
	}
	
	public String getText(String aTextName) {
		return Contract.getTextFormCodes(aTextName);
	}

	public String getText(Contract... contracts) {
		StringBuffer buffer = new StringBuffer();
		for (Contract contract : contracts) {
			buffer.append(this.getText(contract.getCode()));
		}
		return buffer.toString();
	}

	public String getText(Contract enumObj) {
		return this.getText(enumObj.getCode());
	}

	/**
	 * 請輸入
	 * 
	 * @param contract
	 * @return
	 */
	public String getPlsEnterText(Contract contract) {
		return this.getText(Contract.LABEL_WARM_ENTER) + this.getText(contract);
	}
	
	public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        String requestedUrl = request.getPathInfo();
        if (successResultValue == null) successResultValue = requestedUrl;
        return SUCCESS;
    }

    /**
     * @return Returns the successResultValue.
     */
    public String getSuccessResultValue() {
        return successResultValue;
    }

    /**
     * @param successResultValue The successResultValue to set.
     */
    public void setSuccessResultValue(String successResultValue) {
        this.successResultValue = successResultValue;
    }

}
