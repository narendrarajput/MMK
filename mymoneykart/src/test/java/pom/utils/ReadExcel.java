package pom.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel 

{

		public static void main(String args[]) throws IOException
		{
				FileInputStream fis = new FileInputStream(new File("D://Automation//ITASM_TestData.xlsx"));
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				XSSFSheet sheet = workbook.getSheetAt(0);
				
				 Iterator<Row> rowIterator = sheet.iterator();
		           
		            // Traversing over each row of XLSX file
		            while (rowIterator.hasNext())
		            {
		                Row row = rowIterator.next();
		           
		                if(row.getRowNum()!=0)
		                {
		                   Iterator  cellIterator = row.cellIterator();
		               
		                   while (cellIterator.hasNext())
			                {
			                	
			                   Cell cell = (Cell) cellIterator.next();

			                   System.out.print(cell.getStringCellValue() + "\t");
			                   
			                   if(cell.getColumnIndex()!=0)
			                   {
			                	   System.out.println("Hi");
			                   }
			                   else
			                   {
			                	   System.out.println("Hello");
			                   }
			                 
			                }
			                
			                	System.out.println("\n");
		                }
		            }

			}
		
}

