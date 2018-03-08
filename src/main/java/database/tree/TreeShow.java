package database.tree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import database.utils.DbUtils;
import database.utils.DbUtils.DB;


/**
 * mysql由于没有startWith connectBy所以需自己写一个小工具来展示树结构
 * @author yangzhan-xps13
 *
 */
public class TreeShow {
	private Statement state = null;
	
	private static final File destination = new File("C://temp//tree.txt");
	
	private static final DB TYPE = DB.ORACLE;
	/**
	 * 从叶子节点展示到根节点
	 */
	@Test
	public void showLeafToRoot() {
		String childId = "2c904e9f5cc45ca2015cc4a778121922";
		state = DbUtils.getStatement(TYPE);
		
		String sql = "SELECT * FROM SYSTEM_PERMISSION T ORDER BY T.LEVEL_ ASC, T.ORDER_ ASC ";
		List<TreeNode> allList = TreeUtils.setResult(state, sql);
		List<TreeNode> showList = new ArrayList<TreeNode>();
		findLeafToRoot(showList, allList, childId);
		show(showList);
		
		DbUtils.close(TYPE);
	}
	
	/**
	 * 从根节点展示到叶子节点
	 */
	@Test
	public void showRootToLeaf() {
		state = DbUtils.getStatement(TYPE);
		
		/*String sql_manyRoot = "SELECT * FROM SYSTEM_PERMISSION T WHERE T.LEVEL_=0 ORDER BY T.LEVEL_ ASC, T.ORDER_ ASC ";
		List<TreeNode> rootList = TreeUtils.setResult(state, sql_manyRoot);*/
		
		String singleRoot = "d117350374534819b7b786a886bef71e";
		String sql_singleRoot = "SELECT * FROM SYSTEM_PERMISSION T WHERE T.ID = '"+singleRoot+"' ";
		List<TreeNode> rootList = TreeUtils.setResult(state, sql_singleRoot);
		
		try {
			String filename = "C://temp//tree_"+String.valueOf(new Random(1000).nextInt())+".txt";
			File destination_ = new File(filename);
			BufferedWriter bw = new BufferedWriter(new FileWriter(destination_));
			findRootToLeaf(bw, rootList);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DbUtils.close(TYPE);
	}
	
	/**
	 * 查询节点若干层级父节点
	 * @param sort
	 * @param list
	 * @param nodeId
	 */
	public void findLeafToRoot(List<TreeNode> sort, List<TreeNode> allList, String nodeId) {
		String parentId = null;
		for(int i=0; i<allList.size(); i++) {
			TreeNode obj = allList.get(i);
			if(nodeId.equals(obj.getId())) {
				sort.add(obj);
				parentId = obj.getParentId();
			}
		}
		if(parentId != null) {
			findLeafToRoot(sort, allList, parentId);
		}
	}
	
	/**
	 * 采用递归方式组装树
	 */
	public void findRootToLeaf(BufferedWriter bw, List<TreeNode> rootList) {
		for(int i=0; i<rootList.size(); i++) {
			TreeNode node = rootList.get(i);
			TreeUtils.writerFile(bw, writerNode(node));
			System.out.println(this.writerNode(node));
			
			List<TreeNode> childs = this.getChilds(node.getId());
			while(childs != null) {
				for(int j=0; j<childs.size(); j++) {
					TreeNode child = childs.get(j);
					List<TreeNode> tempList = new ArrayList<TreeNode>();
					tempList.add(child);
					this.findRootToLeaf(bw, tempList);
				}
				break;
			}
		}
	}
	
	/**
	 * 获取子节点
	 * @return
	 */
	public List<TreeNode> getChilds(String parentId) {
		String sql_findRoot = 
				"SELECT * FROM SYSTEM_PERMISSION T WHERE T.PARENT_='"+parentId+"' ORDER BY T.LEVEL_ ASC, T.ORDER_ ASC ";
		return TreeUtils.setResult(state, sql_findRoot);
	}
	
	private void show(List<TreeNode> sort){
		System.out.println("ID\t" + "displayName\t" + "name\t" + "parentid");
		for(int i= sort.size()-1; i>=0; i--) {
			TreeNode obj = sort.get(i);
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
	
	private String writerNode(TreeNode node) {
		StringBuffer sb = new StringBuffer();
		int level = node.getLevel();
		for(; level>0 ; level--) {
			if(level == 1) {
				sb.append("|----");
			} else {
				sb.append("|    ");
			}
		}
		sb.append(node.getDisplayName()+" ⇨ "+node.getId()+" ⇨ "+node.getSign()+" ⇨ "+node.getParentId());
		return sb.toString();
	}
	
}
