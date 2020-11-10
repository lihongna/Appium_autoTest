package com.orangelab.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class DosCmd {
	private static Logger log = Logger.getLogger(DosCmd.class);
	/*
	 * execute dos command
	 * @param cmdString, dos command
	 * @return boolean, success is true  failure is false
	 */
	public boolean execCmd(String cmdString) {		
		try {
			Runtime p = Runtime.getRuntime();
			Process process = p.exec("cmd /c " + cmdString);
			System.out.println(cmdString);
			Thread t_in = new Thread(new InputStreamRunnable(process.getInputStream()));
		    t_in.start();		    
		    Thread t_err = new Thread(new InputStreamRunnable(process.getErrorStream()));
		    t_err.start();
			try {
				process.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				log.error(("execCmd " + cmdString + " failure"),e);
				e.printStackTrace();				
			}		
			process.destroy();
			log.debug("execCmd " + cmdString + " success");
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//logger.debug("execCmd " + cmdString + " failure");
			log.error(("execCmd " + cmdString + " failure"),e);
			return false;
		}
	}
	
	/*
	 * excute dos command 
	 * @param cmdSring, dos command
	 * @return List<String>
	 */
	public List<String> execCmdConsole(String cmdString){
		List<String> dosRes = new ArrayList<String>();
		try {
			Runtime p = Runtime.getRuntime();
			Process process;
			
			process = p.exec("cmd /c " + cmdString);
			
			System.out.println(cmdString);
			InputStream in = process.getInputStream();
			    
		    Thread t_err = new Thread(new InputStreamRunnable(process.getErrorStream()));
		    t_err.start();
		    
			BufferedReader inr = new BufferedReader(new InputStreamReader(in,"GBK"));
			String line = null;
			while((line = inr.readLine()) != null ) {
				dosRes.add(line);
			}
			
			in.close();
			inr.close();
			try {
				process.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				log.error(("execCmdConsole " + cmdString + " failure"),e);
				e.printStackTrace();
			}		
			process.destroy();
			log.debug("execCmdConsole " + cmdString + " success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(("execCmdConsole " + cmdString + " failure"),e);
			e.printStackTrace();
			
		}
		return dosRes;
	}
	
	/*
	 * kill server by pid of server
	 * @param pid , pid of server
	 * @return boolean
	 */
	public boolean killServer(int pid) throws InterruptedException {
		if(execCmd("taskkill -F -PID " + String.valueOf(pid))) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * kill server by name of server,the method can kill the same server name
	 * @param serverName, the server's name
	 * @retrun boolean, true is success  false is failure
	 */
	public boolean killServer(String serverName) throws InterruptedException {
		if (execCmd("taskkill -F -IM " + serverName)) {			
			return true;
		} else {
			return false;
		}
	}
	
		
	/*
	 * 	读取InputStream的线程
	 */
	class InputStreamRunnable implements Runnable{
		BufferedReader br = null;
		
		public InputStreamRunnable(InputStream is) throws UnsupportedEncodingException {
			br = new BufferedReader(new InputStreamReader(new BufferedInputStream(is),"GBK"));			
		}
		
		public void run() {
			// TODO Auto-generated method stub
			String line = null;
			int lineNum = 0;
			try {
				while((line=br.readLine())!=null){
					lineNum++;
				}				
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

}
