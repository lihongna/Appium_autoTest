package com.orangelab.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.orangelab.config.Config;

public class Device {
	private static Logger log = Logger.getLogger(Device.class);
	private DosCmd dos = new DosCmd();
	
	/*
	 * get used device's name 
	 * @return List<String>
	 */
	public List<String> getDeviceList() throws IOException, InterruptedException{
		List<String> deviceList = dos.execCmdConsole("adb devices");
		List<String> deviceRes = new ArrayList<String>();
		if(deviceList.size()>1) {
			for(int i=1; i<deviceList.size()-1; i++) {
				String devicesInfo[] = deviceList.get(i).split("\t");
				if(devicesInfo[1].trim().equals("device")) {
					deviceRes.add(devicesInfo[0].trim());
				}
			}
		} else {
			System.out.println("当前没有设备或设备连接状态不对");
			log.error("当前没有设备或设备连接状态不对");
		}
		return deviceRes;
	}
	
	/*
	 * 
	 */
	public int getDevicesCount() throws IOException, InterruptedException {
		int count = 0;
		List<String> deviceList = dos.execCmdConsole("adb devices");
		if(deviceList.size()>1) {
			for(int i=1; i<deviceList.size()-1; i++) {
				String devicesInfo[] = deviceList.get(i).split("\t");
				if(devicesInfo[1].trim().equals("device")) {
					count++;
				}
			}
		} else {
			System.out.println("当前没有设备或设备连接状态不对");
			log.error("当前没有设备或设备连接状态不对");
		}
		return count;
	}
	
	/*
	 * 
	 */
	public List<String> getDeviceVersion(List<String> deviceList) throws IOException, InterruptedException{			
		List<String> commandList = new ArrayList<String>();
		List<String> version = new ArrayList<String>();
		for(int i=0;i<deviceList.size();i++) {
			String command = "adb -s "+deviceList.get(i)+" shell getprop ro.build.version.release";
			version = dos.execCmdConsole(command);
			commandList.add(version.get(0));
		}
		return commandList;		
	}
}
