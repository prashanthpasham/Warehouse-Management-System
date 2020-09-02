package com.project.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hpsf.Array;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	public static List<String> templateHeadersByModule(String moduleName) {
		List<String> headers = new ArrayList<String>();
		try {
			if (moduleName != null) {
				if (moduleName.equalsIgnoreCase("Role")) {
					headers.add("Role_Name*");
					headers.add("Role_Description");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return headers;
	}

	public static File generateTemplateByModule(String moduleName) {
		FileOutputStream output = null;
		XSSFWorkbook workbook = null;
		File file = null;
		try {
			if (moduleName != null) {
				if (moduleName.equalsIgnoreCase("Role")) {
					List<String> headers = templateHeadersByModule(moduleName);
					if (!headers.isEmpty()) {
						workbook = new XSSFWorkbook();
						XSSFSheet sheet = workbook.createSheet("Template");
						Row row = sheet.createRow(0);
						XSSFCellStyle style = workbook.createCellStyle();
						// style.setFillBackgroundColor(Color.GRAY);
						int cellNo = 0;
						for (String header : headers) {
							Cell cell = row.createCell(cellNo++);
							cell.setCellValue(header);
							cell.setCellStyle(style);
						}
						file = new File(moduleName + ".xlsx");
						output = new FileOutputStream(file);
						workbook.write(output);

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (output != null)
					output.close();
				if (workbook != null)
					workbook.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	public static List<String> readHeaderFromXLSXFile(File file) {
		List<String> headers = new ArrayList<String>();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet = workBook.getSheetAt(0);
			Iterator it = sheet.iterator();
			while (it.hasNext()) {
				XSSFRow row = (XSSFRow) it.next();
				for (int k = 0; k < row.getLastCellNum(); k++) {
					XSSFCell cell = row.getCell(k, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					if (cell.getStringCellValue() != null && cell.getStringCellValue().trim().length() > 0) {
						headers.add(cell.getStringCellValue().trim());
					} else {
						headers.add("");
					}
				}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return headers;
	}

	public static List<Map<String, String>> readDataFromXLSXFile(File file) {
		List<Map<String, String>> dataMap = new ArrayList<Map<String, String>>();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet = workBook.getSheetAt(0);
			Iterator it = sheet.iterator();
			int index = 0;
			List<String> headers = new ArrayList<String>();
			
			while (it.hasNext()) {
				XSSFRow row = (XSSFRow) it.next();
				Map<String, String> rowMap = new HashMap<String, String>();
				for (int k = 0; k < row.getLastCellNum(); k++) {
					XSSFCell cell = row.getCell(k, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					String value = cell.getStringCellValue();
					if (index == 0) {
						// headers stored in list
						headers.add(value.trim().length() > 0 ? value.trim() : "");
					} else {
						rowMap.put(headers.get(k), (value.trim().length() > 0 ? value.trim() : ""));
						
					}
				}
				dataMap.add(rowMap);
				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}
}
