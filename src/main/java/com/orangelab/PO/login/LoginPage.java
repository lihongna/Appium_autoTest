package com.orangelab.PO.login;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.orangelab.PO.AndroidCommonPage;
import com.orangelab.PO.main.HomePage;
import com.orangelab.PO.me.SystemSettingPage;
import com.orangelab.util.XmlUtil;

import io.appium.java_client.AppiumDriver;

public class LoginPage extends AndroidCommonPage {
	public static Logger log = Logger.getLogger(LoginPage.class);
	private AppiumDriver driver;
	public final int registerNum = 0;
	public final int phoneNum = 1;
	public final int password = 2;
	private String udid;
	public LoginPage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.udid = udid;
	}
	

	// find 手机号码登录   by xpath (RN)
    public static final String PHONE_XPATH = "//*[(@text='手机号登录') or (@name='手机号登录')]";
    
    // find 游客模式登录  by xpath (RN)
    public static final String GUEST_XPATH = "//*[(@text='游客模式登录') or (@name='游客模式登录')]";
    
    // find QQ登录 by xpath (RN)
    public static final String QQ_XPATH = "//*[contains(@text,'QQ') or (@name='QQ登录')]";
    
    // find 微信登录  by xpath (RN)
    public static final String WCHAT_XPATH = "//*[contains(@text,'微信') or (@name='微信登录')]";
    
    // find facebook登录  by text (RN)
    public static final String FB_NAME = "text(\"Facebook\")";
    
    public static final String MAINLOGIN_XPATH = "//*[(@text='請選擇以下方式登錄')  or (@text='请选择以下方式登录') or (@name='请选择以下方式登录')]";
    public static final String COUNTRY_XPATH = "//*[contains(@text,'國家')  or contains(@text,'国家') or (@name='国家/地区')]";
    
	public static final String EDITTEXT_XPATH = "//android.widget.EditText";	
	public static final String IOS_EDITTEXT_XPATH = "//XCUIElementTypeTextField";
    
    // 点击手机号登录
    public void clickPhoneLogin() {    	
    	WebElement el = FindXpath(PHONE_XPATH);    	
    	press(el);
    }
    
    // 点击QQ登录页面
    public void clickQQLogin() {    	
    	WebElement el = FindXpath(QQ_XPATH);    	
    	press(el);
    }
     
    // 点击WeChat登录页面
    public void clickWeChatLogin() {    	
    	WebElement el = FindXpath(WCHAT_XPATH);    	
    	press(el);
    }
    
    // 点击Facebook登录页面
    public void clickFacebookLogin() {    	
    	WebElement el = FindElement(FB_NAME);			
    	press(el);
    }
    
    // 点击Guest登录页面
    public void clickGuestLogin() {    	   	
    	WebElement el = FindXpath(GUEST_XPATH);    	
    	press(el);
    }
    
    /*
     * 	判断当前在登录主界面
     */
    public boolean isMainLoginPage() {
    	if(!isElementsExist(By.xpath(COUNTRY_XPATH)) && isElementsExist(By.xpath(MAINLOGIN_XPATH))) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    // 进入登录页面
    public void toMainLoginPage() {    	
    	// 如果不在登录页面		
    	int times = 5;
    	while(times>=0) {
			if(isMainLoginPage()){				
				return;
			} else if(isAlreadyLogin()) {
				QuitLogin();
			} else {
				pressBackKey();				
			}
			times--;
    	}
    }
    
    // 进入游客登录
    public void toGuestLogin() {    	
    	// 如果不在登录页面		
    	int times = 5;
    	while(times>=0) {
			if(isMainLoginPage()){				
				clickGuestLogin();
				return;
			} else {
				toMainLoginPage();
				times--;
			}
    	}
    }
    
    /*
     * 	判断已经登录
     */
    public boolean isAlreadyLogin() {
    	if(!isElementsExist(By.xpath(MAINLOGIN_XPATH))) {
    		return true;
    	}
    	return false;
    }
    
    /*
     * 	退出登录
     */
    public void QuitLogin() {
    	SystemSettingPage sys = new SystemSettingPage(this.driver,this.udid);
    	HomePage home = new HomePage(this.driver,this.udid);
    	if(home.isGuestHomePage()) {
    		home.clickMe();
    		home.clickLogin();
    	} else {
			sys.toSystemSetPage();
			sys.clickQuiteLoginButton();
    	}
    }
    
    public List<String> getParam(String uid) {
		List<String> param = XmlUtil.readPamaXML(uid);
		return param;
	}
    
    public String getNumber(String uid) {
		return getParam(uid).get(phoneNum);
	}
	
    public String getRegisterNum(String uid) {
		return getParam(uid).get(registerNum);
	}
	
    public String getPwd(String uid) {
		return getParam(uid).get(password);
	}
}
