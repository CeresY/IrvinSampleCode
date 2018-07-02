package xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
  * @author yangzhan
  * @date 2018年7月2日
  */
public class ReadXml {
	@Test
	public void example() {
		// 解析books.xml文件
        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        try {
            // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
            Document document = reader.read(new File("src/res/books.xml"));
            // 通过document对象获取根节点bookstore
            Element bookStore = document.getRootElement();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator<Element> it = bookStore.elementIterator();
            // 遍历迭代器，获取根节点中的信息（书籍）
            while (it.hasNext()) {
                System.out.println("=====开始遍历某一本书=====");
                Element book = it.next();
                // 获取book的属性名以及 属性值
                List<Attribute> bookAttrs = book.attributes();
                for (Attribute attr : bookAttrs) {
                    System.out.println("属性名：" + attr.getName() + "--属性值："
                            + attr.getValue());
                }
                Iterator<Element> it2 = book.elementIterator();
                while (it2.hasNext()) {
                    Element bookChild = it2.next();
                    System.out.println("节点名：" + bookChild.getName() + "--节点值：" + bookChild.getStringValue());
                }
                System.out.println("=====结束遍历某一本书=====");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
	}
	
	@Test
	public void countIssues() {
        SAXReader reader = new SAXReader();
        try {
            // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
        	FileInputStream fis = new FileInputStream(new File("C:\\PerfLogs\\1.xml"));
            Document document = reader.read(fis);
            // 通过document对象获取根节点bookstore
            Element issues = document.getRootElement();
            List<Element> list = issues.elements();
            System.out.println(list.size());
            for(int i=0; i<list.size(); i++){
            	Element e = list.get(i);
            	Iterator<Element> filedIt = e.elementIterator();
            	while(filedIt.hasNext()) {
            		Element filed = filedIt.next();
            		System.out.print(filed.attributeValue("name") + "\t");
            	}
            	System.out.println();
            }
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
	}
}
