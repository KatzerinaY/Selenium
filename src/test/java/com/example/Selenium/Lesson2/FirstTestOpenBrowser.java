package com.example.Selenium.Lesson2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FirstTestOpenBrowser {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        //       driver = new ChromeDriver();
//        driver = new FirefoxDriver();
        System.setProperty("webdriver.edge.driver", "C:\\Java\\Tools\\msedgedriver.exe");
        driver = new EdgeDriver();


        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() {

        driver.get("https://www.bing.com");
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
