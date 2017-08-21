package com.n11.page;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.n11.util.Browser;
import com.n11.util.Configuration;

public class N11BasePage {
	public Browser browser;
	public static Configuration configuration = new Configuration();
	public boolean acceptNextAlert = true;
	public WebDriverWait wait;
	boolean result = true;
	/*
	 * Success message
	 * public String SUCCESS_TOASTER_XPATH = "";
	 */
	
	protected N11BasePage(Browser browser) {
		this.browser = browser;
		wait = new WebDriverWait(browser.getWebDriver(), 60);
	}
	
	protected boolean isElementPresent(By by) {
		try {
			browser.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	protected boolean isAlertPresent() {
		try {
			browser.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
	
	protected String closeAlertAndGetItsText() {
		try {
			Alert alert = browser.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
	
	protected boolean isValidForm(String formXpath) {
		return (Boolean)browser.getJsExecutor().executeScript(String.format("return $('#%s')['valid']();", browser.findElement(By.xpath(formXpath)).getAttribute("id")));
	}
	
	protected List<WebElement> getTableRows(String dataGridXpath) {
		return browser.findElement(By.xpath(dataGridXpath)).findElements(By.tagName("tr"));
	}
	
	protected void refreshPage(){
		browser.navigate().refresh();
	}
	
	protected boolean isToasterError() {
		return (Boolean)browser.getJsExecutor().executeScript("return $('.toast-error').length > 0");
	}
}
