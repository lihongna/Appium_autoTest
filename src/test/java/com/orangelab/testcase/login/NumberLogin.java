package com.orangelab.testcase.login;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orangelab.PO.login.NumberLoginPage;
import com.orangelab.asserts.Asserts;
import com.orangelab.testcase.BaseTest;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.*;

public class NumberLogin extends BaseTest{
	private static AppiumDriver driver;

	@Feature("登录")
	@Stories(value = { @Story(value = "手机号码登录")})
	@Description("号码为空，点击登录，提示请输入手机号")
	@Step("测试步骤：1、进入手机登录页面；2、点击登录")
	@Parameters({"udid"})
	@Test(priority = 0)
	public void test_emptyNumber(String udid) throws InterruptedException{
		System.out.println("PhoneLogin * 号码为空 * ");
		driver = getDriver(udid);		
		NumberLoginPage pl = new NumberLoginPage(driver,udid);
		// 进入手机登录页面
		pl.toPhoneLogin();
		// 清除数据
		pl.cleanText();
		// 点击登录
		pl.clickLoginButton();
		// 断言-提示请输入手机号
		String t = pl.getToastContext();
		Asserts.verifyEquals(t, pl.pleaseInputNumber);		
	}	

	@Feature("登录")
	@Stories(value = { @Story(value = "手机号码登录")})
	@Description("密码为空，点击登录，提示请输入密码")
	@Step("测试步骤：1、进入手机登录页面；2、输入手机号；3、点击登录")
	@Parameters({"udid"})
	@Test(priority = 1)
	public void test_emptyPassword(String udid) throws InterruptedException{
		System.out.println("PhoneLogin * 密码为空 * ");
		driver = getDriver(udid);		
		NumberLoginPage pl = new NumberLoginPage(driver,udid);
		// 进入手机登录页面
		pl.toPhoneLogin();	
		// 清除数据
		pl.cleanText();
		// 输入手机号
		pl.inputPhoneNumber("18800000030");
		// 点击登录
		pl.clickLoginButton();
		// 断言-提示请输入手机号
		String t = pl.getToastContext();
		Asserts.verifyEquals(t, pl.pleaseInputPassword);				
	}	

	@Feature("登录")
	@Stories(value = { @Story(value = "手机号码登录")})
	@Description("号码不正确，点击登录，提示账号或密码不正确")
	@Step("测试步骤：1、进入手机登录页面；2、输入错误手机号10000；3、输入密码；4、点击登录")
	@Parameters({"udid"})
	@Test(priority = 2)
	public void test_numberErr(String udid) throws InterruptedException{
		System.out.println("PhoneLogin * 号码不正确 * ");
		driver = getDriver(udid);		
		NumberLoginPage pl = new NumberLoginPage(driver,udid);
		// 进入手机登录页面
		pl.toPhoneLogin();	
		// 清除数据
		pl.cleanText();
		// 输入手机号
		pl.inputPhoneNumber("10000");
		// 输入密码
		pl.inputPassword("111111");
		// 点击登录
		pl.clickLoginButton();
		// 断言-提示请输入手机号
		String t = pl.getToastContext();
		Asserts.verifyEquals(t, pl.numberOrPasswordErr);				
	}

	@Feature("登录")
	@Stories(value = { @Story(value = "手机号码登录")})
	@Description("密码不正确，点击登录，提示账号或密码不正确")
	@Step("测试步骤：1、进入手机登录页面；2、输入手机号；3、输入错误密码1；4、点击登录")
	@Parameters({"udid"})
	@Test(priority = 3)
	public void test_passwordErr(String udid) throws InterruptedException{
		System.out.println("PhoneLogin * 密码不正确 * ");
		driver = getDriver(udid);		
		NumberLoginPage pl = new NumberLoginPage(driver,udid);
		// 进入手机登录页面
		pl.toPhoneLogin();	
		// 清除数据
		pl.cleanText();
		// 输入手机号
		pl.inputPhoneNumber("18800000030");
		// 输入密码
		pl.inputPassword("1");
		// 点击登录
		pl.clickLoginButton();
		// 断言-提示请输入手机号
		String t = pl.getToastContext();
		Asserts.verifyEquals(t, pl.numberOrPasswordErr);				
	}

	@Feature("登录")
	@Stories(value = { @Story(value = "手机号码登录")})
	@Description("正常登录")
	@Step("测试步骤：1、进入手机登录页面；2、切换国家；3、输入手机号；4、输入密码；5、点击登录")
	@Parameters({"udid"})
	@Test(priority = 4)
	public void test_login(String udid) throws InterruptedException{
		System.out.println("PhoneLogin * 正常登录 * ");
		driver = getDriver(udid);		
		NumberLoginPage pl = new NumberLoginPage(driver,udid);
		List<String>param=pl.getParam(udid);
		String pNum = param.get(pl.phoneNum);
		String pwd = param.get(pl.password);
		// 进入手机登录页面
		pl.toPhoneLogin();
		// 清除数据
		pl.cleanText();
		// 切换国家/地区
		pl.clickChangeCountry();
		// 输入手机号
		pl.inputPhoneNumber(pNum);
		// 输入密码
		pl.inputPassword(pwd);
		// 点击登录
		pl.clickLoginButton();		
		
		pl.allowPermissionPopup(); // 允许权限
		pl.cancelSuspendedpermissionPopup(); // 设置悬窗
		
		// 断言-进入首页
		boolean flag = pl.isElementsExist(By.xpath("//*[contains(@text,'ID:')]"));
		Asserts.isFoundElement(flag);				
	}	
	
}
