package database;

import org.junit.Test;

import database.utils.DbOracleUtils;

public class TestDb {
	
	@Test
	public void testConnect() {
		DbOracleUtils.getConnection();
	}
}
