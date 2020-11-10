
package com.orangelab.listener;

import org.openqa.selenium.OutputType;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

import com.orangelab.testcase.BaseTest;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Attachment;

public class AllureReporterListener extends BaseTest implements IHookable{
	
	public void run(IHookCallBack callBack, ITestResult testResult) {
		// TODO Auto-generated method stub
		callBack.runTestMethod(testResult);
		System.out.println(testResult.getTestContext().getName());            	
    	AppiumDriver driver = getDriver(testResult.getTestContext().getName());
        if (testResult.getThrowable() != null) {
            try {                  	
            	takeFailPhoto(driver);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
        	 try {                	
             	takePhoto(driver);
             } catch (Exception e) {
                 e.printStackTrace();
             }
        }
	}
	
	/*
	 * 	成功截图
	 */
	@Attachment(value = "成功截图如下：",type = "image/png")
	public byte[] takePhoto(AppiumDriver driver){	
		System.out.println("成功截图");
		return driver.getScreenshotAs(OutputType.BYTES);		
	}
	/*
	 * 	失败截图
	 */
	@Attachment(value = "失败截图如下：",type = "image/png")
	public byte[] takeFailPhoto(AppiumDriver driver){	
		System.out.println("失败截图");
		return driver.getScreenshotAs(OutputType.BYTES);		
	}
	
	/**
	 * 打印测试步骤
	 * @param tr
	 */
	@Attachment(value = "操作步骤如下：")
	public String logCaseStep(ITestResult tr){
		String step = tr.getTestContext().getAllTestMethods().toString();
		System.out.println(tr.getInstance().toString());
		return step;
	}

	/**
	 * 打印测试步骤
	 * @param tr
	 */
	@Attachment(value = "期望结果如下：")
	public String exceptedResult(ITestResult tr){
		String result = "显示查询结果";
		return result;
	}
}
