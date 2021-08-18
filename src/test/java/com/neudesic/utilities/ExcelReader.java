package com.neudesic.utilities;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public FileInputStream fileInput = null;
	public FileOutputStream fileOutput = null;
	private Workbook workbook = null;
	private Sheet sheet = null;
	private Row row = null;
	private Cell cell = null;

	/*********************************************************************************
	 * Constructor
	 * 
	 * @param filePath
	 * @param sheetName
	 * @throws IOException
	 *********************************************************************************/

	public ExcelReader(String filePath, String sheetName) throws IOException {

		try {
			fileInput = new FileInputStream(filePath);

			if (filePath.toLowerCase().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(fileInput);
			} else if (filePath.toLowerCase().endsWith("xls")) {
				workbook = new HSSFWorkbook(fileInput);
			}
			
			sheet = workbook.getSheet(sheetName);
		} catch (Exception e) {
			System.out.println("Unable to load the file. Reason: " + e.getMessage());
		} finally {
			fileInput.close();
		}
	}

	/***********************************************************************************
	 * Callable Methods
	 **********************************************************************************/
	/***********************************************************************************
	 * Get Row Count from a sheet
	 * 
	 * @param sheetName
	 * @return Integer value
	 ***********************************************************************************/

	public int getRowCount(String sheetName) {

		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			throw new RuntimeException("Unable to find the sheet with name: " + sheetName);
		else {
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			if (row == null)
				throw new RuntimeException("There are no rows available in the sheet: " + sheetName);
			return sheet.getLastRowNum() + sheet.getFirstRowNum();
		}
	}

	/***********************************************************************************
	 * Get Column Count from a sheet
	 * 
	 * @param sheetName
	 * @return Integer value
	 ***********************************************************************************/

	public int getColumnCount(String sheetName) {

		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			throw new RuntimeException("Unable to find the sheet with name: " + sheetName);
		else {
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			if (row == null)
				throw new RuntimeException("There are no rows available in the sheet: " + sheetName);
			return row.getLastCellNum();
		}
	}

	/************************************************************************************
	 * Returns the data from a specific cell
	 * 
	 * @param sheetName
	 * @param colName
	 * @param rowNum
	 * @return String value from the given excel sheet
	 ***********************************************************************************/

	public String getCellData(String sheetName, String colName, int rowNum) {

		String excelData = null;

		int index = workbook.getSheetIndex(sheetName);
		int colNum = -1;
		if (index == -1)
			throw new RuntimeException("Unable to find the sheet with name: " + sheetName);
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(0);
		if (row == null)
			throw new RuntimeException("There are no rows available in the sheet: " + sheetName);
		else if (rowNum <= 0 || rowNum > sheet.getLastRowNum())
			throw new RuntimeException("Row number cannot be lesser than or equal to 0 and greater than "
					+ sheet.getLastRowNum() + " for the sheet name " + sheetName);

		for (int i = 0; i < row.getLastCellNum(); i++) {

			if (row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
				colNum = i;
				break;
			}
		}

		if (colNum == -1)
			throw new RuntimeException("Unable to find the column with name: " + colName);

		row = sheet.getRow(rowNum - 1);
		cell = row.getCell(colNum);

		switch (cell.getCellType()) {

		case STRING:
			excelData = cell.getStringCellValue();
			break;
		case NUMERIC:
			excelData = String.valueOf(cell.getNumericCellValue());
			break;
		case FORMULA:

			if (DateUtil.isCellDateFormatted(cell)) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date today = cell.getDateCellValue();
				excelData = dateFormat.format(today);
			}
			break;
		case BLANK:
			return "";
		default:
			excelData = String.valueOf(cell.getBooleanCellValue());
		}

		return excelData;
	}

}
