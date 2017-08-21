package com.n11.page;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.n11.util.Browser;

public class LoginPage extends N11BasePage {
	
	private static final String URL = "/";
	private static final String FACEBOOK_LOGIN_BUTTON = "//div[@class='button inicon facebook medium facebookBtn  btnLogin']";
	private static final String EMAIL_TEXTBOX_ID = "email";
    private static final String PASSWORD_TEXTBOX_ID = "pass";
    private static final String LOGIN_BUTTON_NAME = "login";
    private static final String ASCONTINUE_BUTTON_XPATH = "//*[@id='u_0_4']/div[2]/div[1]/div[1]/button";

    protected LoginPage(Browser browser) {
		super(browser);
	}
	public static LoginPage load(Browser browser) {
		browser.get(configuration.getBaseUrl() + URL);
		return new LoginPage(browser);
	}

	
	public void clickFacebookLoginButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FACEBOOK_LOGIN_BUTTON)));
		browser.clickByXpathWithScript(FACEBOOK_LOGIN_BUTTON);
	}
	
	public void loginWithFacebook(String email, String pass){
		String currentWinHandle = browser.getWindowHandle();
		browser.getCurrentWindowHandle();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(EMAIL_TEXTBOX_ID)));
		browser.setTextById(EMAIL_TEXTBOX_ID, email);
		browser.setTextById(PASSWORD_TEXTBOX_ID, pass);
		browser.clickByName(LOGIN_BUTTON_NAME);
		browser.getBeforeWindowHandle(currentWinHandle);
	}
	
	public void clickAsContinue(){
		browser.clickByXpath(ASCONTINUE_BUTTON_XPATH);
	}
	
	/*public LoginPage clickAcceptAndConfirmContract(){
		browser.waitForAjax(4000);
		//browser.switchWindow();
		browser.clickById(ACCEPT_CONTRACT_CHECKBOX_ID);
		browser.clickById(CONFIRM_CONTRACT_BUTTON_ID);
		return this;	
	}*/
	
	public boolean isLoginSuccessed(){
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("username")));
			if (!"Kerim Aydın".equals(browser.findElement(By.className("username")).getText())) {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}
	
	public boolean isLoginFailed(){
		try {
			if (isElementPresent(By.className("username"))) {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
