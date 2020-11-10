package com.orangelab.testcase.home;

import org.testng.annotations.*;
import com.orangelab.PO.home.SearchPage;
import com.orangelab.PO.me.MyProfilePage;
import com.orangelab.asserts.Asserts;
import com.orangelab.testcase.BaseTest;
import io.qameta.allure.*;

public class FriendSearch extends BaseTest{
	@Feature("首页-搜索")
	@Stories(value = { @Story(value = "搜索好友")})
	@Description("未输入好友ID，提示请输入好友ID")
	@Step("测试步骤：1、进入搜索页面；2、点击好友；3、点击查找")
	@Parameters({"udid"})
	@Test(priority = 0)  
	public void test_emptyID(String udid) {	  
		System.out.println("FriendSearch * 未输入好友ID * " );			
		driver = getDriver(udid);
		SearchPage search = new SearchPage(driver,udid);
		// 进入搜索页面
		search.toSearchPage();
		// 点击好友
		search.clickFriend();
		// 输入空好友ID
		search.inputSearchID("");
		// 点击查找
		search.clickFind();
		// 断言-提示请输入好友ID
		String t = search.getToastContext();
		Asserts.verifyEquals(t, search.pleaseInputFriendNumber);	
	}
	
	@Feature("首页-搜索")
	@Stories(value = { @Story(value = "搜索好友")})
	@Description("输入无效好友ID，提示用户不存在")
	@Step("测试步骤：1、进入搜索页面；2、点击好友；3、输入无效ID；4、点击查找")
	@Parameters({"udid"})
	@Test(priority = 1)  
	public void test_errID(String udid) {	  
		System.out.println("FriendSearch * 无效ID * " );			
		driver = getDriver(udid);
		SearchPage search = new SearchPage(driver,udid);
		// 进入搜索页面
		search.toSearchPage();
		// 点击好友
		search.clickFriend();
		// 输入无效ID
		search.inputSearchID("00000000");
		// 点击查找
		search.clickFind();
		// 断言-提示用户不存在
		String t = search.getToastContext();
		Asserts.verifyEquals(t, search.userNotExist);	
	}
	
	@Feature("首页-搜索")
	@Stories(value = { @Story(value = "搜索好友")})
	@Description("搜索进入个人资料")
	@Step("测试步骤：1、进入搜索页面；2、点击好友；3、输入好友ID；4、点击查找")
	@Parameters({"udid"})
	@Test(priority = 2)  
	public void test_findVoiceRoom(String udid) {	  
		System.out.println("FriendSearch * 好友ID：35000827 * " );			
		driver = getDriver(udid);
		SearchPage search = new SearchPage(driver,udid);
		// 进入搜索页面
		search.toSearchPage();			
		// 点击好友
		search.clickFriend();
		// 输入好友ID：35000827
		search.inputSearchID("35000827");
		// 点击查找
		search.clickFind();
		// 断言-进入个人资料	
		MyProfilePage profile = new MyProfilePage(driver,udid);
		Asserts.verifyTrue(profile.isProfilePage());
	}
	
}
