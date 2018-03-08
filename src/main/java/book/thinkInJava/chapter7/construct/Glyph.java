package book.thinkInJava.chapter7.construct;

abstract class Glyph {
	abstract void draw();

	Glyph() {
		System.out.println("Glyph() before draw()");
		draw();
		System.out.println("Glyph() after draw()");
	}
}
