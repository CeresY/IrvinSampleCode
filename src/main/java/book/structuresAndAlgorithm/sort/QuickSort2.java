package book.structuresAndAlgorithm.sort;

/**
 * �������򣨡����ݽṹ�� �㷨�ڶ��桷������p252��
 * @author yangzhan
 *
 */
public class QuickSort2 {
	private long[] theArray;
	private int nEles;
	private static int count;//�Ƚϴ���

	public QuickSort2(int maxSize) {
		theArray = new long[maxSize];
		nEles = 0;
	}
	
	public void insert(long value) {
		theArray[nEles] = value;
		nEles++;
	}
	
	public void display() {
		for(int i=0; i<nEles; i++) {
			System.out.print(theArray[i] + " ");
		}
		System.out.println("");
	}
	
	public void quickMain() {
		recQuickSort(0, nEles - 1);
	}
	
	/**
	 * С�����󣬴�����ҡ�����ͬʱ�Ƚ�����
	 * @param left
	 * @param right
	 */
	private void recQuickSort(int left, int right) {
		if(left >= right) {
			return;
		}
		
		//pivotһ����lastλ�õ�Ԫ��
		long pivot = theArray[right];
		int leftPtr = left - 1;//��ʼ����ʱ��leftPtr = -1
		int rightPtr = right;//��ʼ����ʱ��rightPtr = last;
		
		while(true) {
			while(theArray[++leftPtr] < pivot) {//��ʼ����ʱ���leftPrt = 0λ�ÿ�ʼ
				//nothing;
				count++;
			}
			while(rightPtr > 0 && theArray[--rightPtr] > pivot) {//��ʼ����ʱ���rightPtr = last - 1λ�ÿ�ʼ
				//nothing
				count++;
			}
			if(leftPtr >= rightPtr) {
				break;
			} else {
				swap(leftPtr, rightPtr);
			}
		}//end while
		
		swap(leftPtr, right);
		
		recQuickSort(left, leftPtr - 1);
		recQuickSort(leftPtr + 1, right);
	}
	
	private void swap(int dex1, int dex2) {
		long temp = theArray[dex1];
		theArray[dex1] = theArray[dex2];
		theArray[dex2] = temp;
	}
	
	public static void main(String[] args) {
		int maxSize = 20;
		QuickSort2 arr = new QuickSort2(maxSize);
		for(int i=0; i<maxSize; i++) {
			long value = (int)(Math.random() * 99);
			arr.insert(value);
		}
		arr.display();
		arr.quickMain();
		arr.display();
		System.out.println("count=" + count);
	}
}
