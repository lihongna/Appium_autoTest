package com.orangelab.testcase.login;

import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orangelab.PO.login.ForgetPasswordPage;
import com.orangelab.PO.main.HomePage;
import com.orangelab.asserts.Asserts;
import com.orangelab.testcase.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.*;

public class ForgetPassword extends BaseTest {
	private static AppiumDriver driver;

	@Feature("忘记密码")
	@Stories(value = { @Story(value = "手机号未注册")})
	@Description("输入未注册号码，手机号未注册")
	@Step("测试步骤：1、进入忘记密码页面；2、切换国家；3、输入手机号18811111130；4、点击获取验证码；5、提示手机号未注册 ")
	@Parameters({"udid"})
	@Test(priority = 0)
	public void test_unregister(String udid) throws InterruptedException{
		System.out.println("ForgetPassword * 号码未注册 * ");
		driver = getDriver(udid);
		ForgetPasswordPage forgetPage = new ForgetPasswordPage(driver,udid);
		
		// 进入忘记密码页面
		forgetPage.toForgetPasswordPage();
		// 切换国家/地区
		forgetPage.clickChangeCountry();
		// 清除数据
		forgetPage.cleanText();
		// 输入手机号
		forgetPage.inputPhoneNumber("18811111130");
		// 点击获取验证码
		forgetPage.clickVerificationButton();
		
		String t = forgetPage.getToastContext();
		Asserts.verifyEquals(t, forgetPage.numberNotRegister);
	}

	@Feature("忘记密码")
	@Stories(value = { @Story(value = "手机号未注册")})
	@Description("获取验证码，出现倒计时")
	@Step("测试步骤：1、进入忘记密码页面；2、切换国家；3、输入手机号；4、点击获取验证码；")
	@Parameters({"udid"})
	@Test(priority = 1)
	public void test_getvcode(String udid) throws InterruptedException{
		System.out.println("ForgetPassword * 获取验证码 * ");
		driver = getDriver(udid);
		ForgetPasswordPage forgetPage = new ForgetPasswordPage(driver,udid);
		
		// 进入忘记密码页面
		forgetPage.toForgetPasswordPage();
		// 切换国家/地区
		forgetPage.clickChangeCountry();
		// 清除数据
		forgetPage.cleanText();
		// 输入手机号
		forgetPage.inputPhoneNumber("18800000030");
		// 点击验证码
		forgetPage.clickVerificationButton();		
		if(forgetPage.isCountdown()) {
			Asserts.verifyTrue(true);
		} else {			
			String t = forgetPage.getToastContext();
			Asserts.verifyEquals(t, forgetPage.onlySend3times);
		}
	}

	@Feature("忘记密码")
	@Stories(value = { @Story(value = "手机号未注册")})
	@Description("未输入验证码，提示请输入验证码")
	@Step("测试步骤：1、进入忘记密码页面；2、切换国家；3、输入手机号；4、点击保存并登录；5、提示请输入验证码 ")
	@Parameters({"udid"})
	@Test(priority = 2)
	public void test_vcodeEmpty(String udid) throws InterruptedException{
		System.out.println("ForgetPassword * 未输入验证码 * ");
		driver = getDriver(udid);
		ForgetPasswordPage forgetPage = new ForgetPasswordPage(driver,udid);
		
		// 进入忘记密码页面
		forgetPage.toForgetPasswordPage();
		// 切换国家/地区
		forgetPage.clickChangeCountry();
		// 清除数据
		forgetPage.cleanText();
		// 输入手机号
		forgetPage.inputPhoneNumber("18800000030");
		// 点击保存并登录
		forgetPage.clickSaveAndLoginButton();
		
		String t = forgetPage.getToastContext();
		Asserts.verifyEquals(t, forgetPage.pleaseInputVcode);
	}

	@Feature("忘记密码")
	@Stories(value = { @Story(value = "手机号未注册")})
	@Description("未输入密码，提示请输入密码")
	@Step("测试步骤：1、进入忘记密码页面；2、切换国家；3、输入手机号；4、输入验证码；5、点击保存并登录；6、提示请输入密码 ")
	@Parameters({"udid"})
	@Test(priority = 3)
	public void test_passwordEmpty(String udid) throws InterruptedException{
		System.out.println("ForgetPassword * 未输入密码 * ");
		driver = getDriver(udid);
		ForgetPasswordPage forgetPage = new ForgetPasswordPage(driver,udid);
		
		// 进入忘记密码页面
		forgetPage.toForgetPasswordPage();
		// 切换国家/地区
		forgetPage.clickChangeCountry();
		// 清除数据
		forgetPage.cleanText();
		// 输入手机号
		forgetPage.inputPhoneNumber("18800000030");
		//输入验证码
		forgetPage.inputVerificationCode("000000");
		// 点击保存并登录
		forgetPage.clickSaveAndLoginButton();
		
		String t = forgetPage.getToastContext();
		Asserts.verifyEquals(t, forgetPage.pleaseInputPassword);
	}

	@Feature("忘记密码")
	@Stories(value = { @Story(value = "修改密码")})
	@Description("错误验证码，提示请输入密码")
	@Step("测试步骤：1、进入忘记密码页面；2、切换国家；3、输入手机号；4、点击获取验证码；5、输入验证码； 6、输入密码； 7、点击保存并登录 ")
	@Parameters({"udid"})
	@Test(priority = 4)
	public void test_vcodeErr(String udid) throws InterruptedException{
		System.out.println("ForgetPassword * 错误验证码 * ");
		driver = getDriver(udid);
		ForgetPasswordPage forgetPage = new ForgetPasswordPage(driver,udid);
		
		// 进入忘记密码页面
		forgetPage.toForgetPasswordPage();
		// 切换国家/地区
		forgetPage.clickChangeCountry();
		// 清除数据
		forgetPage.cleanText();
		// 输入手机号
		forgetPage.inputPhoneNumber("18800000030");
		// 输入验证码
		forgetPage.inputVerificationCode("000000");
		// 输入密码
		forgetPage.inputPassword("111111");
		// 点击保存并登录
		forgetPage.clickSaveAndLoginButton();
		
		String t = forgetPage.getToastContext();
		Asserts.verifyEquals(t, forgetPage.vCodeIsInvalid);
	}
	
	@Feature("忘记密码")
	@Stories(value = { @Story(value = "修改密码")})
	@Description("设置成功")
	@Step("测试步骤：1、进入忘记密码页面；2、切换国家；3、输入手机号；4、点击获取验证码；5、输入验证码； 6、输入密码； 7、点击保存并登录 ")
	@Parameters({"udid"})
	@Test(priority = 5)
	public void test_succuss(String udid) throws InterruptedException{
		System.out.println("ForgetPassword * 设置成功 * ");
		driver = getDriver(udid);
		ForgetPasswordPage forgetPage = new ForgetPasswordPage(driver,udid);	
		List<String>param=forgetPage.getParam(udid);
		String pNum = param.get(forgetPage.phoneNum);
		String pwd = param.get(forgetPage.password);
		// 进入忘记密码页面
		forgetPage.toForgetPasswordPage();
		// 切换国家/地区
		forgetPage.clickChangeCountry();
		// 清除数据
		forgetPage.cleanText();
		// 输入手机号
		forgetPage.inputPhoneNumber(pNum);
		// 输入验证码
		forgetPage.inputVerificationCode("000000");
		// 输入密码
		forgetPage.inputPassword(pwd);
		// 点击保存并登录
		forgetPage.clickSaveAndLoginButton();			
		HomePage home = new HomePage(driver,udid);
		// 关闭新手向导
		home.closeGuide();
		// 允许权限
		home.allowPermissionPopup();
		// 断言-进入首页
		Asserts.verifyTrue(home.isHomePage());
	}
}
