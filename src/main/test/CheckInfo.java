import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class CheckInfo{
    private Logger logger = LogManager.getLogger(CheckInfo.class);
    public List<String> Data = new ArrayList();
    public void DataArray(){ //Массив, содержащий личные данные и контакты для ввода и последующего сравнения
        Data.add("Марияq");
        Data.add("Mariaq");
        Data.add("Ивановаq");
        Data.add("Ivanovaq");
        Data.add("Milanaq");
        Data.add("12.01.1963");

    }
    public WebDriver wd;
    List<String> browserOptions = new ArrayList();
    String url="https://otus.ru";

    @Test
    public void setDataTest() {
        logger.info("Старт теста");
        browserOptions.add("--start-fullscreen");
        browserOptions.add("--incognito");
        browserOptions.add("--disable-notifications");
        WebDriver wd = WebDriverFactory.createNewDriver(webDriverName.CHROME, browserOptions);
        wd.get(url);
        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям",
                wd.getTitle());
        LoginPage page = PageFactory.initElements(wd, LoginPage.class);
        page.authData();
        page.auth();
        page.login();
        page.toLkPage();
        lkPage pagelk = PageFactory.initElements(wd, lkPage.class);
        pagelk.clickProfile();//Профиль
        pagelk.clickLk();//Личный кабинет
        pagelk.clickTabAbout();//О себе
        DataArray();//Запись в массив данных, которые будем вставлять
        pagelk.setPersonalData(Data);
        pagelk.setPersonalContacts();
        WebElement saveButton = wd.findElement(By.xpath("//button[@title='Сохранить и продолжить']"));
        saveButton.click();
        //teardown();
        wd.quit();
    }

    @Test
    public void checkDataTest() {
        logger.info("Старт теста");
        browserOptions.add("--start-fullscreen");
        browserOptions.add("--incognito");
        browserOptions.add("--disable-notifications");
        WebDriver wd = WebDriverFactory.createNewDriver(webDriverName.CHROME, browserOptions);
        wd.get(url);
        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям",
                wd.getTitle());
        LoginPage page= PageFactory.initElements(wd,LoginPage.class);
        page.auth();
        page.login();
        page.toLkPage();
        lkPage pagelk= PageFactory.initElements(wd,lkPage.class);
        pagelk.clickProfile();//Профиль
        pagelk.clickLk();//Личный кабинет
        pagelk.clickTabAbout();//О себе
        DataArray();//Инициализация массива, в котором хранятся личные данные и контакты
        pagelk.checkPersonalData(Data);
        pagelk.checkContactData();
        //
        wd.quit();
    }
}
