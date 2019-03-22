package corejava.loop;

import org.junit.Test;

/**
 * LabelExample 类将随机生成一个二维数组，
 * 数组每一行中的数据都是从小到在的顺序排列，但各行间并没有排序。
 * 同时，LabelExample 还会从生成的二维数组里随机找一个数作为要查找的数据。
 * 随后在 search 方法中使用带标签的 break 和 continue 语句来优化查找代码。
 */
public class LabelExample {

    /**
     * 主程序。
     */
    public static void main(String[] args) {
        LabelExample test = new LabelExample(3, 5);
        test.printMatrix();
        System.out.println();
        test.search();
    }

    int row; // 二维数组的行数

    int col; // 二维数组每行的数据个数

    int[][] data; // 数组数据

    int lookfor; // 要在数组中查找的数

    /**
     * 构造函数，生成一个由 row 指定行数，由 col 指定列数的数组。
     */
    public LabelExample(int row, int col) {
        this.row = row;
        this.col = col;
        createMatrix();
    }

    /**
     * 打印数组内容。
     */
    public void printMatrix() {
        System.out.println("row = " + row + ", col = " + col + ", lookfor = "
                + lookfor);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(" " + data[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 演示查找过程，使用带标签的 break 和 continue 语句。
     */
    public void search() {
        //loop1:
        // 若在此处定义标签，由于不是其后紧跟循环语句，所以会被勿略掉。
        // 被忽略掉的标签，如果在 break 或者 continue 语句中用到，编译时不能通过。
        // 如果没有在 break 或者 continue 中用到则编译可以成功。
        System.out.println("--- Begin Searching ---");
        loop1: for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (data[i][j] > lookfor) {
                    System.out.println("--- JUMP ---");
                    continue loop1; // 想想此处用 break，结果会有什么变化
                }
                if (data[i][j] == lookfor) {
                    System.out.println("FOUND: data[" + i + "][" + j + "] = "
                            + lookfor);
                    break loop1; // 想想此处用 return，结果会有什么变化
                }
                System.out
                        .println("data[" + i + "][" + j + "] = " + data[i][j]);
            }
            System.out.println("--- LOOP2END ---");
        }
        System.out.println("--- End Searching ---");
    }

    /**
     * 生成随机数组和随机抽取要查找的数。
     */
    private void createMatrix() {
        data = new int[row][];
        for (int i = 0; i < row; i++) {
            data[i] = new int[col];
            int t = 0;
            for (int j = 0; j < col; j++) {
                t += (int) (Math.random() * 20);
                data[i][j] = t;
            }
        }
        lookfor = data[(int) (Math.random() * row)][(int) (Math.random() * col)];
    }

    @Test
    public void testContinue() {
    	int[][] arr = {
    			{1,2,3,4},
    			{11,22,33,44},
    			{111,222,333,444}
    	};
    	for(int i=0; i<arr.length; i++) {
    		int[] tmp = arr[i];
    		for(int j=0; j<tmp.length; j++) {
    			System.out.println(tmp[j]);
    		}
    	}
    }
}