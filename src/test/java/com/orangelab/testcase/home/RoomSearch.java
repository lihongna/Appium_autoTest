package com.orangelab.testcase.home;

import org.testng.annotations.*;
import com.orangelab.PO.home.SearchPage;
import com.orangelab.PO.room.RoomPage;
import com.orangelab.asserts.Asserts;
import com.orangelab.testcase.BaseTest;
import io.qameta.allure.*;

public class RoomSearch extends BaseTest {
	@Feature("首页-搜索")
	@Stories(value = { @Story(value = "搜索房间")})
	@Description("未输入房间ID，提示请输入房间号")
	@Step("测试步骤：1、进入搜索页面；2、点击房间；3、点击查找")
	@Parameters({"udid"})
	@Test(priority = 0)  
	public void test_emptyID(String udid) {	  
		System.out.println("RoomSearch * 未输入房间号 * " );			
		driver = getDriver(udid);
		SearchPage search = new SearchPage(driver,udid);
		// 进入搜索页面
		search.toSearchPage();
		// 点击房间
		search.clickRoom();
		// 输入空房间号
		search.inputSearchID("");
		// 点击查找
		search.clickFind();
		// 断言-提示请输入房间号
		String t = search.getToastContext();
		Asserts.verifyEquals(t, search.pleaseInputRoomNumber);	
	}
	
	@Feature("首页-搜索")
	@Stories(value = { @Story(value = "搜索房间")})
	@Description("输入无效房间ID，提示房间不存在或已经失效")
	@Step("测试步骤：1、进入搜索页面；2、点击房间；3、输入无效ID；4、点击查找")
	@Parameters({"udid"})
	@Test(priority = 1)  
	public void test_errID(String udid) {	  
		System.out.println("RoomSearch * 无效ID * " );			
		driver = getDriver(udid);
		SearchPage search = new SearchPage(driver,udid);
		// 进入搜索页面
		search.toSearchPage();
		// 点击房间
		search.clickRoom();
		// 输入无效ID
		search.inputSearchID("00000000");
		// 点击查找
		search.clickFind();
		// 断言-提示房间不存在或已经失效
		String t = search.getToastContext();
		Asserts.verifyEquals(t, search.roomNotExist);	
	}
	
	@Feature("首页-搜索")
	@Stories(value = { @Story(value = "搜索房间")})
	@Description("搜索进入1000语音房房")
	@Step("测试步骤：1、进入搜索页面；2、点击房间；3、输入语音房ID；4、点击查找")
	@Parameters({"udid"})
	@Test(priority = 2)  
	public void test_findVoiceRoom(String udid) {	  
		System.out.println("RoomSearch * 语音房ID：1000 * " );			
		driver = getDriver(udid);
		SearchPage search = new SearchPage(driver,udid);
		// 进入搜索页面
		search.toSearchPage();
		// 点击房间
		search.clickRoom();
		// 输入ID：1000
		search.inputSearchID("1000");
		// 点击查找
		search.clickFind();
		search.allowPermissionPopup();
		// 断言-提示请输入房间号
		RoomPage room = new RoomPage(driver,udid);
		String id = room.getRoomID();
		Asserts.verifyEquals(id, "1000");	
	}
	
	@Feature("首页-搜索")
	@Stories(value = { @Story(value = "搜索房间")})
	@Description("搜索进入狼杀游戏房 ")
	@Step("测试步骤：1、进入搜索页面；2、点击房间；3、输入狼杀房ID；4、点击查找")
	@Parameters({"udid"})
	@Test(priority = 3)  
	public void test_findWolfRoom(String udid) {	  
		System.out.println("RoomSearch * 狼杀房ID：010095 * " );			
		driver = getDriver(udid);
		SearchPage search = new SearchPage(driver,udid);
		// 进入搜索页面
		search.toSearchPage();
		// 点击房间
		search.clickRoom();
		// 输入ID：1000
		search.inputSearchID("010095");
		// 点击查找
		search.clickFind();
		search.allowPermissionPopup();
		// 断言-提示请输入房间号
		RoomPage room = new RoomPage(driver,udid);
		String id = room.getWolfRoomID();
		Asserts.verifyEquals(id, "010095");	
	}
	
	@Parameters({"udid"})
	@AfterMethod
	public void resetStatus(String udid) {
		System.out.println("RoomSearch * AfterMethod * resetStatus" );			
		driver = getDriver(udid);
		RoomPage room = new RoomPage(driver,udid);
		if(room.isInWolfRoom()){
			room.quitWolfRoom();
		} else if(room.isInVoiceRoom()){
			room.quitRoom();
		} 
	}
	
}
