package corejava.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import office.Column;
import office.ExcelExportUtils;


/** http下载示例
  * @author yangzhan
  * 2018年4月27日
  */
public class DownloadDemo {
	/**
	    * 下载指标填报数据
	    * @param response
	    * @param fileName
	    * @throws Exception
	    */
	    @RequestMapping(value = "/dowonloadExcel")
	    public void dowonloadExcel(HttpServletResponse response,
		    @RequestParam(value = "fileName", required = true) String fileName) throws Exception {
	    	String folderPath = "";
	    	fileName = URLDecoder.decode(fileName, "GBK");
			String filePath = folderPath + File.separator + fileName;
			//log.info("下载指标填报数据名称：" + fileName);
			fileName = new String(fileName.getBytes("GBK"), "ISO8859-1");// firefox浏览器
			response.reset();
			response.setContentType("application/x-msdownload");
			response.setHeader("Cache-Control", "max-age=0");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			
			BufferedInputStream br = null;
			OutputStream out = null;
			try{
				br = new BufferedInputStream(new FileInputStream(filePath));
				out = response.getOutputStream();
				// 创建缓冲区
				byte[] buffer = new byte[1024];
				int len = 0;
				while((len = br.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
			}catch(FileNotFoundException e){
				//log.error("下载资源文件没有找到,路径 = " + filePath);
			}catch(Exception e){
				//log.error("下载资源文件出错", e);
			}finally{
				if(br != null){
					br.close();
				}
				if(out != null){
					out.close();
				}
			}
	    }
	    
	    /**
	     * 下载office
	     * @param response
	     * @param fileName
	     * @throws Exception
	     */
	     @RequestMapping(value = "/pullExcel")
	     public void pullExcel(HttpServletResponse response, @RequestParam(value = "fileName", required = true) String canvasIds) throws Exception {
	 		// 文件名编码
	 		String fileName = URLDecoder.decode("画面访问地址列表", "GBK");
	 		
	 		// response参数
	 		fileName = new String(fileName.getBytes("GBK"), "ISO8859-1");// firefox浏览器
	 		response.reset();
	 		response.setContentType("application/x-msdownload");
	 		response.setHeader("Cache-Control", "max-age=0");
	 		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
	 		
	 		// office文档(wookbook转换成ByteArrayInputStream流)
	 		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
	 		List<String> sheetNames = new ArrayList<String>();
	 		List<Column> fieldList = new ArrayList<Column>();
	 		Map<String, List<Map<String,Object>>> data = new HashMap<String, List<Map<String,Object>>>();
	 		XSSFWorkbook wb = ExcelExportUtils.createWorkbook(sheetNames, fieldList, data);
	 		wb.write(byteOut);
	 		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
	 		
	 		OutputStream out = null;
	 		try{
	 			out = response.getOutputStream();
	 			// 创建缓冲区
	 			byte[] buffer = new byte[1024];
	 			int len = 0;
	 			while((len = byteIn.read(buffer)) > 0) {
	 				out.write(buffer, 0, len);
	 			}
	 		}catch(Exception e){
	 			//log.error("下载资源文件出错", e);
	 		}finally{
	 			if(byteOut != null) {
	 				byteOut.close();
	 			}
	 			if(byteIn != null){
	 				byteIn.close();
	 			}
	 			if(out != null){
	 				out.close();
	 			}
	 		}
	     }
}
