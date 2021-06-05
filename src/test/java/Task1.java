import org.aeonbits.owner.ConfigFactory;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Task1 {
    private WebDriver driver;
    private Logger logger = LogManager.getLogger(Task1.class);

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {
        logger.info("Test start");
        String title="Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
        System.out.println(cfg.hostname() + ":" + cfg.port() +
                " will run " + cfg.maxThreads());
        driver.get(cfg.hostname());
        driver.getTitle();
        String actual=driver.getTitle();
        Assert.assertEquals(title, actual);
        logger.info(actual);
    }
}
