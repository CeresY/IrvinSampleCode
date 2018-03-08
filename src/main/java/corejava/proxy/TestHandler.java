package corejava.proxy;

public class TestHandler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LogProxy proxy = new LogProxy();
		IHandler handler = (AroundHandler) proxy.bind(new AroundHandler());
		handler.doSth();
	}

}
