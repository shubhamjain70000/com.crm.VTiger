package Practice_Package;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleDataFromExcelSheet {

	public static void main(String[] args) throws Throwable {
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh = book.getSheet("Organization");
		int lastrow = sh.getLastRowNum();
		for(int i=0;i<=lastrow;i++)
		{
			String value= sh.getRow(i).getCell(0).getStringCellValue();
			System.out.println(value+" ");
		}

	}

}
