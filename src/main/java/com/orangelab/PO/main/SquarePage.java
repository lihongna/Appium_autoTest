package com.orangelab.PO.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class SquarePage extends MainPage{
	
	public SquarePage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
	}

	// 推荐tab
	public static final String RECOMMENDTAB_XPATH = "//*[(@text='推荐') or (@name='推荐')]";
	// 好声音tab
	public static final String VOICETAB_XPATH = "//*[(@text='好声音') or (@name='好声音')]";
	// 关注tab
	public static final String ATTENTIONTAB_XPATH = "//*[(@text='关注') or (@name='关注')]";
	
	//点击推荐
	public void clickRecommend() {
		WebElement el = FindXpath(RECOMMENDTAB_XPATH);					
    	press(el);
	}
	
	//点击好声音
	public void clickGoodvoice() {
		WebElement el = FindXpath(VOICETAB_XPATH);					
    	press(el);
	}
	
	//点击关注
	public void clickAttention() {
		WebElement el = FindXpath(ATTENTIONTAB_XPATH);					
    	press(el);
	}
	
	// 进入广场的页面
    public void toSquarePage() {    	
    	int times = 5;
    	while(times>=0) {
			if(isSquarePage()){	
				return;
			} else {
				toMainPage();
				clickSquare();
				times--;
			}
    	}
    }
    
    public boolean isSquarePage() {
    	if(isElementsExist(By.xpath(SQUARE_XPATH)) && isElementsExist(By.xpath(VOICETAB_XPATH)) && isElementsExist(By.xpath(ATTENTIONTAB_XPATH))){	
			return true;
		}
    	return false;
    }
}
