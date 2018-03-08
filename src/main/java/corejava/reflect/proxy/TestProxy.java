package corejava.reflect.proxy;

public class TestProxy {
	public static void main(String[] args) {
		TestProxy foo =  new TestProxy();
		foo.test1();
	}
	
	public void test1() {
		Player play = new FootballPlayer();
		Player proxyPlay = (Player) MyProxyFactory.getProxy(play);
		proxyPlay.work();
	}
}

