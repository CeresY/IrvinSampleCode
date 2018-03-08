package book.thinkInJava.chapter7.construct;

import book.thinkInJava.chapter7.Egg2;

class RoundGlyph extends Glyph {
	int radius = 1;
	Egg2 e = new Egg2();

	RoundGlyph(int r) {
		radius = r;
		System.out.println("RoundGlyph.RoundGlyph(), radius = " + radius);
	}

	void draw() {
		System.out.println("RoundGlyph.draw(), radius = " + radius);
	}
}

