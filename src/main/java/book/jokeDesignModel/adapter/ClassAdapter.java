package book.jokeDesignModel.adapter;

import java.util.List;

/*
 * 类适配器对象案例
 */
public class ClassAdapter extends LogFileOperate implements LogDbOpeApi{

    public ClassAdapter(String logFilename) {
        super(logFilename);
    }

    @Override
    public void createLog(LogBean logbean) {
        List<LogBean> list = this.readLogFile();
        list.add(logbean);
        this.writeLogFile(list);
    }

}
