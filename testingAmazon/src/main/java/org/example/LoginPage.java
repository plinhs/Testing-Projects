package org.example;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

public class LoginPage {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        driver.get("https://www.trendyol.com/");
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }
    @After
    public void tearDown() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
    @Test
    public void loginTrue() {




        if (!driver.findElements(By.className("homepage-popup-img")).isEmpty()) {
            driver.findElement(By.className("homepage-popup-img")).click();
        }


        driver.findElement(By.cssSelector("div[class='link account-user'] p[class='link-text']")).click();


        driver.findElement(By.id("login-email")).click();


        driver.findElement(By.id("login-email")).sendKeys("testingjava14@gmail.com");
        driver.findElement(By.id("login-password-input")).click();
        driver.findElement(By.id("login-password-input")).sendKeys("testingjava!01");
        driver.findElement(By.id("login-password-input")).click();
        driver.findElement(By.cssSelector(".q-primary")).click();

        {
            String value = driver.findElement(By.id("login-email")).getAttribute("value");
            assertThat(value, is("testingjava14@gmail.com"));
        }
        {
            String value = driver.findElement(By.id("login-password-input")).getAttribute("value");
            assertThat(value, is("testingjava!01"));
        }


    }

    @Test
    public void loginfalsepassword() {



        {
            driver.findElement(By.className("homepage-popup-img")).click();
        }
        {
            driver.findElement(By.cssSelector("div[class='link account-user'] p[class='link-text']")).click();

        }


        driver.findElement(By.id("login-email")).click();
        driver.findElement(By.id("login-email")).click();
        driver.findElement(By.id("login-email")).sendKeys("testingjava14@gmail.com");
        driver.findElement(By.id("login-password-input")).click();
        driver.findElement(By.id("login-password-input")).sendKeys("javatesting!01"); //wrong password
        driver.findElement(By.id("login-password-input")).click();
        driver.findElement(By.cssSelector(".q-primary")).click();
        {
            String value = driver.findElement(By.id("login-email")).getAttribute("value");
            assertThat(value, is("testingjava14@gmail.com"));
        }
        {
            String value = driver.findElement(By.id("login-password-input")).getAttribute("value");
            assertThat(value, not("testingjava!01"));
        }

    }

    @Test
    public void loginfalseemail() {



        {
            driver.findElement(By.className("homepage-popup-img")).click();
        }
        {
            driver.findElement(By.cssSelector("div[class='link account-user'] p[class='link-text']")).click();

        }


        driver.findElement(By.id("login-email")).click();
        driver.findElement(By.id("login-email")).click();
        driver.findElement(By.id("login-email")).sendKeys("testingjavafalse@gmail.com"); //wrong email
        driver.findElement(By.id("login-password-input")).click();
        driver.findElement(By.id("login-password-input")).sendKeys("testingjava!01");
        driver.findElement(By.id("login-password-input")).click();
        driver.findElement(By.cssSelector(".q-primary")).click();
        {
            String value = driver.findElement(By.id("login-email")).getAttribute("value");
            assertThat(value, not("testingjava14@gmail.com"));
        }
        {
            String value = driver.findElement(By.id("login-password-input")).getAttribute("value");
            assertThat(value, is("testingjava!01"));
        }

    }


}




