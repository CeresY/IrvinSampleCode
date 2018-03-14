package book.structuresAndAlgorithm.sort;

import java.util.Random;

/**
 * ��������(�Լ����뷨������ʧ��)
 * @author yangzhan
 *
 */
public class QuickSort {
	static int len = 20;

	public static void main(String[] args) {
		Random rm = new Random();
		int[] stock = new int[len];
		for(int i=0; i<len; i++) {
			stock[i] = rm.nextInt(100);
		}
		
		ptln(stock);
		QuickSort f = new QuickSort();
		int[] tempArray = f.lowAndHight(stock);
		/*f.quick1(stock, 0, len);*/
		System.out.println();
		ptln(tempArray);
	}
	
	/**
	 * �Ե�һ��Ԫ��Ϊ��׼��С����ߣ�����ұ�
	 * @param stock
	 */
	public int[] lowAndHight(int[] stock) {
		int[] tempArray = new int[len];
		int pivot = 0;
		int index = 1;
		while( index < stock.length ){
			System.out.println("pivot=" + pivot + ", index=" + index);
			if(stock[pivot] >= stock[index]) {
				tempArray[pivot] = stock[index];
				tempArray[index] = stock[pivot];
				pivot = index;
			}
			index++;
		} 
		return tempArray;
	}
	
	/**
	  
	 */
	public void quick1(int[] stock, int start_index, int countEle){
		if(start_index >=  countEle - 1) {
			return;
		}
		int index_pivot = start_index;
		for(int i=1; i<countEle;  i++) {
			index_pivot = swap(stock, index_pivot, start_index + i);
		}
		quick1(stock, start_index, index_pivot + 1);
		quick1(stock, index_pivot+1, countEle - index_pivot - 1);
	}
	
	public static int swap(int[] stock, int index_pivot, int index) {
		if(stock[index_pivot] > stock[index]) {
			int temp = stock[index_pivot];
			stock[index_pivot] = stock[index];
			stock[index] = temp;
			return index;
		} else if(stock[index_pivot] == stock[index]) {
			return index;
		} 
		else {
			return index_pivot;
		}
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
