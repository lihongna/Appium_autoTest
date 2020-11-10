package com.orangelab.PO.home;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.orangelab.PO.main.HomePage;
import com.orangelab.config.Config;

import io.appium.java_client.AppiumDriver;

public class SearchPage extends HomePage{
	
	public SearchPage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
	}
	public static final String SEARCH_ROOM_XPATH = "//*[(@text='房间') or (@name='房间')]";
	public static final String SEARCH_FRIEND_XPATH = "//*[(@text='好友') or (@name='好友')]";
	public static final String SEARCH_FAMILY_XPATH = "//*[(@text='家族') or (@name='家族')]";
	public static final String FIND_XPATH = "//*[(@text='查找') or (@name='查找')]";
	public static final String pleaseInputRoomNumber = "请输入房间号";
	public static final String pleaseInputFriendNumber = "请输入好友ID";
	public static final String pleaseInputFamilyNumber = "请输入家族ID";
	public static final String roomNotExist = "房间不存在或已经失效";
	public static final String userNotExist = "用户不存在";
	public static final String familyNotExist = "家族不存在";
	
	// 点击房间
	public void clickRoom() {
		WebElement el = FindXpath(SEARCH_ROOM_XPATH);			
		press(el);
	}
	// 点击好友
	public void clickFriend() {
		WebElement el = FindXpath(SEARCH_FRIEND_XPATH);			
		press(el);
	}
	// 点击家族
	public void clickFamily() {
		List<WebElement> el = findElements(By.xpath(SEARCH_FAMILY_XPATH));	
		press(el.get(1));
	}
	// 点击查找
	public void clickFind() {
		WebElement el = FindXpath(FIND_XPATH);			
		press(el);
	}
	// 编辑框中输入ID
	public void inputSearchID(String id) {
		WebElement el;
		if(Config.platformName.equals("Android")) {
			el = FindXpath(EDITTEXT_XPATH);	
		} else {
			el = FindXpath(IOS_EDITTEXT_XPATH);
		}
		el.clear();
		el.sendKeys(id);		
	}
	
	public void toSearchPage() {
		int times = 3;
		while(times>=0) {
			if(isSearchPage()) {
				return;
			} else {
				toHomePage();
				clickSearch();			
			}
			times--;
		}
	}
	public boolean isSearchPage() {
		if(isElementsExist(By.xpath(FIND_XPATH)) && isElementsExist(By.xpath(SEARCH_ROOM_XPATH)) && 
				isElementsExist(By.xpath(SEARCH_FRIEND_XPATH)) && isElementsExist(By.xpath(SEARCH_FAMILY_XPATH))) {
			return true;
		}
		return false;
	}
	
}
