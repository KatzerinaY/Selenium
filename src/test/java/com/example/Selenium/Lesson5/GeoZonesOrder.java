package com.example.Selenium.Lesson5;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
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

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class GeoZonesOrder {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

//         driver = new FirefoxDriver();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void CheckingCountries() {

        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("Geo Zones | My Store"));

        List<WebElement> rows = driver.findElements(By.className("row"));
        List<String> hrefList = new ArrayList<>();
        for (WebElement item : rows) {
            WebElement hrefElement = item.findElement(By.cssSelector("[href]"));
            hrefList.add(hrefElement.getAttribute("href"));
        }


        for (String href : hrefList) {

            driver.get(href);
            wait.until(titleIs("Edit Geo Zone | My Store"));

            List<WebElement> rowsZone = driver.findElements(By.cssSelector("[name*=zone_code]"));
            List<String> zonesList = new ArrayList<>();

            for (WebElement itemZ : rowsZone) {

                List<WebElement> SelectedItems = itemZ.findElements(By.cssSelector("[selected]"));

                for (WebElement SelectedItem : SelectedItems) {
                    zonesList.add(SelectedItem.getText());
                }

            }

//            if (zonesList.size()==0){
//                    System.out.println("any zone selected");
//            }else {
//                boolean isSortedZ = Ordering.natural().isOrdered(zonesList);
//                System.out.println(">>>>>>>>>zones sorted is " + isSortedZ);
//                Assert.assertTrue("zones not sorted", isSortedZ);
//            }
            boolean isSortedZ = Ordering.natural().isOrdered(zonesList);
            Assert.assertTrue("zones not sorted", isSortedZ);
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

