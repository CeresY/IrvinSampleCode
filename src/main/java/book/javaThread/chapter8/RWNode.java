package book.javaThread.chapter8;
/**
 * 读写锁节点
 * @author st-yz2011
 *
 */
public class RWNode {
	static final int READER = 0;
	static final int WRITER = 1;
	/**
	 * 请求线程
	 */
	Thread t;
	/**
	 * 请求状态
	 */
	int state;
	/**
	 * 请求次数
	 */
	int nAcquires;
	
	RWNode(Thread t, int state) {
		this.t = t;
		this.state = state;
		nAcquires = 0;
	}
}
