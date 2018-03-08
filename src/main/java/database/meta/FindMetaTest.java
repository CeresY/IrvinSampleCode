package database.meta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import database.utils.DbUtils;
import database.utils.DbUtils.DB;


public class FindMetaTest {
	
	private static final DB TYPE = DB.ORACLE;

	public void getMetaColumns() throws Exception {
		String sql = "";
		Connection connection = DbUtils.getConnection(TYPE);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ResultSetMetaData md = rs.getMetaData(); // 得到结果集(rs)的结构信息，比如字段数、字段名等
			int columnCount = md.getColumnCount(); // 返回此 ResultSet 对象中的列数
			for (int i = 1; i <= columnCount; i++) {
				String columnLabel = md.getColumnLabel(i);
				String columnClassName = md.getColumnTypeName(i);
				// 长度
				int length = md.getColumnDisplaySize(i);
				// 有效位数
				int precision = md.getPrecision(i);
				// 精度
				int scale = md.getScale(i);
				if (precision == 0 && scale == -127) {
					precision = 11;
					scale = 2;
				}
				int sub = columnClassName.lastIndexOf(".") + 1;
				columnClassName = columnClassName.substring(sub,columnClassName.length());
			}
		} catch (Exception e) {
			
		} finally {
			
		}
	}
}
