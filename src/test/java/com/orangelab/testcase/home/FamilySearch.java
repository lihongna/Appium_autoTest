package com.orangelab.testcase.home;

import org.testng.annotations.*;
import com.orangelab.PO.home.SearchPage;
import com.orangelab.PO.me.FamilyPage;
import com.orangelab.asserts.Asserts;
import com.orangelab.testcase.BaseTest;
import io.qameta.allure.*;

public class FamilySearch extends BaseTest{
	@Feature("首页-搜索")
	@Stories(value = { @Story(value = "搜索家族")})
	@Description("未输入家族ID，提示请输入家族ID")
	@Step("测试步骤：1、进入搜索页面；2、点击家族；3、点击查找")
	@Parameters({"udid"})
	@Test(priority = 0)  
	public void test_emptyID(String udid) {	  
		System.out.println("FamilySearch * 未输入家族ID * " );			
		driver = getDriver(udid);
		SearchPage search = new SearchPage(driver,udid);
		// 进入搜索页面
		search.toSearchPage();
		// 点击家族
		search.clickFamily();
		// 输入空家族ID
		search.inputSearchID("");
		// 点击查找
		search.clickFind();
		// 断言-提示请输入家族ID
		String t = search.getToastContext();
		Asserts.verifyEquals(t, search.pleaseInputFamilyNumber);	
	}
	
	@Feature("首页-搜索")
	@Stories(value = { @Story(value = "搜索家族")})
	@Description("输入无效家族ID，提示家族不存在")
	@Step("测试步骤：1、进入搜索页面；2、点击家族；3、输入无效ID；4、点击查找")
	@Parameters({"udid"})
	@Test(priority = 1)  
	public void test_errID(String udid) {	  
		System.out.println("FamilySearch * 无效ID * " );			
		driver = getDriver(udid);
		SearchPage search = new SearchPage(driver,udid);
		// 进入搜索页面
		search.toSearchPage();
		// 点击家族
		search.clickFamily();
		// 输入无效ID
		search.inputSearchID("00000000");
		// 点击查找
		search.clickFind();
		// 断言-提示家族不存在
		String t = search.getToastContext();
		Asserts.verifyEquals(t, search.familyNotExist);	
	}
	
	@Feature("首页-搜索")
	@Stories(value = { @Story(value = "搜索家族")})
	@Description("搜索进入家族详情")
	@Step("测试步骤：1、进入搜索页面；2、点击家族；3、输入家族ID；4、点击查找")
	@Parameters({"udid"})
	@Test(priority = 2)  
	public void test_findVoiceRoom(String udid) {	  
		System.out.println("FriendSearch * 家族ID：100125 * " );			
		driver = getDriver(udid);
		SearchPage search = new SearchPage(driver,udid);
		// 进入搜索页面
		search.toSearchPage();			
		// 点击家族
		search.clickFamily();
		// 输入家族ID：100125
		search.inputSearchID("100125");
		// 点击查找
		search.clickFind();
		// 断言-进入家族详情
		FamilyPage family = new FamilyPage(driver,udid);
		String id = family.getFamilyID();
		Asserts.verifyEquals(id, "100125");	
	}
	
}
