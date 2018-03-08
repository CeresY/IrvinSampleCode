package corejava.construct;

import corejava.base.Father;

public class Man extends Father {

	public Man(String name) {
		super(name);
	}
	
	public static void main(String[] args) {
		Man perty = new Man("perty");
		System.out.println(perty.info);
		perty.say();
		Father f = new Father("i`m Father");
		f.say();
	}
	
	public void say() {
		System.out.println("Child say: ");
	}

}
