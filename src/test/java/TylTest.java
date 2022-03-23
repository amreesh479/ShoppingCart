import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import tyl.pages.LoginPage;
import tyl.pages.OrderPage;
import tyl.pages.ProductPage;


public class TylTest {
    WebDriver driver;
    private static final String BASEURL = "https://www.saucedemo.com/";
    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";

    public TylTest() {
        PageFactory.initElements(driver, this);
    }

    @Before
    public void setUp() {
        //Use Chrome driver
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(BASEURL);
    }

    @Test
    public void tylTechTaskTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        loginPage.loginToApp(USERNAME, PASSWORD);

        productPage.sortProductsByPrice();
        String itemNameHighestPrice = productPage.addRequiredValueItemToCart("Highest");
        productPage.clickAddToCartForRequiredItem(itemNameHighestPrice);

        String itemNameLowestPrice = productPage.addRequiredValueItemToCart("Lowest");
        productPage.clickAddToCartForRequiredItem(itemNameLowestPrice);

        productPage.goToShoppingCart();

        orderPage.finishOrder();
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
