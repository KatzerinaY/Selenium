package com.example.Selenium.Lesson2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import java.io.File;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class LoginAdminTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private FirefoxOptions options;

    @Before
     public void start() {

        //       driver = new ChromeDriver();
//               driver = new FirefoxDriver();

        options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary(new File("c:\\Programme\\Firefox Nightly\\firefox.exe")));
        driver = new FirefoxDriver(options);


//        System.setProperty("webdriver.edge.driver", "C:\\Java\\Tools\\msedgedriver.exe");
//        driver = new EdgeDriver();

        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void loginAdmin(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        wait.until(titleIs("My Store"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}