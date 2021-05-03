package com.example.Selenium.Lesson11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class MainPage extends PageBase {

    public MainPage (WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        _driver.get("http://localhost/litecart");
    }

    public void selectFirstProduct() {
        _wait.until(presenceOfElementLocated(By.className("product"))).click(); // any product click
    }
}
