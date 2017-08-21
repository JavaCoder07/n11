package com.n11.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.n11.util.Browser;

public class HomePage extends N11BasePage {
	private static final String BOOK_MUZIK_FILM_GAME_LINK_XPATH = "//a[@href ='http://www.n11.com/kitap-muzik-film-oyun']";
	private static final String BOOK_LINK_XPATH = "//div[@class='subCatMenu l7']//a[@href ='http://www.n11.com/kitap']"; 
	private static final String AUTHOR_LINK_XPATH = "//a[@href = 'http://www.n11.com/yazarlar']";
	private static final String AUTHOR_LIST_XPATH = "//div[@id='authorsList']/div";
	private static final String ALPHABET_AUTHOR_LINK_XPATH = "//div[@class='alphabetPaging']/span[@data-value!='0-9']";
	
	protected HomePage(Browser browser) {
		super(browser);
	}
	
	public static HomePage load(Browser browser) {
		return new HomePage(browser);
	}
	
	public void clickBookMuzikFilmGameLink() {
		browser.clickByXpath(BOOK_MUZIK_FILM_GAME_LINK_XPATH);
	}
	
	public void clickBookLink() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BOOK_LINK_XPATH)));
		browser.clickByXpath(BOOK_LINK_XPATH);
	}
	
	public void clickAuthorLink() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AUTHOR_LINK_XPATH)));
		browser.findElements(By.xpath(AUTHOR_LINK_XPATH)).get(0).click();
	}
	
	public boolean isBookPageOpened(){
		try {
			wait.until(ExpectedConditions.titleContains("Yeni Çıkan, En Çok Satan & Okunan Kitaplar - n11.com"));
			if (!"Yeni Çıkan, En Çok Satan & Okunan Kitaplar - n11.com".equals(browser.getTitle())) {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
		
	}
	
	public boolean isAuthorPageOpened(){
		try {
			wait.until(ExpectedConditions.titleContains("Yazarlar - Türk ve Yabancı Yazarlar - n11.com"));
			if (!"Yazarlar - Türk ve Yabancı Yazarlar - n11.com".equals(browser.getTitle())) {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
		
	}
	
	public boolean isValidAuthorName() {
		List<WebElement> alphabetElements = browser.findElements(By.xpath(ALPHABET_AUTHOR_LINK_XPATH));
		List<String> alphabetCharacter = new ArrayList<String>();
		for (WebElement alphabetElement : alphabetElements) {
			alphabetCharacter.add(alphabetElement.getText());
		}
		
		for (String alphabet : alphabetCharacter) {
			browser.findElement(By.xpath("//div[@class='alphabetPaging']/span[@data-value ='" + alphabet + "']")).click();
			browser.waitForAjax();
			List<WebElement> elements = authorList();
			if (elements.size() > 80) {
				result = false;
			}
			
			for (WebElement authorName : elements) {
				if (!authorName.getText().substring(0,1).toUpperCase().equals(alphabet)) {
					result = false;
				}
			}
			
			if (!result) {
				break;
			}
		}
		
		return result;
	}
	
	public boolean isAuthorLastNameIdentityInOtherPage() {
		List<WebElement> elements = authorList();
		String authorName = elements.get(79).getText();
		
		browser.clickByXpath("//a[@href='//www.n11.com/yazarlar/Z?pg=2']");
		browser.waitForAjax();
		
		List<WebElement> elements2 = authorList();
		
		for (WebElement webElement : elements2) {
			if (webElement.getText().equals(authorName)) {
				result = false;
			}
		}
		
		return result;
	}

	private List<WebElement> authorList() {
		List<WebElement> elements = new ArrayList<WebElement>();
		for (WebElement element : browser.findElements(By.xpath(AUTHOR_LIST_XPATH))) {
			for (WebElement webElement : element.findElements(By.xpath("ul/li"))) {
				elements.add(webElement);
			}
		}
		return elements;
	}
}
