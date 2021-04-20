package com.example.Selenium.Lesson5;

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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CheckingProductCorrect {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

//        driver = new FirefoxDriver();

        driver = new ChromeDriver();

//        System.setProperty("webdriver.edge.driver", "C:\\Java\\Tools\\msedgedriver.exe");
//        driver = new EdgeDriver();

        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void CheckingProductPage() {

        driver.get("http://localhost/litecart");
        wait.until(titleIs("Online Store | My Store"));

        WebElement Campaigns = driver.findElement(By.id("box-campaigns"));
        List<WebElement> ducksList = Campaigns.findElements(By.className("product"));
        List<Product> products = new ArrayList<>();

        for (WebElement item : ducksList) {

            Product product = new Product();
            product.Href = item.findElement(By.className("link")).getAttribute("href");
            product.Name = item.findElement(By.className("name")).getText();

            WebElement price = item.findElement(By.className("regular-price"));
            product.Price = price.getText();
            WebElement promoPrice = item.findElement(By.className("campaign-price"));
            product.PromoPrice = promoPrice.getText();

            products.add(product);
            CheckSizeColor(price, promoPrice);

        }

        for (Product product : products) {
            driver.get(product.Href);
            WebElement item = wait.until(presenceOfElementLocated(By.id("box-product")));

            String prodName = item.findElement(By.className("title")).getText();
            Assert.assertEquals("wrong product-name", prodName, product.Name);

            WebElement price = item.findElement(By.className("regular-price"));
            Assert.assertEquals("wrong regular-price", price.getText(), product.Price);

            WebElement promoPrice = item.findElement(By.className("campaign-price"));
            Assert.assertEquals("wrong campaign-price", promoPrice.getText(), product.PromoPrice);

            CheckSizeColor(price, promoPrice);

        }

    }


    void CheckSizeColor(WebElement price, WebElement promoPrice) {
        Double priceSize = Double.parseDouble(price.getCssValue("font-size").replace("px", ""));
        Double promoPriceSize = Double.parseDouble(promoPrice.getCssValue("font-size").replace("px", ""));
        Assert.assertTrue("wrong size", (priceSize < promoPriceSize));

        RGB priceColor = ParseColor(price.getCssValue("color"));
        Assert.assertEquals("regular-price not gray", priceColor.R, priceColor.G);
        Assert.assertEquals("regular-price not gray", priceColor.B, priceColor.G);

        RGB promoPriceColor = ParseColor(promoPrice.getCssValue("color"));
        Assert.assertEquals("campaign-price not red", promoPriceColor.G, (Integer) 0);
        Assert.assertEquals("campaign-price not red", promoPriceColor.B, (Integer) 0);

        String BoldText = promoPrice.getCssValue("font-weight");
        Assert.assertTrue("campaign-price not bold", BoldText.equals("bold") || (Integer.parseInt(BoldText) >= 700));
        String CrossText = price.getCssValue("text-decoration");
        Assert.assertTrue("regular-price not crossed out", CrossText.contains("line-through"));
    }

    RGB ParseColor(String TextColor) {
        RGB rgbColor = new RGB();
        TextColor = TextColor.replaceAll("[rgba()]", "");
        String[] values = TextColor.split(",");
        rgbColor.R = Integer.parseInt(values[0].trim());
        rgbColor.G = Integer.parseInt(values[1].trim());
        rgbColor.B = Integer.parseInt(values[2].trim());
        return rgbColor;
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    private class RGB {
        Integer R;
        Integer G;
        Integer B;
    }

    private class Product {
        String Name;
        String Price;
        String PromoPrice;
        String Href;
    }
}