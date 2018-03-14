package thread;

public class YieldTest2 implements Runnable{
	public void run() {  
        for (int i = 1; i <= 8; i++) {  
            System.out.println(Thread.currentThread().getName() + ": " + i);  
            // ��ͣ��ǰ����ִ�е��̶߳��󣬲�ִ�������̣߳����ǽ������״̬  
            Thread.yield();  
            // ���ܻ���ִ�� ���̣߳�  
        }  
    }  
  
    public static void main(String[] args) {  
    	YieldTest2 runn = new YieldTest2();  
        Thread t1 = new Thread(runn);  
        Thread t2 = new Thread(runn);  
        Thread t3 = new Thread(runn);  
          
        t2.setPriority(t2.getPriority()+1);  
        t1.start();  
        t2.start();  
        t3.start();  
  
    }  
}
