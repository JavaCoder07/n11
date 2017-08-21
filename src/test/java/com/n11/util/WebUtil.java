package com.n11.util;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class WebUtil {
	private static WebUtil webDriverFactory;

	private Configuration configuration;
	private EventFiringWebDriver webDriver;
	private Browser browser;
	private ArrayList<File> exceptionScreenShots;
	@SuppressWarnings("unused")
	private JavascriptExecutor javascriptExecutor;

	public static WebUtil getInitial() {
		if (webDriverFactory == null) {
			webDriverFactory = new WebUtil();
			try {
				webDriverFactory.createBrowser();
			} catch (Exception e) {
				System.err.println("INFO: Error while creating browser occured.");
			}
		}

		return webDriverFactory;
	}

	public static Browser getBrowser() {
		return getInitial().browser;
	}

	public WebUtil() {
		configuration = new Configuration();
		exceptionScreenShots = new ArrayList<File>();
	}

	public WebDriver createBrowser() throws Exception {
		switch (webDriverFactory.getConfiguration().getBrowserType()) {

		case INTERNETEXPLORER:
			webDriver = new EventFiringWebDriver(new InternetExplorerDriver());
			break;

		case CHROME:
			System.setProperty("webdriver.chrome.driver", "src/test/java/chromedriver.exe");
			ChromeOptions o = new ChromeOptions();
			o.addArguments("disable-extensions");
			o.addArguments("--start-maximized");
			webDriver = new EventFiringWebDriver(new ChromeDriver(o));
			break;

		default:
		
		case FIREFOX:
			FirefoxBinary binary = new FirefoxBinary();

			String currentPath = this.getClass().getClassLoader().getResource("").getPath();
			String firefoxProfilePath = currentPath.substring(0, currentPath.lastIndexOf("target"))
					+ "src/test/resources/firefoxprofile";

			File firefoxProfileFolder = new File(firefoxProfilePath);
			FirefoxProfile profile = new FirefoxProfile(firefoxProfileFolder);
			profile.setAcceptUntrustedCertificates(true);

			webDriver = new EventFiringWebDriver(new FirefoxDriver(binary, profile));
			webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			break;
		}
		browser = new Browser();

		return browser;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public ArrayList<File> getExceptionScreenShots() {
		return exceptionScreenShots;
	}

	public static void checksFor404(Browser browser) {
		try {
			browser.findElement(By.id("error404"));
			fail();
		} catch (Exception e) {

		}
	}

	public static void checksFor500(Browser browser) {
		try {
			browser.findElement(By.id("error500"));
			fail();
		} catch (Exception e) {

		}
	}

	public static void checksFor404And500ByUrl(Browser browser) {
		try {
			browser.waitForAjax();
			System.err.println("INFO: Will check for 404 and 500");

			if (browser.getCurrentUrl().contains("/404")) {
				System.err.println("INFO: ~ 404 ERROR! ~");
			}
			if (browser.getCurrentUrl().contains("/500")) {
				System.err.println("INFO: ~ 500 ERROR! ~");
			}
			Assert.assertFalse(browser.getCurrentUrl().contains("/404"));
			Assert.assertFalse(browser.getCurrentUrl().contains("/500"));
		} catch (Exception e) {
			System.err.println("INFO: 404 or 500 error occured.");
		}
	}

	public static void checksFor404And500ByUrl(Browser browser, String testPage) {
		try {
			browser.waitForAjax();
			System.err.println("INFO: Will check for 404 and 500");

			if (browser.getCurrentUrl().contains("/404")) {
				System.err.println("INFO: ~ 404 ERROR! ~");
			}
			if (browser.getCurrentUrl().contains("/500")) {
				System.err.println("INFO: ~ 500 ERROR! ~");
			}
			Assert.assertFalse(browser.getCurrentUrl().contains("/404"));
			Assert.assertFalse(browser.getCurrentUrl().contains("/500"));
		} catch (Exception e) {
			System.err.println("INFO: 404 or 500 error occured at page:" + testPage + ".");
		}
	}
	
	public static boolean isDateRange(Date date, Date date1, Date date2) {
		if (date.after(date1) || date.equals(date1)) {
			if (date.before(date2) || date.equals(date2)) {
				return true;
			}
		}
		return false;
	}

	public JavascriptExecutor getJavascriptExecutor() {
		return (JavascriptExecutor)browser.getWebDriver();
	}
}
