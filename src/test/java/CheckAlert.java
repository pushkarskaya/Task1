
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
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
    public void TestYandex() throws InterruptedException {}

    public boolean isFramePresent(String locator) {
        try {
            WebElement iFrameElement = driver.findElement(By.xpath("locator"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Test
    public void test220Volt() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");// Отключение уведомлений
        driver = new ChromeDriver(options);
        logger.info("Старт теста");
        driver.get("https://www.220-volt.ru/");
        logger.info("Загружена страница " + driver.getCurrentUrl());
        WebElement electroinstrumentElement = driver.findElement(By.xpath("//a[@title='Электроинструменты']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(electroinstrumentElement).build().perform();
        WebElement perforatorElement = driver.findElement(By.xpath("//a[@title='Перфораторы' and @href='/catalog/perforatory/']"));
        perforatorElement.click();
        WebElement cityBtn = driver.findElement(By.xpath("//span[@class='form-submit-default city-offer-agree-btn']"));
        cityBtn.click();
        WebElement zubrElement = driver.findElement(By.xpath("//input[@title=\"ЗУБР\"]"));
        zubrElement.click();//Фильтр ЗУБР
        WebElement makitaElement = driver.findElement(By.xpath("//input[@title=\"MAKITA\"]"));
        makitaElement.click();//Фильтр Макита
        //      Баннер Не упускайте выгоду
        if (driver.findElements(By.cssSelector(".close")).size() != 0) {
            logger.info("Появился Баннер Не упускайте выгоду");
            WebElement banner1 = driver.findElement(By.cssSelector(".close"));
            banner1.click();//Закрыли баннер Хотите оформить заказ как юр лицо или
            //Закрыли баннер Не упускайте выгоду
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement widget3 = driver.findElement(By.cssSelector(".widget__close"));
        WebElement frame = driver.findElement(By.cssSelector("iframe#fl-313821"));
        driver.switchTo().frame(frame);
        widget3.click();
        // driver.switchTo().frame(driver.findElement(By.tagName("iframe")));

        //WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".widget__close")));


        // driver.switchTo().frame(0);
        driver.switchTo().defaultContent();
        WebElement priceShared = driver.findElement(By.xpath("//li//span[contains(text(),'Популярности')]"));
        priceShared.click();
//        WebDriverWait wait = new WebDriverWait(driver, 20);
//        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@class='filter-content-sort col-20p']")));

        //driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);


        WebElement priceFilter = driver.findElement(By.xpath("(//span[contains(text(),'Цене')])[1]"));
        priceFilter.click();

        WebElement firstZubr = driver.findElement(By.xpath("(//a[contains(text(),'Перфоратор ЗУБР')]/ancestor::li)[1]//i[@title='Добавить к сравнению']"));
        firstZubr.click();
        WebElement firstMakita = driver.findElement(By.xpath("(//a[contains(text(),'Перфоратор MAKITA')]/ancestor::li)[1]//i[@title='Добавить к сравнению']"));
        firstMakita.click();
        WebElement compareElement = driver.findElement(By.xpath("//a[contains(text(),'Перейти к сравнению')]"));
        compareElement.click();
        int sizeElements = driver.findElements(By.xpath("//li[@class='compareItem area inlineBl ui-sortable-handle']")).size();
        logger.info("size= " + sizeElements);

        //a[contains(text(),'Перфоратор ЗУБР')]/ancestor::div[@class='new-item-list-name']

        //i[@title='Добавить к сравнению']/ancestor::li/descendant::div/descendant::a[contains(text(),'Перфоратор ЗУБР')]
        //WebElement samsungModel=driver.findElement(By.xpath("(//span[contains(text(),'Смартфон Samsung')])[1]"));
        WebElement addToCompare = driver.findElement(By.xpath("//article[contains(.,'Смартфон Samsung')]/descendant::div[contains(@aria-label,'сравнению')]"));
        //Actions actions= new Actions(driver);
        //actions.moveToElement(samsungModel).build().perform();
        actions.moveToElement(addToCompare).build().perform();
        //(//div[contains(@aria-label,'Добавить') and contains(@aria-label,'сравнению')]/@aria-label)

        addToCompare.click();
    }
    @Test
        public void testYandex() throws InterruptedException{
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //ChromeOptions options = new ChromeOptions();
            //driver = new ChromeDriver(options);
            logger.info("Старт теста");
            driver.get("https://market.yandex.ru/");
            logger.info("Загружена страница " + driver.getCurrentUrl());
            Thread.sleep(20000); //Captcha
            WebElement electronikaElement = driver.findElement(By.xpath("//span[contains(text(),'Электроника')]"));
            electronikaElement.click();//Нажата ссылка Электроника

            WebElement smartPhones = driver.findElement(By.xpath("//*[@data-autotest-id]//a[.='Смартфоны']"));
            smartPhones.click();//Нажата ссылка Смартфоны
            WebElement checkSamsung = driver.findElement(By.xpath("//label[.='Samsung']"));
            checkSamsung.click();
            WebElement checkXiaomi = driver.findElement(By.xpath("//label[.='Xiaomi']"));
            checkXiaomi.click();
            WebElement priceFilter = driver.findElement(By.xpath("//button[.='по цене']"));
            priceFilter.click();

            //Добавление Sumsung к сравнению
            WebElement samsungModel=driver.findElement(By.xpath("(//span[contains(text(),'Смартфон Samsung')])[1]//ancestor::article"));
            Actions actions = new Actions(driver);
            actions.moveToElement(samsungModel).perform();
            Thread.sleep(2000);
            WebElement addtoCompareSamsung=driver.findElement(By.xpath("(//article[contains(.,'Смартфон Samsung')]/descendant::div[contains(@aria-label,'сравнению')])[1]"));
            addtoCompareSamsung.click();
            WebElement plashka=driver.findElement(By.xpath("//div[contains(text(),'Товар Смартфон Samsung')and contains(text(),'добавлен к сравнению')]"));
            Assert.assertNotNull(driver.findElement(By.xpath("//div[contains(text(),'Товар Смартфон Samsung')and contains(text(),'добавлен к сравнению')]")));
            logger.info("Samsung добавлен к сравнению");

            //Добавление Xiaomi к сравнению
            WebElement xiaomiModel=driver.findElement(By.xpath("(//span[contains(text(),'Смартфон Xiaomi')])[1]//ancestor::article"));
            Actions actions1 = new Actions(driver);
            actions1.moveToElement(xiaomiModel).perform();
            Thread.sleep(2000);
            WebElement addtoCompareXiaomi=driver.findElement(By.xpath("(//article[contains(.,'Смартфон Xiaomi')]/descendant::div[contains(@aria-label,'сравнению')])[1]"));
            addtoCompareXiaomi.click();
            WebElement plashka1=driver.findElement(By.xpath("//div[contains(text(),'Товар Смартфон Xiaomi')and contains(text(),'добавлен к сравнению')]"));
            Assert.assertNotNull(driver.findElement(By.xpath("//div[contains(text(),'Товар Смартфон Xiaomi')and contains(text(),'добавлен к сравнению')]")));
            logger.info(" Xiaomi добавлен к сравнению");
            //Подсчет элементов, добавленных к сранению
        //WebElement compareButton=driver.findElement(By.xpath("//span[contains(text(),'Сравнить')]"));
        //WebElement compareButton=driver.findElement(By.xpath("@href='/my/compare-lists'"));
        WebElement compareButton=driver.findElement(By.cssSelector("a[href='/my/compare-lists']"));
        compareButton.click();
        if (driver.findElements(By.xpath("//div//a[contains(text(),'Смартфон Samsung') or contains(text(),'Смартфон Xiaomi')]")).size()==2)
        {
            logger.info("На странице сравнения 2 элемента, тест пройден");
        }
        }

        @Test
        public void CheckMessage () throws InterruptedException {
            By button = By.xpath("//button[contains(text(),'Change message')]");
            By alert = By.xpath("//ngb-alert[contains(text(), 'Message successfully changed')]");
            final String URL = "https://ng-bootstrap.github.io/#/components/alert/examples";

            driver.get(URL);
            getElement(button).click();
            String messageFirst = getElement(alert).getText();
            logger.info(messageFirst);

            Thread.sleep(3000);

            getElement(button).click();
            String messageSecond = getElement(alert).getText();
            logger.info(messageSecond);

            Assert.assertNotEquals(messageFirst, messageSecond);
        }

        private WebElement getElement (By locator){
            return new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
    }


