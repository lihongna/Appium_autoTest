package com.orangelab.PO.game;

import org.openqa.selenium.By;
import io.appium.java_client.AppiumDriver;


public class JumpGamePage extends PKGamePage {
    
    public JumpGamePage(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
	}

	private void doJump(long distance) {
		System.out.println("distance: " + distance);
		long pressTime=distance;
    	int[] wid_h = appScreen();
		int widthX = wid_h[0];
		int heightY = wid_h[1];
		
		longTouch(widthX*4/5,heightY*4/5,pressTime);
    }
    
    public void playgame() {
    	if(isPKGamePage()) {
    		return;
    	}
    	int time = 1;
    	while(!isGameOverPage()) {
    		if(time>10) time = 0;    		
    		doJump(time * 3);
    		time++;
    	}    	
    }
   
}
