import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class CheckMessage {

    protected static WebDriver driver;
    private final Logger logger = LogManager.getLogger(CheckMessage.class);

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkMessage() throws InterruptedException {
        By button = By.xpath("//button[contains(text(),'Change message')]");
        By alert = By.xpath("//ngb-alert[contains(text(), 'Message successfully changed')]");
        final String URL = "https://ng-bootstrap.github.io/#/components/alert/examples";

        driver.get(URL);
        getElement(button).click();
        String messageFirst = getElement(alert).getText();

        logger.info("messageFirst");
        logger.info(messageFirst);

        Thread.sleep(1500);

        getElement(button).click();
        String messageSecond = getElement(alert).getText();

        logger.info("messageSecond");
        logger.info(messageSecond);


        Assert.assertNotEquals(messageFirst, messageSecond);
    }

    private WebElement getElement(By locator) {
        return new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
