package office;

import corejava.enumration.DataTypeEnum;

/** excel表头信息
  * @author yangzhan
  * 2018年4月27日
  */
public class TableHead {
	private String name; // 表头名称
	private String code; // 表头编码
	private DataTypeEnum type; // 表头数据类型
	private Integer width; // 列宽 (-1为默认宽度)
	
	public TableHead() {
		super();
	}
	public TableHead(String name, String code, DataTypeEnum type, Integer width) {
		super();
		this.name = name;
		this.code = code;
		this.type = type;
		this.width = width;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public DataTypeEnum getType() {
		return type;
	}
	public void setType(DataTypeEnum type) {
		this.type = type;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
}
