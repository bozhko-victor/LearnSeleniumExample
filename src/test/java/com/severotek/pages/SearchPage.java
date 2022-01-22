package com.severotek.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SearchPage {
    private WebElement searchField;
    private WebElement searchButton;
    private List<WebElement> searchLinks;
    private int timeout;

    private By XPATH_SEARCH_FIELD = By.xpath("//input[@name='text']");
//    private By XPATH_SEARCH_BUTTON = By.xpath("//button[contains(@class,'websearch')]");
    private By XPATH_SEARCH_BUTTON = By.xpath("//button[@type='submit']");
    private By XPATH_SEARCH_RESULTS_LINKS = By.xpath("//ul[@role='main']//div[contains(@class,'organic__path')]/a");

    public SearchPage(int timeout) throws Exception {
        this.timeout = timeout;
    }

    public SearchPage clickSearchButton(WebDriver driver) throws Exception {
        this.searchButton = driver.findElement(XPATH_SEARCH_BUTTON);
        waitForElementVisible(searchButton, driver, timeout).click();
        return this;
    }

    public SearchPage fillOutSearchField(String text, WebDriver driver) throws Exception {
        this.searchField = driver.findElement(XPATH_SEARCH_FIELD);
        waitForElementVisible(searchField, driver, timeout).sendKeys(text);
        return this;
    }

    public SearchPage clearSearchField(WebDriver driver) throws Exception {
        this.searchField = driver.findElement(XPATH_SEARCH_FIELD);
        waitForElementVisible(searchField, driver, timeout).clear();
        return this;
    }

    public ArrayList<String> getSearchResultsLinks(WebDriver driver) throws Exception {
        ArrayList<String> textData = new ArrayList<>();
        this.searchLinks = driver.findElements(XPATH_SEARCH_RESULTS_LINKS);
        waitForElementVisible(searchLinks.get(0), driver, timeout);
        for(WebElement element : searchLinks) textData.add(element.getAttribute("href"));
        return textData;
    }

    private WebElement waitForElementVisible(WebElement element, WebDriver driver, int timeout) {
        WebElement explicitWait = (new WebDriverWait(driver, timeout)).until(
                ExpectedConditions.visibilityOf(element));
        return explicitWait;
    }
}
