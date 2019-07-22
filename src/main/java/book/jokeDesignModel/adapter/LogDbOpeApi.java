package book.jokeDesignModel.adapter;

/**
 * 相当于target
 */
public interface LogDbOpeApi {
    /*
     * 新增日志
     * @param 需要新增的日志对象
     */
    public void createLog(LogBean logbean);
}
