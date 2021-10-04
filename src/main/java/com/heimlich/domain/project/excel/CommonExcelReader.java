package com.heimlich.domain.project.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface CommonExcelReader {
	
	/**
	 * 建立HSSFWorkbook
	 * 
	 * @return
	 */
	HSSFWorkbook create();

}
