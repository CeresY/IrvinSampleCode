package office.read;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.util.List;


/**
 * @Description //TODO
 * @Author asuka
 * @Date 2019-5-31
 * @Vesion 1.0
 **/
public class ReadByEasyExcel {
    private static String  path = "H:\\log\\2.xlsx";
    public static void main(String[] args) {
        try {
            /*InputStream inputStream = getResourcesFileInputStream("1.xlsx");
            //FileInputStream inputStream = new FileInputStream(new File(path));
            List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
            inputStream.close();
            print(data);*/

            InputStream inputStream = getResourcesFileInputStream("2007.xlsx");
            List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 0));
            inputStream.close();
            print(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static void print(List<Object> datas){
        int i=0;
        for (Object ob:datas) {
            System.out.println(i++);
            System.out.println(ob);
        }
    }

    @Test
    public void read() throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(path));
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheetAt(0);
        classifier(sheet);
    }

    private void classifier(XSSFSheet sheet) {
        int headNum = 0;
        try {
            File des = new File("H://log//bigData//classifier.sql");
            if (!des.exists()) {
                des.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(des));
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                if(i<=headNum) {
                    continue;
                }
                XSSFRow row = sheet.getRow(i);
                //for (int j = 0; j < row.getLastCellNum(); j++) {
                //XSSFCell cell = row.getCell(j);
                String sql = "insert into [meta_data].[dbo].[mm_classifier]" +
                        "([id],[name],[dis_icon],[is_abstract],[is_mount_point],[dis_composition],[owner_pkg],[description])" +
                        "values(";
                sql += "'"+row.getCell(0).getStringCellValue()+"',"; // id
                sql += "'"+row.getCell(1).getStringCellValue()+"',"; // name
                sql += "'"+row.getCell(5).getStringCellValue()+"',"; // dis_icon
                // is_abstract
                String abs = row.getCell(2).getStringCellValue();
                if(abs.equalsIgnoreCase("F")) {
                    sql += "0,";
                } else {
                    sql += "1,";
                }
                sql += "0,1,";
                sql += "'"+row.getCell(6).getStringCellValue()+"',"; // owner_pkg
                String description = row.getCell(8).getStringCellValue();
                // description
                if("NULL".equals(description)) {
                    sql += "NULL";
                } else {
                    sql += "'"+row.getCell(8).getStringCellValue()+"'";
                }
                sql += ");";
                writer.write(sql);
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
