package com.orangelab.testcase.home;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orangelab.PO.home.HelpPage;
import com.orangelab.PO.login.LoginPage;
import com.orangelab.PO.main.HomePage;
import com.orangelab.asserts.Asserts;
import com.orangelab.testcase.BaseTest;

import io.qameta.allure.*;

public class GuestUsed extends BaseTest{
	private boolean reset = false;
	@Feature("游客登录")
	@Stories(value = { @Story(value = "禁用功能")})
	@Description("首页-点击任务提示去登录")
	@Step("测试步骤：1、进入首页，2、点击任务")
	@Parameters({"udid"})
	@Test(priority = 0)
	public void test_task(String udid) {
		System.out.println("GuestUsed * 任务");	
		driver = getDriver(udid);	
		HomePage home = new HomePage(driver,udid);
		// 进入游客首页
		home.toGuestHomePage();
		// 点击任务
		home.clickTask();
		// 断言-去登录		
		Asserts.verifyTrue(home.isToLoginPopup());
	}
	
	@Feature("游客登录")
	@Stories(value = { @Story(value = "禁用功能")})
	@Description("入首页-点击家族，提示去登录")
	@Step("测试步骤：1、进入首页，2、点击家族")
	@Parameters({"udid"})
	@Test(priority = 1)
	public void test_family(String udid) {
		System.out.println("GuestUsed * 家族");	
		driver = getDriver(udid);		
		HomePage home = new HomePage(driver,udid);
		// 进入游客首页
		home.toGuestHomePage();
		// 点击家族
		home.clickFamily();
		// 断言-去登录		
		Asserts.verifyTrue(home.isToLoginPopup());
	}
	
	@Feature("游客登录")
	@Stories(value = { @Story(value = "禁用功能")})
	@Description("入首页-点击搜索，提示去登录")
	@Step("测试步骤：1、进入首页，2、点击搜索")
	@Parameters({"udid"})
	@Test(priority = 2)
	public void test_search(String udid) {
		System.out.println("GuestUsed * 搜索");	
		driver = getDriver(udid);		
		HomePage home = new HomePage(driver,udid);
		// 进入游客首页
		home.toGuestHomePage();
		// 点击搜索
		home.clickSearch();
		// 断言-去登录		
		Asserts.verifyTrue(home.isToLoginPopup());
	}
	
	@Feature("游客登录")
	@Stories(value = { @Story(value = "禁用功能")})
	@Description("首页-点击创建，提示去登录")
	@Step("测试步骤：1、进入首页，2、点击创建")
	@Parameters({"udid"})
	@Test(priority = 3)
	public void test_create(String udid) {
		System.out.println("GuestUsed * 创建");	
		driver = getDriver(udid);		
		HomePage home = new HomePage(driver,udid);
		// 进入游客首页
		home.toGuestHomePage();
		// 点击创建
		home.clickCreate();
		// 断言-去登录		
		Asserts.verifyTrue(home.isToLoginPopup());
	}
	
	@Feature("游客登录")
	@Stories(value = { @Story(value = "禁用功能")})
	@Description("首页-点击排行，提示去登录")
	@Step("测试步骤：1、进入首页，2、点击排行")
	@Parameters({"udid"})
	@Test(priority = 4)
	public void test_rankings(String udid) {
		System.out.println("GuestUsed * 排行");	
		driver = getDriver(udid);		
		HomePage home = new HomePage(driver,udid);
		// 进入游客首页
		home.toGuestHomePage();
		// 点击排行
		home.clickRankings();
		// 断言-去登录
		Asserts.verifyTrue(home.isToLoginPopup());
	}	
	
	@Feature("游客登录")
	@Stories(value = { @Story(value = "禁用功能")})
	@Description("首页-点击pk小游戏，提示去登录")
	@Step("测试步骤：1、进入首页，2、点击pk小游戏")
	@Parameters({"udid"})
	@Test(priority = 5)
	public void test_pkgame(String udid) {
		System.out.println("GuestUsed * pk小游戏");	
		driver = getDriver(udid);		
		HomePage home = new HomePage(driver,udid);
		// 进入游客首页
		home.toGuestHomePage();
		// 点击小游戏
		home.clickPKGame();
		// 断言-去登录
		Asserts.verifyTrue(home.isToLoginPopup());
	}
	
	@Feature("游客登录")
	@Stories(value = { @Story(value = "禁用功能")})
	@Description("首页-点击小游戏大厅，提示去登录")
	@Step("测试步骤：1、进入首页，2、点击小游戏大厅")
	@Parameters({"udid"})
	@Test(priority = 6)
	public void test_gameCenter(String udid) {
		System.out.println("GuestUsed * 小游戏大厅");	
		driver = getDriver(udid);		
		HomePage home = new HomePage(driver,udid);
		// 进入游客首页
		home.toGuestHomePage();
		// 点击小游戏大厅
		home.clickGameCenter();
		// 断言-去登录
		Asserts.verifyTrue(home.isToLoginPopup());
	}
	
	@Feature("游客登录")
	@Stories(value = { @Story(value = "禁用功能")})
	@Description("首页-点击语音速配，提示去登录")
	@Step("测试步骤：1、进入首页，2、点击语音速配")
	@Parameters({"udid"})
	@Test(priority = 7)
	public void test_game(String udid) {
		System.out.println("GuestUsed * 语音速配");	
		driver = getDriver(udid);		
		HomePage home = new HomePage(driver,udid);
		// 进入游客首页
		home.toGuestHomePage();
		// 点击语音速配
		home.clickVoicePair();
		// 断言-去登录
		Asserts.verifyTrue(home.isToLoginPopup());
	}
	@Feature("游客登录")
	@Stories(value = { @Story(value = "可用功能")})
	@Description("首页-点击帮助，进入新手帮助页面")
	@Step("测试步骤：1、进入首页，2、点击帮助")
	@Parameters({"udid"})
	@Test(priority = 8)
	public void test_help(String udid) {
		System.out.println("GuestUsed * 帮助");	
		driver = getDriver(udid);		
		HelpPage home = new HelpPage(driver,udid);
		// 进入游客首页
		home.toGuestHomePage();
		// 点击帮助
		home.clickHelp();
		// 断言-进入新手帮助页面		
		Asserts.verifyTrue(home.isHelpMainPage());
	}
	
	@Feature("游客登录")
	@Stories(value = { @Story(value = "可用功能")})
	@Description("首页-点击公告，进入公告")
	@Step("测试步骤：1、进入首页，2、点击公告")
	@Parameters({"udid"})
	@Test(priority = 9)
	public void test_notice(String udid) {
		System.out.println("GuestUsed * 公告");	
		driver = getDriver(udid);		
		HomePage home = new HomePage(driver,udid);
		// 进入游客首页
		home.toGuestHomePage();
		// 点击公告
		//home.clickNotice();
		// 断言-进入公告		
		//Asserts.verifyTrue(home.isToLoginPopup());
	}
	
	@Parameters({"udid"})
	@AfterMethod
	public void resetStatus(String udid) {
		System.out.println("GuestUsed * AfterMethod * resetStatus" );			
		driver = getDriver(udid);
		HomePage home = new HomePage(driver,udid);
		if(home.isToLoginPopup()) {
			home.clickCancelButton();
		} else if(reset) {
			LoginPage login = new LoginPage(driver,udid);
			login.QuitLogin();
		} else {
			home.toHomePage();
		}
	}
}
