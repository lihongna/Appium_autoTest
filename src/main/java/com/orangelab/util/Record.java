package com.orangelab.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.orangelab.config.Constants;

public class Record {
	private DosCmd execDos = new DosCmd();
	/**
	 * <br>开始录制视频</br>
	 * 
	 * @param RecordCaseName
	 */
	public void startRecord(String caseId) throws Exception {
	    
	    String RecordCaseName = caseId;
	    String cmdString = "adb shell screenrecord /sdcard/"+ RecordCaseName +".mp4";
	    execDos.execCmd(cmdString);
	    System.out.println(caseId);
	}
	
	/**
	 * <br>结束录制视频</br>
	 * 
	 */
	public void endRecord() throws Exception {
	    	 
  		 execDos.execCmd("adb kill-server");
  		 execDos.execCmd("adb start-server");	  		 
  		 Thread.sleep(8000);
  		 System.out.println("endRecord");

	}
	
	/**
	 * <br>上传录制的视频</br>
	 * 
	 * @param RecordCaseName
	 */
	public void pullRecord(String caseId) throws Exception {

	    String RecordCaseName = caseId;   		 
		execDos.execCmd("adb pull /sdcard/"+ RecordCaseName +".mp4");
		Thread.sleep(3000);
		System.out.println(caseId);
	  	
	}
		
	/**
	 * <br>移动录制的视频</br>
	 * 
	 * @param RecordCaseName
	 */
	public void moveRecord(String caseId) throws Exception {

	    String RecordCaseName = caseId;
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String date = df.format(new Date());
	    String cmdString = "move "+ RecordCaseName +".mp4 " + ConfigUtil.getProperty("video.path", Constants.CONFIG_COMMON)+date+"\\";
	    
		execDos.execCmd(cmdString);
		System.out.println(caseId);
	  	
	}
}
