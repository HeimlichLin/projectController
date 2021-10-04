package com.heimlich.domain.project.controller.fospm1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.heimlich.domain.common.Common2ModelBuider;
import com.heimlich.domain.common.grid.Common2Model;
import com.heimlich.domain.project.define.Fospm101StyleDefine;
import com.heimlich.domain.project.dto.fospm1.impl.Fospm101QueryDTO;
import com.heimlich.domain.project.dto.fospm1.impl.Fospm101ResultDTO;
import com.heimlich.domain.project.excel.CommonExcelReader;
import com.heimlich.domain.project.excel.fospm1.Fospm101Excel;
import com.heimlich.domain.project.excel.impl.ExcelExportTool;
import com.heimlich.domain.project.service.fospm1.Fospm101Service;
import com.heimlich.domain.project.service.fospm1.impl.Fospm101ServiceImpl;
import com.opensymphony.xwork2.Action;

public class Fospm101Controller extends Fospm1Controller {
	private static final long serialVersionUID = 1L;
	
//	private static final String LOCATION_REP = "PAGE_FOSPM501_LocationRep"; // 按全球區位統計
//	private static final String SOUTHLOCATION_REP = "PAGE_FOSPM501_SouthLocationRep"; // 按南向國家區位統計
//	private static final String CONTINENT_REP = "PAGE_FOSPM501_ContinentRep"; // 按洲別統計
//	private static final String COUNTRY_REP = "PAGE_FOSPM501_CountryRep"; // 按國家統計
//	private static final String NEWSOUTH_REP = "PAGE_FOSPM501_NewSouthRep"; // 按南向國家統計
	
	private Fospm101QueryDTO dto = new Fospm101QueryDTO();
	private List<Fospm101ResultDTO> resultDTOs = new ArrayList<Fospm101ResultDTO>();
	private transient Fospm101Service service = new Fospm101ServiceImpl();

	//private QueItems queItems = new QueItems();
	private List<Map<String, String>> importNatList;
	
//	@Override
//	public String execute() {
//		return Action.SUCCESS;
//	}

	/**
	 * 查詢
	 * 
	 * @return
	 */
//	public String fospm501Rep() {
//		try {
//			this.doCheckParmeters();
//		} catch (final Exception e) {
//			this.addActionError("系統異常");
//			return this.execute();
//		}
//		return controllReturnRepPage();
//	}

	/**
	 * 檢查畫面必填欄位
	 */
//	private void doCheckParmeters() {		
//		if (StringUtils.isBlank(this.dto.getCountryLocationMethod())) {
//			throw new ApBusinessException(this.getPlsEnterText(Contract.LABEL_COUNTRYLOCATION));
//		}
//	}
	
	/**
	 * 取得grid參數
	 * 
	 * @return
	 */
	public String qryParam() {
		this.gridModel = this.getMode();
		return Action.SUCCESS;
	}
	
	/**
	 * 查詢結果
	 * 
	 * @return
	 */
	public String doQueryList() {
		this.qryParam();
		this.resultDTOs = this.service.query(this.dto);
		this.gridModel = this.getMode();
		return Action.SUCCESS;
	}

	private Common2Model getMode() {
		final Common2ModelBuider common2ModelBuider = new Common2ModelBuider(this.dto);
		common2ModelBuider.setSupport(this);
		common2ModelBuider.setCommonlStyles(Fospm101StyleDefine.getCommonlStyles(this.dto));
		common2ModelBuider.setResult(this.resultDTOs);
		return common2ModelBuider.buider();
	}

//	public String controllReturnRepPage() {
//		String page = "";
//		if (CountryLocationCode.location.name().equals(dto.getCountryLocationMethod())) {
//			page = LOCATION_REP;
//		} else if (CountryLocationCode.southlocation.name().equals(dto.getCountryLocationMethod())) {
//			page = SOUTHLOCATION_REP;
//		} else if (CountryLocationCode.continent.name().equals(dto.getCountryLocationMethod())) {
//			page = CONTINENT_REP;
//		} else if (CountryLocationCode.country.name().equals(dto.getCountryLocationMethod())) {
//			page = COUNTRY_REP;
//		} else if (CountryLocationCode.newsouth.name().equals(dto.getCountryLocationMethod())) {
//			page = NEWSOUTH_REP;
//		}
//		return page;
//	}

//	public String qryImportProvider() {
//		importNatList = queItems.getCountryCode();
//		return Action.SUCCESS;
//	}

	/**
	 * 匯出excel檔案
	 * 
	 * @return
	 */
	public void exportRep() {
		try {
			this.resultDTOs = this.service.query(this.dto);
			this.gridModel = this.getMode();
			final CommonExcelReader excel = new Fospm101Excel(this.gridModel);
			ExcelExportTool.export2Download(excel, "FOSPM501.xls");
		} catch (final Exception e) {
//			this.logException(e);
//			this.addActionError("系統異常");
		}
	}


	public List<Map<String, String>> getImportNatList() {
		return importNatList;
	}

	public void setImportNatList(List<Map<String, String>> importNatList) {
		this.importNatList = importNatList;
	}

	public Fospm101QueryDTO getDto() {
		return dto;
	}

	public void setDto(Fospm101QueryDTO dto) {
		this.dto = dto;
	}

	public List<Fospm101ResultDTO> getResultDTOs() {
		return resultDTOs;
	}

	public void setResultDTOs(List<Fospm101ResultDTO> resultDTOs) {
		this.resultDTOs = resultDTOs;
	}

	public Fospm101Service getService() {
		return service;
	}

	public void setService(Fospm101Service service) {
		this.service = service;
	}

}
