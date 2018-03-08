package corejava.construct;

public class Meal {
	public Meal() {
        System.out.println("meal");
    }
     
    Bread bread = new Bread();
    
    static {
    	System.out.println("static meal");
    }
    
    Bread bread2 = new Bread(10);
    
    Bread bread3 = new Bread(11);
}
