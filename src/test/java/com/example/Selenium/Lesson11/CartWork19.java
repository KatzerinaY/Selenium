package com.example.Selenium.Lesson11;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CartWork19 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void CartWork()  {

        MainPage mainPage = new MainPage(driver, wait);
        mainPage.open();

        for (int i = 0; i < 3; i++) {
            mainPage.selectFirstProduct();

            ProductPage productPage = new ProductPage(driver, wait);
            productPage.addToCart();
            productPage.backToMainPage();

        }

        mainPage.openCart();

        CartPage cartPage = new CartPage(driver, wait);
        cartPage.clearCart();
        cartPage.backToMainPage();
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
