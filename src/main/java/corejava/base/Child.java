package corejava.base;

public class Child extends Father {
	private String sonNmae;
	
	
	public Child(String name) {
		super("father name");
		this.sonNmae = name;
	}

	@Override
	public void say() {
		System.out.println("Child say: " + this.sonNmae);
		super.say();
	}

	public static void main(String[] args) {
		Child fo = new Child("son name");
		fo.say();
	}
}
