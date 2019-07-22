package book.jokeDesignModel.adapter;

/**
 * @Description //日志数据对象
 * @Author yz
 * @Date 2019-7-19
 * @Vesion 1.0
 **/
public class LogBean {
    private String logId;//日志编号
    private String opeUserId;//操作人员

    public String getLogId(){
        return logId;
    }
    public void setLogId(String logId){
        this.logId = logId;
    }

    public String getOpeUserId(){
        return opeUserId;
    }
    public void setOpeUserId(String opeUserId){
        this.opeUserId = opeUserId;
    }
    public String toString(){
        return "logId="+logId+",opeUserId="+opeUserId;
    }
}
