package book.jokeDesignModel.adapter;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description //实现对日志文件的操作
 * @Author yz
 * @Date 2019-7-19
 * @Vesion 1.0
 **/
public class LogFileOperate implements LogFileOperateApi {
    /*
     * 设置日志文件的路径和文件名称
     */
    private String logFileName = "file.log";
    /*
     * 构造方法，传入文件的路径和名称
     */
    public LogFileOperate(String logFilename){
        if(logFilename!=null){
            this.logFileName = logFilename;
        }
    }

    @Override
    public List<LogBean> readLogFile() {
        List<LogBean> list = new ArrayList<>();
        ObjectInputStream oin =null;
        //业务代码
        return list;
    }

    @Override
    public void writeLogFile(List<LogBean> list) {
        File file = new File(logFileName);
        ObjectOutputStream oout = null;
        //业务代码
    }
}
