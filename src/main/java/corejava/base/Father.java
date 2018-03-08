package corejava.base;

public class Father {
	private String name;
	
	public String info;
	
	public Father(String name) {
		this.name = name;
		this.info = name;
		System.out.println("Father construct");
	}

	public void say() {
		System.out.println("Father say: " + this.name);
	}
}
