package corejava.base;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
	public void test() {
		Map map = new HashMap(){
			{
				put("A", "A1");
			}
		};
	}
}
