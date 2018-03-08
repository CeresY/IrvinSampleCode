package book.thinkInJava.chapter8;

import java.util.Comparator;

public class AlphaComp implements Comparator {
	public int compare(Object o1, Object o2) {
		// Assume it's used only for Strings...
		String s1 = ((String)o1).toLowerCase();
		String s2 = ((String)o2).toLowerCase();
		return s1.compareTo(s2);
	}
}
