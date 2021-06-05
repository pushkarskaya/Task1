import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Lesson7 {
    protected static WebDriver driver;
    private final Logger logger = LogManager.getLogger(Lesson7.class);


    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @After
    public void killDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public  void mainTest() {
        driver.get("https://ng-bootstrap.github.io/#/components/alert/examples");

        logger.info("Браузер открыт");

    }

}
