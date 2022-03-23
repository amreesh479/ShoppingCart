package tyl.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    private WebElement pageTitle;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setUserName(String userName) {
        username.sendKeys(userName);
    }

    public void setPassword(String password1) {
        password.sendKeys(password1);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public String getLoginPageTitle() {
        return pageTitle.getText();
    }

    public void loginToApp(String user, String pass) {
        this.setUserName(user);
        this.setPassword(pass);
        this.clickLogin();
    }
}
