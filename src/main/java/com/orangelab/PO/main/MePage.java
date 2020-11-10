package com.orangelab.PO.main;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class MePage extends MainPage {
	public MePage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
	}	

	public static Logger log = Logger.getLogger(MePage.class);
	//我的战绩
	public static final String GAME_RECORD_XPATH = "//*[contains(@text,'我的战绩')]";
	//我的专辑
	public static final String MY_VIDEO_XPATH="//*[(@text='我的专辑') or (@name='我的专辑')]";
	//系统设置
	public static final String SYSTEMSET_XPATH = "//*[(@text='系统设置') or (@name='系统设置')]";
	//系统设置
	public static final String FAMILY_XPATH = "//*[(@text='所属家族') or (@name='所属家族')]";
	
	// 进入我的页面
    public void toMePage() {    	
    	int times = 5;
    	while(times>=0) {
			if(isMePage()){	
				return;
			} else {
				toMainPage();
				clickMe();
				times--;
			}
    	}
    }
    public boolean isMePage() {
    	if(isElementsExist(By.xpath(ME_XPATH))&&isElementsExist(By.xpath(MY_VIDEO_XPATH))){	
			return true;
		}
    	return false;
    }
    
    // 点击系统设置
    public void clickSystemSetting() {
    	
    	if(!isElementsExist(By.xpath(SYSTEMSET_XPATH))) {
    		swipePageUtilFoundElement(By.xpath(SYSTEMSET_XPATH), "UP", 3);		
		}		
		WebElement sys = FindXpath(SYSTEMSET_XPATH);	
		press(sys);
    }
    // 点击所属家族
    public void clickFamily() {
    	WebElement el = FindXpath(FAMILY_XPATH);	
		press(el);
    }
    
    
}
