package com.example.Selenium.Lesson2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

        driver.navigate().to("https://dict.leo.org/russisch-deutsch/");
        driver.findElement(By.name("search")).sendKeys("webdriver");
 //       driver.findElement(By.id("searchFieldSubmit")).click();
 //       wait.until(titleIs("webdriver - search in Google"));

    }
    @After

    public void stop() {
        driver.quit();
        driver = null;

    }
}
