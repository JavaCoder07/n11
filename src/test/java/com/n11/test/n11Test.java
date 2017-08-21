package com.n11.test;

import org.junit.Assert;
import org.junit.Test;

import com.n11.page.HomePage;
import com.n11.page.LoginPage;

public class n11Test extends n11BaseTest {

	@Test
	public void facebookLogin() {
		LoginPage loginPage = LoginPage.load(browser);
		loginPage.clickFacebookLoginButton();
		loginPage.loginWithFacebook(configuration.getUserName(), configuration.getPassword());
		Assert.assertTrue("Login olunamadı!", loginPage.isLoginSuccessed());

		HomePage homePage = HomePage.load(browser);
		homePage.clickBookMuzikFilmGameLink();
		homePage.clickBookLink();
		Assert.assertTrue("Kitaplar Sayfası Açılmadı!", homePage.isBookPageOpened());
		homePage.clickAuthorLink();
		Assert.assertTrue("Yazarlar Sayfası Açılmadı!", homePage.isAuthorPageOpened());
		Assert.assertTrue(homePage.isValidAuthorName());
		Assert.assertTrue(homePage.isAuthorLastNameIdentityInOtherPage());
	}

	@Test
	public void facebookLoginFail() {
		LoginPage loginPage = LoginPage.load(browser);
		loginPage.clickFacebookLoginButton();
		loginPage.loginWithFacebook(configuration.getFailUserName(), configuration.getFailPassword());
		Assert.assertFalse("Login olunamadı!", loginPage.isLoginFailed());
	}
}
