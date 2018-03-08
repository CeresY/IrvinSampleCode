package core;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestArray {
	@Test
	public void arrayToList() {
		String[] array = {"229.7","228.9", "228.8"};
		List<String> list = Arrays.asList(array);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
