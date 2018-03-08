package util.db.autoCode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Excel {
	private static String excelPath = "D:\\Users\\st-yz2011\\Desktop\\temp\\temp_1.xls";
	private static String pojoPath = "D://Users//st-yz2011//Desktop//temp//excel//pojo//";
	private static String facePath = "D://Users//st-yz2011//Desktop//temp//excel//";
	private static String implPath = "D://Users//st-yz2011//Desktop//temp//excel//";
	private static String sqlPath = "D://Users//st-yz2011//Desktop//temp//excel//sql//";
	
	HSSFWorkbook book;
	
	//param1: pojo名称; param2: 接口名称；param3: 实现类名称
	private static String[][] PojoService = {
		{"DistributionPayDetail", "IDistributionPayDetailSvc", "DistributionPayDetailSvc"}
		/*{"Banklist", "IBanklistSvc", "BanklistSvc"},
		{"BanklistTemp", "IBanklistTempSvc", "BanklistTempSvc"},
		{"BanklistMsg", "IBanklistMsgSvc", "BanklistMsgSvc"},
		{"BanklistDetail", "IBanklistDetailSvc", "BanklistDetailSvc"},
		{"DistributionPay", "IDistributionPaySvc", "DistributionPaySvc"},
		{"CrossBorderPay", "ICrossBorderPaySvc", "CrossBorderPaySvc"},
		{"CrossBorderPayMsg", "ICrossBorderPayInsSvc", "CrossBorderPayInsSvc"},
		{"CardMsg","ICardMsgSvc","CardMsgSvc"},*/
	};
	
	//是否生成代码(true生成,false禁止)
	public enum Produce {
		Create(true), Retrieve(true), RetrieveWithTstamp(true), Update(true),
		UpdateByOid(true), QueryList(true), QueryListAll(true), Delete(true),
		Count(true), AddProcessLock(true);
		private boolean confirm;
		private Produce(boolean bl) {
			this.confirm = bl;
		}
	}
	
	public Excel() {
		try {
			book = new HSSFWorkbook(new FileInputStream(new File(excelPath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Excel foo = new Excel();
		foo.autoPojo();
		foo.autoInterface();
		foo.autoImpl();
		foo.autoSQL();
		System.out.println("all!");
	}
	
	public void autoPojo() {
		try {
			File dir = new File(pojoPath);
			if(!dir.exists()) {
				dir.mkdir();
			}
			for(int i=0; i<book.getNumberOfSheets(); i++) {
				
				String pojo = PojoService[i][0];
				BufferedWriter bw = new BufferedWriter(
						new FileWriter(new File(pojoPath+ pojo+ ".java")));
				HSSFSheet sheet = book.getSheetAt(i);
				
				bw.write("package com.boc.ebctm.service.pojo;" + "\r\n");
				bw.write("import com.boc.ebctm.persistence.IPOJO;" + "\r\n");
				bw.write("\r\n");
				bw.write("public class " + pojo + " implements IPOJO{" + "\r\n");
				
				if(sheet != null) {
					int rows = sheet.getLastRowNum();
					for(int r=1; r<=rows; r++) {
						HSSFRow row = sheet.getRow(r);
						//short cells = row.getLastCellNum();
						//只需要前三列
						HSSFCell cellName = row.getCell((short)0);
						String nameStr = cellName.getStringCellValue().toLowerCase();
						
						HSSFCell cellType = row.getCell((short)1);
						String typeStr = cellType.getStringCellValue();
						
						HSSFCell cellContent = row.getCell((short)2);
						String content = cellContent.getStringCellValue();
						bw.write("\t" + "private String " + nameStr + "; // " + typeStr + "\t" + content + "\r\n");
					}
				}
				bw.write("\t" + "//分页查询游标" + "\r\n");
				bw.write("\t" + "private String startrow; //开始游标" + "\r\n");
				bw.write("\t" + "private String endrow; //结束游标" + "\r\n");
				bw.write("}");
				bw.flush();
				bw.close();
			}
			System.out.println("pojo complete!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成svc的实现类
	 */
	public void autoImpl() {
		try {
			for(int i=0; i<book.getNumberOfSheets(); i++) {
				HSSFSheet sheet = book.getSheetAt(i);
				if(sheet != null){
					String pojo = PojoService[i][0];
//					BufferedWriter bw = new BufferedWriter(
//							new FileWriter(new File(implPath + PojoService[i][2] + ".java")));
					//小小的扩展了BufferedWriter类
					BufferedWriterComponent bw = new BufferedWriterComponent(
							new FileWriter(new File(implPath + PojoService[i][2] + ".java")));
					bw.write("package com.boc.ebctm.service.bank;\r\n");
					bw.write("\r\n");
					bw.write("import java.sql.SQLException;" + "\r\n");
					bw.write("import java.util.List;\r\n");
					bw.write("\r\n");
					bw.write("import com.boc.ebctm.persistence.PersistenceException;\r\n");
					bw.write("import com.boc.ebctm.persistence.PersistenceService;\r\n");
					bw.write("import com.boc.ebctm.service."+PojoService[i][1]+";\r\n");
					bw.write("import com.boc.ebctm.service.pojo." + PojoService[i][0] + ";\r\n");
					bw.write("\r\n");
					bw.write("public class " + PojoService[i][2] + " implements "+PojoService[i][1]+" {\r\n");
					bw.write("\r\n");
					
					bw.write(DaoImplUtil.persistence());
					bw.write("\r\n");
					
					bw.write(DaoImplUtil.create(pojo), Produce.Create.confirm);
					bw.write("\r\n", Produce.Create.confirm);
					
					bw.write(DaoImplUtil.retrieve(pojo), Produce.Retrieve.confirm);
					bw.write("\r\n", Produce.Retrieve.confirm);
					
					bw.write(DaoImplUtil.retrieveWithTstamp(pojo), Produce.RetrieveWithTstamp.confirm);
					bw.write("\r\n", Produce.RetrieveWithTstamp.confirm);

					bw.write(DaoImplUtil.update(pojo), Produce.Update.confirm);
					bw.write("\r\n", Produce.Update.confirm);
					
					bw.write(DaoImplUtil.updateByOid(pojo), Produce.UpdateByOid.confirm);
					bw.write("\r\n", Produce.UpdateByOid.confirm);
					
					bw.write(DaoImplUtil.queryList(pojo), Produce.QueryList.confirm);
					bw.write("\r\n", Produce.QueryList.confirm);
					
					bw.write(DaoImplUtil.queryListAll(pojo), Produce.QueryListAll.confirm);
					bw.write("\r\n", Produce.QueryListAll.confirm);
					
					bw.write(DaoImplUtil.delete(pojo), Produce.Delete.confirm);
					bw.write("\r\n", Produce.Delete.confirm);
					
					bw.write(DaoImplUtil.count(pojo), Produce.Count.confirm);
					bw.write("\r\n", Produce.Count.confirm);
					
					bw.write(DaoImplUtil.addProcessLock(pojo), Produce.AddProcessLock.confirm);
					bw.write("}");
					bw.flush();
					bw.close();
				}
			}
			System.out.println("implement complete!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成dao的接口
	 */
	public void autoInterface() {
		try {
			for(int i=0; i<book.getNumberOfSheets(); i++) {
				HSSFSheet sheet = book.getSheetAt(i);
				if(sheet != null){
					//pojo首字母小写
					String parameter = PojoService[i][0].substring(0, 1).toLowerCase() + PojoService[i][0].substring(1);
					
//					BufferedWriter bw = new BufferedWriter(
//							new FileWriter(new File(facePath + PojoService[i][1] + ".java")));
					//小小的扩展了BufferedWriter类
					BufferedWriterComponent bw = new BufferedWriterComponent(
							new FileWriter(new File(facePath + PojoService[i][1] + ".java")));
					
					bw.write("package com.boc.ebctm.service;\r\n");
					bw.write("\r\n");
					bw.write("import java.sql.SQLException;" + "\r\n");
					bw.write("import java.util.List;\r\n");
					bw.write("\r\n");
					bw.write("import com.boc.ebctm.service.pojo." + PojoService[i][0] + ";\r\n");
					bw.write("\r\n");
					bw.write("public interface " + PojoService[i][1] + " {\r\n");
					bw.write("\r\n");
					
					bw.write("\tpublic String create(" + PojoService[i][0] + " "+ parameter +") throws SQLException;\r\n",
							Produce.Create.confirm);
					bw.write("\r\n", Produce.Create.confirm);
					
					bw.write("\tpublic " + PojoService[i][0] + " retrieve(" + PojoService[i][0] + " " + parameter + ");\r\n",
							Produce.Retrieve.confirm);
					bw.write("\r\n", Produce.Retrieve.confirm);
					
					bw.write("\tpublic " + PojoService[i][0] + " retrieveWithTstamp(" + PojoService[i][0] + " " + parameter + ");\r\n",
							Produce.RetrieveWithTstamp.confirm);
					bw.write("\r\n", Produce.RetrieveWithTstamp.confirm);
					
					bw.write("\tpublic int update(" + PojoService[i][0] + " " + parameter + ");\r\n",
							Produce.Update.confirm);
					bw.write("\r\n", Produce.Update.confirm);
					
					bw.write("\tpublic int updateByOid(" + PojoService[i][0] + " " + parameter + ");\r\n",
							Produce.UpdateByOid.confirm);
					bw.write("\r\n", Produce.UpdateByOid.confirm);
					
					bw.write("\tpublic List<" + PojoService[i][0] + "> queryList("+PojoService[i][0]+" "+parameter+");\r\n",
							Produce.QueryList.confirm);
					bw.write("\r\n", Produce.QueryList.confirm);
					
					bw.write("\tpublic List<" + PojoService[i][0] + "> queryListAll("+PojoService[i][0]+" "+parameter+");\r\n",
							Produce.QueryListAll.confirm);
					bw.write("\r\n", Produce.QueryListAll.confirm);
					
					bw.write("\tpublic int delete("+PojoService[i][0]+" "+parameter+");\r\n",
							Produce.Delete.confirm);
					bw.write("\r\n", Produce.Delete.confirm);
					
					bw.write("\tpublic int count("+PojoService[i][0]+" "+parameter+");\r\n", 
							Produce.Count.confirm);
					bw.write("\r\n", Produce.Count.confirm);
					
					bw.write("\tpublic int addProcessLock("+PojoService[i][0]+" "+parameter+");\r\n",
							Produce.AddProcessLock.confirm);
					bw.write("}");
					bw.flush();
					bw.close();
				}
			}
			System.out.println("interface complete!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void autoSQL() {
		File dir = new File(sqlPath);
		if(!dir.exists()) {
			dir.mkdir();
		}
		//XSSFWorkbook book = new XSSFWorkbook();//2007版
		try {
			for(int i=0; i<book.getNumberOfSheets(); i++) {
				HSSFSheet sheet = book.getSheetAt(i);
				if(sheet != null){
					String sheetName = book.getSheetName(i);
					String pojo = PojoService[i][0];
					
//					BufferedWriter bw = new BufferedWriter(
//							new FileWriter(new File(sqlPath + pojo + "_sql.xml")));
					//小小的扩展了BufferedWriter类
					BufferedWriterComponent bw = new BufferedWriterComponent(
							new FileWriter(new File(sqlPath + pojo + "_sql.xml")));
					
					//pojo首字母小写
					String parameter = pojo.substring(0, 1).toLowerCase() + pojo.substring(1);
					
					//配置文件声明头部
					bw.write("<?xml version=\"1.0\" encoding=\"GB2312\"?>\r\n");
					bw.write("<!DOCTYPE sqlMap PUBLIC \"-//iBATIS.com//DTD SQL Map 2.0//EN\" \"http://www.ibatis.com/dtd/sql-map-2.dtd\">\r\n");
					bw.write("<sqlMap namespace=\"" + pojo + "\">\r\n");
					bw.write("\t<typeAlias alias=\"" + parameter + "\" type=\"com.boc.ebctm.service.pojo."+ pojo + "\"/>\r\n");
					
					//SQL正文
					bw.write(SqlUtil.createSQL(sheet, sheetName, parameter) + "\r\n\r\n", Produce.Create.confirm);
					bw.write(SqlUtil.retrieveSQL(sheet, sheetName, parameter) + "\r\n\r\n", Produce.Retrieve.confirm);
					bw.write(SqlUtil.retrieveWithTstampSQL(sheet, sheetName, parameter) + "\r\n\r\n", Produce.RetrieveWithTstamp.confirm);
					bw.write(SqlUtil.updateSQL(sheet, sheetName, parameter) + "\r\n\r\n", Produce.Update.confirm);
					bw.write(SqlUtil.updateByOidSQL(sheet, sheetName, parameter) + "\r\n\r\n", Produce.UpdateByOid.confirm);
					bw.write(SqlUtil.queryListSQL(sheet, sheetName, parameter) + "\r\n\r\n", Produce.QueryList.confirm);
					bw.write(SqlUtil.queryListAllSQL(sheet, sheetName, parameter) + "\r\n\r\n", Produce.QueryListAll.confirm);
					bw.write(SqlUtil.deleteSQL(sheet, sheetName, parameter) + "\r\n\r\n", Produce.Delete.confirm);
					bw.write(SqlUtil.countSQL(sheet, sheetName, parameter) + "\r\n\r\n", Produce.Count.confirm);
					bw.write(SqlUtil.addProcessLockSQL(sheetName, pojo) + "\r\n", Produce.AddProcessLock.confirm);
					
					//配置文件声明尾部
					bw.write("</sqlMap>");
					bw.flush();
					bw.close();
				}
			}
			System.out.println("SQL complete!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void autoPojoTxt() {
		try {
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(new File(pojoPath + "pojo.txt")));
			for(int i=0; i<book.getNumberOfSheets(); i++) {
				HSSFSheet sheet = book.getSheetAt(i);
				String sheetName = book.getSheetName(i);
				
				bw.write(sheetName + "\r\n");
				bw.write("--------------------------------\r\n");
				
				if(sheet != null) {
					int rows = sheet.getLastRowNum();
					for(int r=1; r<=rows; r++) {
						HSSFRow row = sheet.getRow(r);
						//short cells = row.getLastCellNum();
						//只需要前三列
						HSSFCell cellName = row.getCell((short)0);
						String nameStr = cellName.getStringCellValue().toLowerCase();
						
						HSSFCell cellType = row.getCell((short)1);
						String typeStr = cellType.getStringCellValue();
						
						HSSFCell cellContent = row.getCell((short)2);
						String content = cellContent.getStringCellValue();
						//System.out.println(content);
						bw.write("private String " + nameStr + "; //" + typeStr + "\t" + content + "\r\n");
					}
				}
				
				bw.write("\r\n");
			}
			
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}	
