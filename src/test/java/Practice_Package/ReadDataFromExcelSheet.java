package Practice_Package;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelSheet {

	public static void main(String[] args) throws Throwable {
		//Steps 1: get object representation for physical file
	    FileInputStream fis=new FileInputStream("./src/test/resources/TestData.xlsx");
	    
	    Workbook book = WorkbookFactory.create(fis);
	    Sheet sh = book.getSheet("Organization");
	    Row row=sh.getRow(1);
	    Cell cel=row.getCell(0);
	   String Organization_name=cel.getStringCellValue();
	   System.out.println(Organization_name);
	}

}
