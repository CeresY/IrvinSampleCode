package thread.producerCustomer.pc2;

public class Counter {
    public static int iComputerCount;
    private boolean flag = true;

    public synchronized void productComputer(int i) {
 //��while(!flag)���ã�ֻ��ֻ��2���߳�����if�ǿ��Եģ�����ж���߳�if�ǲ����Ե�
        if (!flag) {
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(iComputerCount + "product one Computer!-" + i);
        iComputerCount++;
        flag = false;
        notify();

    }

    public synchronized void consumeComputer(int i) {
  //��while(flag)���ã�ֻ��ֻ��2���߳�����if�ǿ��Եģ�����ж���߳�if�ǲ����Ե�
        if (flag) {
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(iComputerCount + "consume one Computer!-" + i);
        flag = true;
        notify();

    }
}
