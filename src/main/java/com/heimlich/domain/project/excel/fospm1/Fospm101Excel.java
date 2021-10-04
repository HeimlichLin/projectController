package com.heimlich.domain.project.excel.fospm1;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.heimlich.common.exception.ApBusinessException;
import com.heimlich.domain.common.excel.component.ExcelModel;
import com.heimlich.domain.project.excel.impl.CommonExcelModelExport;

public class Fospm101Excel extends CommonExcelModelExport {
	
	public Fospm101Excel(ExcelModel dto) {
		super(dto);
	}

	@Override
	protected void writeExtract(HSSFWorkbook book, HSSFSheet sheet) {
		final HSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
		final HSSFCellStyle style = this.getDetialStyple();
		double netWeightTotal = 0;
		double valueAmtTotal = 0;

		final ExcelModel model = this.getDto();
		final List<Map<String, String>> mapsList = model.getGridList();
		int size = model.getMapLabel().entrySet().size();

		for (final Map<String, String> map : mapsList) {
			try {
				double netWeight = new DecimalFormat().parse(NVL(map.get("WGT"), 0)).doubleValue();
				double valueAmt = new DecimalFormat().parse(NVL(map.get("CIFOB"), 0)).doubleValue();
				netWeightTotal += netWeight;
				valueAmtTotal += valueAmt;
			} catch (ParseException e) {
				throw new ApBusinessException("加總失敗");
			}
		}
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.000");
		for (int i = 0; i <= size; i++) {
			final HSSFCell cell = row.createCell((short) i);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(style);
			if (i == 0) {
				cell.setCellValue("總和");
			}
			if (i == size - 1) {
				cell.setCellValue(String.valueOf(decimalFormat.format(netWeightTotal)));
			}
			if (i == size) {
				cell.setCellValue(String.valueOf(decimalFormat.format(valueAmtTotal)));
			}
		}
		sheet.addMergedRegion(new Region(sheet.getLastRowNum(), (short) 0, sheet.getLastRowNum(), (short) (size - 2)));
	}

}
