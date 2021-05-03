package com.example.Selenium.Lesson11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class ProductPage extends PageBase{

    public ProductPage (WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void addToCart() {
        String  quantityStr = _wait.until(presenceOfElementLocated(By.className("quantity"))).getText(); //present quantity
        Integer quantityNew = Integer.parseInt(quantityStr)+1;
        String  quantityNewStr = quantityNew.toString(); // quantity+1

        List<WebElement> options = _driver.findElements(By.cssSelector("select[name^=options]"));
        for (WebElement option:options) {
            Select select = new Select(option);
            select.selectByIndex(1);
        }
        _wait.until(presenceOfElementLocated(By.name("add_cart_product"))).click();
        _wait.until(textToBe(By.className("quantity"),quantityNewStr)); // wait for quantity+1
    }

}
