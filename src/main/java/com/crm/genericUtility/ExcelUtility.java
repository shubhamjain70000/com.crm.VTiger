package com.crm.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ExcelUtility extends JavaUtility {
	
	
	/**
	 * This method is used to read data from excel sheet
	 * @author admin
	 * @param Sheetname
	 * @param rownum
	 * @param cellnum
	 * @return
	 * @throws Throwable 
	 * @throws IOException
	 */
	
	public String readDataFromExcelSheet(String Sheetname,int rownum, int cellnum) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstant.Excelspath);
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh = book.getSheet(Sheetname);
		Row row = sh.getRow(rownum);
		Cell cel = row.getCell(cellnum);
		String value = cel.getStringCellValue();
		return value;
	}
	/**
	 * This method is used to write data into excel sheet//
	 * @author admin
	 * @param Sheetname
	 * @param rownum
	 * @param cellnum
	 * @param data
	 * @throws Throwable
	 */
	public void writeDataIntoExcelSheet(String Sheetname,int rownum, int cellnum,String data) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstant.Excelspath);
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh = book.getSheet(Sheetname);
		sh.createRow(rownum).createCell(cellnum).setCellValue(data);   
		FileOutputStream fos=new FileOutputStream(IPathConstant.Excelspath);
		book.write(fos);
	}
	public int getLastRow(String Sheetname) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstant.Excelspath);
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh = book.getSheet(Sheetname);
		int count = sh.getLastRowNum();
		return count;
    }
	public HashMap<String,String> getList(String Sheetname,int keyCell,int valuecell) throws Throwable
	{
		JavaUtility jLib=new JavaUtility();
		int random = jLib.getRandom();
		
		FileInputStream fis = new FileInputStream(IPathConstant.Excelspath);
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh = book.getSheet(Sheetname);
		int count = sh.getLastRowNum();
		HashMap<String,String> map=new HashMap<String,String>();
		for(int i=0;i<=count;i++)
		{
			String key = sh.getRow(i).getCell(keyCell).getStringCellValue();
			String value = sh.getRow(i).getCell(valuecell).getStringCellValue()+random;
			map.put(key, value);
		}
		return map;
		
	}
	public HashMap<String,String> getListWithoutRandom(String Sheetname,int keyCell,int valuecell) throws Throwable
	{
		
		FileInputStream fis = new FileInputStream(IPathConstant.Excelspath);
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh = book.getSheet(Sheetname);
		int count = sh.getLastRowNum();
		HashMap<String,String> map=new HashMap<String,String>();
		for(int i=0;i<=count;i++)
		{
			String key = sh.getRow(i).getCell(keyCell).getStringCellValue();
			String value = sh.getRow(i).getCell(valuecell).getStringCellValue();
			map.put(key, value);
		}
		return map;
	}
	
	
	public ArrayList<String> getArrayList(String Sheetname,int cellno) throws Throwable
	{
		JavaUtility jLib=new JavaUtility();
		
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");
	    FileInputStream fi=new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook book = WorkbookFactory.create(fi);
		Sheet sh = book.getSheet(Sheetname);
		int lastrownum = sh.getLastRowNum();
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<=lastrownum;i++)
		{
			String value = sh.getRow(i).getCell(cellno).getStringCellValue();
			list.add(value);
		}
		return list;
	}
	
	public Object[][] readMultipleSetOfDataFromExcelUsingDataProviderAnnotation(String sheetname) throws Throwable, Throwable
	{
		FileInputStream fis=new FileInputStream(IPathConstant.Excelspath);
		Workbook book=WorkbookFactory.create(fis); 
		Sheet sh = book.getSheet(sheetname);
		int lastrow = sh.getLastRowNum()+1;
		short lastcell = sh.getRow(0).getLastCellNum();
		
		Object[][] obj = new Object[lastrow][lastcell];
		
		for(int i=0;i<lastrow;i++)//for row
		{
			for(int j=0;j<lastcell;j++)
			{
				obj[i][j]=sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return obj;
	}
	
	

}
