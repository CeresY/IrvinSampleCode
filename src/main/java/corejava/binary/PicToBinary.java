package corejava.binary;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import database.utils.DbUtils;
import database.utils.DbUtils.DB;

/** 图片与二进制相互转换
  * @author yangzhan
  * 2018年3月27日
  */
public class PicToBinary {
	private static final DB TYPE = DB.MYSQL;
	
	/**
	 * 二进制图片下载
	 * @throws IOException
	 */
	@Test
	public void picToBinary() throws IOException {
		Statement st = DbUtils.getStatement(TYPE);
		String id = "01a216871f724f7295df5b7eeef03938";
		String sql = "SELECT t.thumbnail as thumbnail from visu_canvas t where t.id = '" + id +"'";
		FileOutputStream out = null;
		ByteArrayInputStream in = null;
		try {
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				byte[] bt = rs.getBytes("thumbnail");
				System.out.println("图片长度：" + bt.length);
				
				in = new ByteArrayInputStream(bt);
				File file = new File("H:/log/1.jpg");
				out = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int b = -1;
				while((b = in.read(buffer)) != -1) {
					out.write(buffer, 0, b);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} finally {
			in.close();
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 二进制图片下载
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void getJpg(HttpServletResponse response) throws Exception {
		byte[] jpg = null;
		OutputStream out = null;
		if (jpg == null) {
			response.sendError(404, "文件已经被删除!");
		}else {
			response.reset();
			response.setContentType("image/jpeg");
			String fileName = URLEncoder.encode("图片","UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".jpg");
			ByteArrayInputStream bais = new ByteArrayInputStream(jpg);
			// 输出
			int len = 0;
	        byte[] buffer = new byte[1024];
	        out = response.getOutputStream();
	        while((len = bais.read(buffer)) != -1) {
	        	out.write(buffer, 0, len);
	        }
		}
	}
	
	/**
	 * 统计对象大小
	 * @param obj
	 * @return byte
	 */
	public static long countObjectSize(Object obj) {
		if(obj == null) {
			return 0;
		}
		ByteArrayOutputStream byteOut = null;
		ObjectOutputStream out = null;
		long size = 0L;
		try {
			byteOut = new ByteArrayOutputStream();
			out = new ObjectOutputStream(byteOut);
			out.writeObject(obj);
			size = byteOut.size();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null) {
					out.close();
				}
				if(byteOut != null) {
					byteOut.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return size;
	}
}
