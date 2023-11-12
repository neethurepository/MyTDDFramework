package testData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelSheet {
	
	public static Workbook book;
	public static Sheet sheet;
	//code to fetch data from excel sheet-details
	//we need to create two-dimensional object  array
	public static Object[][] readdata(String sheetname){//pass reference value as sheet name
		FileInputStream file=null;//file inputstream to read data
		//to handle file not exception and IO exception we are using try catch block
	
		try {
		file=new FileInputStream("C:\\Users\\neeth\\eclipse-workspace\\TDDFramework\\src\\test\\java\\testData\\Details.xlsx");//pass the location of excel sheet
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		//WorkbookFactory is used to create appropriate kind of workbook
		//so, if the excel sheet is of xls or xlsx extension, it will auto detect and create workbook accordingly
		try {
		book=	WorkbookFactory.create(file);//create workbook-the complete excel sheet
		//Workbook is the interface
		//2-types of workbook HSSF-older versions, XSSF-for 2007 and above versions
		}
		catch (IOException a) {
			
			a.printStackTrace();
		}
      sheet=book.getSheet(sheetname);//get the sheet name
      //created two-dimensional array with rows and cols
	Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];//get number of rows and cols
	
	//use for loop to fetch data from the excel sheet
	for(int i=0;i<sheet.getLastRowNum();i++) { //for row
		
		{
			for(int k=0;k<sheet.getRow(0).getLastCellNum();k++) { //for column
				data[i][k]=sheet.getRow(i+1).getCell(k).toString();//to read the data from cell and store in the object array
				//row start from i+1 because, 0 row has the headings and we are converting it to string, to make it readable
				
			}
		}
		
	}
	return data;//object array is returned
	
	
	}
}
