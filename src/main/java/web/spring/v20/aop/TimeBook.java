package web.spring.v20.aop;

public class TimeBook implements TimeBookInterface {

	@Override
	public void doAuditing(String name) {
		System.out.println(name + ": 审核数据内容如示：wertyuiop" );
	}

	@Override
	public void doCheck(String name) {
		System.out.println(name + ": 正在执行相关的程序 ");
	}

}
