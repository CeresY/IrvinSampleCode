package corejava.enumration;

import org.junit.Test;

/**
 * 枚举测试类
 * @author yangzhan-xps13
 * @date 2017年6月15日-上午9:27:36
 */
public class FunTest {
	
	@Test
	public void test1(){
//		CatalogueTypeEnum.Project.getName();
		System.out.println(CatalogueTypeEnum.fromValue("None"));
	}
	
	@Test
	public void color(){
		Color c = Color.BLUE ;        // 取出蓝色
        System.out.println(c) ;
        
        Color color = Color.GREEN;
        switch(color){
        case RED:{
            System.out.println("红颜色") ;
            break ;
        }
        case GREEN:{
            System.out.println("绿颜色") ;
            break ;
        }
        case BLUE:{
            System.out.println("蓝颜色") ;
            break ;
        }
        default:{
            System.out.println("未知颜色") ;
            break ;
        }
    }
	}
}
