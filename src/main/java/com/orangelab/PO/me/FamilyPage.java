package com.orangelab.PO.me;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.orangelab.PO.main.MePage;

import io.appium.java_client.AppiumDriver;

public class FamilyPage extends MePage {
	public FamilyPage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
	}

	public static final String FAMILYBACK_ID = "cn.orangelab.werewolf:id/id_family_info_back";
	public static final String FAMILYID_ID = "cn.orangelab.werewolf:id/id_family_id";
	public static final String FAMILYSETTING_ID = "cn.orangelab.werewolf:id/id_family_info_setting";
	
	
	// 点击back
	public void clickBack() {
		WebElement el = FindId(FAMILYBACK_ID);	
		press(el);
	}
	// 点击设置
	public void clickSetting() {
		WebElement el = FindId(FAMILYSETTING_ID);	
		press(el);
	}
	// 获取家族ID
	public String getFamilyID() {
		WebElement el = FindId(FAMILYID_ID);
		String s = el.getText();
		String id = s.replaceAll("ID:", "");
		return id;
	}
	
	public void toFamilyPage() {
		int times = 5;
		while(times>=0) {
			if(isFamilyPage()) {
				return;
			} else {
				toMePage();
				clickFamily();			
			}
			times--;
		}
	}
	
	public boolean isFamilyPage() {
		if(isElementsExist(By.id(FAMILYID_ID)) && isElementsExist(By.id(FAMILYBACK_ID))) {
			return true;
		}
		return false;
	}
}
