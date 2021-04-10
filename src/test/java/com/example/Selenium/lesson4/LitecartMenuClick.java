package com.example.Selenium.lesson4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


public class LitecartMenuClick {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        driver = new FirefoxDriver();
//        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void loginAdmin() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        wait.until(titleIs("My Store"));


        WebElement leftMenu = wait.until(presenceOfElementLocated(By.id("box-apps-menu")));
        int rows = leftMenu.findElements(By.id("app-")).size();

        for (int i = 0; i < rows; i++) {

            leftMenu = wait.until(presenceOfElementLocated(By.id("box-apps-menu")));

            List<WebElement> menuItems = leftMenu.findElements(By.id("app-"));
            WebElement item = menuItems.get(i);
            item.click();
            System.out.println(wait.until(visibilityOfElementLocated(By.tagName("h1"))).getText());
            leftMenu = wait.until(presenceOfElementLocated(By.id("box-apps-menu")));
            menuItems = leftMenu.findElements(By.id("app-"));
            item = menuItems.get(i);

            List<WebElement> docsElements = item.findElements(By.xpath("//ul[starts-with(@class, 'docs')]"));

            if (docsElements.size() > 0) {
                WebElement docsElement = docsElements.get(0);

                List<WebElement> subMenuItems = docsElement.findElements(By.xpath("//li[starts-with(@id, 'doc-')]"));
                int subItemQuantity = subMenuItems.size();
                for (int subItemIndex = 0; subItemIndex < subItemQuantity; subItemIndex++) {
                    leftMenu = wait.until(presenceOfElementLocated(By.id("box-apps-menu")));
                    menuItems = leftMenu.findElements(By.id("app-"));
                    item = menuItems.get(subItemIndex);
                    docsElement = item.findElement(By.xpath("//ul[starts-with(@class, 'docs')]"));
                    subMenuItems = docsElement.findElements(By.xpath("//li[starts-with(@id, 'doc-')]"));
                    WebElement subItem = subMenuItems.get(subItemIndex);
                    subItem.click();
                    WebElement tagH1Element = wait.until(visibilityOfElementLocated(By.tagName("h1")));
                    System.out.println(">>" + tagH1Element.getText());
                }
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
