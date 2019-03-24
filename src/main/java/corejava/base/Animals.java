package corejava.base;

public class Animals implements DangerousMonster {

	@Override
	public void menace() {
	}

	@Override
	public void destory() {
	}
	
	public static void main(String[] args) {
		Monster m = new Animals();
	}
}
