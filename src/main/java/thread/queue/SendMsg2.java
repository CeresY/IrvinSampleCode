package thread.queue;

import java.util.Vector;

/**
 * ����һ��ʧ�ܵİ�����ȱ��ͬ��<br/>
 * ��java thread�� --- Scott Oaks �ǳ��ʺ϶��߳�����
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
			//���ӡ�ͬ���顱�ĺ����Vectorͬһ��Ԫ�ر���ӡ��Σ����ֻ��ʷǳ��ߣ���
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
		//����N���߳�
		for(int k=0; k<7; k++) {
			new Thread(t).start();
		}
	}

}
