
import org.junit.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

public class CheckInfo{
    private WebDriver driver;
    private Logger logger = LogManager.getLogger(CheckInfo.class);

    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void auth() {
        logger.info("Старт теста");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://otus.ru");
        WebElement enterRegister=driver.findElement(By.xpath("//button[@class='header2__auth js-open-modal']"));
        enterRegister.click();
        WebElement enterEmail=driver.findElement(By.xpath("//input[@name='email' and @type='text' and @placeholder='Электронная почта'] "));
        enterEmail.sendKeys("testpushkarskaya@yandex.ru");
        WebElement enterPassword=driver.findElement(By.xpath("//input[@type='password']"));
        enterPassword.sendKeys("testpushkarskaya2018");
        WebElement enterLk=driver.findElement(By.xpath("//div[@class='new-log-reg__body']/descendant::button[@type='submit' and contains(text(),'Войти')]"));
        enterLk.click();


        //driver.manage().window().fullscreen();

//        String address = "125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02";
//        logger.info("Загружена страница " + driver.getCurrentUrl());
//
//        WebElement contactElement = driver.findElement(By.cssSelector(".header2_subheader-link[href=\"/contacts/\"]"));
//        contactElement.click();//Нажата ссылка Контакты
//        WebElement actAddress= driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[4]/div[1]/div[3]/div[2]"));
//        logger.info(address);
//        logger.info(actAddress.getText());
//        Assert.assertEquals(address, actAddress.getText());
        teardown();
    }

}
