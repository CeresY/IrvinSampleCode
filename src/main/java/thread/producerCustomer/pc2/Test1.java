package thread.producerCustomer.pc2;

public class Test1 {
    public static void main(String[] args) {
        Counter computer = new Counter();
        Thread p = new Thread(new Producter(computer));
        p.start();
        Thread c = new Thread(new Consumer(computer));
        c.start();
        
        /*
         * ��δ����������Ƿ���ǵģ�������sleep(1000).��˼�ǣ��ܲ�����������������1000ms���ڣ�
         * �������ǲ�һ���� 
         */
       /* while (!(p.getState() == Thread.State.TERMINATED && c.getState() == Thread.State.TERMINATED)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        try{
        	p.join();
        	c.join();
        } catch(InterruptedException e) {
        	e.printStackTrace();
        }
        System.out.print(computer.iComputerCount);
    }
}
