package thread.queue;

import java.util.Vector;

/**
 * 这是一个失败的案例。缺少同步<br/>
 * 《java thread》 --- Scott Oaks 非常适合多线程入门
 * @author st-yz2011
 *
 */
public class SendMsg2 implements Runnable {
	private Vector<Msg> vectors;
	private static int SIZE = 50;
	
	public SendMsg2(Vector<Msg> msgs) {
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
			//不加“同步块”的后果：Vector同一个元素被打印多次（出现机率非常高）。
			if(!vectors.isEmpty()) {
				Msg msg = vectors.firstElement();
				System.out.println(Thread.currentThread().getName() + " id=" + msg.id + ", text=" + msg.text);
				vectors.remove(msg);
			} else {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public static void main(String[] args) {
		Vector<Msg> msgs = new Vector<Msg>();
		SendMsg2 t = new SendMsg2(msgs);
		for(int i=0; i<SIZE; i++) {
			Msg m = t.new Msg(i, "MSG-" + i);
			msgs.addElement(m);
		}
		//启动N个线程
		for(int k=0; k<7; k++) {
			new Thread(t).start();
		}
	}

}
