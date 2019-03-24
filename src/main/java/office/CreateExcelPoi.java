package office;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 工具类：使用POI生成EXCEL
 * @author yangzhan-xps13
 * @date 2017年4月25日-上午9:15:58
 */
public class CreateExcelPoi {
	public static Map<String, HSSFColor.HSSFColorPredefined> COLORS =
			new HashMap<String, HSSFColor.HSSFColorPredefined>(){
		{
			put("#333399", HSSFColor.HSSFColorPredefined.INDIGO);
			put("#993366", HSSFColor.HSSFColorPredefined.PLUM);
			put("#993300", HSSFColor.HSSFColorPredefined.BROWN);
			put("#339966", HSSFColor.HSSFColorPredefined.SEA_GREEN);
			put("#969696", HSSFColor.HSSFColorPredefined.GREY_40_PERCENT);
			put("#FF6600", HSSFColor.HSSFColorPredefined.ORANGE);
			put("#FF9900", HSSFColor.HSSFColorPredefined.LIGHT_ORANGE);
			put("#FFCC00", HSSFColor.HSSFColorPredefined.GOLD);
			put("#99CC00", HSSFColor.HSSFColorPredefined.LIME);
			put("#3366FF", HSSFColor.HSSFColorPredefined.LIGHT_BLUE);
			put("#FFCC99", HSSFColor.HSSFColorPredefined.TAN);
			put("#CC99FF", HSSFColor.HSSFColorPredefined.LAVENDER);
			put("#FF99CC", HSSFColor.HSSFColorPredefined.ROSE);
			put("#99CCFF", HSSFColor.HSSFColorPredefined.PALE_BLUE);
			put("#FFFF99", HSSFColor.HSSFColorPredefined.LIGHT_YELLOW);
			put("#FF8080", HSSFColor.HSSFColorPredefined.CORAL);
		}
	};
	
	//字体颜色
	public enum FontColorEnum {
		BLACK(HSSFColor.HSSFColorPredefined.BLACK),
		LIGHT_ORANGE(HSSFColor.HSSFColorPredefined.LIGHT_ORANGE),
		GOLD(HSSFColor.HSSFColorPredefined.GOLD),
		PLUM(HSSFColor.HSSFColorPredefined.PLUM),
		WHITE(HSSFColor.HSSFColorPredefined.WHITE);
		private HSSFColor.HSSFColorPredefined index;
		FontColorEnum(HSSFColor.HSSFColorPredefined index){
			this.index = index;
		}
	}
	
	/**
	 * 创建行
	 * @param sheet sheet对象
	 * @param rowNum 行编号
	 * @param height 行高
	 * @return
	 */
	public static XSSFRow createRow(XSSFSheet sheet, int rowNum, float height){
		XSSFRow row = sheet.createRow(rowNum);
		row.setHeightInPoints(height);//行高
		return row;
	}
	
	/**
	 * 创建单元格
	 * @param row
	 * @param columnIndex 列索引
	 * @return
	 */
	public static XSSFCell createCell(XSSFRow row, int columnIndex){
		XSSFCell cell = row.createCell(columnIndex);
		return cell;
	}
	
	/**
	 * 合并单元格
	 * @param sheet sheet对象
	 * @param firstRow 起始行
	 * @param lastRow 结束行
	 * @param firstCol 起始列
	 * @param lastCol 结束列
	 */
	public static void mergeCell(XSSFSheet sheet, int firstRow, int lastRow, int firstCol, int lastCol){
		sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
	}
	
	/**
	 * 创建单元格样式
	 * @param wb excel对象
	 * @param isAlignCenter 文字是否居中
	 * @param isBorder 是否有边框
	 * @param isWarp 文字是否换行
	 * @return
	 */
	public static XSSFCellStyle createCellStyle(XSSFWorkbook wb, boolean isAlignCenter, boolean isBorder, boolean isWarp) {
		XSSFCellStyle style = wb.createCellStyle();
		if(isAlignCenter) {
			style.setAlignment(HorizontalAlignment.CENTER);
		}
		if(isBorder) {
			style.setBorderBottom(BorderStyle.THIN);; // 下边框
			style.setBorderLeft(BorderStyle.THIN);// 左边框
			style.setBorderTop(BorderStyle.THIN);// 上边框
			style.setBorderRight(BorderStyle.THIN);// 右边框
		}
		// 指定当单元格内容显示不下时自动换行
		style.setWrapText(isWarp);
		return style;
	}
	
	/**
	 * 创建字体样式
	 * @param wb 文档对象
	 * @param color 字体颜色
	 * @param fontPoint 字重
	 * @param isBold 粗体
	 * @return
	 */
	public static Font createFontStyle(XSSFWorkbook wb, FontColorEnum color, short fontPoint, boolean isBold){
		Font font = wb.createFont();
		font.setFontHeightInPoints(fontPoint);
		font.setBold(isBold);
		/*if(isBold) {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		} else {
			font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		}*/
		return font;
	}
	
	/**
	 * 设置背景色(弃用 )
	 * @param cellStyle
	 * @param colorStr
	 */
	private static XSSFCellStyle setBackgroundColor_(XSSFCellStyle cellStyle, String colorStr) {
		java.awt.Color color = hex2rgb(colorStr);
		XSSFColor xssfColor = new XSSFColor(color);
		cellStyle.setFillForegroundColor(xssfColor);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return cellStyle;
	}
	
	/**
	 * 设置背景色
	 * @param cellStyle
	 * @param colorStr
	 */
	public static XSSFCellStyle setBackgroundColor(XSSFCellStyle cellStyle, String colorStr) {
		cellStyle.setFillForegroundColor(COLORS.get(colorStr).getIndex());
		//cellStyle.setFillForegroundColor(COLORS.get(colorStr));
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return cellStyle;
	}
	
	/**
	 * （弃用）
	 * 16进制颜色转换java.awt.Color对象
	 * @param hex
	 * @return
	 */
	private static java.awt.Color hex2rgb(String hex) {
		hex = hex.replace("#", "");
		String r = hex.substring(0, 2);
		String g = hex.substring(2, 4);
		String b = hex.substring(4, 6);
		java.awt.Color color = new java.awt.Color(Integer.parseInt(r, 16), Integer.parseInt(g, 16), Integer.parseInt(b, 16));
		return color;
	}
	
	/**
	 * 获取“大标题”样式实例
	 * @param wb
	 */
	public static XSSFCellStyle getTitleStyle(XSSFWorkbook wb) {
		//大标题-样式
		XSSFCellStyle title_style = CreateExcelPoi.createCellStyle(wb, true, true, false);
		//大标题字体
		Font title_font = CreateExcelPoi.createFontStyle(wb, CreateExcelPoi.FontColorEnum.BLACK, (short)13, true);
		title_style.setFont(title_font);
		return title_style;
	}
	
	/**
	 * 获取"月"样式实例
	 * @param wb
	 */
	public static XSSFCellStyle getMontStyle(XSSFWorkbook wb) {
		//月-样式
		XSSFCellStyle month_style = CreateExcelPoi.createCellStyle(wb, true, true, false);//创建单元格样式
		Font month_font = CreateExcelPoi.createFontStyle(wb, CreateExcelPoi.FontColorEnum.BLACK, (short)13, true);//创建字体
		month_style.setFont(month_font);
		//背景色
		month_style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());
		//month_style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        month_style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return month_style;
	}
	
	/**
	 * 获取“周”样式实例
	 * @param wb
	 */
	public static XSSFCellStyle getWeekStyle(XSSFWorkbook wb) {
		XSSFCellStyle week_style = CreateExcelPoi.createCellStyle(wb, true, true, false);//创建单元格样式
		Font week_font = CreateExcelPoi.createFontStyle(wb, CreateExcelPoi.FontColorEnum.BLACK, (short)9, true);//创建字体
		week_style.setFont(week_font);
		return week_style;
	}
	
	/**
	 * 获取“天”样式实例
	 * @param wb
	 */
	public static XSSFCellStyle getDayStyle(XSSFWorkbook wb) {
		XSSFCellStyle day_style = CreateExcelPoi.createCellStyle(wb, true, true, false);//创建单元格样式
		Font day_font = CreateExcelPoi.createFontStyle(wb, CreateExcelPoi.FontColorEnum.BLACK, (short)11, true);//创建字体
		day_style.setFont(day_font);
		return day_style;
	}
	
	/**
	 * 获取“正文”样式实例
	 * @param wb
	 */
	public static XSSFCellStyle getContentStyle(XSSFWorkbook wb) {
		XSSFCellStyle content_style = CreateExcelPoi.createCellStyle(wb, true, true, true);//创建单元格样式
		Font content_font = CreateExcelPoi.createFontStyle(wb, CreateExcelPoi.FontColorEnum.BLACK, (short)9, false);//创建字体
		content_style.setFont(content_font);
		return content_style;
	}
}








