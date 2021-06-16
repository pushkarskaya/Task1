import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.junit.*;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ConfigDriver {
    private WebDriver driver;
    private Logger logger = LogManager.getLogger(ConfigDriver.class);

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
    public void checkingAddress() {
        logger.info("Старт теста");
        driver.get("https://otus.ru");

        String address = "125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02";
        logger.info("Загружена страница " + driver.getCurrentUrl());

        WebElement contactElement = driver.findElement(By.cssSelector(".header2_subheader-link[href=\"/contacts/\"]"));
        contactElement.click();//Нажата ссылка Контакты
        WebElement actAddress= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[4]/div[1]/div[3]/div[2]"));
        logger.info(address);
        logger.info(actAddress.getText());
        Assert.assertEquals(address, actAddress.getText());
        driver.manage().window().fullscreen();
    }
    @Test
    public void checkingTitle(){
        logger.info("Старт теста");
        driver.get("https://otus.ru");

        String title="Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        logger.info("Название страницы ожидание:"+title);
        logger.info("Название страницы актуальное:"+driver.getTitle());
        Assert.assertEquals(title,driver.getTitle());

    }
    public WebDriverWait wait;
    @Test
    public void tele2() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get("https://msk.tele2.ru/shop/number");
        WebElement numInput=driver.findElement(By.cssSelector("#searchNumber"));
        logger.info("Поиск номера телефона,содержащего 97");
        numInput.sendKeys("97");
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='phone-number'][contains(.,'97')or contains(.,'9-7')]")));
        WebElement element=driver.findElement(By.xpath("//*[@class='phone-number'][contains(.,'97')or contains(.,'9-7')]"));
        //Если элемент,содержащий 97, не найден на странице,тест упадет
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@class='phone-number'][contains(.,'97')or contains(.,'9-7')]")).isDisplayed());
        logger.info(element.getText());
    }
    @Test
    public void findCourse() {
        driver.get("https://otus.ru");
        WebElement faq = driver.findElement(By.cssSelector(".header2_subheader-link[href=\"/faq/\"]"));
        faq.click();
        WebElement question = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/div[4]/div[1]"));
        question.click();

        String answer="Программу курса в сжатом виде можно увидеть на странице курса после блока с преподавателями. Подробную программу курса можно скачать кликнув на “Скачать подробную программу курса”";
        WebElement answerElement=driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/div[4]/div[2]"));

        logger.info("Ответ ожидаемый" + answer);
        logger.info("Ответ актуальный" +answerElement.getText());
        Assert.assertEquals(answer, answerElement.getText());
    }

    @Test
    public void subscription(){
        driver.get("https://otus.ru");
        String inputMail="test1@mail.ru";
        WebElement subscribeElement= driver.findElement(By.cssSelector(".input.footer2__subscribe-input[name=email]"));
        subscribeElement.sendKeys(inputMail);
        WebElement subscribeButtonElement=driver.findElement(By.cssSelector(".footer2__subscribe-button"));
        subscribeButtonElement.click();
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".subscribe-modal__success")));
        WebElement sucsessSubscribeElement=driver.findElement((By.cssSelector(".subscribe-modal__success")));
        String sucsess="Вы успешно подписались";
        Assert.assertEquals(sucsess, sucsessSubscribeElement.getText());
        logger.info("Ожидаемое сообщение после нажатия \"Подписаться \""+sucsess);
        logger.info("Актуальное сообщение после нажатия \"Подписаться \""+sucsessSubscribeElement.getText());
    }
}

