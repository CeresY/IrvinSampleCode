package office;

import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;

import corejava.enumration.DataTypeEnum;


/**
  * @author yangzhan
  * 2018年4月27日
  */
public class CellBean {
	private Integer cellType = Cell.CELL_TYPE_BLANK; //单元格类型
	private DataTypeEnum type; // 单元格数据类型
	private String valStr; // 【单元格数据：字符】
	private Double valDouble; // 【单元格数据：浮点】
	private Integer valInt; // 【单元格数据：整形】
	private Date valDate; // 【单元格数据：日期】
	
	public Integer getCellType() {
		return cellType;
	}
	public void setCellType(Integer cellType) {
		this.cellType = cellType;
	}
	public DataTypeEnum getType() {
		return type;
	}
	public void setType(DataTypeEnum type) {
		this.type = type;
	}
	public String getValStr() {
		return valStr;
	}
	public void setValStr(String valStr) {
		this.valStr = valStr;
	}
	public Double getValDouble() {
		return valDouble;
	}
	public void setValDouble(Double valDouble) {
		this.valDouble = valDouble;
	}
	public Integer getValInt() {
		return valInt;
	}
	public void setValInt(Integer valInt) {
		this.valInt = valInt;
	}
	public Date getValDate() {
		return valDate;
	}
	public void setValDate(Date valDate) {
		this.valDate = valDate;
	}
	
	
}
