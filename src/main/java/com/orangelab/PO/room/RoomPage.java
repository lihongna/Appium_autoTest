package com.orangelab.PO.room;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.orangelab.PO.main.HomePage;
import io.appium.java_client.AppiumDriver;

public class RoomPage extends HomePage {	
	public RoomPage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
	}
	// 语音房
	public static final String ROOM_ID = "cn.orangelab.werewolf:id/tv_room_id";
	public static final String EXIT_ROOM_ID = "cn.orangelab.werewolf:id/iv_back";
	public static final String CONFIRM_QUITROOM_XPATH = "//*[(@text='退出房间') or (@name='退出房间')]";
	public static final String CANCEL_QUITROOM_XPATH = "//*[(@text='取消') or (@name='取消')]";
	public static final String SUSPEND_ROOM_XPATH = "//*[(@text='小窗逛逛') or (@name='小窗逛逛')]";
	// 狼杀游戏房
	public static final String WOLFROOM_ID = "cn.orangelab.werewolf:id/id_werewolf_room_id";
	public static final String EXIT_WOLFROOM_ID = "cn.orangelab.werewolf:id/id_werewolf_exit";
	// 谁是卧底
	public static final String EXIT_SPY_ROOM_ID	= "cn.orangelab.werewolf:id/iv_spy_room_back";
	public static final String SPY_CONFIRM_BUTTON_ID = "cn.orangelab.werewolf:id/btn_sure";
	public static final String SPY_CANCEL_BUTTON_ID = "cn.orangelab.werewolf:id/btn_cancel";
	
	
	// 点击退出按钮
	public void clickBack() {
		WebElement el = FindId(EXIT_ROOM_ID);
		press(el);
	}
	// 点击退出狼杀房按钮
	public void clickWolfBack() {
		WebElement el = FindId(EXIT_WOLFROOM_ID);
		press(el);
	}
	
	
	// 获取roomid
	public String getRoomID() {
		waitTime(2);
		WebElement el = FindId(ROOM_ID);
		return el.getText();
	}
	// 判断是否在房间
	public boolean isInVoiceRoom() {
		if(isElementsExist(By.id(ROOM_ID)) && isElementsExist(By.id(EXIT_ROOM_ID))) {
			return true;
		}
		return false;
	}
	// 点击确定按钮
	public void clickConfirmQuit() {
		WebElement el = FindXpath(CONFIRM_QUITROOM_XPATH);
		press(el);
	}
	// 点击取消按钮
	public void clickCancelQuit() {
		WebElement el = FindXpath(CANCEL_QUITROOM_XPATH);
		press(el);
	}
	// 点击小窗逛逛按钮
	public void clickSuspendWin() {
		WebElement el = FindXpath(SUSPEND_ROOM_XPATH);
		press(el);
	}
	// 确定退出房间
	public void quitRoom() {
		clickBack();
		clickConfirmQuit();
	}
	// 获取Wolfroomid
	public String getWolfRoomID() {
		waitTime(2);
		WebElement el = FindId(WOLFROOM_ID);
		String s = el.getText();
		String id = s.replaceAll("房", "");
		return id;
	}
	// 判断是否在狼杀房
	public boolean isInWolfRoom() {
		if(isElementsExist(By.id(WOLFROOM_ID)) && isElementsExist(By.id(EXIT_WOLFROOM_ID))) {
			return true;
		}
		return false;
	}
	// 点击确定按钮
	public void clickConfirmQuitWolfRoom() {
		WebElement el = FindId(CONFIRM_BUTTON_ID);
		press(el);
	}
	// 点击取消按钮
	public void clickCancelQuitWolfRoom() {
		WebElement el = FindId(CANCEL_BUTTON_ID);
		press(el);
	}
	// 确定退出狼杀房
	public void quitWolfRoom() {
		clickWolfBack();
		clickConfirmQuitWolfRoom();
	}
}
