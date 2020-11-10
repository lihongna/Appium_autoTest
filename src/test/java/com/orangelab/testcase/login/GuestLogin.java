package com.orangelab.testcase.login;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.orangelab.PO.login.LoginPage;
import com.orangelab.PO.main.HomePage;
import com.orangelab.asserts.Asserts;
import com.orangelab.testcase.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.*;

public class GuestLogin extends BaseTest{
	private static AppiumDriver driver;
	private boolean reset = false;
	@Feature("登录")
	@Stories(value = { @Story(value = "游客登录")})
	@Description("游客登录-新手向导")
	@Step("测试步骤：1、进入登录页面；2、点击游客登录")
	@Parameters({"udid"})
	@Test(priority = 0)
	public void test_guide(String udid) {
		System.out.println("GuestLogin * 显示新手向导");	
		driver = getDriver(udid);
		LoginPage gPage = new LoginPage(driver,udid);		
		// 点击“游客登录”
		gPage.toGuestLogin();	
		// 断言-有新手向导
		HomePage home = new HomePage(driver,udid);		
		Asserts.verifyTrueOr(home.isNewGuide(),home.isGuestHomePage());
	}
	
	@Feature("登录")
	@Stories(value = { @Story(value = "游客登录")})
	@Description("游客登录-进入首页")
	@Step("测试步骤：1、进入登录页面；2、点击游客登录;3、关闭向导")
	@Parameters({"udid"})
	@Test(priority = 1)
	public void test_toHome(String udid) {
		System.out.println("GuestLogin * 进入首页");	
		driver = getDriver(udid);		
		//reset = true;
		HomePage home = new HomePage(driver,udid);
		// 关闭新手向导
		home.closeGuide();
		// 允许权限
		home.allowPermissionPopup();
		// 断言-进入首页
		Asserts.verifyTrue(home.isGuestHomePage());
		
	}	
		
	@Parameters({"udid"})
	@AfterMethod
	public void resetStatus(String udid) {
		System.out.println("GuestLogin * AfterMethod * resetStatus" );			
		driver = getDriver(udid);
		if(reset) {
			LoginPage login = new LoginPage(driver,udid);
			login.QuitLogin();
		}
	}
}
