package book.javaThread.chapter9;

/**
 * java�߳��ڶ�CPU�µĲ��л�
 * @author st-yz2011
 * @since 2015-12-10
 */
public class SinTable {
	private float lookupValues[] = null;
	
	public synchronized float[] getValues() {
		lookupValues = new float[360*100];
		
		for(int i=0; i<(360*100); i++) {
			float sinValue = (float)Math.sin((i%360) * Math.PI/180.0);
			lookupValues[i] = sinValue * i/180.0f;
		}
		return lookupValues;
	}
	
}
