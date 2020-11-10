package com.orangelab.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class BasePageWithSwipe extends BasePage{
	private AppiumDriver driver;
	
	public BasePageWithSwipe(AppiumDriver driver, String udid) {
		super(driver, udid);
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	/*
	 * 向上滑动页面
	 */
	public void swipePageToUp() {
		int[] wid_h = appScreen();
		int width = wid_h[0];
		int height = wid_h[1];
		
		int startX =width*1/2;
		int endX = width*1/2;
		int startY = height*4/5;
		int endY = height*1/5;	
		try {
			swipe(startX, startY, endX, endY);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 向下滑动页面
	 */
	public void swipePageToDown() {
		int[] wid_h = appScreen();
		int width = wid_h[0];
		int height = wid_h[1];
		
		int startX =width*1/2;
		int endX = width*1/2;
		int startY = height*1/5;
		int endY = height*4/5;	
		try {
			swipe(startX, startY, endX, endY);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 向左滑动页面
	 */
	public void swipePageToLift() {
		int[] wid_h = appScreen();
		int width = wid_h[0];
		int height = wid_h[1];
		
		int startX = width*4/5;
		int endX = width*1/5;
		int startY = height*1/2;
		int endY = height*1/2;
		try {
			swipe(startX, startY, endX, endY);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 向右滑动页面
	 */
	public void swipeToRight() {
		int[] wid_h = appScreen();
		int width = wid_h[0];
		int height = wid_h[1];
		
		int startX = width*1/5;
		int endX = width*4/5;
		int startY = height*1/2;
		int endY = height*1/2;
		try {
			swipe(startX, startY, endX, endY);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 滑动
	 * @param direction:滑动方向（UP DOWN LEFT RIGHT）
	 */
	public void swipePage(String direction) {
		switch(direction) {
		case "UP":
			swipePageToUp();
			break;
		case "DOWN":
			swipePageToDown();
			break;
		case "LEFT":
			swipePageToLift();
			break;
		case "RIGHT":
			swipeToRight();
			break;
		default:
			swipePageToUp();
			break;
		}
				
	}

	
	/*
	 * 滑动页面至某元素出现
	 * @param str : 要查找的字符串
	 * @param direction:滑动方向（UP DOWN LEFT RIGHT）
	 * @param int : 最多滑动次数
	 */
	public void swipePageUtilFoundElement(String str, String direction, int times) {		
		int startX = 0;
		int endX = 0;
		int startY = 0;
		int endY = 0;	
		
		while(times>0) {
			String pagesource = getPageSource();			
			// 判断元素是否存在，存在则不等于-1，String.indexOf(xxx)返回包含该字符串在父类字符串中的起始位置，不包含测全部返回-1
			if(pagesource.indexOf(str)!= -1) {
				return;
			}
			int[] wid_h = appScreen();
			int width = wid_h[0];
			int height = wid_h[1];
			
			switch(direction) {
			case "UP":
				startX =width*1/2;
				endX = width*1/2;
				startY = height*4/5;
				endY = height*1/5;				
				break;
			case "DOWN":
				startX = width*1/2;
				endX = width*1/2;
				startY = height*1/5;
				endY = height*4/5;
				break;
			case "LEFT":
				startX = width*4/5;
				endX = width*1/5;
				startY = height*1/2;
				endY = height*1/2;
				break;
			case "RIGHT":
				startX = width*1/5;
				endX = width*4/5;
				startY = height*1/2;
				endY = height*1/2;
				break;
			default:
				startX = width*1/2;
				endX = width*1/2;
				startY = height*4/5;
				endY = height*1/5;
				break;
			}
			
			try {
				swipe(startX, startY, endX, endY);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			times--;
		}
	}
	
	/*
	 * 滑动页面至某元素出现
	 * @param element : 要查找的控件元素
	 * @param direction:滑动方向（UP DOWN LEFT RIGHT）
	 * @param int : 最多滑动次数
	 */
	public void swipePageUtilFoundElement(By by, String direction, int times) {		
		int startX = 0;
		int endX = 0;
		int startY = 0;
		int endY = 0;	
		
		while(times>0) {
			// 判断元素是否存在			
			if(isElementsExist(by)) {
				System.out.println("found element !");
				return;
			}
		    System.out.println("Not found element !");
		    int[] wid_h = appScreen();
			int width = wid_h[0];
			int height = wid_h[1];
			
			switch(direction) {
			case "UP":
				startX =width*1/2;
				endX = width*1/2;
				startY = height*4/5;
				endY = height*1/5;			
				break;
			case "DOWN":
				startX = width*1/2;
				endX = width*1/2;
				startY = height*1/5;
				endY = height*4/5;
				break;
			case "LEFT":
				startX = width*4/5;
				endX = width*1/5;
				startY = height*1/2;
				endY = height*1/2;
				break;
			case "RIGHT":
				startX = width*1/5;
				endX = width*4/5;
				startY = height*1/2;
				endY = height*1/2;
				break;
			default:
				startX = width*1/2;
				endX = width*1/2;
				startY = height*4/5;
				endY = height*1/5;
				break;
			}
			
			try {
				swipe(startX, startY, endX, endY);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			times--;
		}
	}

	/*
	 * 在某元素上向左滑动
	 * @param By 要操作的控件元素的属性值
	 * @param distance 要滑动的距离，1：滑到头，2：滑到二分之一处，3：滑到三分之一处
	 */
	public void swipeOnElementToLift(By by, int distance) throws InterruptedException {
		Point start = findElement(by).getLocation();
		int startX = start.x;
		int startY = start.y;
		Dimension el = findElement(by).getSize();
		int widthX = el.getWidth();
		int widthY =el.getHeight();
		try {
			swipe(startX, startY+widthY/2, startX+widthX/distance, startY+widthY/2);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 在某元素上向左滑动
	 * @param element 要操作的控件元素
	 * @param distance 要滑动的距离，1：滑到头，2：滑到二分之一处，3：滑到三分之一处
	 */
	public void swipeOnElementToLift(WebElement element, int distance) {
		Point start = element.getLocation();
		int startX = start.x;
		int startY = start.y;
		Dimension el = element.getSize();
		int widthX = el.getWidth();
		int widthY =el.getHeight();
		try {
			swipe(startX, startY+widthY/2, startX+widthX/distance, startY+widthY/2);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 在某元素上向右滑动
	 * @param By 要操作的控件元素的属性值
	 * @param distance 要滑动的距离，1：滑到头，2：滑到二分之一处，3：滑到三分之一处
	 */
	public void swipeOnElementToRight(By by, int distance) throws InterruptedException {
		Point start = findElement(by).getLocation();
		int startX = start.x;
		int startY = start.y;
		Dimension el = findElement(by).getSize();
		int widthX = el.getWidth();
		int widthY =el.getHeight();
		int endX = widthX + startX;
		try {
			swipe(endX, startY+widthY/2, endX-widthX/distance, startY+widthY/2);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 在某元素上向右滑动
	 * @param element 要操作的控件元素
	 * @param distance 要滑动的距离，1：滑到头，2：滑到二分之一处，3：滑到三分之一处
	 */
	public void swipeOnElementToRight(WebElement element, int distance) {
		Point start = element.getLocation();
		int startX = start.x;
		int startY = start.y;
		Dimension el = element.getSize();
		int widthX = el.getWidth();
		int widthY =el.getHeight();
		int endX = widthX + startX;
		try {
			swipe(endX, startY+widthY/2, endX-widthX/distance, startY+widthY/2);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 在某元素上向上滑动
	 * @param By 要操作的控件元素的属性
	 * @param distance 要滑动的距离，1：滑到头，2：滑到二分之一处，3：滑到三分之一处
	 */
	public void swipeOnElementToUp(By by, int distance) throws InterruptedException {
		Point start = findElement(by).getLocation();
		int startX = start.x;
		int startY = start.y;
		Dimension el = findElement(by).getSize();
		int widthX = el.getWidth();
		int widthY =el.getHeight();
		int endY = widthY + startY;
		try {
			swipe(startX+widthX/2, endY, startX+widthX/2, endY-widthY/distance);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 在某元素上向上滑动
	 * @param element 要操作的控件元素
	 * @param distance 要滑动的距离，1：滑到头，2：滑到二分之一处，3：滑到三分之一处
	 */
	public void swipeOnElementToUp(WebElement element, int distance) {
		Point start = element.getLocation();
		int startX = start.x;
		int startY = start.y;
		Dimension el = element.getSize();
		int widthX = el.getWidth();
		int widthY = el.getHeight();
		int endY = widthY + startY;
		try {
			swipe(startX+widthX/2, endY, startX+widthX/2, endY-widthY/distance);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 在某元素上向下滑动
	 * @param By 要操作的控件元素的属性
	 * @param distance 要滑动的距离，1：滑到头，2：滑到二分之一处，3：滑到三分之一处
	 */
	public void swipeOnElementToDown(By by, int distance) throws InterruptedException {
		Point start = findElement(by).getLocation();
		int startX = start.x;
		int startY = start.y;
		Dimension el = findElement(by).getSize();
		int widthX = el.getWidth();
		int widthY = el.getHeight();
		try {
			swipe(startX+widthX/2, startY, startX+widthX/2, startY+widthY/distance);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 在某元素上向下滑动
	 * @param element 要操作的控件元素
	 * @param distance 要滑动的距离，1：滑到头，2：滑到二分之一处，3：滑到三分之一处
	 */
	public void swipeOnElementToDown(WebElement element, int distance) {
		Point start = element.getLocation();
		int startX = start.x;
		int startY = start.y;
		Dimension el = element.getSize();
		int widthX = el.getWidth();
		int widthY = el.getHeight();
		try {
			swipe(startX+widthX/2, startY, startX+widthX/2, startY+widthY/distance);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void swipe(int startX, int startY, int endX, int endY) {
		(new TouchAction(this.driver)).longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, endY)).release().perform();
	}
		
}
