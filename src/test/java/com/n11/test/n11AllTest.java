package com.n11.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	
})

public class n11AllTest extends n11BaseTest {
	
	@BeforeClass
    public static void beforeClass() {
        allTests = true;
        //Login
    }

    @AfterClass
    public static void afterClass() {
        browser.close();
        browser.quit();
        allTests = false;
    }

}
