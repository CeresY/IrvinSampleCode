package corejava.keyWord;

public final class FinalClass {
	private final String name;
	private int age;
	private AbleClass able;
	
	public FinalClass(String name, int age, AbleClass able) {
		this.name = name;
		this.age = age;
		this.able = able;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public AbleClass getAble() {
		return able;
	}
	
}
