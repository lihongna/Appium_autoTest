package com.orangelab.testcase.login;

import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orangelab.PO.login.RegisterPage;
import com.orangelab.PO.main.HomePage;
import com.orangelab.PO.room.RoomPage;
import com.orangelab.asserts.Asserts;
import com.orangelab.testcase.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.*;

public class RegisterNumber  extends BaseTest{

private static AppiumDriver driver;

	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("号码为空，提示请输入手机号")
	@Step("测试步骤：1、进入注册页面；2、点击下一步；5、提示手机号已经被注册")
	@Parameters({"udid"})
	@Test(priority = 0)
	public void test_numberEmpty(String udid) throws InterruptedException{
		System.out.println("RegisterNumber * 未输入手机号   *");
		driver = getDriver(udid);
		RegisterPage rPage = new RegisterPage(driver,udid);
		// 进入注册页面
		rPage.toRegisterPage();
		// 清除数据
		rPage.cleanText();
		// 点击下一步
		rPage.clickNextButton();
		
		String t = rPage.getToastContext();
		Asserts.verifyEquals(t, rPage.pleaseInputNumber);
	}

	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("输入错误号码，提示手机号码格式错误")
	@Step("测试步骤：1、进入注册页面；2、输入手机号1000；3、点击获取验证码；4、提示手机号码格式错误")
	@Parameters({"udid"})
	@Test(priority = 1)
	public void test_numberErr(String udid) throws InterruptedException{
		System.out.println("RegisterNumber * 输入错误手机号  * ");
		driver = getDriver(udid);
		RegisterPage rPage = new RegisterPage(driver,udid);
		// 进入注册页面
		rPage.toRegisterPage();
		// 切换国家/地区
		rPage.clickChangeCountry();
		// 清除数据
		rPage.cleanText();
		// 输入手机号
		rPage.inputPhoneNumber("1000");
		// 点击获取验证码
		rPage.clickVerificationButton();
		
		String t = rPage.getToastContext();
		Asserts.verifyEquals(t, rPage.formatErr);
	}

	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("未输入验证码，提示请输入验证码")
	@Step("测试步骤：1、进入注册页面；2、输入手机号；3、点击下一步；5、提示请输入验证码")
	@Parameters({"udid"})
	@Test(priority = 2)
	public void test_vcodeEmpty(String udid) throws InterruptedException{
		System.out.println("RegisterNumber * 未输入验证码： * ");
		driver = getDriver(udid);
		RegisterPage rPage = new RegisterPage(driver,udid);
		// 进入注册页面
		rPage.toRegisterPage();
		// 切换国家/地区
		rPage.clickChangeCountry();
		// 清除数据
		rPage.cleanText();
		// 输入手机号
		rPage.inputPhoneNumber("18811111130");
		// 点击下一步
		rPage.clickNextButton();
		
		String t = rPage.getToastContext();
		Asserts.verifyEquals(t, rPage.pleaseInputVcode);
	}

	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("输入已注册号码，提示手机号已经注册")
	@Step("测试步骤：1、进入注册页面；2、切换国家；3、输入手机号；4、点击获取验证码；5、提示手机号已经被注册")
	@Parameters({"udid"})
	@Test(priority = 3)
	public void test_oldnumber(String udid) throws InterruptedException{
		System.out.println("RegisterNumber * 输入已注册号码： * ");
		driver = getDriver(udid);
		RegisterPage rPage = new RegisterPage(driver,udid);
		// 进入注册页面
		rPage.toRegisterPage();
		// 切换国家/地区
		rPage.clickChangeCountry();
		// 清除数据
		rPage.cleanText();
		// 输入手机号
		rPage.inputPhoneNumber("18800000030");
		// 点击获取验证码
		rPage.clickVerificationButton();
		
		String t = rPage.getToastContext();
		Asserts.verifyEquals(t, rPage.numberIsRegister);
	}

	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("新手机号，获取验证码出现倒计时")
	@Step("测试步骤：1、进入注册页面；2、输入手机号18811111130；3、点击获取验证码；")
	@Parameters({"udid"})
	@Test(priority = 4)
	public void test_getvcode(String udid) throws InterruptedException{
		System.out.println("RegisterNumber * 获取验证码出现倒计时 * ");
		driver = getDriver(udid);
		RegisterPage rPage = new RegisterPage(driver,udid);
		// 进入注册页面
		rPage.toRegisterPage();
		// 切换国家/地区
		rPage.clickChangeCountry();
		// 清除数据
		rPage.cleanText();
		// 输入手机号
		rPage.inputPhoneNumber("18811111130");
		// 点击获取验证码
		rPage.clickVerificationButton();
		
		if(rPage.isCountdown()) {
			Asserts.verifyTrue(true);
		} else {
			String t = rPage.getToastContext();
			Asserts.verifyEquals(t, rPage.onlySend3times);
		}		
	}

	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("未输入密码")
	@Step("测试步骤：1、进入注册页面；2、切换国家；3、输入手机号；4、点击获取验证码；5、输入验证码； 6、点击下一步")
	@Parameters({"udid"})
	@Test(priority = 5)
	public void test_passwordEmpty(String udid) throws InterruptedException{
		System.out.println("RegisterNumber * 未输入密码 * ");
		driver = getDriver(udid);		
		RegisterPage rPage = new RegisterPage(driver,udid);
		// 进入注册页面
		rPage.toRegisterPage();
		// 切换国家/地区
		rPage.clickChangeCountry();
		// 清除数据
		rPage.cleanText();
		// 输入手机号
		rPage.inputPhoneNumber("18811111130");
		// 输入验证码
		rPage.inputVerificationCode("000000");		
		// 点击下一步
		rPage.clickNextButton();
		
		String t = rPage.getToastContext();
		Asserts.verifyEquals(t, rPage.pleaseInputPassword);
	}

	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("未输入确认密码")
	@Step("测试步骤：1、进入注册页面；2、切换国家；3、输入手机号；4、点击获取验证码；5、输入验证码； 6、输入密码； 7、点击下一步")
	@Parameters({"udid"})
	@Test(priority = 6)
	public void test_confirmEmpty(String udid) throws InterruptedException{
		System.out.println("RegisterNumber * 未输入确认密码 * ");
		driver = getDriver(udid);		
		RegisterPage rPage = new RegisterPage(driver,udid);
		// 进入注册页面
		rPage.toRegisterPage();
		// 切换国家/地区
		rPage.clickChangeCountry();
		// 清除数据
		rPage.cleanText();
		// 输入手机号
		rPage.inputPhoneNumber("18811111130");
		// 输入验证码
		rPage.inputVerificationCode("000000");
		// 输入密码
		rPage.inputPassword("111111");		
		// 点击下一步
		rPage.clickNextButton();
		
		String t = rPage.getToastContext();
		Asserts.verifyEquals(t, rPage.pleaseConfirmInputPassword);
	}

	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("输入两次不一样的密码")
	@Step("测试步骤：1、进入注册页面；2、切换国家；3、输入手机号；4、点击获取验证码；5、输入验证码； 6、输入密码； 7、再次输入密码；8、点击下一步")
	@Parameters({"udid"})
	@Test(priority = 7)
	public void test_diffrentPassword(String udid) throws InterruptedException{
		System.out.println("RegisterNumber * 两次密码不一致 * ");
		driver = getDriver(udid);		
		RegisterPage rPage = new RegisterPage(driver,udid);
		// 进入注册页面
		rPage.toRegisterPage();
		// 切换国家/地区
		rPage.clickChangeCountry();
		// 清除数据
		rPage.cleanText();
		// 输入手机号
		rPage.inputPhoneNumber("18811111130");
		// 输入验证码
		rPage.inputVerificationCode("000000");
		// 输入密码
		rPage.inputPassword("111111");
		// 再次输入密码
		rPage.inputPasswordAgain("123456");
		// 点击下一步
		rPage.clickNextButton();
		
		String t = rPage.getToastContext();
		Asserts.verifyEquals(t, rPage.passwordNotSame);
	}

	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("输入错误验证码")
	@Step("测试步骤：1、进入注册页面；2、切换国家；3、输入手机号；4、点击获取验证码；5、输入验证码； 6、输入密码； 7、再次输入密码；8、点击下一步")
	@Parameters({"udid"})
	@Test(priority = 8)
	public void test_vcodeErr(String udid) throws InterruptedException{
		System.out.println("RegisterNumber * 错误验证码 * ");
		driver = getDriver(udid);		
		RegisterPage rPage = new RegisterPage(driver,udid);		
		// 进入注册页面
		rPage.toRegisterPage();
		// 切换国家/地区
		rPage.clickChangeCountry();
		// 清除数据
		rPage.cleanText();
		// 输入手机号
		rPage.inputPhoneNumber("18811111130");
		// 输入验证码
		rPage.inputVerificationCode("000000");
		// 输入密码
		rPage.inputPassword("111111");
		// 再次输入密码
		rPage.inputPasswordAgain("111111");
		// 点击下一步
		rPage.clickNextButton();	
		
		String t = rPage.getToastContext();
		Asserts.verifyEqualsOr(t, rPage.vcodeIsError, rPage.vcodeIsInvalid);
	}

	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("成功注册")
	@Step("测试步骤：1、进入注册页面；2、切换国家；3、输入手机号；4、点击获取验证码；5、输入验证码； 6、输入密码； 7、再次输入密码；8、点击下一步")
	@Parameters({"udid"})
	@Test(priority = 9)
	public void test_register(String udid) throws InterruptedException{
		System.out.println("RegisterNumber * 注册新号码 * 成功注册 ");
		driver = getDriver(udid);
		RegisterPage rPage = new RegisterPage(driver,udid);
		List<String> param = rPage.getParam(udid);
		String regNum = param.get(rPage.registerNum);
		String pwd = param.get(rPage.password);
		// 进入注册页面
		rPage.toRegisterPage();
		// 切换国家/地区
		rPage.clickChangeCountry();
		// 清除数据
		rPage.cleanText();
		// 输入手机号
		rPage.inputPhoneNumber(regNum);
		// 输入验证码
		rPage.inputVerificationCode("000000");
		// 输入密码
		rPage.inputPassword(pwd);
		// 再次输入密码
		rPage.inputPasswordAgain(pwd);
		// 点击下一步
		rPage.clickNextButton();
		
		Asserts.verifyTrue(rPage.isRegisterInfo());
	}

	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("成功注册 * 未输入昵称")
	@Step("测试步骤：1、进入注册个人资料页面；2、点击立即进入")
	@Parameters({"udid"})
	@Test(priority = 10)
	public void test_nicknameEmpty(String udid) {
		System.out.println("RegisterNumber * 注册新号码 * ");
		driver = getDriver(udid);
		RegisterPage rPage = new RegisterPage(driver,udid);
		// 点击立即进入
		rPage.clickImmediatelyEntry();
		
		String t = rPage.getToastContext();
		Asserts.verifyEquals(t, rPage.pleaseInputNickname);
	}

	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("成功注册 * 设置性别为男")
	@Step("测试步骤：1、进入注册个人资料页面；2、点击性别；3、选择不同性别")
	@Parameters({"udid"})
	@Test(priority = 11)
	public void test_genderM(String udid) {
		System.out.println("RegisterNumber * 注册新号码 * 设置性别为男");
		driver = getDriver(udid);		
		RegisterPage rPage = new RegisterPage(driver,udid);		
		// 点击女
		rPage.clickWoman();
		// 点击男
		rPage.clickMan();		
		
		Asserts.verifyTrue(rPage.isMan());
	}

	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("成功注册 * 设置性别为女")
	@Step("测试步骤：1、进入注册个人资料页面；2、点击性别；3、选择不同性别")
	@Parameters({"udid"})
	@Test(priority = 12)
	public void test_genderW(String udid) {		
		System.out.println("RegisterNumber * 注册新号码 * 设置性别为女");
		driver = getDriver(udid);		
		RegisterPage rPage = new RegisterPage(driver,udid);		
		// 点击男
		rPage.clickMan();
		// 点击女
		rPage.clickWoman();		
		
		Asserts.verifyTrue(rPage.isWoman());
	}

	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("成功注册 * 设置性别  取消")
	@Step("测试步骤：1、进入注册个人资料页面；2、点击性别；3、选择取消")
	@Parameters({"udid"})
	@Test(priority = 13)
	public void test_genderC(String udid) {
		System.out.println("RegisterNumber * 注册新号码 * 设置性别  取消");
		driver = getDriver(udid);
		RegisterPage rPage = new RegisterPage(driver,udid);
		// 点击女
		rPage.clickWoman();
		// 点击取消
		rPage.clickCancel();
		
		Asserts.verifyTrue(rPage.isWoman());
	}

	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("成功注册 * 设置头像  拍照")
	@Step("测试步骤：1、进入注册个人资料页面；2、设置头像；3、点击拍照")
	@Parameters({"udid"})
	@Test(priority = 14)
	public void test_fromCamera(String udid) {
		System.out.println("RegisterNumber * 注册新号码 * 设置头像  拍照");
		driver = getDriver(udid);
		RegisterPage rPage = new RegisterPage(driver,udid);
		// 点击设置头像
		rPage.clickPhoto();
		// 点击拍照
		rPage.clickCamera();
		
		rPage.allowPermissionPopup();
		
		String s = rPage.getCurrActivity();
		System.out.println(s);
		Asserts.activityEquals("com.android.camera.CaptureActivity", s);
		//rPage.pressBackKey();
	}

	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("成功注册 * 设置头像  从相册中选择")
	@Step("测试步骤：1、进入注册个人资料页面；2、设置头像；3、点击从相册中选择")
	@Parameters({"udid"})
	@Test(priority = 15)
	public void test_fromPicture(String udid) {
		System.out.println("RegisterNumber * 注册新号码 * 设置头像  从相册中选择");
		driver = getDriver(udid);
		RegisterPage rPage = new RegisterPage(driver,udid);
		if(!rPage.isRegisterInfo()) {
			rPage.pressBackKey();
		}
		// 点击设置头像
		rPage.clickPhoto();
		// 点击拍照
		rPage.clickPicture();
		
		rPage.allowPermissionPopup();
		
		String s = rPage.getCurrActivity();
		System.out.println(s);
		Asserts.activityEquals(".DocumentsActivity", s);
		//rPage.pressBackKey();
	}

	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("成功注册 * 设置头像  取消")
	@Step("测试步骤：1、进入注册个人资料页面；2、设置头像；3、点击取消")
	@Parameters({"udid"})
	@Test(priority = 16)
	public void test_cancel(String udid) {
		System.out.println("RegisterNumber * 注册新号码 * 设置头像  取消");
		driver = getDriver(udid);
		RegisterPage rPage = new RegisterPage(driver,udid);
		if(!rPage.isRegisterInfo()) {
			rPage.pressBackKey();
		}
		// 点击设置头像
		rPage.clickPhoto();
		// 点击取消
		rPage.clickCancel();
		
		rPage.allowPermissionPopup();
		
		Asserts.verifyTrue(rPage.isRegisterInfo());
	}

	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("成功注册 * 设置昵称")
	@Step("测试步骤：1、进入注册个人资料页面；2、输入昵称；3、点击立即进入")
	@Parameters({"udid"})
	@Test(priority = 17)
	public void test_nickname(String udid) {
		System.out.println("RegisterNumber * 注册新号码 * 设置昵称-进入");
		driver = getDriver(udid);
		RegisterPage rPage = new RegisterPage(driver,udid);
		HomePage home = new HomePage(driver,udid);
		if(!rPage.isRegisterInfo()) {
			rPage.pressBackKey();
		}
		// 输入昵称
		rPage.inputNickname();
		// 立即进入
		rPage.clickImmediatelyEntry();
		// 断言-进入首页,有新手向导	
		if(home.isNewGuide()) {
			rPage.updateRegiterNumber(udid);
			Asserts.verifyTrue(true);
		} else {
			Asserts.verifyTrue(false);
		}
		
	}
	
	@Feature("注册")
	@Stories(value = { @Story(value = "号码注册账号")})
	@Description("新用户登录-进入首页")
	@Step("测试步骤：1、进入向导页面；2、关闭向导")
	@Parameters({"udid"})
	@Test(priority = 18)
	public void test_toHome(String udid) {
		System.out.println("RegisterNumber * 进入首页");	
		driver = getDriver(udid);		
		//reset = true;
		HomePage home = new HomePage(driver,udid);
		// 关闭新手向导
		home.closeGuide();
		// 允许权限
		home.allowPermissionPopup();
		// 断言-进入首页
		Asserts.verifyTrue(home.isHomePage());		
	}
	
}
