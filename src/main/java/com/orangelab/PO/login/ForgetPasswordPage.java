package com.orangelab.PO.login;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;

public class ForgetPasswordPage extends NumberLoginPage {
	public ForgetPasswordPage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
	}

	public static Logger log = Logger.getLogger(RegisterPage.class);
	
	// find 验证码输入框   by xpath (RN)  
    public static final String VERIFICATIONCODE_XPATH = "//*[contains(@text,'請輸入驗證碼')  or contains(@text,'请输入验证码') or (@name='请输入验证码')]";
    // find 获取验证码按钮   by xpath (RN)  
    public static final String GET_VERIFICATIONCODE_XPATH = "//*[contains(@text,'獲取驗證碼')  or contains(@text,'获取验证码') or (@name='获取验证码')]";
    // find 保存并登录按钮   by xpath (RN)  
    public static final String SAVEANDLOGIN_XPATH = "//*[contains(@text,'保存並登錄')  or contains(@text,'保存并登录') or (@name='保存并登录')]";
    // find 倒计时   by xpath (RN)  
    public static final String TIME_XPATH = "//*[contains(@text,'s') or contains(@name,'s')]";
    
    public static final String numberNotRegister = "手机号未注册";
    public static final String vCodeIsInvalid = "验证码无效";
    
    	
	/*
 	 * 	输入手机号
     */
	public void inputPhoneNumber(String pNumer) {
		waitTime(1);
		List<WebElement> phone = findElements(By.xpath(PHONENUMBER_XPATH));	
		if(phone.size()>1) {
			phone.get(1).sendKeys(pNumer);
		} else {
			phone.get(0).sendKeys(pNumer);
		}
	}
	
	 /*
	 * 	点击获取验证码按钮
	 */
	public void clickVerificationButton() {

		WebElement vButton = FindXpath(GET_VERIFICATIONCODE_XPATH);			
		press(vButton);
	}
	
    /*
 	 * 	输入验证码
     */
	public void inputVerificationCode(String vCode) {

		WebElement code = FindXpath(VERIFICATIONCODE_XPATH);			
		code.sendKeys(vCode);
	}
		
	/*
	 *	 输入密码
	 */
	public void inputPassword(String pWord) {

		List<WebElement> password = findElements(By.xpath(PASSWORD_XPATH));			
		password.get(1).sendKeys(pWord);
	}
	
	/*
	 * 	点击保存并登录按钮
	 */
	public void clickSaveAndLoginButton() {

		WebElement vButton = FindXpath(SAVEANDLOGIN_XPATH);			
		press(vButton);
	}
	
	/*
	 * 倒计时
	 */
	public boolean isCountdown() {
		if(isElementsExist(By.xpath(TIME_XPATH))) {
			return true;
		}
		return false;
	}
	
	/*
	 * 进入忘记密码页面
	 */
	public void toForgetPasswordPage() {
		int times = 5;
		while(times>=0) {
			if(isForgetPasswordPage()) {				
				return;
			} else {
				toPhoneLogin();
				clickForgetButton();
				times--;
			}
		}
	}
	
	public boolean isForgetPasswordPage() {
		if(isElementsExist(By.xpath(FORGETPASSWORD_TITLE))) {
			return true;
		}
		return false;
	}
}
