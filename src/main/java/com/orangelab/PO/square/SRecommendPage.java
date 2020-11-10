package com.orangelab.PO.square;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.orangelab.PO.main.SquarePage;
import io.appium.java_client.AppiumDriver;

public class SRecommendPage extends SquarePage{
	public SRecommendPage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
	}

	// 热门动态
	public static final String HOT_TRENDS_XPATH = "//*[(@text='热门动态') or (@name='热门动态')]";
	// 推荐话题
	public static final String RECOMMENDED_TOPICS_XPATH = "//*[(@text='推荐话题') or (@name='推荐话题')]";
	// 更多热门
	public static final String MORE_HOT_XPATH = "//*[(@text='更多热门') or (@name='更多热门')]";
	// 更多话题
	public static final String MORE_TOPICS_XPATH = "//*[(@text='更多话题') or (@name='更多话题')]";
	
	
	// 点击更多热门
	public void clickMorehot() {
		WebElement el = FindXpath(MORE_HOT_XPATH);					
    	press(el);
	}
	// 点击更多话题
	public void clickMoretoptice() {
		WebElement el = FindXpath(MORE_TOPICS_XPATH);					
    	press(el);
	}
	
	
	// 进入推荐的页面
    public void toRecommend() {    	
    	int times = 5;
    	while(times>=0) {
			if(isRecommendPage()){	
				return;
			} else {
				toSquarePage();
				clickRecommend();
				times--;
			}
    	}
    }
    
    public boolean isRecommendPage() {
    	if(isElementsExist(By.xpath(HOT_TRENDS_XPATH)) && isElementsExist(By.xpath(RECOMMENDED_TOPICS_XPATH))) {
    		return true;
    	}
    	return false;
    }
}
