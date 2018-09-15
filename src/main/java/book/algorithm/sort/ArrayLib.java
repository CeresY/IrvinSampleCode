package book.algorithm.sort;
/**
 * 生成一个数组，提供数组的初始化、赋值、展示等操作
 * @author st-yz2011
 *
 */
public class ArrayLib {
	private long[] theArray;//初始化数组
	private int nEle;//元素个数

	public ArrayLib(int n) {
		theArray = new long[n];
		nEle = 0;//一个空数组
	}

	/**
	 * 插入元素
	 */
	public void insert(long value) {
		theArray[nEle] = value;
		nEle++;
	}

	/**
	 * 展示
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
	 * 快排方法:以最右边的坐标为pivot，小的放在左边，大的放在右边
	 * @param left 最左边坐标
	 * @param right 最右边坐标
	 */
	private void recQuickSort(int left, int right) {
		if(left >= right) {
			return;
		} else {
			long pivot = theArray[right];
			int lefter = left - 1;//从最左边开始向pivot比较
			int righter = right; //从最右边开始向pivot比较(因为pivot是最右的值,所以应该是倒数第二个元素)
			while(true) {
				while(theArray[++lefter] < pivot ) {//++lefter归0
					//
				}
			}
		}
	}

	/**
	 * 交换两个坐标的值
	 */
	private void swap(int dex1, int dex2) {
		long temp = theArray[dex1];
		theArray[dex1] = theArray[dex2];
		theArray[dex2] = temp;
	}
}
