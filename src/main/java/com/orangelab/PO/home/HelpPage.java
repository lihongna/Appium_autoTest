package com.orangelab.PO.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.orangelab.PO.main.HomePage;

import io.appium.java_client.AppiumDriver;

public class HelpPage extends HomePage{
	public HelpPage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
	}
	public static final String HELPTITLE_XPATH = "//*[(@text='新手帮助') or (@name='新手帮助')]";
	public static final String BACKHOME_XPATH = "//*[(@text='返回主界面') or (@name='返回主界面')]";
	
	// 点击返回主界面
	public void clickBackHome() {
		WebElement el = FindXpath(BACKHOME_XPATH);			
		press(el);
	}
	public void toHelpMainPage() {
		int times = 3;
		while(times>=0) {
			if(isHelpMainPage()) {
				return;
			} else {
				toHomePage();
				clickHelp();			
			}
			times--;
		}
	}
	public boolean isHelpMainPage() {
		if(isElementsExist(By.xpath(HELPTITLE_XPATH))) {
			return true;
		}
		return false;
	}

}
