package com.heimlich.domain.project.excel;

import java.io.FileInputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelGet {
	private final String filePath;
	
	public ExcelGet(final String filePath) {
		super();
		this.filePath = filePath;
	}
	
	public HSSFWorkbook getBook() throws Exception {
		if(StringUtils.isBlank(this.filePath)){
			final HSSFWorkbook wb = new HSSFWorkbook();
			return wb;
			
		}else{
			final FileInputStream fileInputStream = new FileInputStream(this.filePath);//
			final HSSFWorkbook wb = new HSSFWorkbook(fileInputStream);
			return wb;
		}
	}

}
