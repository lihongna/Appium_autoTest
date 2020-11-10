package com.orangelab.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

public class FileUtil {
	private static Logger log = Logger.getLogger(FileUtil.class);
	public static String createFile(String filepath) throws IOException {
		log.info("createFile * "+filepath);
		File file = new File(filepath);
		String folderString = file.getParent();
		
		File tempFile =new File(filepath.trim());          
        String fileName = tempFile.getName(); 
        String fileString = fileName.replace(":", "");
		
		File f = new File(folderString+"\\"+fileString);
		if(!f.exists()) {
			createFolder(folderString);
			f.createNewFile();
		}
		return folderString+"\\"+fileString;
	}
	
	public static void createFolder(String folderString) throws IOException {	
		
		File folder = new File(folderString);		
		if(!folder.exists()) {
			folder.mkdirs();
		}
			
	}
	
	public static void copyFile(String srcPath, String targetPath) throws Exception {
		File srcFile = new File(srcPath);
		File target = new File(targetPath);
		if (!srcFile.exists()) {
			throw new Exception("文件不存在！");
		}
		if (!srcFile.isFile()) {
			throw new Exception("不是文件！");
		}
		//判断目标路径是否是目录
		if (!target.isDirectory()) {
			throw new Exception("文件路径不存在！");
		}

		// 获取源文件的文件名
		String fileName = srcPath.substring(srcPath.lastIndexOf("\\") + 1);
		//TODO:判断是否存在相同的文件名的文件
		File[] listFiles = target.listFiles();
		for (File file : listFiles) {
			if(fileName.equals(file.getName())){
				fileName += "_1";
			}
		}
		String newFileName = targetPath + File.separator + fileName;
		File targetFile = new File(newFileName);
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(targetFile);
			//从in中批量读取字节，放入到buf这个字节数组中，
			// 从第0个位置开始放，最多放buf.length个 返回的是读到的字节的个数
			byte[] buf = new byte[8 * 1024];
			int len = 0;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(in != null){
				  in.close();
				}
			}catch(Exception e){
				 System.out.println("关闭输入流错误！");
			}
			try{
				if(out != null){
					out.close();
				}
			}catch(Exception e){
				System.out.println("关闭输出流错误！");
			}
		}
	}

}
