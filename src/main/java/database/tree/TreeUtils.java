package database.tree;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TreeUtils {
	public static List<TreeNode> setResult(Statement state, String sql) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		try {
			ResultSet rs = state.executeQuery(sql);
			if(rs != null) {
				while(rs.next()) {
					TreeNode obj = new TreeNode();
					obj.setId(rs.getString("id"));
					obj.setLeaf("Y".equals(rs.getString("leaf_")));
					obj.setLevel(rs.getInt("level_"));
					obj.setOrder(rs.getDouble("order_"));
					obj.setDisplayName(rs.getString("display_name"));
					obj.setSign(rs.getString("name"));
					obj.setMenu("1".equals(rs.getString("menu")));
					obj.setParentId(rs.getString("parent_"));
					list.add(obj);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL执行错误");
		}
		return list.isEmpty() ? null : list;
	}
	
	public static List<TreeNode> setResult(Connection conn, String sql) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					TreeNode obj = new TreeNode();
					obj.setId(rs.getString("id"));
					obj.setLeaf("Y".equals(rs.getString("leaf_")));
					obj.setLevel(rs.getInt("level_"));
					obj.setOrder(rs.getDouble("order_"));
					obj.setDisplayName(rs.getString("display_name"));
					obj.setSign(rs.getString("name"));
					obj.setMenu("1".equals(rs.getString("menu")));
					obj.setParentId(rs.getString("parent_"));
					list.add(obj);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL执行错误");
		}
		return list.isEmpty() ? null : list;
	}
	
	public static void writerFile(BufferedWriter writer, String str) {
		try {
			writer.write(str);
			writer.write("\r\n");// 换行符
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
