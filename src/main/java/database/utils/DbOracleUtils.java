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
public class DbOracleUtils {
	static final String DRIVER = "oracle.jdbc.OracleDriver";
	static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
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
		// statement.executeQuery(sql)
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
            System.out.println("成功加载Oracle驱动！");
            conn = DriverManager.getConnection(URL, USER, PWD);
        }catch(ClassNotFoundException e1){
            System.out.println("找不到Oracle驱动!");
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
