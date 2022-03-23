package tyl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.TreeMap;

public class ProductPage {

    WebDriver driver;

    String itemNameTemp;

    @FindBy(className = "product_sort_container")
            WebElement sortProduct;

    @FindBy(className = "inventory_item_price")
            List<WebElement> prices;

    @FindBy(className = "inventory_item_name")
            List<WebElement> names;

    @FindBy(className = "shopping_cart_link")
            WebElement shoppingCart;


    String descendingSort = "Price (high to low)";

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void sortProductsByPrice() throws InterruptedException {
        sortProduct.click();
        Select dropdown = new Select(sortProduct);
        dropdown.selectByVisibleText(descendingSort);
        Thread.sleep(2000);
    }

    public String addRequiredValueItemToCart(String itemValue) {
        List<WebElement> itemPriceList = prices;
        List<WebElement> itemList = names;

        String item, itemPrice;
        int price;

        TreeMap<Integer, String> itemsMap = new TreeMap<Integer, String>();

        for(int i = 0; i < itemList.size(); i++) {
            item = itemList.get(i).getText();
            itemPrice = itemPriceList.get(i).getText();
            itemPrice = itemPrice.replaceAll("[^0-9]", "");
            price = Integer.parseInt(itemPrice);
            itemsMap.put(price, item);
        }

        if(itemValue.equalsIgnoreCase("lowest")) {
            itemNameTemp = itemsMap.firstEntry().toString();
        }
        if(itemValue.equalsIgnoreCase("highest")) {
            itemNameTemp = itemsMap.lastEntry().toString();
        }
        String itemNameTemp1 = itemNameTemp.substring(itemNameTemp.indexOf("=") + 1);
        String itemNameTempLoCase = itemNameTemp1.toLowerCase();
        String itemNameTempWithoutSpaces = itemNameTempLoCase.replaceAll("\\s+", "-");
        return itemNameTempWithoutSpaces;
    }

    public void clickAddToCartForRequiredItem(String suffix) {
        String addToCartBtn = "#add-to-cart-";
        String addToCartFinalBtn = addToCartBtn+suffix;
        driver.findElement(By.cssSelector(addToCartFinalBtn)).click();
    }

    public void goToShoppingCart() {
        shoppingCart.click();
    }
}
