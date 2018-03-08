package thread.queue;

import java.util.Vector;

public class SendMsg implements Runnable {
	private Vector<Msg> vectors;
	private static int SIZE = 50;
	
	public SendMsg(Vector<Msg> msgs) {
		vectors = msgs;
	}
	
	class Msg {
		int id;
		String text;
		
		public Msg(int id, String msg) {
			this.id = id;
			this.text = msg;
		}
	}
	
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			synchronized(this) {
				this.notifyAll();
				if(!vectors.isEmpty()) {
					Msg msg = vectors.firstElement();
					System.out.println(Thread.currentThread().getName() + " id=" + msg.id + ", text=" + msg.text);
					vectors.remove(msg);
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					Thread.currentThread().interrupt();
				}
				
			}
		}
	}
	
	public void process(int threadNum) {
		for(int k=0; k<threadNum; k++) {
			//new Thread(t).start();
		}
	}
	
	public static void main(String[] args) {
		Vector<Msg> msgs = new Vector<Msg>();
		SendMsg t = new SendMsg(msgs);
		for(int i=0; i<SIZE; i++) {
			Msg m = t.new Msg(i, "MSG-" + i);
			msgs.addElement(m);
		}
		
		for(int k=0; k<3; k++) {
			new Thread(t).start();
		}
	}

}
