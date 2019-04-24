package book.thinkInJava.chapter6;


public class Beetle extends Insect {
	int m = prt("★   first word...");
	int k = prt("Beetle.k initialized");

	{
		System.out.println("假装构造器");
	}

	Beetle() {
		prt("k = " + k);
		prt("j = " + j);
	}

	static int x2 = prt("static Beetle.x2 initialized");

	static int prt(String s) {
        System.out.println(s);
        return 63;
	}

	public static void main(String[] args) {
		prt("Beetle constructor");
		Beetle b = new Beetle();
	}
} // /:~