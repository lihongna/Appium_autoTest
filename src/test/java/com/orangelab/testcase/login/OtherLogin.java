package com.orangelab.testcase.login;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orangelab.PO.login.LoginPage;
import com.orangelab.testcase.BaseTest;
import io.qameta.allure.*;

public class OtherLogin extends BaseTest {
	@Feature("登录")
	@Stories(value = { @Story(value = "QQ登录")})
	@Step("测试步骤：1、进入登录主页面；2、点击QQ登录")
	@Parameters({"udid"})
	@Test(priority = 0)
	public void test_QQ(String udid) throws InterruptedException{
		System.out.println("OtherLogin * QQ登录 * ");
		driver = getDriver(udid);		
		LoginPage login = new LoginPage(driver,udid);
		// 进入登录主页面
		login.toMainLoginPage();	
		// 点击QQ登录
		login.clickQQLogin();
			
	}
	
	@Feature("登录")
	@Stories(value = { @Story(value = "微信登录")})
	@Step("测试步骤：1、进入登录主页面；2、点击微信登录")
	@Parameters({"udid"})
	@Test(priority = 0)
	public void test_WChat(String udid) throws InterruptedException{
		System.out.println("OtherLogin * 微信登录 * ");
		driver = getDriver(udid);		
		LoginPage login = new LoginPage(driver,udid);
		// 进入登录主页面
		login.toMainLoginPage();	
		// 点击微信登录
		login.clickWeChatLogin();			
	}
	
	@Feature("登录")
	@Stories(value = { @Story(value = "Facebook登录")})
	@Step("测试步骤：1、进入登录主页面；2、点击Facebook登录")
	@Parameters({"udid"})
	@Test(priority = 0)
	public void test_Facebook(String udid) throws InterruptedException{
		System.out.println("OtherLogin * Facebook登录 * ");
		driver = getDriver(udid);		
		LoginPage login = new LoginPage(driver,udid);
		// 进入登录主页面
		login.toMainLoginPage();	
		// 点击Facebook登录
		login.clickFacebookLogin();		
	}
}
