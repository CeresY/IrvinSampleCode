package thread.lock;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class AsyncReadSocket extends Thread {
	private Socket s;
	private StringBuffer result;
	
	public AsyncReadSocket(Socket s) {
		this.s = s;
		this.result = new StringBuffer();
	}

	@Override
	public void run() {
		DataInputStream is = null;
		try {
			is = new DataInputStream(s.getInputStream());
		} catch (IOException e) {}
		while(true) {
			try {
				char c = is.readChar();
				appendResult(c);
			} catch (IOException e) {}
			
		}
	}

	public synchronized String getResult() {
		String retval = result.toString();
		result = new StringBuffer();
		return retval;
	}
	
	public synchronized void appendResult(char c) {
		result.append(c);
	}
}
