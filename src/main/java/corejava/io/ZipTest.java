package corejava.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.Test;


public class ZipTest {

	@Test
	private void toZip() {
		ZipOutputStream zos = null;
		BufferedInputStream bis = null;
		try {
			// 目标路径
			String fileName = "";
			File zipFile = new File(fileName + ".zip");
			
			// 写入目标目录
			FileOutputStream fos = new FileOutputStream(zipFile);
			zos = new ZipOutputStream(new BufferedOutputStream(fos)); 
			
			// 创建ZIP实体，并添加进压缩包
			ZipEntry zipEntry = new ZipEntry(fileName);
			zos.putNextEntry(zipEntry);
			// 读取待压缩的文件并写进压缩包里
			FileInputStream fis = new FileInputStream(new File("源文件"));
			bis = new BufferedInputStream(fis, 1024 * 1024);
			
			byte[] bufs = new byte[1024 * 10];
			int read = 0;
			while ((read = bis.read(bufs, 0, 1024 * 1024)) != -1) {
				zos.write(bufs, 0, read);
			}
		} catch (Exception e) {
		}finally{
			if(zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
