package com.example.Selenium.Lesson2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FirstTestOpenBrowser {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() {

      // driver.navigate().to("https://dict.leo.org/russisch-deutsch/");
      // driver.findElement(By.name("search")).sendKeys("webdriver");

        driver.navigate().to("https://www.bing.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        wait.until(titleIs("webdriver - Bing"));
    }
    @After

    public void stop() {
        driver.quit();
        driver = null;

    }
}
