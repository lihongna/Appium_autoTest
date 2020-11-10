package com.orangelab.PO.login;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.orangelab.util.XmlUtil;

import io.appium.java_client.AppiumDriver;

public class RegisterPage extends NumberLoginPage {
	public RegisterPage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
	}

	public static Logger log = Logger.getLogger(RegisterPage.class);
	protected static List<String> param = new ArrayList();

	
	// find 验证码输入框   by xpath (RN)  
    public static final String VERIFICATIONCODE_XPATH = "//*[(@text='請輸入驗證碼')  or (@text='请输入验证码') or (@name='请输入验证码')]";
    // find 获取验证码按钮   by xpath (RN)  
    public static final String GET_VERIFICATIONCODE_XPATH = "//*[(@text='獲取驗證碼')  or (@text='获取验证码') or (@name='获取验证码')]";    
    // find 确认密码输入框  by xpath (RN)
    public static final String SECOND_PASSWORD_XPATH = "//*[(@text='請確認輸入的密碼')  or (@text='请确认输入的密码') or (@name='请确认输入的密码')]";    
    // find 下一步按钮 by xpath (RN)
    public static final String NEXT_XPATH = "//*[(@text='下一步') or (@name='下一步')]";    
    // find 下一步按钮 by xpath (RN)
    public static final String TIME_XPATH = "//*[contains(@text,'s') or contains(@name,'s')]";
    // 立即进入
    public static final String ENTER_XPATH = "//*[(@text='立即进入') or (@name='立即进入')]";
    //请输入昵称
    public static final String NICKNAME_XPATH = "//*[contains(@text,'请输入昵称') or (@name='请输入昵称')]";
    //设置头像
    public static final String SETPHOTO_XPATH = "//*[contains(@text,'设置头像') or (@name='设置头像')]";
    //拍照
    public static final String CAMERA_XPATH = "//*[contains(@text,'拍照') or (@name='拍照')]";
    //从相册选择
    public static final String PICTURE_XPATH = "//*[contains(@text,'从相册选择') or (@name='从相册选择')]";
    //性别：男
    public static final String MAN_XPATH = "//*[contains(@text,'男') or (@name='男')]";
    //性别：女
    public static final String WOMAN_XPATH = "//*[contains(@text,'女') or (@name='女')]";
    //取消
    public static final String CANCEL_XPATH = "//*[contains(@text,'取消') or (@name='取消')]";
    
    public static final String numberIsRegister = "手机号已经被注册";
    public static final String passwordNotSame = "两次密码不一致";
    public static final String vcodeIsError = "验证码不正确";
    public static final String vcodeIsInvalid = "验证码无效";
    public static final String pleaseInputNickname = "请输入昵称";
    
    public static int gender = 1;  // 1:女  2:男
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
	 *	 输入密码
	 */
	public void inputPassword(String pWord) {

		List<WebElement> password = findElements(By.xpath(PASSWORD_XPATH));			
		password.get(1).sendKeys(pWord);
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
	 *	 再次输入密码
	 */
	public void inputPasswordAgain(String pWord) {

		WebElement password = FindXpath(SECOND_PASSWORD_XPATH);			
		password.sendKeys(pWord);
	}
	
	/*
	 * 	点击下一步按钮
	 */
	public void clickNextButton() {

		WebElement next = FindXpath(NEXT_XPATH);			
		press(next);
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
	 * 进入注册页面
	 */
	public void toRegisterPage() {
		int times = 5;
		while(times>=0) {
			if(isRegisterPage()) {				
				return;
			} else {
				toPhoneLogin();
				clickRegisterButton();				
				times--;
			}
		}
	}
	public boolean isRegisterPage() {
		if(isElementsExist(By.xpath(REGISTER_TITLE))) {
			return true;
		}
		return false;
	}
	/*
	 * 判断是否进入注册信息页面
	 */
	public boolean isRegisterInfo() {
		if(isElementsExist(By.xpath(ENTER_XPATH)) && isElementsExist(By.xpath(NICKNAME_XPATH))) {
			return true;
		} 
		
		return false;		
	}
	
	/*
	 * 点击立即进入按钮
	 */
	public void clickImmediatelyEntry() {
		WebElement button = FindXpath(ENTER_XPATH);			
		press(button);
	}
	
	/*
	 *  点击男
	 */
	public void clickMan() {		
		WebElement button = FindXpath(MAN_XPATH);			
		press(button);		
	}
	
	/*
	 *  点击女
	 */
	public void clickWoman() {		
		WebElement button = FindXpath(WOMAN_XPATH);			
		press(button);		
	}
	
	/*
	 * 点击取消
	 */
	public void clickCancel() {		
		WebElement button = FindXpath(CANCEL_XPATH);			
		press(button);		
	}
	
	/*
	 * 判断是否为男
	 */
	public boolean isMan() {
		if(isElementsExist(By.xpath(MAN_XPATH))) {
			return true;
		}		
		return false;
	}
	/*
	 * 判断是否为女
	 */
	public boolean isWoman() {
		if(isElementsExist(By.xpath(WOMAN_XPATH))) {
			return true;
		}		
		return false;
	}
	
	/*
	 * 点击设置头像
	 */
	public void clickPhoto() {
		WebElement photo = FindXpath(SETPHOTO_XPATH);			
		press(photo);
	}
	/*
	 * 点击拍照
	 */
	public void clickCamera() {
		WebElement photo = FindXpath(CAMERA_XPATH);			
		press(photo);
	}
	/*
	 * 点击从相册选择
	 */
	public void clickPicture() {
		WebElement photo = FindXpath(PICTURE_XPATH);			
		press(photo);
	}
	
	/*
	 * 输入昵称
	 */
	public void inputNickname() {
		WebElement nickname = FindXpath(NICKNAME_XPATH);			
		nickname.sendKeys("test");
	}
	
	public void updateRegiterNumber(String udid) {
		XmlUtil.updateRegisterNum(udid);
	}
	
}
