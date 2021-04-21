package com.example.Selenium.Lesson8;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;


import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CountriesNewWindow {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

       driver = new FirefoxDriver();
       // driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }


    @Test
    public void CheckingCountries() throws InterruptedException {

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        WebElement country = wait.until(presenceOfElementLocated(By.className("row")));
        country.findElement(By.cssSelector("a[href]")).click();

        WebElement content = wait.until(presenceOfElementLocated(By.cssSelector("form")));
        List<WebElement> targets = content.findElements(By.cssSelector("[target=_blank]"));

        for (WebElement target: targets) {

            WebDriverWait waitLong = new WebDriverWait(driver, 30);
            String mainWindow = driver.getWindowHandle();
            target.click();

            waitLong.until(numberOfWindowsToBe(2));
            String newWindow = WindowOtherThan(mainWindow);
            Assert.assertNotEquals("no new Window",newWindow,"");

            driver.switchTo().window(newWindow);

            //waitLong.until(presenceOfElementLocated(By.cssSelector("body")));
            waitLong.until((ExpectedCondition<Boolean>) d -> {
                return !((JavascriptExecutor) d).executeScript("return document.readyState").equals("loading"); //complete||interactive
            });

            driver.close();
            driver.switchTo().window(mainWindow);

        }

    }

    private String WindowOtherThan(String mainWindow){
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(mainWindow)) {
                return windowHandle;
            }
        }
        return "";
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}
