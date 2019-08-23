package util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * yml文件属性读取类
 */
@Slf4j
public class YmlCacheUtils {

    private static String default_file = "application.yml";

    private static Map<String, String> result = new HashMap<>();

    private static Map<String, String> resultDefalut = new HashMap<>();

    private static Map<String, String> resultActive = new HashMap<>();

    public static void main(String[] args) {
        /*for(int i=0; i<3; i++) {
            Map<String, String> map = getYmlActive();
            for(Map.Entry<String,String> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("====================="+i+"==========================");
        }*/
        for (int i = 0; i < 3; i++) {
            System.out.println("==============="+i+"=================");
            System.out.println(getValueDefalut("spring.jackson.time-zone"));
            System.out.println(getValueActive("spring.datasource.url"));
        }
    }

    public static String getValueActive(String key) {
        return getYmlActive().get(key);
    }

    /**
     * 根据yml中的key获取值
     * @param key
     * @return
     */
    public static String getValueDefalut(String key) {
        return getYmlDefalut().get(key);
    }

    public static Map<String, String> getYmlDefalut() {
        if(MapUtils.isEmpty(resultDefalut)) {
            resultDefalut = getYmlByFileName(null);
        }
        return resultDefalut;
    }

    public static Map<String, String> getYmlActive() {
        if(MapUtils.isEmpty(resultActive)) {
            String file = "application-"+getValueDefalut("spring.profiles.active")+".yml";
            resultActive = getYmlByFileName(file);
        }
        return resultActive;
    }

    /**
     * 根据文件名获取yml的文件内容
     * @return
     */
    public static Map<String, String> getYmlByFileName(String file) {
        log.info("解析文件{}",file);
        result = new HashMap<>();
        if (file == null)
            file = default_file;
        try {
            Resource resource = new ClassPathResource(file);
            //通过stream去读取，打包后才能读到。
            InputStream in = resource.getInputStream();
            Yaml props = new Yaml();
            Object obj = props.loadAs(in, Map.class);
            Map<String, Object> param = (Map<String, Object>) obj;
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                String key = entry.getKey();
                Object val = entry.getValue();
                if (val instanceof Map) {
                    forEachYaml(key, (Map<String, Object>) val);
                } else {
                    result.put(key, val.toString());
                }
            }
        } catch (Exception ex) {

        }
        return result;
    }

    /**
     * 遍历yml文件，获取map集合
     * @param key_str
     * @param obj
     * @return
     */
    private static Map<String, String> forEachYaml(String key_str, Map<String, Object> obj) {
        for (Map.Entry<String, Object> entry : obj.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();

            String str_new = "";
            if (StringUtils.isNotEmpty(key_str)) {
                str_new = key_str + "." + key;
            } else {
                str_new = key;
            }
            if (val instanceof Map) {
                forEachYaml(str_new, (Map<String, Object>) val);
            } else {
                result.put(str_new, val.toString());
            }
        }
        return result;
    }
}
