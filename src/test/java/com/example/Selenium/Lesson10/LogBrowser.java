package com.example.Selenium.Lesson10;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class LogBrowser {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void Product()  {

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("Catalog | My Store"));

        List<WebElement> rows = driver.findElements(By.cssSelector("a[href*=product_id]"+":not([title='Edit'])"));

        for (int i = 0; i < rows.size(); i++) {
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
            rows = driver.findElements(By.cssSelector("a[href*=product_id]"+":not([title='Edit'])"));
            System.out.println("<<<<<<<"+rows.get(i).getAttribute("href"));
            rows.get(i).click();

            driver.manage().logs().get("browser").getAll().forEach(System.out::println);
            Assert.assertEquals("log browser",0, driver.manage().logs().get("browser").getAll().size());

        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
