package corejava.enumration;

public enum ColumnTypeEnum {

    Default("原始"), VBI("内置字段"), Custom("自定义"), DynmiCal("动态计算");

    private String name;

    private ColumnTypeEnum(String name) {
	this.name = name;
    }

    // 普通方法
    public static ColumnTypeEnum fromValue(String name) {
	for (ColumnTypeEnum c : ColumnTypeEnum.values()) {
	    if (c.name().equals(name)) {
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
