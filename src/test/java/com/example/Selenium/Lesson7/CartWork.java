package com.example.Selenium.Lesson7;

import org.junit.After;
import org.junit.Assert;
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

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CartWork {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        driver = new FirefoxDriver();

      //  driver = new ChromeDriver();

//        System.setProperty("webdriver.edge.driver", "C:\\Java\\Tools\\msedgedriver.exe");
//        driver = new EdgeDriver();

        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void CartWork() throws InterruptedException {

        driver.get("http://localhost/litecart");

        for (int i = 0; i < 3; i++) {

            ChromePause(driver);
            wait.until(presenceOfElementLocated(By.className("product"))).click(); // any product click
            ChromePause(driver);

            String  quantityStr = wait.until(presenceOfElementLocated(By.className("quantity"))).getText(); //present quantity
            Integer quantityNew = Integer.parseInt(quantityStr)+1;
            String  quantityNewStr = quantityNew.toString(); // quantity+1

            List<WebElement> options = driver.findElements(By.cssSelector("select[name^=options]"));
            for (WebElement option:options) {
                Select select = new Select(option);
                select.selectByIndex(1);
            }

            wait.until(presenceOfElementLocated(By.name("add_cart_product"))).click();

            wait.until(textToBe(By.className("quantity"),quantityNewStr)); // wait for quantity+1

            driver.findElement(By.id("logotype-wrapper")).click();
        }

        WebElement checkout =  wait.until(presenceOfElementLocated(By.cssSelector("[class=link][href*=checkout]")));
        checkout.click();

        Integer itemsSize =  driver.findElements(By.cssSelector("td[class=item]")).size();

        ////for Chrome, as click is unreliable
        Integer counter=0;
        while (itemsSize==(Integer) 0){
            counter = counter++;
            checkout.click();
            itemsSize =  driver.findElements(By.cssSelector("td[class=item]")).size();
            Assert.assertTrue("click ignored by browser",(counter<2));
        }////////////////////////////////


        for (int i = 0; i < itemsSize; i++) {
            ChromePause(driver);
            WebElement dataTable = wait.until(presenceOfElementLocated(By.className("dataTable")));
            wait.until(presenceOfElementLocated(By.name("remove_cart_item"))).click();
            ChromePause(driver);
            wait.until(stalenessOf(dataTable));

        }
        driver.findElement(By.id("logotype-wrapper")).click();
    }

    public void ChromePause(WebDriver driver) throws InterruptedException {
      if (!driver.toString().contains("Firefox")){
          TimeUnit.MILLISECONDS.sleep(500);
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
