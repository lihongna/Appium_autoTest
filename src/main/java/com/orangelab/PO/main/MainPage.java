package com.orangelab.PO.main;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.orangelab.PO.login.LoginPage;
import com.orangelab.PO.login.NumberLoginPage;
import com.orangelab.PO.room.RoomPage;

import io.appium.java_client.AppiumDriver;

public class MainPage extends LoginPage {
	private AppiumDriver driver;
	private String udid;
	
	public MainPage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		this.udid = udid;
	}
	
	public static Logger log = Logger.getLogger(MainPage.class);
	//首页
	public static final String HOMETAB_XPATH = "//*[contains(@content-desc,'Home') or (@name='Home, tab, 1 of 5')]";
	//广场
	public static final String SQUARE_XPATH = "//*[contains(@content-desc,'Square') or (@name='Square, tab, 2 of 5')]";
	//语音房
	public static final String VIOCEROOM_XPATH = "//*[contains(@content-desc,'VoiceRoomManager') or (@name='VoiceRoomManager, tab, 3 of 5')]";
	//消息
	public static final String MESSAGE_XPATH = "//*[contains(@content-desc,'MainMessage') or (@name='MainMessage, tab, 4 of 5')]";
	//我的
	public static final String ME_XPATH = "//*[contains(@content-desc,'Me, tab, 5 of 5') or (@name='Me, tab, 5 of 5')]";
	
	// 点击首页tab
	public void clickHome() {
		WebElement home = FindXpath(HOMETAB_XPATH);					
    	press(home);
	}
	
	// 点击广场tab
	public void clickSquare() {
		WebElement square = FindXpath(SQUARE_XPATH);					
    	press(square);
	}
	
	// 点击语音tab
	public void clickVoice() {
		WebElement vRoom = FindXpath(VIOCEROOM_XPATH);					
    	press(vRoom);
	}
	
	// 点击消息tab
	public void clickMessage() {
		WebElement message = FindXpath(MESSAGE_XPATH);					
    	press(message);
	}
	
	// 点击我的tab
	public void clickMe() {
		WebElement mine = FindXpath(ME_XPATH);					
    	press(mine);
	}    
   
    
    // 进入主页面
	public void toMainPage() {   		
		int times = 10;
		NumberLoginPage login = new NumberLoginPage(this.driver,this.udid);
		RoomPage room = new RoomPage(this.driver,this.udid);
		login.toNumberLogin();
		while(times>=0) {
			if(isMainPage()){				
		    	return;
			} else if(!login.isAlreadyLogin()) {
	    		login.userLogin();
	    	} else if(room.isInWolfRoom()){
				room.quitWolfRoom();
			} else if(room.isInVoiceRoom()){
				room.quitRoom();
			} else {	    		
				pressBackKey();	
				closedWindow(); 
			}
			times--;
		}
	}
	
	public boolean isMainPage() {
		if(isElementsExist(By.xpath(HOMETAB_XPATH))){				
	    	return true;
		}
		return false;
	}
}
