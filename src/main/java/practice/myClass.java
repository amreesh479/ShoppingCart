package practice;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class myClass {

    public static void main(String[] args) throws InterruptedException {
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        String baseurl = "https://www.saucedemo.com/";

        driver.get(baseurl);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
//        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
//        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();
//        Thread.sleep(1000);

        driver.findElement(By.className("product_sort_container")).click();
//        Thread.sleep(1000);
        Select dropDown = new Select(driver.findElement(By.className("product_sort_container")));
//        Thread.sleep(1000);
        dropDown.selectByIndex(3);

        List<WebElement> itemPriceList = driver.findElements(By.className("inventory_item_price"));
        List<WebElement> itemList = driver.findElements(By.className("inventory_item_name"));

        String item;
        String itemPrice;
        int price;

        TreeMap<Integer, String> itemsMap = new TreeMap<Integer, String>();

        for(int i = 0; i < itemList.size(); i++) {
            item = itemList.get(i).getText();
            itemPrice = itemPriceList.get(i).getText();
            itemPrice = itemPrice.replaceAll("[^0-9]", "");
            price = Integer.parseInt(itemPrice);
            itemsMap.put(price, item);
        }

        String lowestPriceItem = itemsMap.firstEntry().toString();
        String loItem = lowestPriceItem.substring(lowestPriceItem.indexOf("=") + 1);
        String loCaseLoItem = loItem.toLowerCase();
        String loItemBtn = loCaseLoItem.replaceAll("\\s+", "-");

        String highestPriceItem = itemsMap.lastEntry().toString();
        String hiItem = highestPriceItem.substring(highestPriceItem.indexOf("=") + 1);
        String loCaseHiItem = hiItem.toLowerCase();
        String hiItemBtn = loCaseHiItem.replaceAll("\\s+", "-");


       String addToCartBtn = "#add-to-cart-";
       String hiBtn = addToCartBtn+hiItemBtn;
       String loBtn = addToCartBtn+loItemBtn;
//       System.out.println("High Add to Cart Btn  >>>>>> : " + hiBtn);
//       System.out.println("Low Add to Cart Btn  >>>>>> : " + loBtn);

       driver.findElement(By.cssSelector(hiBtn)).click();
       driver.findElement(By.cssSelector(loBtn)).click();
//       Thread.sleep(1000);

       driver.findElement(By.className("shopping_cart_link")).click();
//       Thread.sleep(1000);
       driver.findElement(By.id("checkout")).click();
//       Thread.sleep(1000);
       driver.findElement(By.id("first-name")).sendKeys("Amreesh");
//       Thread.sleep(1000);
       driver.findElement(By.id("last-name")).sendKeys("Singh");
//       Thread.sleep(1000);
       driver.findElement(By.id("postal-code")).sendKeys("RG1 3FH");
//       Thread.sleep(1000);
       driver.findElement(By.id("continue")).click();
//       Thread.sleep(1000);
       driver.findElement(By.id("finish")).click();
//       Thread.sleep(1000);
       driver.findElement(By.id("back-to-products")).click();
//       Thread.sleep(1000);
        driver.close();
        System.exit(0);

    }
}
