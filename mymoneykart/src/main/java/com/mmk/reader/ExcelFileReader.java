package com.mmk.reader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReader 
{
		FileInputStream fis;
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		
		public ExcelFileReader() throws IOException
		{
			// Read Excel File
			fis = new FileInputStream(new File("src/test/resources/SiteTestData.xlsx"));
			workbook = new XSSFWorkbook(fis);
			
			// To get Sheet 1
			sheet = workbook.getSheetAt(0);

		}
		
		public String getCellData(int rowNum, int colNum)
		{
			return sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
		}
		
}

