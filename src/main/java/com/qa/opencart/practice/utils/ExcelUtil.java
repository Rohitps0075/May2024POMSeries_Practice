package com.qa.opencart.practice.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static final String TEST_DATA_SHEET_PATH="./src/test/resources/testData/openCartTestData.xlsx";
	private static Workbook workbook;
	private static Sheet sheet;
	
	
	
	public static Object[][] getTestData(String sheetName)
	{
		Object data[][]=null;
		System.out.println("Reading data from the sheet==>"+sheetName);
		
		try {
			FileInputStream ip=new FileInputStream(TEST_DATA_SHEET_PATH);// this is for build the connection
			 workbook = WorkbookFactory.create(ip);// this is for to load the file;
			  sheet = workbook.getSheet(sheetName);// navigate to particular sheet;
			
			  data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			  for(int i=0;i<sheet.getLastRowNum();i++)
			  { 
				  for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
				  {
					 data[i][j]= sheet.getRow(i+1).getCell(j).toString();// converting to java string.  
				  }  
			  }
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
		
		
		
	}
	
	
	
	
	
	
	
	
}
