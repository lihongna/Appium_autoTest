package com.orangelab.PO;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import com.orangelab.config.Constants;
import com.orangelab.util.ConfigUtil;
import com.orangelab.util.FileUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class BasePage {
	public static Logger log = Logger.getLogger(BasePage.class);
	AppiumDriver driver;
	String udid;
	public BasePage(AppiumDriver driver,String udid) {
		this.driver = driver;
		this.udid = udid;
	}
	
	public AppiumDriver getDriver() {
		return this.driver;
	}
	/*
	 *通过id去找元素
	 *@param str :控件的resource-id属性值
	 */
	public WebElement FindId(String str){
		wait(5);
		WebElement el = this.driver.findElementById(str);		
		return el;
	}
	
	/*
	 *通过xpath去找元素
	 *@param str : xpath路径
	 */
	public WebElement FindXpath(String str){
		wait(5);
		WebElement el = this.driver.findElementByXPath(str);
		return  el;
	}
		
	/*
	 * 通过content-desc去找元素
	 *@param str : content-desc的值
	 */
	public WebElement FindDesc(String str) {
		wait(5);
		WebElement el = this.driver.findElementByAccessibilityId(str); 
		return el;
	}
	/*
	 *定位元素
	 *@param str :
	 *	通过id去找元素:findElementByAndroidUIAutomator("resource-id(\"str\")");
	 *	通过name去找元素:findElementByAndroidUIAutomator("text(\"textStr\")");
	 *	通过desc去找元素:findElementByAndroidUIAutomator("content-desc(\"descStr\")");
	 *	通过class去找元素:findElementsByAndroidUIAutomator("class(\"classStr\")");
	 */
	public WebElement FindElement(String str){
		wait(5);
		WebElement el = ((AndroidDriver)this.driver).findElementByAndroidUIAutomator(str);
		return el;
	}
	
	/*
	 *定位元素组
	 *@param str :
	 *	通过id去找元素:findElementByAndroidUIAutomator("resource-id(\"str\")");
	 *	通过name去找元素:findElementByAndroidUIAutomator("text(\"textStr\")");
	 *	通过desc去找元素:findElementByAndroidUIAutomator("content-desc(\"descStr\")");
	 *	通过class去找元素:findElementsByAndroidUIAutomator("class(\"classStr\")");
	 */
	public List<WebElement> FindElements(String str){
		wait(5);
		return ((AndroidDriver)this.driver).findElementsByAndroidUIAutomator(str);
	}
	
	/*
	 *  返回元素
	 *  @param by :
	 *  	By.name("控件的text属性值")
	 *  	By.id("控件的resource-de属性值")
	 *  	By.xpath("控件的xpath值") 
	 *   多个时，只返回第一个
	 */
	public WebElement findElement(By by){
		wait(5);
		WebElement elements = this.driver.findElement(by);
		return  elements;
	}
	
	/*
	 *  返回元素组
	 *  @param by :
	 *  	By.name("控件的text属性值")
	 *  	By.id("控件的resource-de属性值")
	 *  	By.xpath("控件的xpath值") 
	 */
	public List<WebElement> findElements(By by){
		wait(5);
		List<WebElement> elements = this.driver.findElements(by);
		return  elements;
	}
	
	/*
	 * 元素是否存在
	 * @param by :
	 *  	By.name("控件的text属性值")
	 *  	By.id("控件的resource-de属性值")
	 *  	By.xpath("控件的xpath值") 
	 */
	public boolean isElementsExist(By by) {
		try {
			wait(5);
			this.driver.findElement(by);
			return true;
		} catch (Exception e){
			log.info("Not find element!");
			return false;
		}
	}
	/*
	 * 元素是否存在
	 * @param str :
	 *	通过id去找元素:findElementByAndroidUIAutomator("resource-id(\"str\")");
	 *	通过name去找元素:findElementByAndroidUIAutomator("text(\"textStr\")");
	 *	通过desc去找元素:findElementByAndroidUIAutomator("content-desc(\"descStr\")");
	 *	通过class去找元素:findElementsByAndroidUIAutomator("class(\"classStr\")");
	 */
	public boolean isElementsExist(String str) {
		try {
			wait(5);
			((AndroidDriver)this.driver).findElementsByAndroidUIAutomator(str);
			return true;
		} catch (Exception e){
			log.info("Not find element!");
			return false;
		}
	}
	
	/*
	 * 指定时间内，元素是否存在，
	 * 存在，立刻返回结果
	 * 不存在，超时后返回结果
	 *  @param by : 控件中相对于的值
	 *  @param tiomout : 超时时间
	 */
	public boolean isElementsExist(By by, int timeout) {
		try {
			wait(timeout);
			this.driver.findElement(by);
			return true;
		} catch (Exception e){
			System.out.println("Not find element!");
			log.info("Not find element!");
			return false;
		}
	}
	
	/*
	 * click事件
	 */
	
	
	/*
	 *点击某元素
	 * @param element : 操作的元素
	 */
	public boolean tab(WebElement element) {
		try {
			TouchAction ta = new TouchAction(this.driver);
			ta.tap(ElementOption.element(element));		
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/*
	 *通过坐标点击某元素
	 * @param x ： x轴坐标
	 * @param y : y轴坐标
	 */
	public boolean tab(int x, int y) {
		try {
			TouchAction ta = new TouchAction(this.driver);
			ta.tap(PointOption.point(x,y));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/*
	 *点击某元素（RN可用）
	 * @param element : 操作的元素
	 */
	public boolean press(WebElement element) {
		try {
			TouchAction ta = new TouchAction(this.driver);
			ta.press(ElementOption.element(element)).release().perform();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/*
	 *通过坐标点击某元素（RN可用）
	 * @param x ： x轴坐标
	 * @param y : y轴坐标
	 */
	public boolean press(int x, int y) {
		try {
			TouchAction ta = new TouchAction(this.driver);
			//ta.tap(PointOption.point(x,y));
			ta.press(PointOption.point(x,y)).release().perform();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/*
	 * 长按某个元素
	 * @param element : 操作的元素
	 */
	public boolean longTouch(WebElement element) {
		try {
			TouchAction ta = new TouchAction(this.driver);
			ta.longPress(ElementOption.element(element)).release().perform();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/*
	 * 长按某个元素
	 * @param element : 操作的元素
	 * @param timeout : 持续时间    单位：ms
	 */
	public boolean longTouch(WebElement element, long timeout) {
		try {
			TouchAction ta = new TouchAction(this.driver);
			ta.longPress(ElementOption.element(element)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeout))).release().perform();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/*
	 * 长按某个坐标位置
	 * @param x ： x轴坐标
	 * @param y : y轴坐标
	 */
	public boolean longTouch(int x, int y) {
		try {
			TouchAction ta = new TouchAction(this.driver);
			ta.longPress(PointOption.point(x,y)).release().perform();		
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/*
	 * 长按某个坐标位置
	 * @param x ： x轴坐标
	 * @param y : y轴坐标
	 * @param timeout:持续时间   单位：ms
	 */
	public boolean longTouch(int x, int y, long timeout) {
		try {
			TouchAction ta = new TouchAction(this.driver);
			ta.press(PointOption.point(x,y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeout))).release().perform();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/* 向输入框输入内容
	 * @param element : 操作的元素
	 * @param string : 输入的内容
	 */
	public void sendkey(WebElement element, String string) {
		
		element.sendKeys(string);
	}
			
	
	/*
	 * 隐式等待
	 * 第一时间没有找到需要的元素，等待时间后，再次查找
	 * @param waittime ： 等待时间
	 */
	public void wait(int waittime) {
		this.driver.manage().timeouts().implicitlyWait(waittime, TimeUnit.SECONDS);
	}
	
	
	/*
	 * 显示等待
	 * @param time : 等待时间   单位：s
	 */
	public void waitTime(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	}
	
	/*
	 * Open the notification shade, on Android devices.
	 */
	public void openNotification() {
		((AndroidDriver)this.driver).openNotifications();
	}
	//获取当前activity
	public String getCurrActivity() {
		waitTime(1);
		String activity = ((AndroidDriver)this.driver).currentActivity();		
		return activity;
	}
		
	public String getPageSource() {
		return this.driver.getPageSource();
	}
	
	public String getTitle() {
		return this.driver.getTitle();
	}
	
	public String getPageContext() {
		return this.driver.getContext();
	}
	
	public void changeToWebView() {
		this.driver.switchTo();
	}
	
	public void changeToNative() {
		
	}
	/*
	 * 	关闭driver，退出应用
	 */
	public void quitDriver() {
		this.driver.quit();
	}
	
		
	/* 
	 * 获取手机屏幕大小
	 */
	public int[] appScreen() {
		int width = this.driver.manage().window().getSize().getWidth();
		int height = this.driver.manage().window().getSize().getHeight();
		int[] appWidthAndHeight = {width,height};
		return appWidthAndHeight;		
	}
	
	/*
	 * Android Home键
	 */
	public void pressHomeKey() {
		((AndroidDriver)this.driver).pressKey(new KeyEvent(AndroidKey.HOME));		
	}
	
	/*
	 * Android Back键
	 */
	public void pressBackKey() {
		((AndroidDriver)this.driver).pressKey(new KeyEvent(AndroidKey.BACK));
	}
	
	
}
