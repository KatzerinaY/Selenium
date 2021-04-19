package com.example.Selenium.Lesson6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class NewProductAdd {


    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        driver = new FirefoxDriver();
        //driver = new ChromeDriver();

//        System.setProperty("webdriver.edge.driver", "C:\\Java\\Tools\\msedgedriver.exe");
//        driver = new EdgeDriver();

        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void AddProduct() throws InterruptedException {

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");

        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        TimeUnit.MILLISECONDS.sleep(500);//Chrome click delay

        wait.until(presenceOfElementLocated(By.cssSelector("[class=button][href*=product]"))).click();

        WebElement contentTab = wait.until(presenceOfElementLocated(By.id("tab-general")));
        TimeUnit.MILLISECONDS.sleep(500); //Chrome delay

        contentTab.findElement(By.name("status")).click();
        contentTab.findElement(By.name("name[en]")).sendKeys("Batman-Duck");
        contentTab.findElement(By.name("code")).sendKeys("BD-001");
        contentTab.findElement(By.cssSelector("[name*=categories][value=\"1\"]")).click();
        contentTab.findElement(By.cssSelector("[name*=categories][value=\"0\"]")).click();
        contentTab.findElement(By.cssSelector("[name*=product_groups][value*=\"3\"]")).click();
        WebElement quantity = contentTab.findElement(By.name("quantity"));
        quantity.clear();
        quantity.sendKeys("20");

        WebElement fileItem = contentTab.findElement(By.cssSelector("[type=file]"));

        String textPath =FileSystems.getDefault().getPath("").toAbsolutePath().toString();
        Path path = Paths.get(textPath, "images\\RubberDuckBatman.jfif");
        fileItem.sendKeys(path.toString());

        String fromDateSet = "2021-04-18";
        WebElement dateFrom = contentTab.findElement(By.name("date_valid_from"));
        dateFrom.sendKeys(fromDateSet);
        String toDateSet = "2022-04-18";
        WebElement dateTo = contentTab.findElement(By.name("date_valid_to"));
        dateTo.sendKeys(toDateSet);

        String fromDate = dateFrom.getAttribute("value");
        String toDate = dateTo.getAttribute("value");

        if (!fromDate.equals(fromDateSet)) {    // for Chrome
            dateFrom.sendKeys("18.04.2021");
        }
        if (!toDate.equals(toDateSet)){
            dateTo.sendKeys("18.04.2022");
        }


        TimeUnit.SECONDS.sleep(1);//waiting for the human eye to see
        driver.findElement(By.cssSelector("[href*=information]")).click();

        contentTab = wait.until(presenceOfElementLocated(By.id("tab-information")));
        WebElement manufacturer = contentTab.findElement(By.name("manufacturer_id"));
        Select select = new Select(manufacturer);
        select.selectByValue("1");
        contentTab.findElement(By.name("keywords")).sendKeys("Batman Duck black");
        contentTab.findElement(By.name("short_description[en]")).sendKeys("To be a superhero for once - " +
                "this cute bath duck made this dream come true. With a mask, bat wings and the bat badge on the chest, " +
                "the duckling teaches fear to the underworld.");

        contentTab.findElement(By.className("trumbowyg-editor")).sendKeys("This duck squeaks just as happily as everyone else - " +
                "even if its role model is better known for its deep voice. With the unmistakable bat logo on the chest, " +
                "it not only ensures law and order in the duck world! General manufacturer information: Safe and fascinating - " +
                "not only for big duck fans and collectors, but also ideal for babies and toddlers! " +
                "The ducks are 8 centimeters tall and weigh an average of 50 grams.");

        contentTab.findElement(By.name("head_title[en]")).sendKeys("black Batman Duck");
        contentTab.findElement(By.name("meta_description[en]")).sendKeys("black Batman Duck ideal for babies and toddlers");

        TimeUnit.SECONDS.sleep(1);//waiting for the human eye to see
        driver.findElement(By.cssSelector("[href*=prices]")).click();

        contentTab = wait.until(presenceOfElementLocated(By.id("tab-prices")));
        WebElement price = contentTab.findElement(By.name("purchase_price"));
        price.clear();
        price.sendKeys("18");

        WebElement currency = contentTab.findElement(By.name("purchase_price_currency_code"));
        select = new Select(currency);
        select.selectByValue("EUR");

        contentTab.findElement(By.name("prices[USD]")).sendKeys("22");
        contentTab.findElement(By.name("prices[EUR]")).sendKeys("18");

        driver.findElement(By.cssSelector("button[name=save]")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
