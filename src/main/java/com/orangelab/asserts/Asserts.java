package com.orangelab.asserts;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;


public class Asserts {
	
	/*
	 * 断言：实际结果与预期一致
	 */
	public static void verifyEquals(Object actual,Object expected) {		
		Assert.assertEquals(actual, expected);	
	}
	

	/*
	 * 断言：实际结果与其中一个预期一致
	 */
	public static void verifyEqualsOr(Object actual,Object expected1,Object expected2) {
		boolean flag1 = true;
		boolean flag2 = true;
		try {
			Assert.assertEquals(actual, expected1);
		}catch(Error e) {
			flag1 = false;
		}
		
		try {
			Assert.assertEquals(actual, expected2);
		}catch(Error e) {
			flag2 = false;
		}
		
		Assert.assertTrue(flag1 || flag2);
	}
	
	/*
	 * 断言：实际结果与所有预期都一致
	 */
	public static void verifyAllEquals(Object actual,Object expected1,Object expected2) {
		boolean flag1 = true;
		boolean flag2 = true;
		try {
			Assert.assertEquals(actual, expected1);
		}catch(Error e) {
			flag1 = false;
		}
		
		try {
			Assert.assertEquals(actual, expected2);
		}catch(Error e) {
			flag2 = false;
		}
		
		Assert.assertTrue(flag1 && flag2);
	}
	
	/*
	 * 断言：当前activity与预期一致
	 */
	public static void activityEquals(String activityName,String currActivity) {
		Assert.assertEquals(activityName, currActivity);	
	}
	
	/*
	 * 断言：
	 */
	public static void verifyTrue(boolean condition) {		
		Assert.assertTrue(condition);			
	}
	
	/*
	 * 断言：
	 */
	public static void verifyTrueOr(boolean condition1,boolean condition2) {		
		Assert.assertTrue(condition1 || condition2);			
	}
	
	/*
	 * 断言：
	 */
	public static void verifyTrueAnd(boolean condition1,boolean condition2) {		
		Assert.assertTrue(condition1 && condition2);			
	}
	/*
	 * 断言：是否可以找到元素对象
	 */
	public static void isFoundElement(Boolean flag) {		
		Assert.assertTrue(flag);
	}
		
}
