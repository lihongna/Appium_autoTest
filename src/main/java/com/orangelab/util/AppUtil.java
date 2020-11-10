package com.orangelab.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.orangelab.config.Config;

public class AppUtil {
	static Logger log = Logger.getLogger(AppUtil.class);
	public String appName = "";
	public String packageName = "";
	public String versionName = "";
	public int versionCode = 0;
	private DosCmd dos = new DosCmd();
	private Device device = new Device();
	/*
	 * apk info
	 * 
	 * @return List<String>, get(0):packagename ,get(1):versionCode, get(2):versionName
	 */
	public List<String> getAPKInfo(String apkFile) throws Exception {
		log.info("getAPKInfo");
		List<String> info = dos.execCmdConsole("aapt dump badging "+ apkFile);
		
		List<String> apkRes = new ArrayList();	
		// packagename,versionCode,versionName,activityname
		for(int i=0;i<info.size();i++ ) {
			if(info.get(i).trim().contains("package:")) {
				System.out.println(info.get(i).trim());
				String apkInfo[] = info.get(i).split(" ");
				for(int j=1;j<apkInfo.length;j++) {
					String s[] = apkInfo[j].split("=");
					apkRes.add(s[1].replaceAll("'", "").trim() );
				}		
			}
			else if(info.get(i).trim().contains("launchable-activity")) {
				System.out.println(info.get(i).trim());
				String activity[] = info.get(i).split(" ");
				
				String s[] = activity[1].split("=");
				apkRes.add(s[1].replaceAll("'", "").trim());					
			}
		}
		return apkRes;
	}
	
	/*
	 * check apk is already install 
	 */
	private boolean isHasAPK(String deviceName,String packageName) throws Exception {		
		boolean flag = false;
		
		List<String> info = dos.execCmdConsole("adb -s "+deviceName+" shell pm list packages");
		for(int j=0;j<info.size();j++) {
			if(info.get(j).trim().contains(packageName)) {
				flag = true;
				log.info("isHasAPK  *  true");
			}
		}		
		
		return flag;
	}
	
	/*
	 * install app
	 */
	public void installApk(String apk) throws Exception {
		List<String> apkInfo = getAPKInfo(apk);		
		List<String> deviceList = device.getDeviceList();
		String packageName = apkInfo.get(0);
		for(int i=0;i>deviceList.size();i++) {
			if(isHasAPK(deviceList.get(i),packageName)) {
				uninstallApk(deviceList.get(i),packageName);
			}
			installApk(deviceList.get(i),apk);
		}
		
	}
	
	/*
	 * install app
	 */
	private void installApk(String deviceName,String apkFile) throws Exception {
		List<String> cmd = dos.execCmdConsole("adb -s "+deviceName+" install "+apkFile);
		for(int j=0;j<cmd.size();j++) {
			if(cmd.get(j).trim().contains("Success")) {
				System.out.println(deviceName+" install apk success!");
				log.info(deviceName+" install apk success!");
			} else {				
				System.out.println(deviceName+" install apk failure!");
				log.info(deviceName+" install apk failure!");
			}
		}
	}
	
	/*
	 * uninstall app
	 */
	private void uninstallApk(String deviceName,String packageName) throws Exception {
		List<String> cmd = dos.execCmdConsole("adb -s "+deviceName+" uninstall "+packageName );
		
		for(int j=0;j<cmd.size();j++) {
			if(cmd.get(j).trim().contains("Success")) {
				System.out.println(deviceName+" uninstall apk success!");
				log.info(deviceName+" uninstall apk success!");
			} else {				
				System.out.println(deviceName+" uninstall apk failure!");
				log.info(deviceName+" uninstall apk failure!");
			}
		}
	}
	
	/*
	 * start app
	 */
	public void startApplication(String apk) throws Exception  {
		
		List<String> apkInfo = getAPKInfo(apk);		
		List<String> deviceList = device.getDeviceList();
		String packageName = apkInfo.get(0);
		String activityName = apkInfo.get(3);
		for(int i=0;i>deviceList.size();i++) {
			startApplication(deviceList.get(i),packageName,activityName);
		}
	}
	
	private void startApplication(String deviceName,String packageName,String activityName) throws Exception {
		List<String> cmd = dos.execCmdConsole("adb -s "+deviceName+" shell am start -W -n "+packageName+"//"+activityName);
		for(int j=0;j<cmd.size();j++) {
			if(cmd.get(j).trim().contains("Complete")) {
				System.out.println(deviceName+" start app success!");
				log.info(deviceName+" start apk success!");
			} else {				
				System.out.println(deviceName+" start app failure!");
				log.info(deviceName+" start apk failure!");
			}
		}
	}
	
	/*
	 * clear 
	 */
	public void clearAppmem(String apk) throws Exception  {
		
		List<String> apkInfo = getAPKInfo(apk);		
		List<String> deviceList = device.getDeviceList();
		String packageName = apkInfo.get(0);
		for(int i=0;i>deviceList.size();i++) {
			dos.execCmd("adb -s "+deviceList.get(i)+" shell pm clear "+packageName);
		}
	}
	
	/*
	 * quite app
	 */
	public void stopApplication(String deviceName,String packageName) throws Exception {
		List<String> cmd = dos.execCmdConsole("adb -s "+deviceName+" shell am force-stop "+packageName);
		
	}
	
}
