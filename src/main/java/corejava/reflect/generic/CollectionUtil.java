package corejava.reflect.generic;

import java.util.Collection;

public class CollectionUtil {
	public static <T> void copy(Collection<T> dest, Collection<? extends T> src) {
		for(T t : src) {
			dest.add(t);
		}
	}
	
	public static <T> T copyT(Collection<? super T> dest, Collection<T> src) {
		T last = null;
		for(T t : src) {
			last = t;
			dest.add(t);
		}
		return last;
	}
}
