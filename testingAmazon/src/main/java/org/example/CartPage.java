package org.example;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.util.*;
import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Map<String, Object> vars;

    @Before
    public void setUp() {
        // Create an instance of ChromeOptions
        ChromeOptions options = new ChromeOptions();
        // Add the argument to disable notifications
        options.addArguments("--disable-notifications");

        // Initialize the ChromeDriver with the options
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        vars = new HashMap<>();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public String waitForWindow(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> whNow = driver.getWindowHandles();
        Set<String> whThen = (Set<String>) vars.get("window_handles");
        if (whNow.size() > whThen.size()) {
            whNow.removeAll(whThen);
        }
        return whNow.iterator().next();
    }

    @Test
    public void addToCard() {
        driver.get("https://www.trendyol.com/");
        driver.manage().window().setSize(new Dimension(1920, 1080));

        // Close pop-up if it appears
        try {
            WebElement popupCloseButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("homepage-popup-img")));
            popupCloseButton.click();
        } catch (Exception e) {
            System.out.println("No pop-up found");
        }

        // Click on account user link
        driver.findElement(By.cssSelector("div[class='link account-user'] p[class='link-text']")).click();

        // Login steps
        driver.findElement(By.id("login-email")).sendKeys("testingjava14@gmail.com");
        driver.findElement(By.id("login-password-input")).sendKeys("testingjava!01");
        driver.findElement(By.xpath("//*[@id=\"login-register\"]/div[3]/div[1]/form/button")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Wait for login to complete and search for product
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".V8wbcUhU"))).sendKeys("jbl 500 kulaklık");
        driver.findElement(By.cssSelector(".V8wbcUhU")).sendKeys(Keys.ENTER);

        // Handle any pop-up after search
        try {
            WebElement popupOverlay = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.overlay[data-dr-hide='true']")));
            popupOverlay.click();
        } catch (Exception e) {
            System.out.println("No additional pop-up found");
        }

        // Click on the second product
        driver.findElement(By.xpath("//*[@id=\"search-app\"]/div/div[1]/div[2]/div[4]/div[1]/div/div[2]/div[1]/div[2]/button")).click();

        // Open basket
        driver.findElement(By.xpath("//*[@id=\"account-navigation-container\"]/div/div[2]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pb-container\"]/div/div[3]/div[3]/div/div[3]/div/div/div[3]/div/div/div/button")));
        driver.findElement(By.xpath("//*[@id=\"pb-container\"]/div/div[3]/div[3]/div/div[3]/div/div/div[3]/div/div/div/button")).click();

        WebElement basket = driver.findElement(By.xpath("//*[@id=\"pb-container\"]/div/div[3]/div[3]/div"));
        boolean isVisible = basket.isDisplayed();

        assertThat(isVisible, is(true));
 }



     @Test
     public void productCount() {
         driver.get("https://www.trendyol.com/");
         driver.manage().window().setSize(new Dimension(1920, 1080));

         // Close pop-up if it appears
         try {
             WebElement popupCloseButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("homepage-popup-img")));
             popupCloseButton.click();
         } catch (Exception e) {
             System.out.println("No pop-up found");
         }

         // Click on account user link
         driver.findElement(By.cssSelector("div[class='link account-user'] p[class='link-text']")).click();

         // Login steps
         driver.findElement(By.id("login-email")).sendKeys("testingjava14@gmail.com");
         driver.findElement(By.id("login-password-input")).sendKeys("testingjava!01");
         driver.findElement(By.xpath("//*[@id=\"login-register\"]/div[3]/div[1]/form/button")).click();

         try {
             Thread.sleep(1000);
         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }
         // Wait for login to complete and search for product
         wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".V8wbcUhU"))).sendKeys("jbl 500 kulaklık");
         driver.findElement(By.cssSelector(".V8wbcUhU")).sendKeys(Keys.ENTER);

         // Handle any pop-up after search
         try {
             WebElement popupOverlay = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.overlay[data-dr-hide='true']")));
             popupOverlay.click();
         } catch (Exception e) {
             System.out.println("No additional pop-up found");
         }

         // Click on the second product
         driver.findElement(By.xpath("//*[@id=\"search-app\"]/div/div[1]/div[2]/div[4]/div[1]/div/div[2]/div[1]/div[2]/button")).click();

         // Open basket
         driver.findElement(By.xpath("//*[@id=\"account-navigation-container\"]/div/div[2]")).click();

         wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pb-container\"]/div/div[3]/div[3]/div/div[3]/div/div/div[3]/div/div/div/button")));
         driver.findElement(By.xpath("//*[@id=\"pb-container\"]/div/div[3]/div[3]/div/div[3]/div/div/div[3]/div/div/div/button")).click();

         // Increase product count in the basket
         for (int i = 0; i < 10; i++) {
             wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-numeric-counter-button:nth-child(3) path"))).click();
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
         }
         wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".V8wbcUhU"))).sendKeys("jbl 500 kulaklık");
         driver.findElement(By.cssSelector(".V8wbcUhU")).sendKeys(Keys.ENTER);
         driver.findElement(By.xpath("//*[@id=\"search-app\"]/div/div[1]/div[2]/div[4]/div[1]/div/div[2]/div[1]/div[2]/button")).click();
         driver.findElement(By.xpath("//*[@id=\"account-navigation-container\"]/div/div[2]")).click();


         WebElement limit = driver.findElement(By.xpath("//*[@id=\"pb-container\"]/div/div[3]/div[3]/div/div[4]/div[1]/div/div/div/input"));
         String limitValue = limit.getAttribute("value");

         assertThat(limitValue, is("10"));

}


//Before checkout cart must contain less than 10 of the same product.
    @Test
    public void checkout() {

        driver.get("https://www.trendyol.com/");
        driver.manage().window().setSize(new Dimension(1920, 1080));

        // close pop-up if it appears
        try {
            WebElement popupCloseButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("homepage-popup-img")));
            popupCloseButton.click();
        } catch (Exception e) {
            System.out.println("No pop-up found");
        }

        // click on account user link
        driver.findElement(By.cssSelector("div[class='link account-user'] p[class='link-text']")).click();

        // login steps
        driver.findElement(By.id("login-email")).sendKeys("testingjava14@gmail.com");
        driver.findElement(By.id("login-password-input")).sendKeys("testingjava!01");
        driver.findElement(By.xpath("//*[@id=\"login-register\"]/div[3]/div[1]/form/button")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // wait for login to complete and search for product
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".V8wbcUhU"))).sendKeys("jbl 500 kulaklık");
        driver.findElement(By.cssSelector(".V8wbcUhU")).sendKeys(Keys.ENTER);

        // handle any pop-up after search
        try {
            WebElement popupOverlay = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.overlay[data-dr-hide='true']")));
            popupOverlay.click();
        } catch (Exception e) {
            System.out.println("No additional pop-up found");
        }

        // click on the second product
        driver.findElement(By.xpath("//*[@id=\"search-app\"]/div/div[1]/div[2]/div[4]/div[1]/div/div[2]/div[1]/div[2]/button")).click();

        // open basket
        driver.findElement(By.xpath("//*[@id=\"account-navigation-container\"]/div/div[2]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pb-container\"]/div/div[3]/div[3]/div/div[3]/div/div/div[3]/div/div/div/button")));
        driver.findElement(By.xpath("//*[@id=\"pb-container\"]/div/div[3]/div[3]/div/div[3]/div/div/div[3]/div/div/div/button")).click();

        driver.findElement(By.cssSelector(".account-basket > .link-text")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".pb-summary-approve:nth-child(6) span")));

        driver.findElement(By.cssSelector(".pb-summary-approve:nth-child(6) span")).click();
        driver.findElement(By.cssSelector(".approve-button-wrapper:nth-child(4) > .ty-primary-btn")).click();
        driver.switchTo().frame(1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.xpath("//*[@id=\"card-number\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"card-number\"]")).sendKeys("1234 5678 9123 4567");
        driver.findElement(By.id("card-date-month")).click();
        {
            WebElement dropdown = driver.findElement(By.id("card-date-month"));
            dropdown.findElement(By.xpath("//option[. = '02']")).click();
        }
        // 34 | click | id=card-date-year |  |
        driver.findElement(By.id("card-date-year")).click();
        // 35 | select | id=card-date-year | label=2039 |
        {
            WebElement dropdown = driver.findElement(By.id("card-date-year"));
            dropdown.findElement(By.xpath("//option[. = '2039']")).click();
        }
        // 36 | click | css=.card-cvv |  |
        driver.findElement(By.cssSelector(".card-cvv")).click();
        // 37 | type | id=card-cvv | 999 |
        driver.findElement(By.id("card-cvv")).sendKeys("999");
        // 38 | selectFrame | relative=parent |  |
        driver.switchTo().defaultContent();
        // 39 | click | css=.p-card-options-wrapper |  |
        driver.findElement(By.cssSelector(".p-card-options-wrapper")).click();


        WebElement error = driver.findElement(By.xpath("//*[@id=\"p-layout\"]/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]"));
        boolean isVisible = error.isDisplayed();

        assertThat(isVisible, is(true));
}



 }


