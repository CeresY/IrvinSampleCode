package corejava.enumration;

public enum CatalogueTypeEnum {
    
    None("None"),//
    Project("Project"),//项目
    CanvasRoot("CanvasRoot"),//画布根目录
    CanvasCatalog("CanvasCatalog"),//画布目录
    Canvas("Canvas"),//画布
    FileRoot("FileRoot"),//文件资源根目录
    FileCatalog("FileCatalog"),//文件资源目录
    File("File")//文件资源
    ;
    
    // 成员变量
    private String name;

    // 构造方法
    private CatalogueTypeEnum(String name) {
	this.name = name;
    }

    // 普通方法
    public static CatalogueTypeEnum fromValue(String name) {
	for (CatalogueTypeEnum c : CatalogueTypeEnum.values()) {
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
