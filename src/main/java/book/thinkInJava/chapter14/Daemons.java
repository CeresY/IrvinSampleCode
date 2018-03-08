package book.thinkInJava.chapter14;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Daemons {
	public static void main(String[] args) {
		Thread d = new Daemon();
		System.out.println("Daemon isDaemon " + d.isDaemon());
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("waiting for CR");
		try {
			//stdin.readLine();
			d.sleep(1000);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
