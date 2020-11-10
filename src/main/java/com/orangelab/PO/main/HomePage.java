package com.orangelab.PO.main;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



import io.appium.java_client.AppiumDriver;

public class HomePage extends MainPage {
	public HomePage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub		
	}

	public static Logger log = Logger.getLogger(HomePage.class);
	
	// 用户账号ID
	public static final String USEID_XPATH = "//*[contains(@text,'ID:') or contains(@name,'ID:')]";
	// 游客账号
	public static final String GUEST_XPATH = "//*[contains(@text,'遊客')  or contains(@text,'游客') or (@name='游客')]";
	public static final String TOLOGIN_XPATH = "//*[(@text='去登录') or (@name='去登录')]";
	//新手向导按钮
	public static final String GUIDE_XPATH = "//android.view.ViewGroup[2]/android.widget.ImageView";
	//PK小游戏
	public static final String GAME_PK_XPATH = "//*[contains(@text,'PK小')]";	
	public static final String SEARCH_XPATH = "//*[(@text='搜索') or (@name='搜索')]";
	public static final String CREATE_XPATH = "//*[(@text='创建') or (@name='创建')]";
	public static final String HELP_XPATH = "//*[(@text='帮助') or (@name='帮助')]";
	public static final String RANKINGS_XPATH = "//*[(@text='排行') or (@name='排行')]";
	public static final String NOTICE_XPATH = "//*[(@text='公告') or (@name='公告')]";
	public static final String TASK_XPATH = "//*[(@text='任务') or (@name='任务')]";
	public static final String FAMILY_XPATH = "//*[(@text='家族') or (@name='家族')]";
	public static final String SHOP_XPATH = "//*[(@text='商城') or (@name='商场')]";
	public static final String GAME_CENTER = "//*[(@text='大厅') or (@name='大厅')]";
	public static final String VOICE_PAIR = "//*[(@text='语音速配') or (@name='语言速配')]";
	
	// 进入首页页面
    public void toHomePage() {    	
    	int times = 5;
    	while(times>=0) {
			if(isHomePage()){	
				return;
			} else {
				toMainPage();
				clickHome();
				times--;
			}
    	}
    }
    public boolean isHomePage() {
    	if(isElementsExist(By.xpath(USEID_XPATH)) && isElementsExist(By.xpath(HOMETAB_XPATH))){	
			return true;
		} 
    	return false;
    }
    // 进入游客首页页面
    public void toGuestHomePage() {
    	int times = 3;
    	while(times>=0) {
			if(isGuestHomePage()){	
				return;
			} else {
				toGuestLogin();
				times--;
			}
    	}
    }
    /*
     * 	判断是否为游客登录主页
     * 	return： true-游客登录首页；false-未进入游客登录首页
     */
    public boolean isGuestHomePage() {
    	if(isElementsExist(By.xpath(HOMETAB_XPATH)) && isElementsExist(By.xpath(GUEST_XPATH))){	
			return true;
		} 
    	return false;
    }
    
    // 新手向导
    public void closeGuide() {
    	while(isNewGuide()){				
    		WebElement home = FindXpath(GUIDE_XPATH);					
	    	press(home);
		}
    }
    // 判断是否进入新手向导
    public boolean isNewGuide() {
    	if(isElementsExist(By.xpath(GUIDE_XPATH)) && !isElementsExist(By.xpath(HOMETAB_XPATH)) && !isElementsExist(By.xpath(COUNTRY_XPATH))) {
    		return true;
    	} 
    	return false;
    }
    // 点击PK小游戏
    public void clickPKGame() {
    	WebElement pk = FindXpath(GAME_PK_XPATH);					
    	press(pk);
    }
    // 点击搜索
    public void clickSearch() {
    	WebElement el = FindXpath(SEARCH_XPATH);					
    	press(el);
    }
    // 点击创建
    public void clickCreate() {
    	WebElement el = FindXpath(CREATE_XPATH);					
    	press(el);
    }
    // 点击帮助
    public void clickHelp() {
    	WebElement el = FindXpath(HELP_XPATH);					
    	press(el);
    }
    // 点击排行
    public void clickRankings() {
    	WebElement el = FindXpath(RANKINGS_XPATH);					
    	press(el);
    }
    // 点击公告
    public void clickNotice() {
    	WebElement el = FindXpath(NOTICE_XPATH);					
    	press(el);
    }
    // 点击任务
    public void clickTask() {
    	WebElement el = FindXpath(TASK_XPATH);					
    	press(el);
    }
    // 点击家族
    public void clickFamily() {
    	WebElement el = FindXpath(FAMILY_XPATH);					
    	press(el);
    }
    // 点击商城
    public void clickShop() {
    	WebElement el = FindXpath(SHOP_XPATH);					
    	press(el);
    }
    // 点击游戏大厅
    public void clickGameCenter() {
    	WebElement el = FindXpath(GAME_CENTER);					
    	press(el);
    }
    // 点击语音速配
    public void clickVoicePair() {
    	WebElement el = FindXpath(VOICE_PAIR);					
    	press(el);
    }
    // 点击去登录
    public void clickLogin() {
    	WebElement el = FindXpath(TOLOGIN_XPATH);					
    	press(el);
    }
    
    public boolean isToLoginPopup() {
    	if(isElementsExist(By.xpath(TOLOGIN_XPATH))) {
    		return true;
    	}
    	return false;
    }
}
