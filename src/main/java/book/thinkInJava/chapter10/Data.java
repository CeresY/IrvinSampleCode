package book.thinkInJava.chapter10;

import java.io.Serializable;

public class Data implements Serializable {
	private int i;
	
	Data(int x) {
		i = x;
	}
	
	@Override
	public String toString() {
		return Integer.toString(i);
	}
}
