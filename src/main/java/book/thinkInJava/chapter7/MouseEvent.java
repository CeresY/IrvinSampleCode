package book.thinkInJava.chapter7;

public class MouseEvent extends Event {

	public MouseEvent(long eventTime) {
		super(eventTime);
	}

	@Override
	public void action() {
		
	}

	@Override
	public String description() {
		return null;
	}

	public static void main(String[] args) {
		Event e = new MouseEvent(1457222);
		boolean b = e.ready();
		System.out.println(b);
	}
}
