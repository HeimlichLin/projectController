package com.heimlich.domain.project.excel.impl;

import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.heimlich.common.ApLogger;
import com.heimlich.common.exception.ApBusinessException;
import com.heimlich.domain.project.excel.CommonExcelReader;

public class ExcelExportTool {
	static final ApLogger LOGGER = new ApLogger();

	/**
	 * 跳出下載視窗
	 * 
	 * @param excel
	 * @param fileName
	 */
	final public static void export2Download(CommonExcelReader excel, final String fileName) {
		try {
			ExcelExportTool.exportFile(excel, fileName);
		} catch (Exception e) {
			throw new ApBusinessException("產出失敗", e);
		}
	}

	private static void exportFile(CommonExcelReader excel, String fileName) throws Exception {
		final HttpServletResponse response = ServletActionContext.getResponse();
		final HSSFWorkbook wb = excel.create();
		final StringBuffer header = new StringBuffer();
		header.append("attachment; filename=");
		header.append(URLEncoder.encode(new StringBuffer(fileName).toString(), "utf-8"));
		response.setHeader("Content-Disposition", header.toString());
		final ServletOutputStream output = response.getOutputStream();
		wb.write(output);
		output.flush();
		output.close();

	}

}
