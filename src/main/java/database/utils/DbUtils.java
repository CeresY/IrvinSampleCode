package database.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils {
	
	public static enum DB {
		ORACLE, MYSQL;
	}
	
	/**
	 * 获取处理对象
	 * @param type
	 * @return
	 */
	public static Statement getStatement(DB type) {
		if(type.equals(DB.ORACLE)) {
			return DbOracleUtils.getStatement();
		} else if(type.equals(DB.MYSQL)) {
			return DbMysqlUtils.getStatement();
		} else {
			return null;
		}
	}
	
	/**
	 * 获取预处理对象
	 * @param type
	 * @return
	 */
	public static PreparedStatement getPs(String sql, DB type) {
		if(type.equals(DB.ORACLE)) {
			return DbOracleUtils.getPs(sql);
		} else if(type.equals(DB.MYSQL)) {
			return DbMysqlUtils.getPs(sql);
		} else {
			return null;
		}
	}
	
	/**
	 * 获取数据库连接
	 * @param type
	 * @return
	 */
	public static Connection getConnection(DB type) {
		if(type.equals(DB.ORACLE)) {
			return DbOracleUtils.getConnection();
		} else if(type.equals(DB.MYSQL)) {
			return DbMysqlUtils.getConnection();
		} else {
			return null;
		}
	}
	
	/**
	 * 关闭数据库连接
	 * @param type
	 */
	public static void close(DB type) {
		if(type.equals(DB.ORACLE)) {
			DbOracleUtils.close();
		} else if(type.equals(DB.MYSQL)) {
			DbMysqlUtils.close();
		}
	}
	
	/**
	 * 执行查询结果
	 * @param sql
	 * @param type
	 * @return
	 */
	public static ResultSet executeQuery(String sql, DB type) {
		PreparedStatement pstmt = DbUtils.getPs(sql, type);
		ResultSet resultSet = null;
		try {
			resultSet = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	/**
	 * 执行dml语句
	 * @param sql
	 * @param type
	 * @return
	 */
	public static int executeUpdate(String sql, DB type) {
		PreparedStatement pstmt = DbUtils.getPs(sql, type);
		int i= -1;
		try {
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
}
