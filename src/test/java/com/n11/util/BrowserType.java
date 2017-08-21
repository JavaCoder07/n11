package com.n11.util;



public enum BrowserType {
	INTERNETEXPLORER("iexplorer"),
    CHROME("chrome"),
    FIREFOX("firefox"),
    SAFARI("safari"),
    IOSSAFARI("iossafari"),
    ANDROIDBROWSER("androidbrowser"),
    ANDROIDCHROME("androidchrome"),
    SELENDROID("selendroid");
	
    private String name;

    BrowserType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static BrowserType findByName(String name) {
        for (BrowserType browser : values()) {
            if (browser.getName().equals(name)) {
                return browser;
            }
        }
        return null;
    }
}
