package util.db.autoCode;

public class DaoImplUtil {
	
	public static String persistence() {
		StringBuffer bf = new StringBuffer();
		bf.append(sign("\t", 1) + "public PersistenceService persistenceService;\r\n");
		bf.append(sign("\t", 1) + "public void setPersistenceService(PersistenceService persistenceService) {\r\n");
		bf.append(sign("\t", 2) + "this.persistenceService = persistenceService;\r\n");
		bf.append(sign("\t", 1) + "}" + "\r\n");
		return bf.toString();
	}
	
	public static String create(String pojo) {
		//pojo首字母小写
		String parameter = pojo.substring(0, 1).toLowerCase() + pojo.substring(1);
		StringBuffer bf = new StringBuffer();
		bf.append("\t" + "@Override\r\n");
		bf.append("\t" + "public String create(" + pojo + " "+ parameter +") throws SQLException {\r\n");
		bf.append("\t\t" + "return (String) persistenceService.create(" + parameter + ");\r\n");
		bf.append("\t" + "}" + "\r\n");
		return bf.toString();
	}
	
	public static String retrieve(String pojo) {
		//pojo首字母小写
		String parameter = pojo.substring(0, 1).toLowerCase() + pojo.substring(1);
		StringBuffer bf = new StringBuffer();
		bf.append("\t" + "@Override\r\n");
		bf.append("\t" + "public " + pojo + " retrieve(" + pojo + " " + parameter + ") {" + "\r\n");
		bf.append("\t\t" + "return ("+pojo+") persistenceService.retrive(" + parameter + ");\r\n");
		bf.append("\t" + "}" + "\r\n");
		return bf.toString();
	}
	
	public static String retrieveWithTstamp(String pojo) {
		//pojo首字母小写
		String parameter = pojo.substring(0, 1).toLowerCase() + pojo.substring(1);
		StringBuffer bf = new StringBuffer();
		bf.append("\t" + "@Override" + "\r\n");
		bf.append("\t" + "public "+pojo+" retrieveWithTstamp("+pojo+" "+parameter+") {" + "\r\n");
		bf.append("\t\t" + "try {" + "\r\n");
		bf.append("\t\t\t" + "return ("+pojo+") persistenceService.getSqlMapClient().queryForObject(\""+pojo+".retrieveWithTstamp\", "+parameter+");" + "\r\n");
		bf.append("\t\t" + "} catch (SQLException e) {" + "\r\n");
		bf.append("\t\t\t" + "e.printStackTrace();" + "\r\n");
		bf.append("\t\t\t" + "throw new PersistenceException(e);" + "\r\n");
		bf.append("\t\t" + "}" + "\r\n");
		bf.append("\t" + "}" + "\r\n");
		return bf.toString();
	}
	
	public static String update(String pojo) {
		//pojo首字母小写
		String parameter = pojo.substring(0, 1).toLowerCase() + pojo.substring(1);
		StringBuffer bf = new StringBuffer();
		bf.append("\t" + "@Override" + "\r\n");
		bf.append("\t" + "public int update("+pojo+" "+parameter+") {" + "\r\n");
		bf.append("\t\t" + "return persistenceService.update("+parameter+");" + "\r\n");
		bf.append("\t" + "}" + "\r\n");
		return bf.toString();
	}
	
	public static String updateByOid(String pojo) {
		//pojo首字母小写
		String parameter = pojo.substring(0, 1).toLowerCase() + pojo.substring(1);
		StringBuffer bf = new StringBuffer();
		bf.append("\t" + "@Override" + "\r\n");
		bf.append("\t" + "public int updateByOid("+pojo+" "+parameter+") {" + "\r\n");
		bf.append("\t\t" + "try {" + "\r\n");
		bf.append("\t\t\t" + "return persistenceService.getSqlMapClient().update(\""+pojo+".updateByOid\", "+parameter+");" + "\r\n");
		bf.append("\t\t" + "} catch (SQLException e) {" + "\r\n");
		bf.append("\t\t\t" + "e.printStackTrace();" + "\r\n");
		bf.append("\t\t\t" + "throw new PersistenceException(e);" + "\r\n");
		bf.append("\t\t" + "}" + "\r\n");
		bf.append("\t" + "}" + "\r\n");
		return bf.toString();
	}
	
	public static String queryList(String pojo) {
		//pojo首字母小写
		String parameter = pojo.substring(0, 1).toLowerCase() + pojo.substring(1);
		StringBuffer bf = new StringBuffer();
		bf.append("\t" + "@Override" + "\r\n");
		bf.append("\t" + "public List<"+pojo+"> queryList("+pojo+" "+parameter+") {" + "\r\n");
		bf.append("\t\t" + "return (List<"+pojo+">)persistenceService.queryList("+parameter+");" + "\r\n");
		bf.append("\t" + "}" + "\r\n");
		return bf.toString();
	}
	
	public static String queryListAll(String pojo) {
		//pojo首字母小写
		String parameter = pojo.substring(0, 1).toLowerCase() + pojo.substring(1);
		StringBuffer bf = new StringBuffer();
		bf.append("\t" + "@Override" + "\r\n");
		bf.append("\t" + "public List<"+pojo+"> queryListAll("+pojo+" "+parameter+") {" + "\r\n");
		bf.append("\t\t" + "return (List<"+pojo+">)persistenceService.queryListAll("+parameter+");" + "\r\n");
		bf.append("\t" + "}" + "\r\n");
		return bf.toString();
	}
	
	public static String delete(String pojo) {
		//pojo首字母小写
		String parameter = pojo.substring(0, 1).toLowerCase() + pojo.substring(1);
		StringBuffer bf = new StringBuffer();
		bf.append("\t" + "@Override" + "\r\n");
		bf.append("\t" + "public int delete("+pojo+" "+parameter+") {" + "\r\n");
		bf.append("\t\t" + "return persistenceService.delete("+parameter+");" + "\r\n");
		bf.append("\t" + "}" + "\r\n");
		return bf.toString();
	}
	
	public static String count(String pojo) {
		//pojo首字母小写
		String parameter = pojo.substring(0, 1).toLowerCase() + pojo.substring(1);
		StringBuffer bf = new StringBuffer();
		bf.append("\t" + "@Override" + "\r\n");
		bf.append("\t" + "public int count("+pojo+" "+parameter+") {" + "\r\n");
		bf.append("\t\t" + "return persistenceService.count("+parameter+");" + "\r\n");
		bf.append("\t" + "}" + "\r\n");
		return bf.toString();
	}
	
	public static String addProcessLock(String pojo) {
		//pojo首字母小写
		String parameter = pojo.substring(0, 1).toLowerCase() + pojo.substring(1);
		StringBuffer bf = new StringBuffer();
		bf.append("\t" + "@Override" + "\r\n");
		bf.append("\t" + "public int addProcessLock("+pojo+" "+parameter+") {" + "\r\n");
		bf.append("\t\t" + "try {" + "\r\n");
		bf.append("\t\t\t" + "return persistenceService.getSqlMapClient().update(\""+pojo+".addProcessLock\", "+parameter+");" + "\r\n");
		bf.append("\t\t" + "} catch (SQLException e) {" + "\r\n");
		bf.append("\t\t\t" + "e.printStackTrace();" + "\r\n");
		bf.append("\t\t\t" + "throw new PersistenceException(e);" + "\r\n");
		bf.append("\t\t" + "}" + "\r\n");
		bf.append("\t" + "}" + "\r\n");
		return bf.toString();
	}
	
	/**
	 * @param key 点位符
	 * @param count 个数
	 * @return
	 */
	public static String sign(String key, int count) {
		StringBuffer bf = new StringBuffer();
		while(count-->0) {
			bf.append(key);
		}
		return bf.toString();
	}
	public static void main(String[] args) {
		System.out.println(sign("k",2));
	}
}
