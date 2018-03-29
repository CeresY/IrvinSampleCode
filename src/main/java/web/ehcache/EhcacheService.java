package web.ehcache;

import corejava.model.Account;

/** 
  * @author yangzhan
  * 2018年3月29日
  */
public interface EhcacheService {
    // 测试失效情况，有效期为5秒
    public long getTimestamp(String param);

    public String getDataFromDB(String key);

    public void removeDataAtDB(String key);

    public String refreshData(String key);

    public Account findById(String userId);

    public boolean isReserved(String userId);

    public void removeUser(String userId);

    public void removeAllUser();
}
