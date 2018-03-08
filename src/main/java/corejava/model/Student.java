package corejava.model;

import java.io.Serializable;

public class Student implements Serializable{
	public String name;
	public Integer age;
	public String classid;
	public char sex;
	
	public Student(String name, Integer age, String classid, char sex) {
		super();
		this.name = name;
		this.age = age;
		this.classid = classid;
		this.sex = sex;
	}

	public Student() {
	}
	
	public Student(String name) {
		this.name = name;
	}

	/*@Override
	public String toString() {
		return "name: " + this.name + ", age: " + this.age.toString()
			+ ", classid: " + this.classid + ", sex: " + this.sex;
	}*/
	
	@Override
	public String toString() {
		return "name: " + this.name;
	}
	
	
}
