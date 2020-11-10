package com.orangelab.PO.me;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.orangelab.PO.main.MePage;

import io.appium.java_client.AppiumDriver;

public class SystemSettingPage extends MePage {
	public SystemSettingPage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
	}	
	
	public static Logger log = Logger.getLogger(SystemSettingPage.class);	
	
	public static final String QUITE_XPATH = "//*[(@text='退出登录') or (@name='退出登录')]";
	public static final String SHARE_XPATH = "//*[(@text='分享给好友') or (@name='分享给好友')]";
	
	/*
	 * 	点击退出登录按钮
	 */
	public void clickQuiteLoginButton() {
		WebElement qlogin = FindXpath(QUITE_XPATH);			
		press(qlogin);
	}
	
	// 进入系统设置页面
	public void toSystemSetPage() {
		int times = 5;
		while(times>=0) {
			if(isSystemSetPage()) {
				return;
			} else {
				toMePage();
				clickSystemSetting();
				times--;
			}
		}
	}
	
	public boolean isSystemSetPage() {
		if(isElementsExist(By.xpath(QUITE_XPATH)) && isElementsExist(By.xpath(SYSTEMSET_XPATH)) && isElementsExist(By.xpath(SHARE_XPATH))) {
			return true;
		} 
		return false;
	}
}
