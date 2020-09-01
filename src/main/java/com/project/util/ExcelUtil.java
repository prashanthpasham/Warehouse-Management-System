package com.project.util;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
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
						//style.setFillBackgroundColor(Color.GRAY);
						int cellNo = 0;
						for (String header : headers) {
							Cell cell = row.createCell(cellNo++);
							cell.setCellValue(header);
							cell.setCellStyle(style);
						}
						file = new File(moduleName+".xlsx");
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
}
