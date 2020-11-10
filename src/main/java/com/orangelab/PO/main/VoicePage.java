package com.orangelab.PO.main;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;

public class VoicePage extends MainPage{	
	public VoicePage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
	}

	// 推荐tab
	public static final String RECOMMENDTAB_XPATH = "//*[(@text='推荐') or (@name='推荐')]";
	// 语音房tab
	public static final String VOICETAB_XPATH = "//*[(@text='语音房') or (@name='语音房')]";
	// KTVtab
	public static final String KTVTAB_XPATH = "//*[(@text='KTV') or (@name='KTV')]";
	// FMtab
	public static final String FMTAB_XPATH = "//*[(@text='FM') or (@name='FM')]";
	// 戏说tab
	public static final String PALYTAB_XPATH = "//*[(@text='戏说') or (@name='戏说')]";
	// 进入语音的页面
    public void toVoicePage() {    	
    	int times = 5;
    	while(times>=0) {
			if(isVoicePage()){	
				return;
			} else {
				toMainPage();
				clickVoice();
				times--;
			}
    	}
    }
    
    public boolean isVoicePage() {
    	if(isElementsExist(By.xpath(VIOCEROOM_XPATH)) && isElementsExist(By.xpath(VOICETAB_XPATH)) && isElementsExist(By.xpath(KTVTAB_XPATH)) 
				&& isElementsExist(By.xpath(FMTAB_XPATH)) && isElementsExist(By.xpath(PALYTAB_XPATH))){	
			return true;
		}
    	return false;
    }
}
