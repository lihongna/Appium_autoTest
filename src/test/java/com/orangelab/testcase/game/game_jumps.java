package com.orangelab.testcase.game;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orangelab.PO.game.JumpGamePage;
import com.orangelab.PO.main.HomePage;
import com.orangelab.asserts.Asserts;
import com.orangelab.testcase.BaseTest;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;

public class game_jumps extends BaseTest{
private static AppiumDriver driver;
	
	@Feature("小游戏")
	@Stories(value = { @Story(value = "跳一跳")})
	@Description("进入开始匹配页面")
	@Step("测试步骤：1、进入PK小游戏页面；2、选择跳一跳；")
	@Parameters({"udid"})
	@Test(priority = 0)
	public void test_selectJump(String udid){
		boolean flag = false;
		System.out.println("game_jumps * 选择跳一跳 * ");
		driver = getDriver(udid);		
		JumpGamePage jump = new JumpGamePage(driver,udid);
		// 进入PK小游戏页面
		jump.toPKGamePage();
		//选择跳一跳
		jump.click_game_jumps();		
		// 断言-进入匹配页面
		Asserts.verifyTrue(jump.isMatchPage());
	}	
	
	@Feature("小游戏")
	@Stories(value = { @Story(value = "跳一跳")})
	@Description("开始匹配，超时退出,或游戏结束")
	@Step("测试步骤：1、进入PK小游戏页面；2、选择跳一跳；3、点击开始匹配；")
	@Parameters({"udid"})
	@Test(priority = 1)
	public void test_match(String udid){
		boolean flag = false;
		System.out.println("game_jumps * 跳一跳 * 匹配中 ");
		driver = getDriver(udid);		
		JumpGamePage jump = new JumpGamePage(driver,udid);		
		//开始匹配
		jump.clickMatch();	
		// 等待匹配
		jump.waitMatch();
		if(jump.isTimeout) {
			// 断言-超时退出-回到PK游戏主界面
			System.out.println("game_jumps * 跳一跳 * 超时 ");
			Asserts.verifyTrue(jump.isPKGamePage());
		} else {
			// 开始游戏
			jump.playgame();
			// 断言-游戏正常结束
			System.out.println("game_jumps * 跳一跳 * 游戏结束 ");
			Asserts.verifyTrue(jump.isGameOverPage());
		}
		
	}		
	
	@Parameters({"udid"})
	@AfterMethod
	public void resetStatus(String udid) {
		System.out.println("game_jumps * AfterMethod * resetStatus");			
		driver = getDriver(udid);
		JumpGamePage jump = new JumpGamePage(driver,udid);
		if(jump.isGameOverPage()) {
			jump.closeGameOverDialog();
			jump.toHomePage();
		}
	}
}
