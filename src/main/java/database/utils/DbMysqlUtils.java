package database.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * mysql工具类
 * @author yangzhan-xps13
 *
 */
public class DbMysqlUtils {
	static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // com.mysql.cj.jdbc.Driver、com.mysql.jdbc.Driver
	 
	// 172.16.206.31、127.0.0.1
	// jdbc:mysql://localhost:3306/mytest?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false
	static final String URL = "jdbc:mysql://localhost:3306/vbi2?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false";
	static final String USER = "vbi";
	static final String PWD = "123456";
	static Connection conn = null;
	static Statement statement = null;
	static PreparedStatement ps = null;
	
	public static Statement getStatement() {
		conn = getConnection();
		try {
			statement = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}
	
	public static PreparedStatement getPs(String sql) {
		try {
			if(conn == null) {
				conn = getConnection();
			}
			
			if(ps == null) {
				ps = conn.prepareStatement(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	public static Connection getConnection() {
		if(conn != null) {
			return conn;
		}
		try{
            Class.forName(DRIVER);
            System.out.println("成功加载MySQL驱动！");
            conn = DriverManager.getConnection(URL, USER, PWD);
        }catch(ClassNotFoundException e1){
            System.out.println("找不到MySQL驱动!");
            e1.printStackTrace();
        } catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close() {
		if(statement != null) {
			try {
				statement.close();
				System.out.println("关闭statement");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(ps != null) {
			try {
				ps.close();
				System.out.println("关闭ps");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
				System.out.println("关闭conn");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
