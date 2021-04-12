package com.example.Selenium.lesson4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class LitecartStickerDucks {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        driver = new FirefoxDriver();
//        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void StickerPresence() {
        driver.get("http://localhost/litecart");
        wait.until(titleIs("Online Store | My Store"));

        List<WebElement> ducksList = driver.findElements(By.className("product"));
//        System.out.println(" Amount ducks : " + ducksList.size());

        for (WebElement item : ducksList) {
//            System.out.println(item.getAttribute("class"));
            List<WebElement> stickers = item.findElements(By.cssSelector("[class^=sticker]"));
            Assert.assertEquals("Invalid amount of stickers: ", 1, stickers.size());
        }


    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
