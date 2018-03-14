package book.headFirst.obsever;

public interface Subject {
	public void add(Observer obj);
	
	public void del(Observer obj);
	
	public void notifyObserver();
	
	public void operator();
}
