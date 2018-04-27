package corejava.enumration;

public enum MetaDataTypeEnum {

    Dimension("维度"), Measure("度量");

    private String name;

    private MetaDataTypeEnum(String name) {
	this.name = name;
    }

    // 普通方法
    public static MetaDataTypeEnum fromValue(String name) {
	for (MetaDataTypeEnum c : MetaDataTypeEnum.values()) {
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
