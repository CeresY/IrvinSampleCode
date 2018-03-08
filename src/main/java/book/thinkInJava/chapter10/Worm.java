package book.thinkInJava.chapter10;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Worm implements Serializable {
	private static int r() {
		return (int)(Math.random() * 10);
	}
	
	private Data[] d = {
		new Data(r()), new Data(r()), new Data(r())
	};
	
	private Worm next;
	private char c;
	
	Worm(int i, char x) {
		System.out.println(" Worm constructor: " + i);
		c = x;
		//System.out.println("Worm.c = " + c);
		if(--i > 0) {
			next = new Worm(i, (char)(x+1));
		}
	}
	
	Worm() {
		System.out.println("Default constructor");
	}

	@Override
	public String toString() {
		String s = ":-" + c + "(";
		//System.out.println("Worm.toString.d.length = " + d.length);
		for(int i=0; i<d.length; i++) {
			s += d[i].toString()+"|";
		}
		s += ")";
		if(next != null) {
			s += next.toString();
		}
		return s;
	}
	
	public static void main(String[] args) {
		Worm w = new Worm(6, 'a');
		System.out.println("(1) w = " + w);
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("worm.out"));
			out.writeObject("Worm storage");
			out.writeObject(w);
			out.close();
			
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream("worm.out"));
			String s = (String)in.readObject();
			Worm w2 = (Worm)in.readObject();
			System.out.println("(2) " + s + ", w2 = " + w2);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("----------------------");
		try{
			ByteArrayOutputStream bout = 
				new ByteArrayOutputStream();
			ObjectOutputStream out = 
				new ObjectOutputStream(bout);
			out.writeObject("Worm storage");
			out.writeObject(w);
			out.flush();
			
			ObjectInputStream in = new ObjectInputStream(
					new ByteArrayInputStream(
							bout.toByteArray()));
			String s = (String)in.readObject();
			Worm w3 = (Worm)in.readObject();
			System.out.println(s + ", w3 = " + w3);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
