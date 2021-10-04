package com.heimlich.domain.project.excel.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.heimlich.domain.common.excel.component.ExcelModel;
import com.heimlich.domain.common.excel.component.ExcelType;
import com.heimlich.domain.project.define.Contract;

public class CommonExcelModelExport extends AbstractExcelExport<ExcelModel>{
	private HSSFFont detilaFont;
	private HSSFFont captionFont;
	private HSSFFont queryItemFont;
	private HSSFFont headerFont;
	private HSSFCellStyle detialStyple;

	public CommonExcelModelExport(final ExcelModel dto) {
		super(dto);
	}

	private final static short CAPTION_FONT_SIZE = 14;
	private final static short DEF_FONT_SIZE = 10;
	private final static short QUERY_FONT_SIZE = 12;

	@Override
	public final HSSFWorkbook createContent(final HSSFWorkbook book) {
		final HSSFSheet sheet = this.createTableHelper(book);
		this.writeExtract(book, sheet);
		return book;
	}

	/**
	 * 產製table helper
	 * 
	 * @param book
	 * @return
	 */
	protected final HSSFSheet createTableHelper(final HSSFWorkbook book) {
		this.initial(book);
		final HSSFSheet sheet = book.createSheet();
		this.setCaption(book, sheet);// 設定標題
		this.setHeader(book, sheet);// 設定表頭
		this.setQueryItem(book, sheet);// 設定查詢項目
		this.setDataList(book, sheet);// 設定結果資料
		return sheet;
	}

	// 額外資料
	protected void writeExtract(HSSFWorkbook book, HSSFSheet sheet) {
		// 額外資料
	}

	/**
	 * 初始化
	 * 
	 * @param book
	 */
	private void initial(final HSSFWorkbook book) {
		final HSSFPalette palette = book.getCustomPalette();
		palette.setColorAtIndex((short) 9, (byte) (0xff & 192), (byte) (0xff & 192), (byte) (0xff & 192));// 顏色

		// Font
		this.detilaFont = this.getFont(book, DEF_FONT_SIZE);
		this.captionFont = this.getBoldWeightFont(book, CommonExcelModelExport.CAPTION_FONT_SIZE);
		this.queryItemFont = this.getBoldWeightFont(book, QUERY_FONT_SIZE);
		this.headerFont = this.getFont(book, 12);

		// STYPLE
		this.detialStyple = this.getCenterStyle(book);
		this.detialStyple.setFont(this.detilaFont);
		this.detialStyple.setBorderBottom((short) 1);
		this.detialStyple.setBorderTop((short) 1);
		this.detialStyple.setBorderLeft((short) 1);
		this.detialStyple.setBorderRight((short) 1);
	}

	/**
	 * 設定結果資料
	 * 
	 * @param book
	 * @param sheet
	 */
	private void setDataList(final HSSFWorkbook book, final HSSFSheet sheet) {
		final ExcelModel model = this.getDto();
		final List<Map<String, String>> mapsList = model.getGridList();
		int index = 0;

		final List<Map<String, String>> newSortMap = this.getNewMap(mapsList);

		for (final Map<String, String> map : newSortMap) {
			short cellNo = 0;
			final HSSFRow row = sheet.createRow(index + 4);
			if (cellNo == 0) {

				final HSSFCell cell = row.createCell(cellNo++);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(String.valueOf(index + 1));
				cell.setCellStyle(this.detialStyple);
			}
			for (final Map.Entry<String, String> showLabelMap : model.getMapLabel().entrySet()) {

				if (map.containsKey(showLabelMap.getKey())) {
					final HSSFCellStyle style = this.getCenterStyle(book);
					style.setFont(this.detilaFont);
					style.setBorderBottom((short) 1);
					style.setBorderTop((short) 1);
					style.setBorderLeft((short) 1);
					style.setBorderRight((short) 1);

					final HSSFCell cell = row.createCell(cellNo++);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(map.get(showLabelMap.getKey()));
					cell.setCellStyle(this.detialStyple);
				}

			}

			index++;
		}

		this.addSumItem(book, sheet);// 加入額外的總和
	}

	private void addSumItem(final HSSFWorkbook book, final HSSFSheet sheet) {
		final ExcelModel model = this.getDto();
		int nowInde = model.getGridList().size() + 3;
		if (model.getExcelType() == ExcelType.SUM) {
			final HSSFRow row = sheet.createRow(nowInde);
			final Map<String, String> map = this.getDto().getSum().get(0);
			short mergedSize = 0;
			short cellNo = 0;

			final HSSFCell cellFirst = row.createCell(cellNo++);
			cellFirst.setEncoding(HSSFCell.ENCODING_UTF_16);
			cellFirst.setCellValue("總和");
			cellFirst.setCellStyle(this.detialStyple);

			for (final Map.Entry<String, String> showLabelMap : model.getMapLabel().entrySet()) {
				if (map.containsKey(showLabelMap.getKey())) {
					final HSSFCell cell = row.createCell(cellNo++);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					final String valueString = map.get(showLabelMap.getKey());
					if (StringUtils.isBlank(valueString) || StringUtils.equals("總和", valueString)) {
						mergedSize++;
					}
					cell.setCellValue(map.get(showLabelMap.getKey()));
					cell.setCellStyle(this.detialStyple);
				}

			}
			sheet.addMergedRegion(new Region(nowInde, (short) 0, nowInde, (mergedSize)));
		}
	}

	/**
	 * 取得標題內預設風格
	 * 
	 * @return
	 */
	public HSSFCellStyle getDetialStyple() {
		return detialStyple;
	}

	private List<Map<String, String>> getNewMap(final List<Map<String, String>> mapsList) {

		if (this.getDto().getExcelType() == ExcelType.SUM) {
			Map<String, String> firstMap = null;
			final List<Map<String, String>> newSortMap = new ArrayList<Map<String, String>>();
			for (final Map<String, String> map : mapsList) {
				if (firstMap == null) {
					firstMap = map;
				} else {
					newSortMap.add(map);
				}
			}
			return newSortMap;
		} else {
			return mapsList;
		}

	}

	/**
	 * 設定查詢項目
	 * 
	 * @param book
	 * @param sheet
	 */
	private void setQueryItem(final HSSFWorkbook book, final HSSFSheet sheet) {
		final HSSFRow row = sheet.createRow(1);
		final HSSFCellStyle style = this.getCenterStyle(book);
		style.setFont(this.queryItemFont);

		final HSSFCell cell1 = row.createCell((short) 0);
		cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell1.setCellValue("查詢條件");
		cell1.setCellStyle(style);

		final ExcelModel model = this.getDto();
		final HSSFCell cell2 = row.createCell((short) 1);
		cell2.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell2.setCellValue(model.getQuery().toLine());
		cell2.setCellStyle(style);
	}

	/**
	 * 設定表頭
	 * 
	 * @param book
	 * @param sheet
	 */
	private void setHeader(final HSSFWorkbook book, final HSSFSheet sheet) {
		final ExcelModel model = this.getDto();
		for (int i = 0; i < 2; i++) {
			int index = 1;
			final HSSFRow row = sheet.createRow(i + 2);

			for (final Map.Entry<String, String> entry : model.getMapLabel().entrySet()) {
				final HSSFCellStyle style = this.getCenterStyle(book);
				style.setFont(this.headerFont);
				style.setBorderBottom((short) 1);
				style.setBorderTop((short) 1);
				style.setBorderLeft((short) 1);
				style.setBorderRight((short) 1);
				style.setFillForegroundColor((short) 9);
				style.setFillPattern((short) 1);
				final String text = StringUtils.defaultIfEmpty(entry.getValue(), "");

				final HSSFCell cell = row.createCell((short) index);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(text);
				cell.setCellStyle(style);
				sheet.addMergedRegion(new Region(2, (short) index, 3, (short) index));
				sheet.setColumnWidth((short) index, (short) (text.getBytes().length * 2 * 256));// 調整寬度
				index++;
			}
			final HSSFCellStyle style = this.getCenterStyle(book);
			style.setFont(this.headerFont);
			style.setBorderBottom((short) 1);
			style.setBorderTop((short) 1);
			style.setBorderLeft((short) 1);
			style.setBorderRight((short) 1);
			style.setFillForegroundColor((short) 9);
			style.setFillPattern((short) 1);

			final HSSFCell cell = row.createCell((short) 0);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue("編號");
			cell.setCellStyle(style);
			sheet.addMergedRegion(new Region(2, (short) 0, 3, (short) 0));
			sheet.setColumnWidth((short) index, (short) ("編號".getBytes().length * 2 * 256));// 調整寬度
		}
	}

	/**
	 * 設定標題
	 * 
	 * @param book
	 * @param sheet
	 */
	private void setCaption(final HSSFWorkbook book, final HSSFSheet sheet) {
		final ExcelModel model = this.getDto();

		final HSSFRow row = sheet.createRow(0);
		final HSSFCell cell = row.createCell((short) 0);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(model.getCaption());

		final HSSFCellStyle style = this.getCenterStyle(book);
		style.setFont(captionFont);

		cell.setCellStyle(style);
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) (model.getMapLabel().size())));
	}

	/**
	 * 取得粗體字
	 * 
	 * @param book
	 * @param size
	 * @return
	 */
	public HSSFFont getBoldWeightFont(final HSSFWorkbook book, final int size) {
		final HSSFFont font = book.createFont();
		font.setFontName(this.getText(Contract.LABEL_FontName_Type1));
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short) size);
		return font;
	}

	public HSSFCellStyle getCenterStyle(final HSSFWorkbook book) {
		final HSSFCellStyle hssfcellstyle = book.createCellStyle();
		hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return hssfcellstyle;
	}

	/**
	 * 取得字形
	 * 
	 * @param book
	 * @return
	 */
	public HSSFFont getFont(final HSSFWorkbook book, final int size) {
		final HSSFFont font = book.createFont();
		font.setFontName(this.getText(Contract.LABEL_FontName_Type1));
		font.setFontHeightInPoints((short) size);
		return font;
	}

	/**
	 * 取得表格內預設文字
	 * 
	 * @return
	 */
	public HSSFFont getDetilaFont() {
		return detilaFont;
	}

}
