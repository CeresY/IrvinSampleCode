package database.other;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import database.tree.TreeNode;
import database.utils.DbUtils;
import database.utils.DbUtils.DB;

/**
  * @date 2018年12月14日
  */
public class Vbi5 {
	private static final DB TYPE = DB.ORACLE;
	
	/**
	 * 插入语句
	 */
	@Test
	public void fix() {
		String sql = "select t.id, t.parent_ from system_permission t";
		try {
			ResultSet rs = DbUtils.executeQuery(sql, TYPE);
			Map<String, String> map = new HashMap<>();
			List<String> parentList = new ArrayList<>();
			while(rs.next()) {
				String id = rs.getString("id");
				String parent = rs.getString("parent_");
				map.put(id, parent);
				parentList.add(parent);
			}
			//分析
			for(String pid : parentList) {
				if(!map.containsKey(pid)) {
					if(pid != null && !"".equals(pid)) {
						String sql3 = "update system_permission set parent_='4028098167aa6eba0167aa98a5280004', level_=4 where parent_='"+pid+"';";
						System.out.println(sql3);
						//DbUtils.executeUpdate(sql3, TYPE);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtils.close(TYPE);
	}
}
