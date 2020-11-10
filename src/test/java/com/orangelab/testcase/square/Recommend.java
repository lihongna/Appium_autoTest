package com.orangelab.testcase.square;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.orangelab.PO.square.SRecommendPage;
import com.orangelab.asserts.Asserts;
import com.orangelab.testcase.BaseTest;
import io.qameta.allure.*;

public class Recommend extends BaseTest {
	@Feature("广场")
	@Stories(value = { @Story(value = "推荐")})
	@Description("进入广场-推荐")
	@Step("测试步骤：1、进入广场页面；2、点击推荐")
	@Parameters({"udid"})
	@Test(priority = 0)  
	public void test_001(String udid) {	  
		System.out.println("Recommend * test_001 * " );			
		driver = getDriver(udid);
		SRecommendPage re = new SRecommendPage(driver,udid);
		re.toRecommend();	
		Asserts.verifyTrue(re.isRecommendPage());
	}
}
