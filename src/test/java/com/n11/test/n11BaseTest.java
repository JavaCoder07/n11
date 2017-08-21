package com.n11.test;

import java.io.File;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.n11.util.Browser;
import com.n11.util.Configuration;
import com.n11.util.WebUtil;

public class n11BaseTest {
	protected static Browser browser = WebUtil.getBrowser();;
	protected static Configuration configuration = WebUtil.getInitial().getConfiguration();
	protected static ArrayList<File> exceptionScreenShots = WebUtil.getInitial().getExceptionScreenShots();
	protected static boolean allTests = false;
	protected static int randomNumber = 0;

	@Rule
	public TestRule takeScreenShotOnFailure = new TestWatcher() {
		@Override
		public void succeeded(Description description) {
			exceptionScreenShots.clear();
		}

		@Override
		public void failed(Throwable e, Description description) {
			try {
				exceptionScreenShots.add(((TakesScreenshot) browser.getWebDriver()).getScreenshotAs(OutputType.FILE));
				DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy-HH.mm.ss");
				Calendar cal = Calendar.getInstance();
				File dir;
				String spr = System.getProperty("file.separator");

				if (configuration.getIsLoggingEnabled()) {
					dir = new File(System.getProperty("user.home") + spr + "Selenium TestResults");
					if (dir.exists()) {
					} else {
						dir.mkdir();
					}
				} else {
					dir = new File(
							spr + "data" + spr + "jenkins" + spr + "workspace" + spr + "DMallReleaseDailySelenium" + spr
									+ "selenium" + spr + "target" + spr + "surefire-reports");
					if (dir.exists()) {
						System.out.println("A folder with name 'surefire-reports' is already exist in the path ");
					} else {
						dir.mkdir();
					}
				}

				String filePathRoot = dir.toString();
				String fullFilePath = filePathRoot + System.getProperty("file.separator") + description.getClassName();
				dir = new File(fullFilePath);
				dir.mkdirs();
				for (int i = 0; i < exceptionScreenShots.size(); i++) {
					FileUtils.copyFile(exceptionScreenShots.get(i),
							new File(fullFilePath + System.getProperty("file.separator") + description.getMethodName()
									+ "-" + dateFormat.format(cal.getTime()) + "_" + i + ".jpg"));
				}

				exceptionScreenShots.clear();

				PrintStream writer = new PrintStream(fullFilePath + System.getProperty("file.separator")
						+ description.getMethodName() + "-" + dateFormat.format(cal.getTime()) + ".log", "UTF-8");
				e.printStackTrace(writer);

			} catch (Exception ex) {
				System.out.println(ex.toString());
				System.out.println(ex.getMessage());
			}
		}
	};

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@BeforeClass
	public static void beforeClass() {
		if (!allTests) {
			
		}
	}

	@AfterClass
	public static void afterClass() {
		if (!allTests) {

			browser.close();
			browser.quit();
		}
		browser = new Browser();
		configuration = new Configuration();
		exceptionScreenShots = new ArrayList<File>();
	}
}
