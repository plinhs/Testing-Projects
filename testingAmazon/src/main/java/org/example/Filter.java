package org.example;// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class Filter {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
    driver.get("https://www.trendyol.com/butik/liste/2/erkek");
    driver.manage().window().setSize(new Dimension(1920, 1080));
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void filterBrand() {


    driver.findElement(By.cssSelector(".V8wbcUhU")).click();
    driver.findElement(By.cssSelector(".V8wbcUhU")).sendKeys("kulaklık");
    driver.findElement(By.cssSelector(".V8wbcUhU")).sendKeys(Keys.ENTER);

    driver.findElement(By.cssSelector(".fltrs:nth-child(3) .ReactVirtualized__Grid__innerScrollContainer > div:nth-child(1) .chckbox")).click();

  }

  @Test
  public void filterAscendingPrice() {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); /////
    driver.findElement(By.cssSelector(".V8wbcUhU")).click();
    driver.findElement(By.cssSelector(".V8wbcUhU")).sendKeys("kulaklık");
    driver.findElement(By.cssSelector(".V8wbcUhU")).sendKeys(Keys.ENTER);

    driver.findElement(By.xpath("//div[@class='selected-order']")).click();
    driver.findElement(By.xpath("//div[@data-fragment-name='Search']//li[2]")).click();

    js.executeScript("window.scrollTo(0,200)");

    if (!driver.findElements(By.className("popup-heading")).isEmpty()) {
      driver.findElement(By.cssSelector("div.overlay[data-dr-hide='true']")).click();
    }
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='selected-order']")));

    List<WebElement> productPrice = this.driver.findElements(By.className("prc-box-dscntd"));
    boolean ascending = true;
    int loopLimit = Math.min(productPrice.size(), 50);
    for (int i = 1; i < loopLimit; i++){
      try {
        // Re-fetch the elements to avoid StaleElementReferenceException
        productPrice = driver.findElements(By.className("prc-box-dscntd"));

        String price1 = productPrice.get(i - 1).getText().replace("TL", "").replaceAll(",",".").trim();
        String price2 = productPrice.get(i).getText().replace("TL", "").replaceAll(",",".").trim();

        double numericPrice1 = Double.parseDouble(price1);
        double numericPrice2 = Double.parseDouble(price2);
        // Compare the prices
        if (numericPrice1 > numericPrice2) {
          // if the prices are not in ascending order, fail
          ascending = false;
          break;
        }
      } catch (StaleElementReferenceException e) {
        productPrice = driver.findElements(By.className("prc-box-dscntd"));
        i--;
      }
    }
    assert(ascending);
  }



  @Test
  public void filterDescendingPrice() {


    driver.findElement(By.cssSelector(".V8wbcUhU")).click();
    driver.findElement(By.cssSelector(".V8wbcUhU")).sendKeys("kulaklık");
    driver.findElement(By.cssSelector(".V8wbcUhU")).sendKeys(Keys.ENTER);


    driver.findElement(By.xpath("//div[@class='selected-order']")).click();
    driver.findElement(By.xpath("//span[normalize-space()='En yüksek fiyat']")).click();

    List<WebElement> productPrice = this.driver.findElements(By.className("prc-box-dscntd"));
    boolean descending = true;
    int loopLimit = Math.min(productPrice.size(), 50);
    for (int i = 1; i < loopLimit; i++) {
      try {
        // Re-fetch the elements to avoid StaleElementReferenceException
        productPrice = driver.findElements(By.className("prc-box-dscntd"));

        String price1 = productPrice.get(i - 1).getText().replace("TL", "").replaceAll(",", "").trim();
        String price2 = productPrice.get(i).getText().replace("TL", "").replaceAll(",", "").trim();

        double numericPrice1 = Double.parseDouble(price1);
        double numericPrice2 = Double.parseDouble(price2);

        // Compare the prices
        if (numericPrice1 < numericPrice2) {
          // if the prices are not in descending order, fail
          descending = false;
          break;
        }
      } catch (StaleElementReferenceException e) {
        productPrice = driver.findElements(By.className("prc-box-dscntd"));
        i--;
      }
    }
    assert(descending);
  }



  @Test
  public void filterPrice() {
    try {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); /////
    driver.findElement(By.cssSelector(".V8wbcUhU")).click();
    driver.findElement(By.cssSelector(".V8wbcUhU")).sendKeys("kulaklık");
    driver.findElement(By.cssSelector(".V8wbcUhU")).sendKeys(Keys.ENTER);
    String value = "800";
    String value2 = "10000";


    js.executeScript("window.scrollTo(0,500)");

    if (!driver.findElements(By.className("popup-heading")).isEmpty()) {
      driver.findElement(By.cssSelector("div.overlay[data-dr-hide='true']")).click();
    }

      WebElement filterButton = driver.findElement(By.xpath("//div[@id='sticky-aggregations']//div[4]//div[1]//div[2]//i[1]"));
      wait.until(ExpectedConditions.elementToBeClickable(filterButton)).click();

      WebElement minPriceInput = driver.findElement(By.xpath("//input[@placeholder='En Az']"));
      minPriceInput.click();
      minPriceInput.sendKeys(value);


      WebElement maxPriceInput = driver.findElement(By.xpath("//input[@placeholder='En Çok']"));
      maxPriceInput.click();
      maxPriceInput.sendKeys(value2);

      WebElement applyPriceFilterButton = driver.findElement(By.className("fltr-srch-prc-rng-srch"));
      wait.until(ExpectedConditions.elementToBeClickable(applyPriceFilterButton)).click();

      // waiting for the results to load & sort
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='selected-order']")));
      js.executeScript("window.scrollTo(0, 0)");
      WebElement sortOrder = driver.findElement(By.xpath("//div[@class='selected-order']"));
      sortOrder.click();
      WebElement sortOption = driver.findElement(By.xpath("//div[@data-fragment-name='Search']//li[2]"));
      wait.until(ExpectedConditions.elementToBeClickable(sortOption)).click();

      WebElement firstProductPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-fragment-name='Search']//div[@class='prc-box-dscntd']")));
      String firstProductPriceText = firstProductPriceElement.getText().replace("TL", "").replace(",",".").trim();
      double firstProductPrice = Double.parseDouble(firstProductPriceText);

      List<WebElement> elements = driver.findElements(By.xpath("//div[@class='prc-box-dscntd']"));


      WebElement lastProductPriceElement = elements.get(elements.size() - 1);
      String lastProductPriceText = firstProductPriceElement.getText().replace("TL", "").replace(",",".").trim();
      double lasttProductPrice = Double.parseDouble(firstProductPriceText);

      assertTrue(firstProductPrice >= 800 && lasttProductPrice<= 10000 );
    } catch (Exception e) {
      e.printStackTrace();

  }


}
}