package corejava.keyWord;

public class NoPerson {
	private final NoName name;

	public NoPerson(NoName name) {
//		this.name = name;
		this.name = new NoName(name.getName1(), name.getName2());
	}

	public NoName getName() {
//		return name;
		return new NoName(name.getName1(), name.getName2());
	}
	
	public static void main(String[] args) {
		NoName name = new NoName("N1", "N2");
		NoPerson p = new NoPerson(name);
		System.out.println("name1: " + p.getName().getName1());
		name.setName1("chang1");
		System.out.println("name1: " + p.getName().getName1());
		new Integer(0);
	}
}
