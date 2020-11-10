package com.orangelab.PO.main;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;

public class MessagePage extends MainPage {
	
	public MessagePage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
	}

	// 消息tab
	public static final String MTAB_XPATH = "//*[(@text='消息') or (@name='消息')]";
	// 好友tab
	public static final String FTAB_XPATH = "//*[(@text='好友') or (@name='好友')]";
	// 新朋友
	public static final String NEWFRIENDTAB_XPATH = "//*[(@text='新朋友') or (@name='新朋友')]";	
	// 进入消息的页面
    public void toMessagePage() {    	
    	int times = 5;
    	while(times>=0) {
			if(isMessagePage()){	
				return;
			} else {
				toMainPage();
				clickMessage();
				times--;
			}
    	}
    }
    
    public boolean isMessagePage() {    	
    	if(isElementsExist(By.xpath(MESSAGE_XPATH)) && isElementsExist(By.xpath(MTAB_XPATH)) && isElementsExist(By.xpath(FTAB_XPATH))){	
			return true;
		}
    	return false;
    }
}
