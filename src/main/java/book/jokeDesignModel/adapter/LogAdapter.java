package book.jokeDesignModel.adapter;

import java.util.List;

/**
 * @Description // 适配器对象，将记录日志到文件的功能适配成数据库功能
 * @Author yz
 * @Date 2019-7-19
 * @Vesion 1.0
 **/
public class LogAdapter implements LogDbOpeApi {
    private LogFileOperateApi adaptee;

    public LogAdapter(LogFileOperateApi adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void createLog(LogBean logbean) {
        List<LogBean> list = adaptee.readLogFile();
        list.add(logbean);
        adaptee.writeLogFile(list);
    }

}
