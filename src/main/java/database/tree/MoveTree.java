package database.tree;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import database.utils.DbUtils;
import database.utils.DbUtils.DB;


/**
 * 移动“权限树的位置”
 * @author yangzhan-xps13
 * 2017年8月17日
 */
public class MoveTree {
	private Statement stat = null;
	
	private static final DB TYPE = DbUtils.DB.ORACLE;
	
	@Test
	public void movePosition() {
		// 大屏设计器 screenDesigner
		String sql = "SELECT * FROM system_permission t where t.name='screenDesigner'";
		stat = DbUtils.getStatement(TYPE);
		List<TreeNode> rs = this.setResult(sql);
	
		if(rs != null && rs.size() == 1) {
			TreeNode root = rs.get(0);
			this.findByFind(rs, root.getId());
		} else {
			System.out.println("查询有误");
		}
		//show(rs);
		moveLevel_(rs);
		System.out.println("操作记录：" + rs.size());
		DbUtils.close(TYPE);
	}
	
	public void moveLevel_(List<TreeNode> list) {
		String sql = "update system_permission set level_=? where id=?";
		PreparedStatement ps = DbUtils.getPs(sql, TYPE);
		try {
			for(int i=0; i<list.size(); i++) {
				TreeNode node = list.get(i);
				int level_ = node.getLevel()-1;
				String id = node.getId();
				ps.setInt(1, level_);
				ps.setString(2, id);
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void findByFind(List<TreeNode> rs, String parentId) {
		String sql = "SELECT * FROM system_permission t where t.parent_='"+parentId+"'";
		List<TreeNode> temp = this.setResult(sql);
		if(temp != null) {
			rs.addAll(temp);
			for(int i=0; i<temp.size(); i++) {
				TreeNode node = temp.get(i);
				findByFind(rs, node.getId());
			}
		}
	}
	
	public List<TreeNode> setResult(String sql) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		try {
			ResultSet rs = stat.executeQuery(sql);
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
			System.out.println("SQL执行错误\\n");
		}
		return list.isEmpty() ? null : list;
	}
	
	public void show(List<TreeNode> list) {
		System.out.println("ID\t" + "displayName\t" + "name\t" + "parentid");
		for(int i=0; i<list.size(); i++) {
			TreeNode obj = list.get(i);
			int le = obj.getLevel();
			int level = le;
			if(le > 0) {
				System.out.print("|");
			}
			for(; level>0 ; level--) {
				System.out.print("--");
			}
			System.out.print(obj.getDisplayName()+" ⇨ "+obj.getId()+" ⇨ "+obj.getSign()+" ⇨ "+obj.getParentId());
			System.out.println();
		}
	}
}
