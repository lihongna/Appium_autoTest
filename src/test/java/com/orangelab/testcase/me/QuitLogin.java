package com.orangelab.testcase.me;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orangelab.PO.login.LoginPage;
import com.orangelab.PO.main.MainPage;
import com.orangelab.PO.me.SystemSettingPage;
import com.orangelab.asserts.Asserts;
import com.orangelab.testcase.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.*;


public class QuitLogin extends BaseTest{
private static AppiumDriver driver;
	
	@Feature("退出登录")
	@Stories(value = { @Story(value = "系统设置-退出登录")})
	@Description("手动退出登录")
	@Parameters({"udid"})
	@Step("测试步骤：1、进入系统设置页面，2、点击退出登录")
	@Test(priority = 1)	
	public void test_quitLogin(String udid){
		System.out.println("QuiteLogin * test_quitLogin * ");			
		driver = getDriver(udid);

		SystemSettingPage sys = new SystemSettingPage(driver,udid);
		sys.toSystemSetPage();
		sys.clickQuiteLoginButton();
		// 断言-进入登录页面
		LoginPage mPage = new LoginPage(driver,udid);
		boolean flag = mPage.isMainLoginPage();
		Asserts.isFoundElement(flag);
	}
	
	@Feature("退出登录")
	@Parameters({"udid"})
	@Step("测试步骤：1、进入系统设置页面，2、点击退出登录")
	@Test(priority = 0)
	public void test(String udid){
		System.out.println("QuiteLogin * test_001 * " + udid);			
		driver = getDriver(udid);

		MainPage main = new MainPage(driver,udid);
		main.toMainPage();
		main.clickHome();
		main.clickSquare();
		main.clickVoice();
		main.clickMessage();
		main.clickMe();
	}
}
