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

public class CountriesOrder {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        //      driver = new FirefoxDriver();
         driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void CheckingCountries() {

//        driver.get("http://localhost/litecart/admin/");
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("Countries | My Store"));


        List<WebElement> rows = driver.findElements(By.className("row"));

        List<String> countries = new ArrayList<>();
        List<String> hrefsWithZone = new ArrayList<>();

        for(WebElement item : rows)
        {
            WebElement hrefElement = item.findElement(By.cssSelector("a[href]"));
            String countryName = hrefElement.getText();
            countries.add(countryName);
            WebElement parentHrefElement = hrefElement.findElement(By.xpath(".//parent::td"));
            WebElement zoneElement = parentHrefElement.findElement(By.xpath(".//following-sibling::td"));
            int zones = Integer.parseInt(zoneElement.getText());
            if (zones > 0)
            {
                String hrefAttribute = hrefElement.getAttribute("href");
                hrefsWithZone.add(hrefAttribute);
//                System.out.println("Country: " + countryName + " Zones: " + zones);
//                System.out.println("HREF: " + hrefAttribute);
            }
        }

        boolean isSorted = Ordering.natural().isOrdered(countries);
        Assert.assertTrue("countries not sorted", isSorted);

        for(String href : hrefsWithZone)
        {
            driver.get(href);
            wait.until(titleIs("Edit Country | My Store"));

            WebElement tableZones = driver.findElement(By.id("table-zones"));

            List<WebElement> rowsZone = tableZones.findElements(By.cssSelector("[name*=name][type=hidden]"));

//            for (WebElement itemZ:rowsZone) {
//                System.out.println("Zone: " + itemZ.getAttribute("value")+ itemZ.getAttribute("name"));
//            }

            List<String> zonesList = Lists.transform(rowsZone, itemZ -> itemZ.getAttribute("value"));
            boolean isSortedZ = Ordering.natural().isOrdered(zonesList);
            Assert.assertTrue("zones not sorted", isSortedZ);

//            System.out.println("zones sorted is " + isSortedZ);

        }


    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
