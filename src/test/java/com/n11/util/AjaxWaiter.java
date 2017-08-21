package com.n11.util;

import org.openqa.selenium.JavascriptExecutor;


public class AjaxWaiter {
	public static void waitForAjaxLoad() {
		long startTime = System.currentTimeMillis();
		long estimatedTime;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		JavascriptExecutor js = (JavascriptExecutor) WebUtil.getBrowser().getWebDriver();
		boolean stillRunnigPageLoad = (Boolean)js.executeScript("return document.readyState != 'complete'");
		try {
			while (stillRunnigPageLoad) {
				stillRunnigPageLoad = (Boolean)js.executeScript("return document.readyState != 'complete'");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
				estimatedTime = System.currentTimeMillis() - startTime;

				if (estimatedTime >= 20000) {
					stillRunnigPageLoad = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void waitForAjaxLoad(int firstMilliSeconds, int secondMilliSeconds) {
		try {
			Thread.sleep(firstMilliSeconds);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		JavascriptExecutor executor = (JavascriptExecutor) WebUtil.getBrowser().getWebDriver();

		boolean stillRunningAjax = (Boolean)executor.executeScript("return document.readyState != 'complete'");

		while (stillRunningAjax) {

			stillRunningAjax = (Boolean)executor.executeScript("return document.readyState != 'complete'");
			try {
				Thread.sleep(secondMilliSeconds);
			} catch (InterruptedException e) {
			}
		}
	}
	
	public static void waitForDataLoad() {
		long startTime = System.currentTimeMillis();
		long estimatedTime;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		JavascriptExecutor js = (JavascriptExecutor) WebUtil.getBrowser().getWebDriver();
		boolean stillRunnigPageLoad = (Boolean)js.executeScript("return $('.page-content').css('position') != 'static'");
		try {
			while (stillRunnigPageLoad) {
				stillRunnigPageLoad = (Boolean)js.executeScript("return $('.page-content').css('position') != 'static'");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
				estimatedTime = System.currentTimeMillis() - startTime;

				if (estimatedTime >= 20000) {
					stillRunnigPageLoad = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}