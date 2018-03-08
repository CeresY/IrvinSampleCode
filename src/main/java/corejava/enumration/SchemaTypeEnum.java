package corejava.enumration;
/**
 * 指标类型
 * @author yangzhan-xps13
 * 2017年8月31日
 */
public enum SchemaTypeEnum {
	NUMBER("number"), // 数值
	DATE("date"), // 日期
	TEXT("text"), // 文本
	VARCHAR("varchar"); // 文本

	// 成员变量
	private String name;

	// 构造方法
	private SchemaTypeEnum(String name) {
		this.name = name;
	}

	// 普通方法
	public static SchemaTypeEnum fromValue(String name) {
		for (SchemaTypeEnum c : SchemaTypeEnum.values()) {
			if (c.getName().equals(name)) {
				return c;
			}
		}
		throw new IllegalArgumentException(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}


