package Practice_Package;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleDataFromExcelSheet_2 {

	public static void main(String[] args) throws Throwable {
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh=book.getSheet("Organization");
		int lastrow=sh.getLastRowNum();
//		System.out.println(lastrow);
//		int lastcell=sh.getRow(0).getLastCellNum();
//		System.out.println(lastcell);
		DataFormatter format=new DataFormatter();
		for(int i=0;i<=lastrow;i++)
		{
			Row row = sh.getRow(i);
			for(int j=0;j<row.getLastCellNum();j++)
			{
				String value=format.formatCellValue(row.getCell(j));
				System.out.print(value+" ");
			}
			System.out.println();
		}

	}

}
