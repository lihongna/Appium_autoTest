package com.orangelab.PO.login;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.orangelab.config.Config;

import io.appium.java_client.AppiumDriver;

public class NumberLoginPage extends LoginPage {
	private String udid;
	public NumberLoginPage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
		this.udid = udid;
	}
	public static Logger log = Logger.getLogger(NumberLoginPage.class);
	// find 手机号登录页面title  by xpath (RN)
    public static final String PHONELOGIN_TITLE = "//*[(@text='手機號登錄')  or (@text='手机号登录') or (@name='手机号登录') ]";
    // find 注册页面title  by xpath (RN)
    public static final String REGISTER_TITLE = "//*[(@text='手機號注冊') or (@text='手机号注册') or (@name='手机号注册')]";
 // find 忘记密码页面title  by xpath (RN)
    public static final String FORGETPASSWORD_TITLE = "//*[(@text='忘記密碼')  or (@text='忘记密码') or (@name='忘记密码')]";
	// find 号码输入框   by xpath (RN)
    public static final String PHONENUMBER_XPATH = "//*[contains(@text,'輸入手機號')  or contains(@text,'输入手机号') or (@name='请输入手机号')]";    
    // find 密码输入框  by xpath (RN)
    public static final String PASSWORD_XPATH = "//*[contains(@text,'請輸入密碼')  or contains(@text,'请输入密码') or (@name='请输入密码')]";    
    // find 登录按钮 by xpath (RN)
    public static final String LOGIN_XPATH = "//*[(@text='登錄')  or (@text='登录') or (@name='登录')]";    
    // find 注册按钮  by xpath (RN)   
    public static final String REGISTER_XPATH = "//*[(@text='注冊')  or (@text= '注册') or (@name='注册')]";    
    // find 忘记密码  by xpath (RN)
    public static final String FORGETPASSWORD_XPATH = "//*[contains(@text,'忘記密碼？')  or contains(@text,'忘记密码？')  or (@name='忘记密码？')]";    
    // find 国家/地区  by xpath (RN)
    //public static final String COUNTRY_XPATH = "//*[contains(@text,'國家')  or contains(@text,'国家') or (@name='国家/地区')]";    
    public static final String COUNTRY_CHINA_XPATH = "//*[contains(@text,'中國')  or contains(@text,'中国') or (@name='中国大陆')]";    
    public static final String COUNTRY_A_XPATH = "//*[(@text='A') or (@name='A')]";
    
    public static final String pleaseInputNumber = "请输入手机号";
    public static final String pleaseInputPassword = "请输入密码";
    public static final String pleaseConfirmInputPassword ="请确认输入的密码";
    public static final String pleaseInputVcode = "请输入验证码";
    public static final String numberOrPasswordErr = "账号或密码不正确";
    public static final String formatErr = "手机号码格式错误";
    public static final String onlySend3times = "24小时只能发送3次";
    
    
    /*
 	 * 	输入手机号
     */
	public void inputPhoneNumber(String pNumer) {
		WebElement phone = FindXpath(PHONENUMBER_XPATH);		
		phone.sendKeys(pNumer);			
	}
	
	/*
	 *	 输入密码
	 */
	public void inputPassword(String pWord) {
		WebElement password = FindXpath(PASSWORD_XPATH);
		password.sendKeys(pWord);		
	}
	
	/*
	 * 	点击登录按钮
	 */
	public void clickLoginButton() {

		WebElement login = FindXpath(LOGIN_XPATH);			
		press(login);
	}
	
	/*
	 * 	修改国家/地区
	 */
	public void cliclChangeCountry(String string){

		if(isElementsExist(string)) {
			return;
		}
		
		WebElement country = FindXpath(COUNTRY_XPATH);	
		press(country);				
		swipePageUtilFoundElement(string, "UP", 5);		
		WebElement china = FindXpath(COUNTRY_CHINA_XPATH);	
		press(china);
	}
	
	/*
	 * 	修改国家/地区
	 */
	public void clickChangeCountry()  {

		if(isElementsExist(By.xpath(COUNTRY_CHINA_XPATH))) {
			return;
		}
		
		WebElement country = FindXpath(COUNTRY_XPATH);	
		press(country);
		
		if(isElementsExist(By.xpath(COUNTRY_A_XPATH))) {
			
			swipePageUtilFoundElement("中", "UP", 5);
			
			WebElement china = FindXpath(COUNTRY_CHINA_XPATH);	
			press(china);
		}
	}
	
	/*
	 * 	点击注册按钮
	 */
	public void clickRegisterButton() {
		
		WebElement register = FindXpath(REGISTER_XPATH);			
		press(register);
	}
	
	/*
	 * 	点击忘记密码按钮
	 */
	public void clickForgetButton() {
		
		WebElement foget = FindXpath(FORGETPASSWORD_XPATH);			
		press(foget);
	}
	
	/*
	 * 	清空编辑框内容
	 */
	public void cleanText() {
		waitTime(1);
		List<WebElement> edit = new ArrayList();
		if(Config.platformName.equals("Android")) {
			edit = findElements(By.xpath(EDITTEXT_XPATH));
		} else {
			edit = findElements(By.xpath(IOS_EDITTEXT_XPATH));
		}
		int total = edit.size();
		for(int i=0; i<total; i++) {
			edit.get(i).clear();
		}
	}
	/*
	 * 	进入手机号码登录页面
	 */
	public void toPhoneLogin() {
		int times = 5;
		while(times>=0) {
			if (isPhoneLoginPage()) {
				return;
			} else {
				toMainLoginPage();
				clickPhoneLogin();
				times--;
			}
		}
	}
	public boolean isPhoneLoginPage() {
		if(isElementsExist(By.xpath(COUNTRY_XPATH)) && isElementsExist(By.xpath(PHONELOGIN_TITLE)) && 
				!isElementsExist(By.xpath(REGISTER_TITLE)) && !isElementsExist(By.xpath(FORGETPASSWORD_TITLE))) {
			return true;
		}
		return false;
	}
	 // 没有登录，直接登录
    public void toNumberLogin() {    	
    	if(!isAlreadyLogin()) {
    		userLogin();
    	}
    }
	/*
	 * 	使用手机号登录
	 */
	public void userLogin() {	
		List<String>param=getParam(this.udid);
		String number = param.get(phoneNum);
		String pwd = param.get(password);
    	// 进入手机登录页面
		toPhoneLogin();
		// 切换国家/地区
		clickChangeCountry();
		// 输入手机号
		inputPhoneNumber(number);
		// 输入密码
		inputPassword(pwd);
		// 点击登录
		clickLoginButton();	   
		// 关闭系统提示
		closedWindow(); 
    	
	}
}
