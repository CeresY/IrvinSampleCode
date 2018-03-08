package office;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * POI生成下拉框
 * @author yangzhan-xps13
 * @date 2017年7月3日-下午1:37:31
 */
public class ExcelTest {

	@Test
	public void cellBox() throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("下拉列表");
		String[] selects = {"A", "B", "C", "D"};
		
		XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
		XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(selects);
		
		int firstRow = 1;
		int lastRow = 10;
		int firstCol = 1;
		int lastCol = 1;
		CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
		
		XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
		sheet.addValidationData(validation);
		
		
		try {
			FileOutputStream fos = new FileOutputStream(new File("C:/YZPersonalTech/log/boxExcel.xlsx"));
			wb.write(fos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
