package office;

import corejava.enumration.DataTypeEnum;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.*;
import util.SimpleUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/** 导出excel工具类
  * @author yangzhan
  * 2018年4月26日
  */
public class ExcelExportUtils {

	//private static Log log = LogFactory.getLog(ExcelExportUtils.class);
	private static Logger log = Logger.getLogger(ExcelExportUtils.class);

	/**
	 * 创建excel对象
	 * @param sheetNames
	 * @param fieldList 表头信息
	 * @return
	 */
	public static XSSFWorkbook createWorkbook(List<String> sheetNames, List<TableHead> fieldList, Map<String, List<Map<String,Object>>> data) {
		XSSFWorkbook wb = new XSSFWorkbook();
		// ---------------常量----------------
		float row_height = 19f; // 行高
		// 字体
		Font font = CreateExcelPoi.createFontStyle(wb, CreateExcelPoi.FontColorEnum.BLACK, (short)9, false);
		Font font_head = CreateExcelPoi.createFontStyle(wb, CreateExcelPoi.FontColorEnum.BLACK, (short)10, true);
		// 单元格样式
		XSSFCellStyle style_head = CreateExcelPoi.createCellStyle(wb, true, true, true);
		XSSFCellStyle style = CreateExcelPoi.createCellStyle(wb, false, true, true);
		// 把字体应用到当前的样式
		style.setFont(font);
		style_head.setFont(font_head);
		// 日期单元格样式
		XSSFCellStyle style_date = CreateExcelPoi.createCellStyle(wb, false, true, true);
		short s = 0xe; // 0xe表示 yyyy-mm-dd格式的数据
		style_date.setDataFormat(s);

		// -------------多个sheet-----------------
		for(int i=0; i<sheetNames.size(); i++) {
			// 创建一个sheet
			XSSFSheet sheet = wb.createSheet();
			wb.setSheetName(i, sheetNames.get(i));

			// 设置列宽
			for(int col=0; col<fieldList.size(); col++) {
				int width = fieldList.get(col).getWidth();
				if(width > 0) {
					sheet.setColumnWidth(col, width);
				}
			}

			// 写入表头
			XSSFRow row_head = CreateExcelPoi.createRow(sheet, 0, row_height);
			for(int j=0; j<fieldList.size(); j++) {
				XSSFCell cell_head = row_head.createCell(j);
				cell_head.setCellValue(fieldList.get(j).getName());
				cell_head.setCellStyle(style_head);
				cell_head.setCellType(CellType.STRING);
			}

			// 写入正文
			List<Map<String,Object>> dataList = data.get(sheetNames.get(i));
			for(int x=0; x<dataList.size(); x++) {
				// 写入一行数据
				XSSFRow row = CreateExcelPoi.createRow(sheet, (x+1), row_height);
				Map<String,Object> rowData = dataList.get(x);
				for(int j=0; j<fieldList.size(); j++) {
					TableHead column = fieldList.get(j);
					DataTypeEnum type = column.getType();
					// 单元格对象
					XSSFCell cell = row.createCell(j);
					// 单元格样式
					cell.setCellStyle(style);

					// 单元格数据类型
					Object tempVal = rowData.get(column.getCode());

					// 格式化单元格数据
					CellBean cellBean = formatterCell(type, tempVal);
					if(DataTypeEnum.String.equals(type) || DataTypeEnum.Boolean.equals(type)) {
						cell.setCellValue(cellBean.getValStr());
						cell.setCellType(CellType.STRING);
					} else if(DataTypeEnum.Double.equals(type) || DataTypeEnum.Numeric.equals(type)) {
						cell.setCellValue(cellBean.getValDouble());
						cell.setCellType(CellType.NUMERIC);
					}
					else if(DataTypeEnum.Integer.equals(type)) {
						cell.setCellValue(cellBean.getValInt());
						cell.setCellType(CellType.NUMERIC);
					}
					else if(DataTypeEnum.DateTime.equals(type) || DataTypeEnum.Date == type || DataTypeEnum.Timestamp == type) {
						cell.setCellValue(cellBean.getValDate());
						cell.setCellStyle(style_date);
						cell.setCellType(CellType.NUMERIC);
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
				cell.setCellType(CellType.STRING);
			}
		} else if(DataTypeEnum.Double.equals(type) || DataTypeEnum.Numeric.equals(type)) {
			try {
				String numberStr = String.valueOf(tempVal);
				double val_d = Double.valueOf(numberStr);
				cell.setValDouble(val_d);
				cell.setCellType(CellType.NUMERIC);
			} catch (Exception e) {
				log.warn("【格式化单元格数据，数据类型="+type.toString()+"，数据="+tempVal+"，异常信息："+e.getMessage()+"】");
			}
		}
		else if(DataTypeEnum.Integer.equals(type)) {
			try {
				String numberStr = String.valueOf(tempVal);
				int val_i = Integer.valueOf(numberStr);
				cell.setValInt(val_i);
				cell.setCellType(CellType.NUMERIC);
			} catch (Exception e) {
				log.warn("【格式化单元格数据，数据类型="+type.toString()+"，数据="+tempVal+"，异常信息："+e.getMessage()+"】");
			}
		}
		else if(DataTypeEnum.Boolean.equals(type)) {
			String val = "";
			if(tempVal instanceof Boolean) {
				val = (boolean) tempVal ? "是" : "否";
			}
			cell.setValStr(val);
			cell.setCellType(CellType.STRING);
		}
		else if(DataTypeEnum.DateTime.equals(type)) {
			try {
				Date date = SimpleUtils.getJavaDate(tempVal, SimpleUtils.FORMATDATETIME);
				cell.setValDate(date);
				cell.setCellType(CellType.NUMERIC);
			} catch(Exception e) {
				log.warn("【格式化单元格数据，数据类型="+type.toString()+"，数据="+tempVal+"，异常信息："+e.getMessage()+"】");
			}
		} else if(DataTypeEnum.Date == type) {
			try {
				Date date = SimpleUtils.getJavaDate(tempVal, SimpleUtils.FORMATDATE);
				cell.setValDate(date);
				cell.setCellType(CellType.NUMERIC);
			} catch(Exception e) {
				log.warn("【格式化单元格数据，数据类型="+type.toString()+"，数据="+tempVal+"，异常信息："+e.getMessage()+"】");
			}
		} else if(DataTypeEnum.Timestamp == type) {
			try {
				Date date = SimpleUtils.getJavaDate(tempVal, SimpleUtils.FORMATTIMESTAMP);
				cell.setValDate(date);
				cell.setCellType(CellType.NUMERIC);
			} catch(Exception e) {
				log.warn("【格式化单元格数据，数据类型="+type.toString()+"，数据="+tempVal+"，异常信息："+e.getMessage()+"】");
			}
		}
		return cell;
	}

}
