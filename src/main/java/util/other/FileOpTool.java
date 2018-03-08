package util.other;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class FileOpTool {

	/*
	 * 根据路径判断是否为目录
	 */
	public boolean isDirectory(String path) {
		File dirName = new File(path);
		if (dirName.isDirectory()) {
			return true;
		} else {
			return false;
		}
	}
	
    /*
	 * 创建目录
	 */
	public boolean createDirectory(String path) {
		File dirName = new File(path);
		return dirName.mkdirs();
	}

	/*
	 * 判断文件是否存在-标准文件用isFile
	 */
	public boolean isFile(String path) {
		File fileName = new File(path);
		if (fileName.isFile()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据文件路径删除文件
	 */
	public boolean deleteFile(String path) {
		File file = new File(path);
		if (file.exists()) { // 存在就删除
			return file.delete();
		} else {
			return true;
		}
	}
	
	/**
	 * 上传文件到指定服务器
	 * @param files 文件对象数组
	 * @param path 上传地址
	 */
	public void uploadFile(MultipartFile[] files, String path) {
		try{
			File target = new File(path);
			if(!target.exists()) {
				target.mkdirs();
			}
			if(files.length>0) {
				for(int i=0; i<files.length; i++) {
					MultipartFile srcFile = files[i];
					if(!srcFile.isEmpty()) {
						String filename = srcFile.getOriginalFilename();
						srcFile.transferTo(new File(path + File.separator + filename));
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除文件夹
	 * @param path
	 */
	public void delFolder(String path) {
		File file = new File(path);
		if(file.exists()) {
			File[] fs = file.listFiles();
			for(File f : fs) {
				f.delete();
			}
			file.delete();
		}
	}
}
