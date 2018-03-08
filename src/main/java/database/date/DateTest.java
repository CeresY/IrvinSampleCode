package database.date;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import database.utils.DbUtils;
import database.utils.DbUtils.DB;


public class DateTest {
	private static final DB TYPE = DB.ORACLE;
	
	/**
	 * 插入语句
	 */
	@Test
	public void insertDate() {
		Statement statement = DbUtils.getStatement(TYPE);
		int days = 31;
		for(int i=0; i<days; i++) {
			String countSql = "select count(rank_) as rank_ from test1 ";
			try {
				// 个数
				ResultSet rs = statement.executeQuery(countSql);
				int count = 0;
				while(rs.next()) {
					count = rs.getInt("rank_");
				}
				// 插入
				//String day = this.getRandom(false, 31);
				String day = String.valueOf(i+1);
				String hour = this.getRandom(true, 24);
				String min = this.getRandom(true, 60);
				String second = this.getRandom(true, 60);
				String sql = "insert into test1(rank_, create_time) "
						+ "values("+count+", "
						+ "to_date('2016-11-"+day+" "+hour+":"+min+":"+second+"', 'yyyy-MM-dd HH24:mi:ss'))";
				System.out.println("sql: " + sql);
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		DbUtils.close(TYPE);
	}
	
	/**
	 * 查询表头
	 * @throws SQLException 
	 */
	@Test
	public void findMetaData() throws SQLException {
		String sql = "select * from visu_schema_sys where 1=-1";
		ResultSet rs = DbUtils.executeQuery(sql, TYPE);
		ResultSetMetaData md = rs.getMetaData(); // 得到结果集(rs)的结构信息，比如字段数、字段名等
		for(int i=1; i<=md.getColumnCount(); i++) {
			int size = md.getColumnDisplaySize(i);
			System.out.println("size="+size);
			
			String typeName = md.getColumnTypeName(i);
			System.out.println("typeName="+typeName);
			
			int colType = md.getColumnType(i);
			System.out.println("colType="+colType);
			
			String colTypeName = md.getColumnTypeName(i);
			System.out.println("colTypeName="+colTypeName);
			
			String colLabel = md.getColumnLabel(i);
			System.out.println("colLabel="+colLabel);
			
			String colName = md.getColumnName(i);
			System.out.println("colName="+colName);
			
			int precision = md.getPrecision(i);
			System.out.println("precision="+precision);
			
			int scale = md.getScale(i);
			System.out.println("scale="+scale);
			
			String catalogName = md.getCatalogName(i);
			System.out.println("catalogName="+catalogName);
			
			String schemaName = md.getSchemaName(i);
			System.out.println("schemaName="+schemaName);
			System.out.println("----------------------------------");
		}
		DbUtils.close(TYPE);
	}
	
	/**
	 * 参考资料
	 * {@link http://blog.csdn.net/u011637069/article/details/52046662}
	 * @throws Exception
	 */
	@Test
	public void findColumns() throws Exception {
		String table = "VISU_SCHEMA_SYS";
		Connection conn = DbUtils.getConnection(TYPE);
		DatabaseMetaData dbmd = conn.getMetaData();
		ResultSet resultSet = dbmd.getTables(null, "%", table.toUpperCase(), new String[]{"TABLE"});
		
		while(resultSet.next()) {
			String tableName = resultSet.getString("TABLE_NAME").toUpperCase();
			System.out.println("--------表名["+tableName+"]--------");
			if(table.equals(tableName)) {
				ResultSet rs = dbmd.getColumns(null, getSchema(conn), tableName, "%");
				//ResultSetMetaData rsmd = rs.getMetaData(); // 得到结果集(rs)的结构信息，比如字段数、字段名等
				//printResultSetMetaData(rsmd);
				printResultSet(rs);
			}
		}
		DbUtils.close(TYPE);
	} 
	
	public static void printResultSetMetaData(ResultSetMetaData rsmd) {
		System.out.println("----------打印rsmd开始--------------");
		try {
			for(int i=1; i<=rsmd.getColumnCount(); i++) {
				String columnLabel = rsmd.getColumnLabel(i);
				String columnClassName = rsmd.getColumnTypeName(i);
				// 长度
				int length = rsmd.getColumnDisplaySize(i);
				// 有效位数
				int precision = rsmd.getPrecision(i);
				// 精度
				int scale = rsmd.getScale(i);
				System.out.println("Label:"+columnLabel + "\tClassName:"+columnClassName+
						"\tlength:"+length + "\tprecision:"+precision + "\tscale:"+scale);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("-----------打印rsmd结束-------------");
	}
	
	public static void printResultSet(ResultSet rs) {
		System.out.println("----------打印resultSet开始--------------");
		try {
			while(rs.next()) {
				System.out.println("字段名："+rs.getString("COLUMN_NAME") +
						"\t字段注释："+rs.getString("REMARKS") + 
						"\t字段数据类型："+rs.getString("TYPE_NAME"));  
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("----------打印resultSet结束--------------");
	}
	
	private static String changeDbType(String dbType) {  
        dbType = dbType.toUpperCase();  
        if(dbType.equals("VARCHAR") || dbType.equals("VARCHAR2") || dbType.equals("CHAR")) {
            return "1"; 
        } else if(dbType.equals("NUMBER") || dbType.equals("DECIMAL")) {
        	return "4";  
        } else if(dbType.equals("INT") || dbType.equals("SMALLINT") || dbType.equals("INTEGER")) {
        	return "2";  
        } else if(dbType.equals("BIGINT")) {
        	return "6";  
        } else if(dbType.equals("DATETIME") || dbType.equals("TIMESTAMP") || dbType.equals("DATE")) {
        	return "7";  
        } else {
        	return "1";
        }
    }  
	
	/**
	 * 其他数据库不需要这个方法 oracle和db2需要  
	 * @param conn
	 * @return
	 * @throws Exception
	 */
    public static String getSchema(Connection conn) throws Exception {  
        String schema;  
        schema = conn.getMetaData().getUserName();  
        System.out.println("[schema]="+schema);
        if ((schema == null) || (schema.length() == 0)) {  
            throw new Exception("ORACLE数据库模式不允许为空");  
        }  
        return schema.toUpperCase().toString();  
    } 
	
	private String getRandom(boolean zero, int max) {
		int i = new Random().nextInt(max);
		if(!zero) {
			if(i == 0) {
				this.getRandom(zero, max);
			}
		}
		if(i < 10) {
			return "0"+i;
		} else {
			return String.valueOf(i);
		}
	}
}
