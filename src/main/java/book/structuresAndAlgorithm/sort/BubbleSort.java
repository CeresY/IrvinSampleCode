package book.structuresAndAlgorithm.sort;

import java.util.Random;

/**
 * ð������
 * @author yangzhan
 *
 */
public class BubbleSort {

	public static void main(String[] args) {
		BubbleSort foo = new BubbleSort();
		foo.bubble1();
	}
	
	/**
	 * ���ѭ��ֻ��Ҫn-1�Σ���Ϊð��ѭ��������2��Ԫ�ؼ��໥�Ƚϣ�Ȼ������Ҳ��������С�������������<br>
	 * ��Ԫ�ط�����������´�ѭ���Ϳ��Ժ������һ�����Դ�����ÿһ�����ѭ���ͻ����һ�������
	 */
	public void bubble1(){
		int len = 20;
		Random rm = new Random();
		int[] stock = new int[len];
		for(int i=0; i<len; i++) {
			stock[i] = rm.nextInt(100);
		}
		
		ptln(stock);
		int count = 0;
		for(int j=0; j<len-1; j++) {
			for(int k=0; k<len-j-1; k++) {
				count++;
				if(stock[k] > stock[k+1]) {
					int temp = stock[k];
					stock[k] = stock[k+1];
					stock[k+1] = temp;
				}
			}
		}
		System.out.println();
		ptln(stock);
		System.out.println("\n����" + count);
		
	}
	
	public static void ptln(int[] objs) {
		for(int i=0; i<objs.length; i++) {
			if(i % 10 == 0) {
				System.out.println();
			}
			System.out.print(objs[i] + " ");
		}
	}

}
