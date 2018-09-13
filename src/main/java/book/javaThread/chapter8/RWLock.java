package book.javaThread.chapter8;

import java.util.Enumeration;
import java.util.Vector;

/**
 * “读写锁”实现
 * @author st-yz2011
 *
 */
public class RWLock {
	/**
	 * 队列模型
	 * top [0] [1] [] []... ...[N] [N+1] last<br>
	 * Vector是一个矢量数组，新元素是在尾部(last)添加
	 */
	private Vector waiters;
	
	/**
	 * 找到首次出现"读锁"的索引处
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
	 * 查询线程在队列的索引处
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
		 * 队列如下：
		 * top [] [] [写] ... ... [] [读] last 
		 * 说明：“读”的请求被挂起(因为“写”请求先到)，要先执行完“写”请求，避免产生竞态条件
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
		 * 队列如下：
		 * top [] [] [写] ... ... [] last 
		 * 说明："写"请求不是队列top[0]元素就要被挂起，要先执行之前的“读”请求。
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
		//请求执行完毕，从队列删除
		if(node.nAcquires == 0) {
			waiters.removeElementAt(index);
			notifyAll();
		}
	}
}








