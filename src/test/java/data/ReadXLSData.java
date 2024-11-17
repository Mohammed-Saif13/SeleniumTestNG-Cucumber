package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.checkerframework.checker.units.qual.m;
import org.testng.annotations.DataProvider;



public class ReadXLSData
{
	@DataProvider(name="excel")
	public String[][] getData(Method m) throws EncryptedDocumentException, IOException
	{
		String excelSheetName = m.getName();
		File f = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\testdata.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheetName = wb.getSheet(excelSheetName);
		
		int totalRows = sheetName.getLastRowNum();
		System.out.println(totalRows);
		Row headerRow =sheetName.getRow(0);
		int totalCols = headerRow.getLastCellNum();
		System.out.println(totalCols);
		
		DataFormatter format = new DataFormatter();
		
		String testdata [][] = new String[totalRows][totalCols];
		for(int i=1;i<=totalRows;i++)
		{
			for(int j =0;j<totalCols;j++)
			{
				testdata[i-1][j]= format.formatCellValue(sheetName.getRow(i).getCell(j));
				System.out.println(testdata[i-1][j]);
			}
				
			}
		return testdata;
	}
	
				
	
		
	}