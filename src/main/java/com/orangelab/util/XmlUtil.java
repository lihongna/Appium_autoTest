package com.orangelab.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.orangelab.config.Config;


public class XmlUtil {
	private static String deviceFile = Config.devicePath;
	private static String testngFile= Config.testNGFile;
	private static String testngClasses = Config.classFile;
	private static String paramFile = "param.xml";
	private static String paramFile_tmp = "data\\param.xml";
	private DosCmd dos = new DosCmd();
	/*
	 * 
	 */
	public static List<String> readXML(String filePath) throws DocumentException {
		
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new File(filePath));
		
		Element root = document.getRootElement();
		
		@SuppressWarnings("unchecked")
		List<Element> devicesList = root.elements("deviceId");
		List<String> deviceData = new ArrayList<String>();
		
		for(Element e : devicesList) {
			for(Iterator iter = e.elementIterator(); iter.hasNext();) {
				Element el = (Element)iter.next();
				deviceData.add(el.getText());
			}
		}
		
		return deviceData;
	}
	
	/*
	 * 
	 */
	public static void creatDeviceXml(List<String> deviceList, List<Integer> appiumPortList,
			List<String> deviceVersion) throws IOException {
		File file = new File(deviceFile);
		FileUtil.createFile(deviceFile);
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("Device");
		root.addAttribute("name", "devicelist");
		if(deviceList.size()>0) {
			for(int j = 0; j < deviceList.size(); j++) {
				Element deviceId = root.addElement("deviceId");
				deviceId.addAttribute("id", String.valueOf(j));
				Element deviceName = deviceId.addElement("deviceName");
				Element dVersion = deviceId.addElement("deviceVersion");
				Element appiumPort = deviceId.addElement("appiumPort");
				
				deviceName.setText(deviceList.get(j));
				appiumPort.setText(String.valueOf(appiumPortList.get(j)));
				dVersion.setText(deviceVersion.get(j));
			}
		}
		FileWriter fileWriter = new FileWriter(deviceFile);
		// 设置美观的缩进格式，便于阅读
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xmlWR = new XMLWriter(new FileOutputStream(file),format);
		xmlWR.setWriter(fileWriter);
		xmlWR.write(document);
		xmlWR.close();
	}
	
	/*
	 * 
	 */
	public static void creatTestngXml(List<String> deviceList) throws Exception {
		File file = new File(testngFile);
		
		int threadCount = deviceList.size();
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("suite");
		root.addAttribute("name", "Suite");
		root.addAttribute("parallel", "tests");
		root.addAttribute("thread-count", String.valueOf(threadCount));
		
		List<String> s=readXML(deviceFile);
		for(int j=0;j<threadCount;j++) {
			Element test = root.addElement("test");
			test.addAttribute("name", deviceList.get(j));
			
			Element parameter_port = test.addElement("parameter");
			parameter_port.addAttribute("name", "port");
			parameter_port.addAttribute("value", s.get(j*3+2));
			
			Element parameter_version = test.addElement("parameter");
			parameter_version.addAttribute("name", "pVersion");
			parameter_version.addAttribute("value", s.get(j*3+1));
			
			Element parameter_uuid = test.addElement("parameter");
			parameter_uuid.addAttribute("name", "uuid");
			parameter_uuid.addAttribute("value", s.get(j*3));
			// 从文件中获取classname
			List<Element> mClasses = getClasses();
			for(int index=0;index<mClasses.size();index++) {
				Element classes = test.addElement("classes");
				List<String> mClass = getClass(mClasses.get(index));
				for(int k=0;k<mClass.size();k++) {
					Element classnote = classes.addElement("class");
					classnote.addAttribute("name", mClass.get(k));
				}
			}
		}
		FileWriter fileWriter = new FileWriter(testngFile);
		// 设置美观的缩进格式，便于阅读
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xmlWR = new XMLWriter(new FileOutputStream(file),format);
		xmlWR.setWriter(fileWriter);
		xmlWR.write(document);
		xmlWR.close();
	}
	
	private static List<Element> getClasses() throws Exception{		
		File f=new File(testngClasses); 
		SAXReader reader = new SAXReader();
		Document doc = reader.read(f);
		Element root = doc.getRootElement();
		List<Element> classes = root.elements();
		
		return classes;
	}
	
	private static List<String> getClass(Element parent) {	
		List<String> mClass = new ArrayList();
		List<Element> foo = parent.elements();
		for (int i=0;i<foo.size();i++) { 
			mClass.add(foo.get(i).getText());			   
		}			
		return mClass;
	}
	
	public static List<String> readPamaXML(String udid)  {
		String uid = "";
		List<String> pamas = new ArrayList<String>();
		SAXReader saxReader = new SAXReader();
		Document document;
		try {
			getParamFile();
			document = saxReader.read(new File(paramFile));			
			Element root = document.getRootElement();
			List<Element> uidList = root.elements("udid");
		
			for(int i=0;i<uidList.size();i++) {
				uid = uidList.get(i).attributeValue("name");
				if(uid.equals(udid)) {
					@SuppressWarnings("unchecked")
					Element pnum = uidList.get(i).element("pNum");
					Element rnum = uidList.get(i).element("regNum");
					Element pwd = uidList.get(i).element("passWrod");					
					pamas.add(rnum.getText());
					pamas.add(pnum.getText());					
					pamas.add(pwd.getText());
					break;
				}
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return pamas;
	}

	public static void updatePamaXML(String udid,String key,String value)  {		
		try {
			String uid = "";
			getParamFile();
			List<String> pamas = new ArrayList<String>();
			SAXReader saxReader = new SAXReader();
	        Document doc = saxReader.read(new File(paramFile));
	        Element root = doc.getRootElement();			
			List<Element> uidList = root.elements("udid");	
			
			for(int i=0;i<uidList.size();i++) {
				uid = uidList.get(i).attributeValue("name");
				if(uid.equals(udid)) {
			        Element el = uidList.get(i).element(key);
			        el.setText(value);
				}
			}
	        	        
	        //写入到文件
	        XMLWriter output = new XMLWriter(new FileWriter(new File(paramFile)));
	        output.write(doc);
	        output.close();
	    } catch (DocumentException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void updateRegisterNum(String udid) {
		try {
			String uid = "";
			getParamFile();
			List<String> pamas = new ArrayList<String>();
			SAXReader saxReader = new SAXReader();
	        Document doc = saxReader.read(new File(paramFile));
	        Element root = doc.getRootElement();			
			List<Element> uidList = root.elements("udid");	
			
			for(int i=0;i<uidList.size();i++) {
				uid = uidList.get(i).attributeValue("name");
				if(uid.equals(udid)) {
			        Element el = uidList.get(i).element("regNum");
			        int num = Integer.parseInt(el.getText());
			        el.setText(String.valueOf(num+1));
				}
			}
	        	        
	        //写入到文件
	        XMLWriter output = new XMLWriter(new FileWriter(new File(paramFile)));
	        output.write(doc);
	        output.close();
	    } catch (DocumentException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void getParamFile() {
		File f = new File(paramFile);
		if(!f.exists()) {
			try {
				FileUtil.copyFile(paramFile_tmp, ".\\");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
