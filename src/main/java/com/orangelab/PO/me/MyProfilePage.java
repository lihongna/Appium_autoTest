package com.orangelab.PO.me;

import org.openqa.selenium.By;
import com.orangelab.PO.main.MePage;
import io.appium.java_client.AppiumDriver;

public class MyProfilePage extends MePage{
	public MyProfilePage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
	}

	public static final String MY_PROFILE_XPATH = "//*[(@text='个人资料') or (@name='个人资料')]";	

	public void toProfilePage() {
		
	}
	
	public boolean isProfilePage() {
		if(isElementsExist(By.xpath(MY_PROFILE_XPATH))){
			return true;
		}
		return false;
	}
}
