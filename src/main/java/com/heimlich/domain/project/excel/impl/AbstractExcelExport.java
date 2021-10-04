package com.heimlich.domain.project.excel.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.heimlich.common.ApLogger;
import com.heimlich.common.exception.ApBusinessException;
import com.heimlich.common.exception.ExceptionHander;
import com.heimlich.domain.common.codes.briefcode.BriefCodeComponent;
import com.heimlich.domain.common.codes.briefcode.BriefCodesCache;
import com.heimlich.domain.project.component.AppSupportContext;
import com.heimlich.domain.project.component.TextSupport;
import com.heimlich.domain.project.define.Contract;
import com.heimlich.domain.project.excel.CommonExcelReader;
import com.heimlich.domain.project.excel.ExcelGet;

public abstract class AbstractExcelExport<DTO> implements CommonExcelReader {
	protected static ApLogger LOGGER = new ApLogger();

	private final DTO dto;
	private final ExcelGet excelGet;
	private final TextSupport textSupport;

	public AbstractExcelExport(final DTO dto, final String filePath) {

		this.dto = dto;
		this.textSupport = AppSupportContext.getTextSupport();
		this.excelGet = new ExcelGet(filePath);
	}

	public AbstractExcelExport(final DTO dto) {
		this(dto, StringUtils.EMPTY);
	}
	
	public ExcelGet getExcelGet() {
		return excelGet;
	}

	public DTO getDto() {
		return this.dto;
	}

	public String getText(String resource) {
		return this.textSupport.getText(resource);
	}

	public String getText(Contract contract) {
		return this.textSupport.getText(contract.getCode());
	}
	
	protected String NVL(String val, Object val2) {
		if (val == null || val.equals("")) {
			return val2.toString();
		}
		return val;
	}
	
	protected void createCell(HSSFWorkbook wb, HSSFSheet sheet, HSSFRow row, short colIndex, Object cellValue,
			HSSFFont fontName, String dataformat, short... align) {

		final HSSFCell cell = row.createCell(colIndex);
		this.setCellStyle(wb, cell, cellValue, fontName, dataformat, false, align);
	}

	private void setCellStyle(HSSFWorkbook wb, HSSFCell cell, Object cellValue, HSSFFont fontName, String dataformat,
			boolean autowrap, short... align) {
		if (align.length > 0) {
			cell.setCellStyle(this.setHSSFCellStyle(wb, fontName, dataformat, autowrap, align));
		} else {
			cell.setCellStyle(this.setHSSFCellStyle(wb, fontName, dataformat, autowrap));
		}
		if (cellValue instanceof String) {
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue((String) cellValue);
		} else if (cellValue instanceof Double) {
			cell.setCellValue((Double) cellValue);
		} else if (cellValue instanceof Boolean) {
			cell.setCellValue((Boolean) cellValue);
		} else if (cellValue instanceof Calendar) {
			cell.setCellValue((Calendar) cellValue);
		} else if (cellValue instanceof Date) {
			cell.setCellValue((Date) cellValue);
		}
	}

	private HSSFCellStyle setHSSFCellStyle(HSSFWorkbook wb, HSSFFont fontName, String dataformat, boolean autowrap,
			short... align) {
		final HSSFCellStyle style = wb.createCellStyle();
		if (autowrap) {
			style.setWrapText(true);
		}

		if (!dataformat.equals("") && dataformat != null) {
			final HSSFDataFormat df = wb.createDataFormat();
			style.setDataFormat(df.getFormat(dataformat));
		}

		if (align.length > 0) {
			if (align.length == 2) {
				style.setAlignment(align[0]);
				style.setVerticalAlignment(align[1]);
			} else {
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			}
		} else {
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		}

		if (fontName != null) {
			style.setFont(fontName);
		}

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		return style;
	}
	
	public final HSSFWorkbook create() {
		try {
			final HSSFWorkbook book = this.getHSSFWorkbook();
			return this.createContent(book);
		} catch (final Exception e) {
			ExceptionHander.handError(LOGGER, e);
			throw new ApBusinessException("create fill", e);
		}

	}
	
	private HSSFWorkbook getHSSFWorkbook() throws Exception {
		final HSSFWorkbook wb = excelGet.getBook();
		return wb;
	}
	
	public BriefCodeComponent getBriefCodeComponent() {
		return BriefCodesCache.Instance.getBridfCodeComponent();
	}
	
	public abstract HSSFWorkbook createContent(HSSFWorkbook book);

}
