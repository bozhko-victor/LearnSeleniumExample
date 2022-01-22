package com.severotek.tests;

import com.severotek.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.AssertJUnit.assertEquals;

public class ExampleTest {

    public WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void aFastTest() throws Exception {
        driver.get("https://yandex.ru");
        SearchPage searchPage = new SearchPage(5000);

        searchPage.clearSearchField(driver)
                .fillOutSearchField("selenium", driver)
                .clickSearchButton(driver);
        ArrayList<String> searchResults = searchPage.getSearchResultsLinks(driver);

        assertEquals("FAIL/ Wrong first search result address",
                "https://selenium.dev/", searchResults.get(0));
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}