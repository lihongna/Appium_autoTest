package com.orangelab.util;

import java.util.ArrayList;
import java.util.List;

public class Port {
	private DosCmd execDos = new DosCmd();
	/*
	 * Verify the port pccupancy
	 * @param portNum
	 * @return boolean
	 */
	public boolean isPortUsed(int portNum) {
		List<String> portRes = new ArrayList<String>();
		boolean flag = true;
		try {
			portRes = execDos.execCmdConsole("netstat -aon |findstr "+ String.valueOf(portNum));
			if(portRes.size()>0) {
				flag = true;
			}else {
				flag = false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/*
	 * Generated based on the number of devices available ports
	 * @param portStart, starting ports
	 * @param deviceTotal,total number of devices
	 * @return List<Integer>
	 */
	public List<Integer> GeneraProtList(int portStart , int deviceTotal){
		List<Integer> portList = new ArrayList<Integer>();
		while(portList.size() != deviceTotal) {
			if(portStart >= 0 && portStart <= 65535) {
				if(!isPortUsed(portStart)) {
					portList.add(portStart);
				}
				portStart++;
			}
		}
		return portList;
	}
}
