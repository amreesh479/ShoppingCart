package tyl.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {

    WebDriver driver;

    @FindBy(id = "checkout")
    WebElement checkout;

    @FindBy(id = "first-name")
    WebElement firstName;

    @FindBy(id = "last-name")
    WebElement lastName;

    @FindBy(id = "postal-code")
    WebElement postalCode;

    @FindBy(id = "continue")
    WebElement goNext;

    @FindBy(id = "finish")
    WebElement finish;

    @FindBy(id = "back-to-products")
    WebElement backToProducts;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void finishOrder() {
        checkout.click();
        firstName.sendKeys("Amreesh");
        lastName.sendKeys("Singh");
        postalCode.sendKeys("RG1");
        goNext.click();
        finish.click();
        backToProducts.click();
    }

}
