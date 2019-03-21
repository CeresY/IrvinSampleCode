package util.db.autoCode;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
/**
 * 小小的扩展了BufferedWriter类
 * @author st-yz2011, 2016-3-4
 *
 */
public class BufferedWriterComponent extends BufferedWriter {

	public BufferedWriterComponent(Writer writer) {
		super(writer);
	}

	public void write(String content, boolean input) throws IOException {
		if(input) {
			super.write(content);
		} else {
			return;
		}
	}
}
