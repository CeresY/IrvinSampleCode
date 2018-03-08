package book.javaThread.chapter8;

import java.util.Enumeration;
import java.util.Vector;

/**
 * ����д����ʵ��
 * @author st-yz2011
 *
 */
public class RWLock {
	/**
	 * ����ģ��
	 * top [0] [1] [] []... ...[N] [N+1] last<br>
	 * Vector��һ��ʸ�����飬��Ԫ������β��(last)���
	 */
	private Vector waiters;
	
	/**
	 * �ҵ��״γ���"����"��������
	 * @return
	 */
	private int firstWriter() {
		Enumeration e;
		int index;
		for(index = 0, e = waiters.elements(); e.hasMoreElements(); index++) {
			RWNode node = (RWNode)e.nextElement();
			if(node.state == RWNode.WRITER) {
				return index;
			}
		}
		return Integer.MAX_VALUE;
	}
	
	/**
	 * ��ѯ�߳��ڶ��е�������
	 * @param t
	 * @return
	 */
	private int getIndex(Thread t) {
		Enumeration e;
		int index;
		for(index = 0, e = waiters.elements(); e.hasMoreElements(); index++) {
			RWNode node = (RWNode)e.nextElement();
			if(node.t == t) {
				return index;
			}
		}
		return -1;
	}
	
	public RWLock() {
		waiters = new Vector();
	}
	
	public synchronized void lockRead() {
		RWNode node;
		Thread me = Thread.currentThread();
		int index = getIndex(me);
		if(index == -1) {
			node = new RWNode(me, RWNode.READER);
			waiters.addElement(node);
		} else {
			node = (RWNode) waiters.elementAt(index);
		}
		/**
		 * �������£�
		 * top [] [] [д] ... ... [] [��] last 
		 * ˵���������������󱻹���(��Ϊ��д�������ȵ�)��Ҫ��ִ���ꡰд�����󣬱��������̬����
		 */
		while(getIndex(me) > firstWriter()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		node.nAcquires++;
	}
	
	public synchronized void lockWrite() {
		RWNode node;
		Thread me = Thread.currentThread();
		int index = getIndex(me);
		if(index == -1) {
			node = new RWNode(me, RWNode.WRITER);
			waiters.addElement(node);
		} else {
			node = (RWNode) waiters.elementAt(index);
			if(node.state == RWNode.READER) {
				throw new IllegalArgumentException("upgrade lock");
			}
			node.state = RWNode.WRITER;
		}
		/**
		 * �������£�
		 * top [] [] [д] ... ... [] last 
		 * ˵����"д"�����Ƕ���top[0]Ԫ�ؾ�Ҫ������Ҫ��ִ��֮ǰ�ġ���������
		 */
		while(getIndex(me) != 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		node.nAcquires++;
	}
	
	public synchronized void unlock() {
		RWNode node;
		int index;
		Thread me = Thread.currentThread();
		index = this.getIndex(me);
		if(index > this.firstWriter()) {
			throw new IllegalArgumentException("lock not held");
		}
		node = (RWNode)waiters.elementAt(index);
		node.nAcquires--;
		//����ִ����ϣ��Ӷ���ɾ��
		if(node.nAcquires == 0) {
			waiters.removeElementAt(index);
			notifyAll();
		}
	}
}








