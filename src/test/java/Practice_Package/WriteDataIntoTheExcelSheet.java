package Practice_Package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoTheExcelSheet {

	public static void main(String[] args) throws Throwable {
		FileInputStream fis=new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh = book.getSheet("Organization");
		Cell cel=sh.createRow(2).createCell(0);
		cel.setCellValue("shubham");
		FileOutputStream fos=new FileOutputStream("./src/test/resources/TestData.xlsx");
		book.write(fos);
		fos.flush();
		}

}
