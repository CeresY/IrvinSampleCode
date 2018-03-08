package util.db.autoCode;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class SqlUtil {
	
	private static String _NUMBER = "NUMBER";
	private static String _VARCHAR2 = "VARCHAR2";
	private static String _CHAR = "CHAR";
	private static String _DATE = "DATE";
	private static String _TIMESTAMP = "TIMESTAMP";
	private static String _INTEGER = "INTEGER";
	
	/**
	 * insert 插入
	 * @param sheet
	 * @param sheetName
	 * @param pojo
	 * @return
	 */
	public static String createSQL(HSSFSheet sheet, String sheetName, String pojo) {
		StringBuffer sb = new StringBuffer();
		sb.append("<insert id=\"create\" parameterClass=\"" + pojo + "\">\r\n");
		sb.append("\tINSERT INTO " + sheetName.toUpperCase() + "(\r\n");
		sb.append("\t\t");
		int rows = sheet.getLastRowNum();
		StringBuffer text = new StringBuffer();
		int count=0;
		for(int i=1; i<=rows; i++) {
			count++;
			HSSFRow row = sheet.getRow(i);
			short cells = row.getLastCellNum();
			//只需要前两列
			HSSFCell cellName = row.getCell((short)0);
			String nameStr = cellName.getStringCellValue().toLowerCase();
			HSSFCell cellType = row.getCell((short)1);
			String typeStr = cellType.getStringCellValue();
			
			sb.append(nameStr + ", ");
			
			if(typeStr.startsWith(_NUMBER)) {
				text.append("#" + nameStr + ":NUMERIC#");
			} else if(typeStr.startsWith(_VARCHAR2) ||
					typeStr.startsWith(_CHAR)) {
				text.append("#" + nameStr + ":VARCHAR#");
			} else if(typeStr.startsWith(_DATE)) {
				text.append("TO_DATE(#" + nameStr + ":VARCHAR#, 'YYYY-MM-DD HH24:MI:SS')");
			} else if(typeStr.startsWith(_TIMESTAMP)) {
				text.append("TO_TIMESTAMP(#" + nameStr + ":VARCHAR#, 'YYYY-MM-DD HH24:MI:SS.FF6')");
			} else if(typeStr.startsWith(_INTEGER)) {
				text.append("#" + nameStr + ":INTEGER#");
			}
			text.append(", ");
			if(count%5 == 0) {
				sb.append("\r\n\t\t");
				text.append("\r\n\t\t");
			}
		}
		sb.replace(sb.length()-2, sb.length()-1, "");
		sb.append("\r\n");
		sb.append("\t)VALUES(\r\n");
		sb.append("\t\t");
		text.replace(text.length()-2, text.length()-1, "");
		sb.append(text);
		sb.append("\t\r\n\t)\r\n</insert>");
		return sb.toString();
	}
	
	/**
	 * select 查询
	 * @param sheet
	 * @param sheetName
	 * @param pojo
	 * @return
	 */
	public static String retrieveSQL(HSSFSheet sheet, String sheetName, String pojo) {
		StringBuffer sb = new StringBuffer();
		sb.append("<select id=\"retrieve\" resultClass=\"" + pojo + "\" parameterClass=\"" + pojo + "\">\r\n");
		sb.append("\tSELECT\r\n");
		sb.append("\t\t");
		int rows = sheet.getLastRowNum();
		int count=0;
		for(int i=1; i<=rows; i++) {
			count++;
			HSSFRow row = sheet.getRow(i);
			short cells = row.getLastCellNum();
			//只需要前两列
			HSSFCell cellName = row.getCell((short)0);
			String nameStr = cellName.getStringCellValue().toLowerCase();
			HSSFCell cellType = row.getCell((short)1);
			String typeStr = cellType.getStringCellValue();
			
			sb.append(select(nameStr, typeStr));
			
			if(count%5 == 0) {
				sb.append("\r\n\t\t");
			}
		}
		sb.replace(sb.length()-2, sb.length()-1, "");
		sb.append("\r\n");
		sb.append("\tFROM " + sheetName.toUpperCase() + " \r\n");
		sb.append("\tWHERE oid=#oid#\r\n");
		sb.append("</select>");
		return sb.toString();
	}
	
	/**
	 * select 查询
	 * @param sheet
	 * @param sheetName
	 * @param pojo
	 * @return
	 */
	public static String retrieveWithTstampSQL(HSSFSheet sheet, String sheetName, String pojo) {
		StringBuffer sb = new StringBuffer();
		sb.append("<select id=\"retrieveWithTstamp\" resultClass=\"" + pojo + "\" parameterClass=\"" + pojo + "\">\r\n");
		sb.append("\tSELECT\r\n");
		sb.append("\t\t");
		int rows = sheet.getLastRowNum();
		int count=0;
		for(int i=1; i<=rows; i++) {
			count++;
			HSSFRow row = sheet.getRow(i);
			short cells = row.getLastCellNum();
			//只需要前两列
			HSSFCell cellName = row.getCell((short)0);
			String nameStr = cellName.getStringCellValue().toLowerCase();
			HSSFCell cellType = row.getCell((short)1);
			String typeStr = cellType.getStringCellValue();
			
			sb.append(select(nameStr, typeStr));
			
			if(count%5 == 0) {
				sb.append("\r\n\t\t");
			}
		}
		sb.replace(sb.length()-2, sb.length()-1, "");
		sb.append("\r\n");
		sb.append("\tFROM " + sheetName.toUpperCase() + " \r\n");
		sb.append("\tWHERE oid=#oid# and tstamp=TO_TIMESTAMP(#tstamp#,'YYYY-MM-DD HH24:MI:SS.FF6')\r\n");
		sb.append("</select>");
		return sb.toString();
	}
	
	/**
	 * update 更新
	 * @param sheet
	 * @param sheetName
	 * @param pojo
	 * @return
	 */
	public static String updateSQL(HSSFSheet sheet, String sheetName, String pojo) {
		StringBuffer sb = new StringBuffer();
		sb.append("<update id=\"update\" parameterClass=\"" + pojo + "\">\r\n");
		sb.append("\tupdate " + sheetName.toUpperCase() + " set tstamp=systimeStamp\r\n");
		sb.append("\t\t<dynamic>\r\n");
		int rows = sheet.getLastRowNum();
		int count=0;
		for(int i=1; i<=rows; i++) {
			count++;
			HSSFRow row = sheet.getRow(i);
			short cells = row.getLastCellNum();
			//只需要前两列
			HSSFCell cellName = row.getCell((short)0);
			String nameStr = cellName.getStringCellValue().toLowerCase();
			HSSFCell cellType = row.getCell((short)1);
			String typeStr = cellType.getStringCellValue();
			sb.append(isNotEmptyAnd(nameStr, typeStr, "\t\t\t", ","));
		}
		sb.append("\t\t</dynamic>\r\n");
		sb.append("\twhere oid=#oid# and tstamp=TO_TIMESTAMP(#tstamp#,'YYYY-MM-DD HH24:MI:SS.FF6')\r\n");
		sb.append("</update>");
		return sb.toString();
	}
	
	/**
	 * update by primery key 主键更新
	 * @param sheet
	 * @param sheetName
	 * @param pojo
	 * @return
	 */
	public static String updateByOidSQL(HSSFSheet sheet, String sheetName, String pojo) {
		StringBuffer sb = new StringBuffer();
		sb.append("<update id=\"updateByOid\" parameterClass=\"" + pojo + "\">\r\n");
		sb.append("\tupdate " + sheetName.toUpperCase() + " set tstamp=systimeStamp\r\n");
		sb.append("\t\t<dynamic>\r\n");
		int rows = sheet.getLastRowNum();
		int count=0;
		for(int i=1; i<=rows; i++) {
			count++;
			HSSFRow row = sheet.getRow(i);
			short cells = row.getLastCellNum();
			//只需要前两列
			HSSFCell cellName = row.getCell((short)0);
			String nameStr = cellName.getStringCellValue().toLowerCase();
			HSSFCell cellType = row.getCell((short)1);
			String typeStr = cellType.getStringCellValue();
			sb.append(isNotEmptyAnd(nameStr, typeStr, "\t\t\t", ","));
		}
		sb.append("\t\t</dynamic>\r\n");
		sb.append("\twhere oid=#oid# and tstamp=TO_TIMESTAMP(#tstamp#,'YYYY-MM-DD HH24:MI:SS.FF6')\r\n");
		sb.append("</update>");
		return sb.toString();
	}
	
	/**
	 * select 分页查询
	 * @param sheet
	 * @param sheetName
	 * @param pojo
	 * @return
	 */
	public static String queryListSQL(HSSFSheet sheet, String sheetName, String pojo) {
		StringBuffer sb = new StringBuffer();
		sb.append("<select id=\"queryList\" resultClass=\"" + pojo + "\" parameterClass=\"" + pojo + "\">\r\n");
		sb.append("\tSELECT\r\n");
		sb.append("\t\t");
		int rows = sheet.getLastRowNum();
		StringBuffer text = new StringBuffer();
		text.append("\t\t\t\t<dynamic prepend=\"WHERE\">\r\n");
		int count=0;
		for(int i=1; i<=rows; i++) {
			count++;
			HSSFRow row = sheet.getRow(i);
			short cells = row.getLastCellNum();
			//只需要前两列
			HSSFCell cellName = row.getCell((short)0);
			String nameStr = cellName.getStringCellValue().toLowerCase();
			HSSFCell cellType = row.getCell((short)1);
			String typeStr = cellType.getStringCellValue();
			
			sb.append(select(nameStr, typeStr));
			if(count%5 == 0) {
				sb.append("\r\n\t\t");
			}
			text.append(isNotEmptyAnd(nameStr, typeStr, "\t\t\t\t\t", "AND"));
		}
		text.append("\t\t\t\t</dynamic>\r\n");
		sb.replace(sb.length()-2, sb.length()-1, "");
		sb.append("\r\n");
		sb.append("\tFROM(\r\n");
		sb.append("\t\tSELECT T.*, ROWNUM RN FROM (\r\n");
		sb.append("\t\t\tSELECT * FROM " + sheetName.toUpperCase() + "\r\n");
		sb.append(text);
		sb.append("\t\t\tORDER BY oid ASC\r\n");
		sb.append("\t\t) T WHERE <![CDATA[ROWNUM <= #endrow#]]>\r\n");
		sb.append("\t) REC");
		sb.append("\tWHERE <![CDATA[REC.RN >= #startrow#]]>\r\n");
		sb.append("</select>");
		return sb.toString();
	}
	
	/**
	 * select 全部查询
	 * @param sheet
	 * @param sheetName
	 * @param pojo
	 * @return
	 */
	public static String queryListAllSQL(HSSFSheet sheet, String sheetName, String pojo) {
		StringBuffer sb = new StringBuffer();
		sb.append("<select id=\"queryListAll\" resultClass=\"" + pojo + "\" parameterClass=\"" + pojo + "\">\r\n");
		sb.append("\tSELECT\r\n");
		sb.append("\t\t");
		int rows = sheet.getLastRowNum();
		StringBuffer text = new StringBuffer();
		text.append("\t<dynamic prepend=\"WHERE\">\r\n");
		int count=0;
		for(int i=1; i<=rows; i++) {
			count++;
			HSSFRow row = sheet.getRow(i);
			short cells = row.getLastCellNum();
			//只需要前两列
			HSSFCell cellName = row.getCell((short)0);
			String nameStr = cellName.getStringCellValue().toLowerCase();
			HSSFCell cellType = row.getCell((short)1);
			String typeStr = cellType.getStringCellValue();
			
			sb.append(select(nameStr, typeStr));
			
			if(count%5 == 0) {
				sb.append("\r\n\t\t");
			}
			
			text.append(isNotEmptyAnd(nameStr, typeStr, "\t\t", "AND"));
		}
		text.append("\t</dynamic>\r\n");
		sb.replace(sb.length()-2, sb.length()-1, "");
		sb.append("\r\n");
		sb.append("\tFROM " + sheetName.toUpperCase() + " \r\n");
		sb.append(text);
		sb.append("\tORDER BY oid ASC\r\n");
		sb.append("</select>");
		return sb.toString();
	}
	
	/**
	 * delete 删除
	 * @param sheet
	 * @param sheetName
	 * @param pojo
	 * @return
	 */
	public static String deleteSQL(HSSFSheet sheet, String sheetName, String pojo) {
		StringBuffer sb = new StringBuffer();
		sb.append("<delete id=\"delete\" parameterClass=\"" + pojo + "\">\r\n");
		sb.append("\tdelete from " + sheetName.toUpperCase() + " \r\n");
		sb.append("\twhere oid=#oid#\r\n");
		sb.append("</delete>");
		return sb.toString();
	}
	
	/**
	 * select 计数器
	 * @param sheet
	 * @param sheetName
	 * @param pojo
	 * @return
	 */
	public static String countSQL(HSSFSheet sheet, String sheetName, String pojo){
		StringBuffer sb = new StringBuffer();
		sb.append("<select id=\"count\" resultClass=\"java.lang.Integer\" parameterClass=\"" + pojo + "\">\r\n");
		sb.append("\tSELECT count(oid)\r\n");
		sb.append("\tFROM " + sheetName.toUpperCase() + " \r\n");
		int rows = sheet.getLastRowNum();
		StringBuffer text = new StringBuffer();
		text.append("\t<dynamic prepend=\"WHERE\">\r\n");
		int count=0;
		for(int i=1; i<=rows; i++) {
			count++;
			HSSFRow row = sheet.getRow(i);
			//只需要前两列
			HSSFCell cellName = row.getCell((short)0);
			String nameStr = cellName.getStringCellValue().toLowerCase();
			HSSFCell cellType = row.getCell((short)1);
			String typeStr = cellType.getStringCellValue();
			
			text.append(isNotEmptyAnd(nameStr, typeStr, "\t\t", "AND"));
		}
		sb.append(text);
		sb.append("\t</dynamic>\r\n");
		sb.append("</select>");
		return sb.toString();
	}
	
	/**
	 * 乐观锁
	 * @param sheetName
	 * @param pojo
	 * @return
	 */
	public static String addProcessLockSQL(String sheetName, String pojo){
		StringBuffer sf = new StringBuffer();
		sf.append("\t" + "<!--指令预处理专用   报文(预处理、应答)作业加锁-->" + "\r\n");
		sf.append("\t" + "<update id=\"addProcessLock\" parameterClass=\"" +pojo+ "\">" + "\r\n");
		sf.append("\t\t" + "update "+sheetName+" set processlock=#processlock# where processlock='00' and dealstatus=#dealstatus:VARCHAR#" + "\r\n");
		sf.append("\t\t" + "<![CDATA[and ROWNUM <= #endrow#]]>" + "\r\n");
		sf.append("\t" + "</update>" +"\r\n");
		return sf.toString();
	}
	
	private static String isNotEmptyAnd(String property, String type, String space, String prepend) {
		StringBuffer sb = new StringBuffer();
		sb.append(space + "<isNotEmpty prepend=\"" + prepend + "\" property=\"" + property +"\">\r\n");
		if(type.startsWith(_NUMBER)) {
			sb.append(space + "\t" + property + "=#" + property + ":NUMERIC#\r\n");
		} else if(type.startsWith(_VARCHAR2) || type.startsWith(_CHAR)) {
			sb.append(space + "\t" + property + "=#" + property + "#\r\n");
		} else if(type.startsWith(_INTEGER)) {
			sb.append(space + "\t" + property + "=#" + property + ":INTEGER#\r\n");
		} else if(type.startsWith(_DATE)) {
			sb.append(space + "\t" + property + "=TO_DATE(#" + property + "#, 'YYYY-MM-DD HH24:MI:SS')\r\n");
		} else if(type.startsWith(_TIMESTAMP)) {
			sb.append(space + "\t" + property + "=TO_TIMESTAMP(#" + property + "#, 'YYYY-MM-DD HH24:MI:SS.FF6')\r\n");
		} 
		sb.append(space + "</isNotEmpty>\r\n");
		return sb.toString();
	}
	
	private static String select(String property, String type) {
		StringBuffer sb = new StringBuffer();
		if(type.startsWith(_NUMBER)) {
			sb.append(property + ", ");
		} else if(type.startsWith(_VARCHAR2) ||
				type.startsWith(_CHAR)) {
			sb.append(property + ", ");
		} else if(type.startsWith(_INTEGER)) {
			sb.append(property + ", ");
		} else if(type.startsWith(_DATE)) {
			sb.append("TO_CHAR(" + property + ", 'YYYY-MM-DD HH24:MI:SS') " + property + ", ");
		} else if(type.startsWith(_TIMESTAMP)) {
			sb.append("TO_CHAR(" + property + ", 'YYYY-MM-DD HH24:MI:SS.FF6') " + property + ", ");
		} 
		return sb.toString();
	}
}
