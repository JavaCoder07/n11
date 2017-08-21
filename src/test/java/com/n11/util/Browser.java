package com.n11.util;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser implements WebDriver {
public EventFiringWebDriver webDriver;
	
    public WebDriver getWebDriver() {
        return WebUtil.getInitial().getWebDriver();
    }
    
    public JavascriptExecutor getJsExecutor() {
    	return WebUtil.getInitial().getJavascriptExecutor();
    }

    public void get(String s) {
        getWebDriver().get(s);
    }
    
    public String getCurrentUrl() {
        return getWebDriver().getCurrentUrl();
    }

    public String getTitle() {
        return getWebDriver().getTitle();
    }

    public List<WebElement> findElements(By by) {
        return getWebDriver().findElements(by);
    }

    public WebElement findElement(By by) {
        return getWebDriver().findElement(by);
    }

    public String getPageSource() {
        return getWebDriver().getPageSource();
    }

    public void close() {
        getWebDriver().close();
    }

    public void quit() {
        getWebDriver().quit();
    }

    public Set<String> getWindowHandles() {
        return getWebDriver().getWindowHandles();
    }

    public String getWindowHandle() {
        return getWebDriver().getWindowHandle();
    }

    public TargetLocator switchTo() {
        return getWebDriver().switchTo();
    }

    public Navigation navigate() {
        return getWebDriver().navigate();
    }

    public Options manage() {
        return getWebDriver().manage();
    }

    public void waitForVisibility(int seconds, By elementLocator) {
        (new WebDriverWait(this, seconds)).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }
    
    public void waitForClickable(int seconds, By elementLocator) {
        (new WebDriverWait(this, seconds)).until(ExpectedConditions.elementToBeClickable(elementLocator));
    }

    public void waitForPresenceOf(int seconds, By elementLocator) {
        (new WebDriverWait(this, seconds)).until(ExpectedConditions.presenceOfElementLocated(elementLocator));
    }

    public void sendKeysForId(String id, String text) {
        getWebDriver().findElement(By.id(id)).sendKeys(text);
    }
    
    public void sendKeysForXpath(String xpath, String text) {
        getWebDriver().findElement(By.xpath(xpath)).sendKeys(text);
        waitForAjax();
    }
    
    public void sendKeysForName(String name, String text) {
        getWebDriver().findElement(By.name(name)).sendKeys(text);
        waitForAjax();
    }
    
    public void sendKeysForClassName(String className, String text) {
        getWebDriver().findElement(By.className(className)).sendKeys(text);
        waitForAjax();
    }

    public void selectOptionText(WebElement element, String data) {
       Select select = new Select(element);
       select.selectByVisibleText(data);
       waitForAjax();
    }
    
    public void selectOptionValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
        waitForAjax();
     }

    public void selectFromComboBoxByVisibleText(WebElement label, String visibleText){
        label.click();
        waitForAjax();
        findElement(By.xpath("//ul/li[text()='" + visibleText + "']")).click();
        waitForAjax();
    }

    public String selectFromComboBoxByIndex(WebElement label, WebElement input, int indexNo){
        waitForAjax();
        label.click();
        waitForAjax();
        Select select = new Select(input);
        select.getOptions().get(indexNo).click();
        waitForAjax();

        return select.getOptions().get(indexNo).getText();
    }
    
    public void checkRadioButtonById(String id) {
        getWebDriver().findElement(By.id(id)).click();
    }
    
    public void checkRadioButtonByXpath(String id) {
        getWebDriver().findElement(By.id(id)).click();
    }
    
    public void checkRadioButtonByName(String name) {
        getWebDriver().findElement(By.name(name)).click();
        waitForAjax();
    }

    public void checkRadioButtonByClass(String className ) {
        getWebDriver().findElement(By.className(className)).click();
    }
    
    public void clickById(String id) {
        getWebDriver().findElement(By.id(id)).click();
    }
    
    public void clickByXpath(String xpath){
        getWebDriver().findElement(By.xpath(xpath)).click();
        waitForAjax();
    }
    
    public void clickByCssSelector(String css){
        getWebDriver().findElement(By.cssSelector(css)).click();
        waitForAjax();
    }
    
    public void clickByName(String name) {
        getWebDriver().findElement(By.name(name)).click();
    }
    
    public void clickByClassName(String className) {
        getWebDriver().findElement(By.className(className)).click();
    }
    
    public void clickByIdWithScript(String id) {
    	getJsExecutor().executeScript("arguments[0].click()", findElement(By.id(id)));
        waitForAjax();
    }
    
    public void clickByXpathWithScript(String xpath){
    	getJsExecutor().executeScript("arguments[0].click()", findElement(By.xpath(xpath)));
        waitForAjax();
    }
    
    public void clickByNameWithScript(String name) {
    	getJsExecutor().executeScript("arguments[0].click()", findElement(By.name(name)));
        waitForAjax();
    }
    
    public void clickByClassNameWithScript(String className) {
    	getJsExecutor().executeScript("arguments[0].click()", findElement(By.className(className)));
        waitForAjax();
    }

    public void waitTillInvisibilityOf(int seconds, By elementLocator) {
        WebDriverWait wait = new WebDriverWait(this, seconds);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
    }

    public void checkRadioButtonByXPath(String attributeExp) {
        getWebDriver().findElement(By.xpath(attributeExp)).click();
    }

    public String getTextById(String id){
        return getWebDriver().findElement(By.id(id)).getText();
    }

    public String getTextByXpath(String xPath){
        return getWebDriver().findElement(By.xpath(xPath)).getText();
    }
    
    public String getTextByName(String name){
        return getWebDriver().findElement(By.name(name)).getText();
    }
    
    public String getTextByClassName(String className){
        return getWebDriver().findElement(By.className(className)).getText();
    }
    
    public String getAttributeById(String id, String attribute){
        return getWebDriver().findElement(By.id(id)).getAttribute(attribute);
    }

    public String getAttributeByXpath(String xPath, String attribute){
        return getWebDriver().findElement(By.xpath(xPath)).getAttribute(attribute);
    }
    
    public String getAttributeByName(String name, String attribute){
        return getWebDriver().findElement(By.name(name)).getAttribute(attribute);
    }
    
    public String getAttributeByClassName(String className, String attribute){
        return getWebDriver().findElement(By.className(className)).getAttribute(attribute);
    }

    public void scrollBy(int x, int y){
        ((JavascriptExecutor)getWebDriver()).executeScript("window.scrollBy("+String.valueOf(x)+","+String.valueOf(y)+");");
        waitForAjax();
    }
    
    public void clickIconInDataGrid(WebElement dataGrid, String buttonXpath) {
    	waitForAjax();
    	if (dataGrid.findElements(By.tagName("tr")).size() > 1) {	
    		dataGrid.findElements(By.tagName("tr")).get(0).findElement(By.xpath(buttonXpath)).click();
    		waitForData();
		}
    }
    
    public void waitForAjax() {
        AjaxWaiter.waitForAjaxLoad();
    }
    
    public void waitForData() {
    	AjaxWaiter.waitForDataLoad();
    }

    public void waitForAjaxLong(){
        AjaxWaiter.waitForAjaxLoad(2500, 2000);
    }

    public void waitForAjax(int time){
        AjaxWaiter.waitForAjaxLoad(time,time);
    }
    
    public void setTextById(String id, String text){
        getWebDriver().findElement(By.id(id)).sendKeys(text);
        waitForAjax();
    }
    
    public void getCurrentWindowHandle(){
		for(String winHandle : getWindowHandles()){
			switchTo().window(winHandle);
		}
    }
    
    public void getBeforeWindowHandle(String winHandle){
		switchTo().window(winHandle);
    }
}

