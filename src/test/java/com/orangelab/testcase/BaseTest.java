package com.orangelab.testcase;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import com.orangelab.PO.AndroidCommonPage;
import com.orangelab.config.Config;
import com.orangelab.service.AppiumServer;

import com.orangelab.util.Device;
import com.orangelab.util.DosCmd;
import com.orangelab.util.Port;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class BaseTest {
	public static Logger log = Logger.getLogger(BaseTest.class);
	public AppiumServer server = new AppiumServer(new Port(), new DosCmd(), new Device());
	public static List<Map<String,Object>> driverlist = new ArrayList<Map<String,Object>>();
	Map<String,Object> m =new HashMap<String,Object>();
	public static AppiumDriver driver;	
	@Parameters({"port","platformVersion","udid"})
	//@BeforeSuite
	public void startServer(String port,String platformVersion,String udid) {
		System.out.println("TestBase * BeforeSuite");	
		List<String> param = new ArrayList();
		param.add(port);
		param.add(platformVersion);
		param.add(udid);
		//server.StartAppiumServer(param);*/
	}
			
	@Parameters({"port","platformVersion","udid","systemPort","deviceName"})	
	@BeforeTest
	public void BeforeTest(String port,String platformVersion,String udid,String systemPort,String deviceName) throws Exception {
		System.out.println("TestBase * BeforeTest:port="+port+",pVersion="+platformVersion+",udid="+udid);	
		if(Config.platformName.equals("IOS")) {
			setIOSDriver(udid, platformVersion, port,systemPort,deviceName);
		} else {
			setAndroidDriver(udid, platformVersion, port,systemPort,deviceName);
		}
	}
	
	@Parameters({"udid"})	
	@BeforeMethod
	public void closeSystemWindow(String udid) throws InterruptedException {
		System.out.println("TestBase * BeforeMethod");	
		if(Config.platformName.equals("Android")) {
			driver = getDriver(udid);
			AndroidCommonPage common = new AndroidCommonPage(driver,udid);
			common.closedWindow();
		}
	}
	
	@Parameters({"port"})
	@AfterSuite
	public void tearDown(String port) throws InterruptedException {
		System.out.println("TestBase * AfterSuite:");
		//server.StopAppiumServer();
	}
	
	private void setAndroidDriver(String udid, String platformVersion, String port, String systemPort,String deviceName){		
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android"); 
        capabilities.setCapability("udid", udid);  //  MFRSIBM799999999
        capabilities.setCapability("deviceName", deviceName); 
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("sessionOverride", true);
        capabilities.setCapability("unicodeKeyboard", true); 
        capabilities.setCapability("resetKeyboard", true);  
        capabilities.setCapability("systemPort", systemPort);
        capabilities.setCapability("noReset", true);   //重置应用状态
        capabilities.setCapability("appPackage", "cn.orangelab.werewolf");  // packageName
        capabilities.setCapability("appActivity", "com.game_werewolf.FakeLauncherActivity"); // MainActivityName
        System.out.println("http://127.0.0.1:"+port+"/wd/hub");
        log.info("http://127.0.0.1:"+port+"/wd/hub");	       
		
        try {
			driver=new AndroidDriver<>(new URL("http://127.0.0.1:"+port+"/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        setDriverList(driver,udid);

	}	
	private void setIOSDriver(String udid, String platformVersion, String port, String systemPort,String deviceName){		
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "IOS"); 
        capabilities.setCapability("udid", udid);  //  MFRSIBM799999999
        capabilities.setCapability("deviceName", deviceName); 
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("sessionOverride", true);
        capabilities.setCapability("unicodeKeyboard", true); 
        capabilities.setCapability("resetKeyboard", true);  
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("automationName", "appium");  //XCUITest
        //capabilities.setCapability("systemPort", systemPort);
        capabilities.setCapability("noReset", true);   // 重置应用状态
        capabilities.setCapability("bundleId", "cn.orangelab.werewolf");  // packageName
        capabilities.setCapability("autoAcceptAlerts", true);
        System.out.println("http://127.0.0.1:"+port+"/wd/hub");
        log.info("http://127.0.0.1:"+port+"/wd/hub");	       
		
        try {
			driver=new IOSDriver<>(new URL("http://127.0.0.1:"+port+"/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
        setDriverList(driver,udid);

	}	
	public AppiumDriver getDriver(String udid) {
		AppiumDriver driverObj = null;
		
		if(driverlist.size()>0) {
			for(Map<String,Object> d : driverlist) {
				if(d.get("device").equals(udid)) {					
				    driverObj = (AppiumDriver)d.get("driver");
				    return driverObj;
				}
			}
		} 		
		return driverObj;
	}
	
	private void setDriverList(AppiumDriver driverObj,String udid) {
		Map<String,Object> m =new HashMap<String,Object>();
		if(driverlist.size()>0) {
			for(Map<String,Object> d : driverlist) {
				if(!d.get("device").equals(udid)) {
					m.put("device", udid);
				    m.put("driver", driverObj);
				    driverlist.add(m);
				    System.out.println(driverlist);
				    return;
				} else if (d.get("device").equals(udid)){
					m.put("driver", driverObj);
				}
			}
		} else {
		 m.put("device", udid);
	     m.put("driver", driverObj);
	     driverlist.add(m);
		}
		System.out.println(driverlist);
	}
}
