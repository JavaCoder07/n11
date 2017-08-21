package com.n11.util;


import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	private String baseUrl = "";
    private Properties configProps = new Properties();
    private boolean isLoggingEnabled;
    private BrowserType browserType; 
    //General
    private String userName = "";
	private String password = "";
	private String filePath = "";
	private String searchText = "";
	private String failUserName = "";
	private String failPassword = "";
    
    public Configuration() {
        try {
            this.isLoggingEnabled = true;//Ekran görüntüsü alıp almayacağı
            String configFile =  "test_config.properties"; //Properties dosyasının adı
            this.browserType= (BrowserType.findByName("chrome")) ;// browserın adı

            InputStream in = ClassLoader.getSystemResourceAsStream(configFile);
            configProps.load(in);
           
            this.baseUrl = configProps.getProperty("baseUrl");
            this.userName = configProps.getProperty("username");
            this.password = configProps.getProperty("password");
            this.filePath = configProps.getProperty("filePath");
            this.searchText = configProps.getProperty("searchText");
            this.failUserName = configProps.getProperty("failusername");
            this.failPassword = configProps.getProperty("failpassword");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	public String getBaseUrl() {
		return baseUrl;
	}
    
    public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
    
    public boolean getIsLoggingEnabled(){
        return isLoggingEnabled;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

	public String getUserName() {
		return userName;
	}
	
	public String getFailUserName() {
		return failUserName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getFailPassword() {
		return failPassword;
	}
	
	
	public String getSearchText() {
		return searchText;
	}
}
