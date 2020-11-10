package com.orangelab.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.orangelab.config.Constants;
import com.orangelab.util.ConfigUtil;
import com.orangelab.util.Device;
import com.orangelab.util.DosCmd;
import com.orangelab.util.FileUtil;
import com.orangelab.util.Port;
import com.orangelab.util.XmlUtil;

public class AppiumServer{
	
	private List<Integer> appiumPortList = new ArrayList<Integer>();
	private List<Integer> bootstrapPortList = new ArrayList<Integer>();;
	private List<String> deviceList = new ArrayList<String>();;
	private List<String> deviceVersion = new ArrayList<String>();;
	private Port port;
	private DosCmd dos;
	private Device device;
	
	public AppiumServer(Port port, DosCmd dos, Device device) {		
		this.port = port;
		this.dos = dos;
		this.device = device;
	}
	
	public AppiumServer(DosCmd dos) {		
		this.dos = dos;
	}
	
	/*
	 * get port for devices
	 * @param start , the first port number
	 * @return List<Integer>
	 */
	private List<Integer> getPortList(int start, int deviceTotal) throws IOException, InterruptedException{		
		List<Integer> portList = port.GeneraProtList(start, deviceTotal);
		return portList;
	}
			
	/*
	 * execute start appium server dos command
	 * @return List<String>
	 */
	public List<String> GeneratServerCommand() throws Exception {		
		deviceList = device.getDeviceList();
		int deviceCount = deviceList.size();
		appiumPortList = getPortList(4723,deviceCount);
		bootstrapPortList = getPortList(2233,deviceCount);
		
		List<String> commandList = new ArrayList<String>();
		for(int i=0;i<deviceList.size();i++) {
			String filepath = ConfigUtil.getProperty("appiumLog.dir", Constants.CONFIG_COMMON)+deviceList.get(i)+".log";
			String file = FileUtil.createFile(filepath);
			String command = "appium -a 127.0.0.1 -p "+appiumPortList.get(i)+" -bp "+bootstrapPortList.get(i)+" -U "+deviceList.get(i)+" --session-override >"+file;
			commandList.add(command);
		}		
		return commandList;
	}
	
	public String GeneratServerCommand(List<String> param) throws Exception {			
		String port = param.get(0);
		String bootsPort = param.get(1);
		String deviceName = param.get(2);
		String filepath = ConfigUtil.getProperty("appiumLog.dir", Constants.CONFIG_COMMON)+deviceName+".log";
		String file = FileUtil.createFile(filepath);
		String command = "appium -a 127.0.0.1 -p "+port+" -bp "+bootsPort+" -U "+deviceName+" --session-override >"+file;
			
		return command;
	}
	
	public List<String> init() throws Exception {
		List<String> startCommand = GeneratServerCommand();
		deviceVersion = device.getDeviceVersion(deviceList);
		XmlUtil.creatDeviceXml(deviceList,appiumPortList,deviceVersion);
		XmlUtil.creatTestngXml(deviceList);
		return startCommand;
	}
	
	/*
	 * start appium Server
	 * @return boolean , true is success  false is failure
	 */	
	public void StartAppiumServer(String cmd) {			
		dos.execCmd(cmd);		
	}
	
	/*
	 * stop appium server
	 * @return boolean, true is success  false is failure
	 */
	public boolean StopAppiumServer() throws InterruptedException {
		return dos.killServer("node.exe");
	}
		
	public static void main() throws Exception {
		AppiumServer server = new AppiumServer(new Port(), new DosCmd(), new Device());
		server.StopAppiumServer();	
		server.init();
		/*
		AppiumServer server = new AppiumServer(new Port(), new DosCmd(), new Device());
		server.StopAppiumServer();	
		List<String> startCommand = server.init();
		if(startCommand.size()>0) {
			for(String s:startCommand) {				
				Thread appiumStart = new Thread(new AppiumRunnable(server,s));
				appiumStart.start();
			}
		}		
		
		Thread startTestng = new Thread(new testngRunnable(server));
		startTestng.start();*/
	}		
	
	public void StartAppiumServer(List<String> args) throws Exception {	
		AppiumServer server = new AppiumServer(new Port(), new DosCmd(), new Device());
		String s = GeneratServerCommand(args);
		Thread appiumStart = new Thread(new AppiumRunnable(server,s));
		appiumStart.start();
	}
	
	public void StartTestng() {
		dos.execCmd("mvn -e test");	
	}
}

class AppiumRunnable implements Runnable{
	AppiumServer server = null;	
	String command = null;
	public AppiumRunnable(AppiumServer aServer,String cmd) {
		server = aServer;		
		command = cmd;
	}		
	public void run() {
		// TODO Auto-generated method stub
		try {
			server.StartAppiumServer(command);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

class testngRunnable implements Runnable{
	AppiumServer server = null;	
	String command = null;
	public testngRunnable(AppiumServer aServer) {
		server = aServer;	
	}		
	public void run() {
		// TODO Auto-generated method stub
		try {
			server.StartTestng();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
