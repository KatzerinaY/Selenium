package com.example.Selenium.Lesson6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class NewUserRegistration {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        // driver = new FirefoxDriver();

        // driver = new ChromeDriver();

        System.setProperty("webdriver.edge.driver", "C:\\Java\\Tools\\msedgedriver.exe");
        driver = new EdgeDriver();

        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void UserRegistration() throws InterruptedException {

        driver.get("http://localhost/litecart");

        WebElement LoginForm = wait.until(visibilityOfElementLocated(By.name("login_form")));
        LoginForm.findElement(By.cssSelector("[href]")).click();


        WebElement accountForm = wait.until(visibilityOfElementLocated(By.id("create-account")));

        accountForm.findElement(By.name("company")).sendKeys("McDonald's");
        accountForm.findElement(By.name("firstname")).sendKeys("Jim");
        accountForm.findElement(By.name("lastname")).sendKeys("Smith");
        accountForm.findElement(By.name("address1")).sendKeys("123 Avenue, 20");
        accountForm.findElement(By.name("address2")).sendKeys("Old-park-str, 8a");
        accountForm.findElement(By.name("postcode")).sendKeys("55277");
        accountForm.findElement(By.name("city")).sendKeys("New-York");
        WebElement phone = accountForm.findElement(By.name("phone"));
        phone.sendKeys("+10555333696");
        String textEmail = "Jm" + System.currentTimeMillis() + "@m.com";
        accountForm.findElement(By.name("email")).sendKeys(textEmail);
        String textPassword = "Jim999Smith";
        accountForm.findElement(By.name("password")).sendKeys(textPassword);
        accountForm.findElement(By.name("confirmed_password")).sendKeys(textPassword);

        // updated 18.04.2021
        WebElement selectContainer = accountForm.findElement(By.className("select2-container"));
        selectContainer.click();
        WebElement itemsList = wait.until(visibilityOfElementLocated(By.className("select2-results")));
        WebElement usItem = itemsList.findElement(By.cssSelector("li[id$=US]"));
        usItem.click();


        WebElement zone_code = accountForm.findElement(By.cssSelector("select[name=zone_code]"));
        Select selectZ = new Select(zone_code);
        selectZ.selectByValue("FL");
        accountForm.findElement(By.name("tax_id")).sendKeys("123456789");


        accountForm.findElement(By.name("create_account")).click(); //Button

        WebElement navigation = wait.until(visibilityOfElementLocated(By.id("navigation")));
        navigation.findElement(By.cssSelector("[href$=logout]")).click();

        LoginForm = wait.until(visibilityOfElementLocated(By.name("login_form")));
        LoginForm.findElement(By.name("email")).sendKeys(textEmail);
        LoginForm.findElement(By.name("password")).sendKeys(textPassword);
        LoginForm.findElement(By.name("login")).click();

        navigation = wait.until(visibilityOfElementLocated(By.id("navigation")));
        navigation.findElement(By.cssSelector("[href$=logout]")).click();

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}



