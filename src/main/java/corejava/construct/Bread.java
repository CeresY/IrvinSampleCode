package corejava.construct;

public class Bread {
	
	static int i=0;
	
	static{
        System.out.println("Bread is loaded: " + i++);
    }
    public Bread() {
        System.out.println("bread:" + i++);
    }
    
    public Bread(int num) {
        System.out.println("bread param:" + num);
    }
}
