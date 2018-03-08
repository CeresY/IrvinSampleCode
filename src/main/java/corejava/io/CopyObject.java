package corejava.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import corejava.model.Student;

/**
 * 通过IO来复制对象
 * @author st-yz2011
 *
 */
public class CopyObject {
	
	public static void main(String[] args) {
		copy();
	}
	
	public static void copy() {
		Student s = new Student("Macle", 35, "C001", '1');
		try{
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(byteOut);
			out.writeObject(s);
			
			ObjectInputStream in = new ObjectInputStream(
					new ByteArrayInputStream(byteOut.toByteArray()));
			Student sCopy = (Student)in.readObject();
			sCopy.name = "Cher";
			
			System.out.println(s.toString());
			System.out.println(sCopy.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
