package web.spring.v20.aop;

public class TimeBook implements TimeBookInterface {

	@Override
	public void doAuditing(String name) {
		System.out.println(name + ": �������������ʾ��wertyuiop" );
	}

	@Override
	public void doCheck(String name) {
		System.out.println(name + ": ����ִ����صĳ��� ");
	}

}
