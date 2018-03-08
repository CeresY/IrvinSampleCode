package book.algorithm.sort;
/**
 * ����һ�����飬�ṩ����ĳ�ʼ������ֵ��չʾ�Ȳ���
 * @author st-yz2011
 *
 */
public class ArrayLib {
	private long[] theArray;//��ʼ������
	private int nEle;//Ԫ�ظ���
	
	public ArrayLib(int n) {
		theArray = new long[n];
		nEle = 0;//һ��������
	}
	
	/**
	 * ����Ԫ��
	 */
	public void insert(long value) {
		theArray[nEle] = value;
		nEle++;
	}
	
	/**
	 * չʾ
	 */
	public void display() {
		for(int i=0; i<nEle; i++) {
			System.out.print(theArray[i] + " ");
		}
		System.out.println();
	}
	
	public void quickSort() {
		recQuickSort(0, nEle-1);
	}
	
	/**
	 * ���ŷ���:�����ұߵ�����Ϊpivot��С�ķ�����ߣ���ķ����ұ�
	 * @param left ���������
	 * @param right ���ұ�����
	 */
	private void recQuickSort(int left, int right) {
		if(left >= right) {
			return;
		} else {
			long pivot = theArray[right];
			int lefter = left - 1;//������߿�ʼ��pivot�Ƚ�
			int righter = right; //�����ұ߿�ʼ��pivot�Ƚ�(��Ϊpivot�����ҵ�ֵ,����Ӧ���ǵ����ڶ���Ԫ��)
			while(true) {
				while(theArray[++lefter] < pivot ) {//++lefter��0
					//
				}
			}
		}
	}
	
	/**
	 * �������������ֵ
	 */
	private void swap(int dex1, int dex2) {
		long temp = theArray[dex1];
		theArray[dex1] = theArray[dex2];
		theArray[dex2] = temp;
	} 
}
