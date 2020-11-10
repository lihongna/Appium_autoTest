package com.orangelab.PO.game;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.orangelab.PO.main.HomePage;

import io.appium.java_client.AppiumDriver;

public class PKGamePage extends HomePage {
	public PKGamePage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
	}
	public static Logger log = Logger.getLogger(PKGamePage.class);
	public static final String MATCHOPPONENT_XPATH = "//*[contains(@text,'匹配對手')  or contains(@text,'匹配对手')]"; 
	public static final String MYBATTLE_XPATH = "//*[contains(@text,'我的對戰')  or contains(@text,'我的对战')]"; 
	public static final String JUMPGAME_XPATH = "//*[contains(@text,'跳一跳') ]";
	public static final String STARTMATCH_XPATH = "//*[contains(@text,'開始匹配')  or contains(@text,'开始匹配')]"; 
	public static final String LOADING_XPATH = "//*[contains(@text,'资源加载中')]";
	public static final String MATCHING_XPATH = "//*[contains(@text,'速配中')]";
	// 关闭游戏结束dailog
	public static final String CLOSE_DIALOG_ID = "cn.orangelab.werewolf:id/id_dialog_close";
	// 游戏返回键
    public static final String BACK_BUTTON_ID = "cn.orangelab.werewolf:id/id_mini_game_exit";
    // 游戏匹配返回键    
    public static final String MATCHBACK_BUTTON_ID = "cn.orangelab.werewolf:id/id_mini_game_back";
     // 游戏结果界面
    public static final String GAMERESULT_ID = "cn.orangelab.werewolf:id/id_mini_game_result";
    public static boolean isTimeout = false;

	/*
	 * 	 点击跳一跳
	 */
	public void click_game_jumps() {
		swipePageUtilFoundElement(By.xpath(JUMPGAME_XPATH), "UP", 2);
		
		while(!isMatchPage()) {
			WebElement jGame = FindXpath(JUMPGAME_XPATH);			
			press(jGame);
			wait(1);
		}
	}
	public boolean isMatchPage() {
		if(isElementsExist(By.xpath(STARTMATCH_XPATH))) {
			return true;
		}
		return false;
	}
	/*
	 * 点击开始匹配
	 */
	public void clickMatch() {
		WebElement match = FindXpath(STARTMATCH_XPATH);			
		press(match);
	}
	// 退出匹配页面
	public void clickMatchBack() {
		WebElement match = FindId(MATCHBACK_BUTTON_ID);			
		press(match);
	}
	/*
	 * 等待匹配
	 */
	public void waitMatch() {
		boolean flag = false;
		isTimeout = false;
		Thread timeout = new Thread(new TimeoutRunnable());
		timeout.start();
		while(!flag && !isTimeout) {			
			flag = isGamePlaying();
			wait(3);
		}
		if(isTimeout) {
			clickMatchBack();
		}
		
	}
	/*
	 * 判断是否游戏中
	 */
	public boolean isGamePlaying() {
    	if(!isElementsExist(By.xpath(LOADING_XPATH)) && !isElementsExist(By.xpath(MATCHING_XPATH))) {
    		return true;
    	}
    	return false;
    }
	/*
	 * 	关闭游戏结束dialog
	 */
	public void closeGameOverDialog() {
		WebElement closed = FindId(CLOSE_DIALOG_ID);		
		press(closed);
	}
	
	/*
	 * 	进入PK小游戏
	 */
	public void toPKGamePage() {
		int times = 5;
		while(times>=0) {
			if(isPKGamePage()) {
				return;
			} else {
				toHomePage();
				clickPKGame();
				times--;
			}
		}
	}
	
	public boolean isPKGamePage() {
		if(isElementsExist(By.xpath(MATCHOPPONENT_XPATH)) && isElementsExist(By.xpath(MYBATTLE_XPATH))) {			
			return true;
		}
		return false;
	}
	public boolean isGameOverPage() {
		if(isElementsExist(By.id(GAMERESULT_ID))) {
			return true;
		}
		return false;
	}
	class TimeoutRunnable implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			int s= 12;			
			while(s>=0) {				
				waitTime(10);
				s--;
			}
			isTimeout = true;
		}
    	
    }
}
