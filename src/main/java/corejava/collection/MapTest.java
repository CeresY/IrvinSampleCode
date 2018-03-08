package corejava.collection;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class MapTest {
	@Test
	public void getNoExsisKey() {
		Map<String, String> map = new HashMap<String, String>();
		System.out.println(map.get("key"));
	}
}
