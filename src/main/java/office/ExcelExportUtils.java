package office;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;

import corejava.enumration.DataTypeEnum;
import util.SimpleUtils;

/** 导出excel工具类
  * @author yangzhan
  * 2018年4月26日
  */
public class ExcelExportUtils {

	/**
	 * 创建excel对象
	 * @param sheetNames
	 * @param fieldList
	 * @return
	 */
	public static XSSFWorkbook createWorkbook(List<String> sheetNames, List<Column> fieldList, Map<String, List<Map<String,Object>>> data) {
		XSSFWorkbook wb = new XSSFWorkbook();
		// ---------------常量----------------
		float row_height = 17f; // 行高
		// 字体
		Font cell_font = CreateExcelPoi.createFontStyle(wb, CreateExcelPoi.FontColorEnum.BLACK, (short)9, false);
		Font cell_head_font = CreateExcelPoi.createFontStyle(wb, CreateExcelPoi.FontColorEnum.BLACK, (short)10, true);
		// 单元格样式
		XSSFCellStyle cell_head_style = CreateExcelPoi.createCellStyle(wb, true, true, true);
		XSSFCellStyle cell_style = CreateExcelPoi.createCellStyle(wb, false, true, true);
		// 把字体应用到当前的样式
		cell_style.setFont(cell_font);
		cell_head_style.setFont(cell_head_font);
		// 日期单元格样式
		XSSFCellStyle style_date = CreateExcelPoi.createCellStyle(wb, false, true, true);
		short s = 0xe; // 0xe表示 yyyy-mm-dd格式的数据
		style_date.setDataFormat(s);
		
		// -------------多个sheet-----------------
		for(int i=0; i<sheetNames.size(); i++) {
			// 创建一个sheet
			XSSFSheet sheet = wb.createSheet();
			wb.setSheetName(i, sheetNames.get(i));
			
			// 写入表头
			XSSFRow row_head = CreateExcelPoi.createRow(sheet, 0, row_height);
			for(int j=0; j<fieldList.size(); j++) {
				XSSFCell cell_head = row_head.createCell(j);
				cell_head.setCellValue(fieldList.get(j).getName());
				cell_head.setCellStyle(cell_head_style);
			}
			
			// 写入正文
			List<Map<String,Object>> dataList = data.get(sheetNames.get(i));
			for(int x=0; x<dataList.size(); x++) {
				// 写入一行数据
				XSSFRow row_text = CreateExcelPoi.createRow(sheet, (x+1), row_height);
				Map<String,Object> rowData = dataList.get(x);
				for(int j=0; j<fieldList.size(); j++) {
					Column column = fieldList.get(j);
					DataTypeEnum type = column.getType();
					// 单元格对象
					XSSFCell cellObj = row_text.createCell(j);
					// 单元格样式
					cellObj.setCellStyle(cell_style);
					
					// 单元格数据类型
					Object tempVal = rowData.get(column.getName());
					
					// 格式化单元格数据
					CellBean cellBean = formatterCell(type, tempVal);
					int typeInt = cellBean.getCellType();
					cellObj.setCellType(typeInt);
					if(DataTypeEnum.String.equals(type)) {
						cellObj.setCellValue(cellBean.getValStr());
					} else if(DataTypeEnum.Double.equals(type) || DataTypeEnum.Numeric.equals(type)) {
						cellObj.setCellValue(cellBean.getValDouble());
					} 
					else if(DataTypeEnum.Integer.equals(type)) {
						cellObj.setCellValue(cellBean.getValInt());
					}
					else if(DataTypeEnum.DateTime.equals(type) || DataTypeEnum.Date == type || DataTypeEnum.Timestamp == type) {
						cellObj.setCellValue(cellBean.getValDate());
						cellObj.setCellStyle(style_date);
					}
				}
			}
		}
		return wb;
	}
	
	/**
	 * 格式化单元格数据
	 * @param type 数据格式
	 * @param tempVal 单元格数据
	 * @return
	 */
	public static CellBean formatterCell(DataTypeEnum type, Object tempVal) {
		CellBean cell = new CellBean();
		cell.setType(type);
		// 类型
		if(DataTypeEnum.String.equals(type)) {
			if(tempVal != null) {
				cell.setValStr(String.valueOf(tempVal));
				cell.setCellType(Cell.CELL_TYPE_STRING);
			}
		} else if(DataTypeEnum.Double.equals(type) || DataTypeEnum.Numeric.equals(type)) {
			try {
				String numberStr = String.valueOf(tempVal);
				double val_d = Double.valueOf(numberStr);
				cell.setValDouble(val_d);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			} catch (Exception e) {
				//log.warn("【格式化单元格数据，数据类型={}，数据={}，异常信息：{}】", type.toString(), tempVal, e.getMessage());
			}
		} 
		else if(DataTypeEnum.Integer.equals(type)) {
			try {
				String numberStr = String.valueOf(tempVal);
				int val_i = Integer.valueOf(numberStr);
				cell.setValInt(val_i);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			} catch (Exception e) {
				//log.warn("【格式化单元格数据，数据类型={}，数据={}，异常信息：{}】", type.toString(), tempVal, e.getMessage());
			}
		}
		else if(DataTypeEnum.DateTime.equals(type)) {
			try {
				Date date = SimpleUtils.getJavaDate(tempVal, SimpleUtils.FORMATDATETIME);
				cell.setValDate(date);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			} catch(Exception e) {
				//log.warn("【格式化单元格数据，数据类型={}，数据={}，异常信息：{}】", type.toString(), tempVal, e.getMessage());
			}
		} else if(DataTypeEnum.Date == type) {
			try {
				Date date = SimpleUtils.getJavaDate(tempVal, SimpleUtils.FORMATDATE);
				cell.setValDate(date);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			} catch(Exception e) {
				//log.warn("【格式化单元格数据，数据类型={}，数据={}，异常信息：{}】", type.toString(), tempVal, e.getMessage());
			}
		} else if(DataTypeEnum.Timestamp == type) {
			try {
				Date date = SimpleUtils.getJavaDate(tempVal, SimpleUtils.FORMATTIMESTAMP);
				cell.setValDate(date);
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			} catch(Exception e) {
				//log.warn("【格式化单元格数据，数据类型={}，数据={}，异常信息：{}】", type.toString(), tempVal, e.getMessage());
			}
		}
		return cell;
	}
	
}
