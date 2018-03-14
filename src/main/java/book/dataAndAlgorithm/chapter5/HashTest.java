package book.dataAndAlgorithm.chapter5;

public class HashTest {
	public static void main(String[] args) {
		String key = "alan";
		int tableSize = 1001;
		System.out.println(hash1(key, tableSize));
	}
	public static int hash1(String key, int tableSize) {
		int hashValue =0;
		for(int i=0; i<key.length(); i++) {
			hashValue = 37*hashValue + key.charAt(i);
		}
		System.out.println("hashValue=" + hashValue);
		hashValue %= tableSize;
		if(hashValue < 0) {
			hashValue += tableSize;
		}
		return hashValue;
	}
}
