package database.tree;

import org.junit.Test;

import database.utils.DbUtils.DB;

/**
  * @author yangzhan
  * 2018年4月24日
  */
public class TreeShowTest {
	private TreeShow foo = new TreeShow(DB.MYSQL); 

	@Test
	public void showRootToLeaf() {
		String rootId = "d117350374534819b7b786a886bef71e";
		foo.showRootToLeaf(rootId);
	}
}
