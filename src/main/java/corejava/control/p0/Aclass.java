package corejava.control.p0;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;
import util.log.LogService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description //
 * @Author yz
 * @Date 2019-8-27
 * @Vesion 1.0
 **/
public class Aclass {
    public List<String> getList() {
        LogService.print("初始化 Aclass.getList");
        return new ArrayList<>();
    }

    public List<String> getList(String key) {
        LogService.print("初始化 Aclass.getList(key)");
        return Lists.newArrayList(key);
    }

    public void print() {
        LogService.print("执行 Aclass.print");
        List<String> list = getList();
        if(CollectionUtils.isEmpty(list)) {
            LogService.print("空");
        } else {
            for(String str : list) {
                LogService.print(str);
            }
        }
    }
}
