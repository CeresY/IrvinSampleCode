package web.spring.demo1;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class HelloWord {
	private List<String> msg = null;
	private Set<String> container = null;
	private Map container1 = null;

	public List<String> getMsg() {
		return msg;
	}

	public void setMsg(List<String> msg) {
		this.msg = msg;
	}

	public Set<String> getContainer() {
		return container;
	}

	public void setContainer(Set<String> container) {
		this.container = container;
	}

	public Map getContainer1() {
		return container1;
	}

	public void setContainer1(Map container1) {
		this.container1 = container1;
	}
	
}
