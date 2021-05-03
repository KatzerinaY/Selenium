package com.example.Selenium.Lesson11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class CartPage extends PageBase {

    public CartPage (WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clearCart(){
        Integer itemsSize =  _driver.findElements(By.cssSelector("td[class=item]")).size();

        for (int i = 0; i < itemsSize; i++) {

            WebElement dataTable = _wait.until(presenceOfElementLocated(By.className("dataTable")));
            _wait.until(presenceOfElementLocated(By.name("remove_cart_item"))).click();
            _wait.until(stalenessOf(dataTable));

        }

    }
}
