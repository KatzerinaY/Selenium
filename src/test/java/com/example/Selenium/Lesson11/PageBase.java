package com.example.Selenium.Lesson11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class PageBase {

    protected WebDriver _driver;
    protected WebDriverWait _wait;

    protected PageBase (WebDriver driver, WebDriverWait wait) {
        _driver = driver;
        _wait = wait;
    }

    public void backToMainPage(){
        _driver.findElement(By.id("logotype-wrapper")).click();
    }

    public void openCart(){
        WebElement checkout =  _wait.until(presenceOfElementLocated(By.cssSelector("[class=link][href*=checkout]")));
        checkout.click();
    }

}
