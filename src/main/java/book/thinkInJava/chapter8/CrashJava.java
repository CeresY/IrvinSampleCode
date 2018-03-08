package book.thinkInJava.chapter8;

import java.util.BitSet;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;

public class CrashJava {

	public static void main(String[] args) {
		CrashJava f = new CrashJava();
		f.stacks();
		//printAds();
	}

	@Override
	public String toString() {
		//return "CrashJava adrress " + this + "\n";
		return super.toString();
	}
	
	public static void printAds() {
		Vector v = new Vector();
		for(int i=0; i<10; i++) {
			v.addElement(new CrashJava());
		}
		System.out.println(v);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public static void bits() {
		Random r = new Random();
		BitSet bs = new BitSet();
		for(int i=7; i>=0; i--) {
			//if(((1<<i)&bs) {}
		}
	}
	
	public void stacks() {
		String[] months = {
			"1", "2", "3", "4",
			"5", "6", "7", "8", 
			"9", "10","11","12"
		};
		
		Stack stk = new Stack();
		for(int i=0; i<months.length; i++) {
			stk.push(months[i]+"");
		}
		System.out.println("stk = " + stk);
		stk.addElement("the last line");
		System.out.println("element 5 = " + stk.elementAt(5));
		System.out.println("-->popping elements: ");
		while(!stk.isEmpty()) {
			System.out.println(stk.pop());
		}
	}

}
