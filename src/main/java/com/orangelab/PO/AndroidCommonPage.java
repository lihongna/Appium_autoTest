package com.orangelab.PO;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.orangelab.PO.login.NumberLoginPage;

import io.appium.java_client.AppiumDriver;

public class AndroidCommonPage extends BasePageWithSwipe {
	public AndroidCommonPage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
	}

	public static Logger log = Logger.getLogger(NumberLoginPage.class);
	//申请权限框
	public static final String PERMISSION_MESSAGE_ID = "com.android.packageinstaller:id/permission_message";
	public static final String PERMISSION_ALLOW_BTN_ID = "com.android.packageinstaller:id/permission_allow_button";
	public static final String PERMISSION_DENY_BTN_ID = "com.android.packageinstaller:id/permission_deny_button";
	//设置悬浮窗
	public static final String SUSPENDEDPERMISSION_ALLOW_BTN_ID = "cn.orangelab.werewolf:id/btn_sure";
	public static final String SUSPENDEDPERMISSION_CANCEL_BTN_ID = "cn.orangelab.werewolf:id/btn_cancel";
	// Toast弹窗
	public static final String TOAST_XPATH = "//*[contains(@class,'android.widget.Toast')]";
	//RN提示
	public static final String UPDATE_MESSAGE_XPATH = "//*[contains(@text,'發現新版本') or contains(@text,'发现新版本')]";
	public static final String CANCEL_BUTTON_XPATH = "//*[contains(@text,'取消') or contains(@text,'取消') ]";
	public static final String UPDATE_BUTTON_XPATH = "//*[contains(@text,'更新') or contains(@text,'更新')]";
	public static final String LOGIN_BUTTON_XPATH = "//*[(@text='去登录') ]";
	public static final String LOGIN_MESSAGE_XPATH = "//*[(@text='登录终极狼人杀') ]";	
	//	Dialog提示
	public static final String DIALOG_MESSAGE_ID="cn.orangelab.werewolf:id/id_werewolf_dialog_hint_message";
	public static final String CONFIRM_BUTTON_ID="cn.orangelab.werewolf:id/id_werewolf_dialog_two_button_confirm";
	public static final String CANCEL_BUTTON_ID="cn.orangelab.werewolf:id/id_werewolf_dialog_two_button_cancel";
	
	/*
	 *	 允许权限		
	 */
	public boolean  allowPermissionPopup()  {
		boolean flag = false;
		int times = 3;
		waitTime(1);
		while(isPermissionPopup() && times>0) {
			clickAllowButton();
			flag = true;
			times--;
		}
		return flag;
	}
	
	/*
	 * 	拒绝权限
	 */
	public void cancelPermissionPopup() {		
		if(isPermissionPopup()) {
			clickDenyButton();
		}
	}
	// 权限框
	public boolean isPermissionPopup() {
		if(isElementsExist(By.id(PERMISSION_MESSAGE_ID))) {
			return true;
		}
		return false;
	}
	// 点击允许按钮
	public void clickAllowButton() {
		WebElement allowBtn = FindId(PERMISSION_ALLOW_BTN_ID);
		press(allowBtn);
		waitTime(1);
	}
	// 点击拒绝按钮
	public void clickDenyButton() {
		WebElement allowBtn = FindId(PERMISSION_DENY_BTN_ID);
		press(allowBtn);
	}
	/*
	 *	 取消悬浮框
	 */
	public boolean cancelSuspendedpermissionPopup() {
		boolean flag = false;
		if(isSuspendedpermissionPopup()) {
			clickNotSureButton();
			flag = true;
		}
		return flag;
	}
	
	/*
	 *	 设置开启悬浮框
	 */
	public void allowSuspendedpermissionPopup() {		
		if(isSuspendedpermissionPopup()) {
			clickSureButton();
		}
	}
	public boolean isSuspendedpermissionPopup() {
		if(isElementsExist(By.id(SUSPENDEDPERMISSION_ALLOW_BTN_ID))) {
			return true;
		}
		return false;
	}
	// 点击开启
	public void clickSureButton() {
		WebElement el = FindId(SUSPENDEDPERMISSION_ALLOW_BTN_ID);
		press(el);
	}
	// 点击暂不开启
	public void clickNotSureButton() {
		WebElement el = FindId(SUSPENDEDPERMISSION_CANCEL_BTN_ID);
		press(el);
	}
	/*
	 * 	获取toast元素
	 */
	public WebElement getSystemToast() {
		WebElement toast = null;		
		toast = FindXpath(TOAST_XPATH);			
		return toast;
	}
	
	/*
	 *	 获取toast内容
	 */
	public String getToastContext() {
		WebElement toast = getSystemToast();
		String text = toast.getText();
		return text;
	}
	// 点击取消按钮
	public void clickCancelButton() {
		WebElement cancelBtn = FindXpath(CANCEL_BUTTON_XPATH);
		press(cancelBtn);
	}
	// 点击更新按钮
	public void clickUpdateButton() {
		WebElement updateBtn = FindXpath(UPDATE_BUTTON_XPATH);
		press(updateBtn);
	}
	// 点击去登录按钮
	public void clickLoginButton() {
		WebElement el = FindXpath(LOGIN_BUTTON_XPATH);
		press(el);
	}
	// 系统更新页面
	public boolean isSysUpdatePage() {
		if(isElementsExist(By.xpath(UPDATE_MESSAGE_XPATH))) {
			return true;
		}
		return false;
	}
	/*
	 *	 取消系统更新
	 */
	public boolean cancelUpdateSystem() {
		boolean flag = false;
		if(isSysUpdatePage()) {
			clickCancelButton();
			flag = true;
		}
		return flag;
	}
	
	/*
	 *	 确定系统更新
	 */
	public void allowUpdateSystem() {
		
		if(isSysUpdatePage()) {
			clickUpdateButton();
		}
	}
	
	public void closedWindow() {
		boolean flag = true;
		while(flag) {
			if(allowPermissionPopup()) {
				flag = true;
			} else if(cancelUpdateSystem()) {
				flag = true;
			} else {
				flag = false;
			}
		}
	}
}
