
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;



public class CheckAlert {
    private WebDriver driver;
    private Logger logger = LogManager.getLogger(String.valueOf(CheckAlert.class));

    @BeforeClass
    public static void setupClass() {

        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
        //        Set implicit wait:
//        wait for WebElement
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

//        wait for loading page
        driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);

//         wait for an asynchronous script to finish execution
        driver.manage().timeouts().setScriptTimeout(5000, TimeUnit.MILLISECONDS);

    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriverWait wait;

    @Test
    public void waitForFilter() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        logger.info("Старт теста");
        driver.get("https://market.yandex.ru/");
        logger.info("Загружена страница " + driver.getCurrentUrl());
        WebElement electronikaElement = driver.findElement(By.xpath("//span[contains(text(),'Электроника')]"));
        electronikaElement.click();//Нажата ссылка Электроника
        WebElement smartPhones = driver.findElement(By.xpath("//*[@data-autotest-id]//a[.='Смартфоны']"));
        smartPhones.click();//Нажата ссылка Смартфоны
        WebElement checkElementS = driver.findElement(By.xpath("//label[.='Samsung']"));
        checkElementS.click();
        WebElement checkElementX = driver.findElement(By.xpath("//label[.='Samsung']"));
        checkElementX.click();
        WebElement priceFilter= driver.findElement(By.xpath("//button[.='по цене']"));
        priceFilter.click();

    }
}
