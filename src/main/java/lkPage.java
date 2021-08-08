import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class lkPage {
    private WebDriver driver;

    public lkPage(WebDriver driver) {

        this.driver = driver;
    }
    public Logger logger = LogManager.getLogger(lkPage.class);
    //Элементы страницы
    //Личные данные
    private By tabAbout = By.xpath("(//a[contains(text(),'О себе')])[1]");
    private By profile = By.cssSelector(".header2-menu__item-wrapper__username");
    private By lk = By.xpath("//a[@title='Личный кабинет']");
    private By name = By.xpath("//input[@name='fname']");
    private By enName = By.xpath("//input[@name='fname_latin']");
    private By surname = By.xpath("//input[@name='lname']");
    private By enSurname = By.xpath("//input[@name='lname_latin']");
    private By blogName = By.xpath("//input[@name='blog_name']");
    private By dateOfBirth = By.xpath("//input[@name='date_of_birth']");
    //Контакты
    private By skype = By.xpath("//button[@title='Skype']");
    private By vk = By.xpath("//button[@title='VK']");




    //clickProfile
    //clickTabAbout
    // setName -> void, params = void
    // checkName -> void, pass, login


    public void clickProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement profile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".header2-menu__item-wrapper__username")));
        profile.click();
    }

    public void clickLk() {
        driver.findElement(lk).click();

    }

    public void clickTabAbout() {
        driver.findElement(tabAbout).click();
    }


    //Ввод Личных данных
    public void setPersonalData(List<String> Data) {
        driver.findElement(name).clear();
        driver.findElement(name).sendKeys(Data.get(0));
        driver.findElement(enName).clear();
        driver.findElement(enName).sendKeys(Data.get(1));
        driver.findElement(surname).clear();
        driver.findElement(surname).sendKeys(Data.get(2));
        driver.findElement(enSurname).clear();
        driver.findElement(enSurname).sendKeys(Data.get(3));
        driver.findElement(blogName).clear();
        driver.findElement(blogName).sendKeys(Data.get(4));
        driver.findElement(dateOfBirth).clear();
        driver.findElement(dateOfBirth).sendKeys(Data.get(5));
    }
    public void setPersonalContacts(){

        driver.findElement(By.xpath("//span[contains(text(),'Способ связи')]")).click();
        driver.findElement(skype).click();
        driver.findElement(By.xpath("//input[@name='contact-0-value']")).sendKeys("Skype");
        driver.findElement(By.xpath("//button[contains(text(),'Добавить')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Способ связи')]")).click();
        driver.findElement(By.xpath("(//button[@title='VK'])[2]")).click();
        driver.findElement(By.xpath("//input[@name='contact-1-value']")).sendKeys("12345567788");
    }

    //Считываем значения введенных полей
//    public HashMap<String, String> getPersonalData() {
//        HashMap<String, String> personalData = new HashMap();
//
//        personalData.put("name", driver.findElement(name).getText());
//        personalData.put("enName", driver.findElement(enName).getText());
//        personalData.put("surname", driver.findElement(surname).getText());
//        personalData.put("enSurname", driver.findElement(enSurname).getText());
//        personalData.put("blogName", driver.findElement(blogName).getText());
//        personalData.put("dateOfBirth", driver.findElement(dateOfBirth).getText());
//        personalData.put("email", driver.findElement(email).getText());
//        personalData.put("telephone", driver.findElement(telephone).getText());
//
//        return personalData;
//    }

//    public void checkPersonalData(HashMap<String, String> savedData) {
//        logger.info("Ожидание имя: "+savedData.get("name"));
//        logger.info("Фактический результат имя: "+driver.findElement(name).getText());
//        Assert.assertEquals(savedData.get("enName"), driver.findElement(enName).getText());
//        Assert.assertEquals(savedData.get("name"), driver.findElement(name).getText());
//        logger.info("Ожидаем "+savedData.get("enName")+", фактический результат: "+driver.findElement(enName).getText());
//        Assert.assertEquals(savedData.get("surname"), driver.findElement(surname).getText());
//        logger.info("Ожидаем "+savedData.get("surname")+", фактический результат: "+driver.findElement(surname).getText());
//        Assert.assertEquals(savedData.get("enSurname"), driver.findElement(enSurname).getText());
//        logger.info("Ожидаем "+savedData.get("enSurname")+", фактический результат: "+driver.findElement(enSurname).getText());
//        Assert.assertEquals(savedData.get("blogName"), driver.findElement(blogName).getText());
//        logger.info("Ожидаем "+savedData.get("blogName")+", фактический результат: "+driver.findElement(blogName).getText());
//        Assert.assertEquals(savedData.get("dateOfBirth"), driver.findElement(dateOfBirth).getText());
//        logger.info("Ожидаем "+savedData.get("dateOfBirth")+", фактический результат: "+driver.findElement(dateOfBirth).getText());
//        //Assert.assertEquals(savedData.get("email"), driver.findElement(email).getText());
//        //logger.info("Ожидаем "+savedData.get("email")+", фактический результат: "+driver.findElement(email).getText())
//        //Assert.assertEquals(savedData.get("telephone"), driver.findElement(telephone).getText());
//    }

    public void checkPersonalData(List<String>Data) {

        logger.info("Ожидание поле имя " +Data.get(0));
        logger.info("Фактический результат поле имя "+ driver.findElement(name).getAttribute("value"));
        Assert.assertEquals(Data.get(0), driver.findElement(name).getAttribute("value"));
        logger.info("Ожидание поле имя (лат.) " +Data.get(1));
        logger.info("Фактический результат поле имя (лат.)"+ driver.findElement(enName).getAttribute("value"));
        Assert.assertEquals(Data.get(1), driver.findElement(enName).getAttribute("value"));
        logger.info("Ожидание поле фамилия " +Data.get(2));
        logger.info("Фактический результат поле фамилия"+ driver.findElement(surname).getAttribute("value"));
        Assert.assertEquals(Data.get(2), driver.findElement(surname).getAttribute("value"));
        logger.info("Ожидание поле фамилия (лат.) " +Data.get(3));
        logger.info("Фактический результат поле фамилия (лат.)"+ driver.findElement(enSurname).getAttribute("value"));
        Assert.assertEquals(Data.get(3), driver.findElement(enSurname).getAttribute("value"));
        logger.info("Ожидание поле имя (в блоге) " +Data.get(4));
        logger.info("Фактический результат (в блоге)"+ driver.findElement(blogName).getAttribute("value"));
        Assert.assertEquals(Data.get(4), driver.findElement(blogName).getAttribute("value"));
        logger.info("Ожидание поле дата рождения " +Data.get(5));
        logger.info("Фактический результат дата рождения"+ driver.findElement(dateOfBirth).getAttribute("value"));
        Assert.assertEquals(Data.get(5), driver.findElement(dateOfBirth).getAttribute("value"));
    }



    public void checkContactData() {
        logger.info("Ожидаемый результат: количество контактов 2");
        logger.info("Фактический результат: количество контактов "+driver.findElements(By.xpath("//div[@data-prefix=\"contact\"]/descendant::input[@name='contact-0-value' or @name='contact-1-value']")).size());
Assert.assertEquals(2,driver.findElements(By.xpath("//div[@data-prefix=\"contact\"]/descendant::input[@name='contact-0-value' or @name='contact-1-value']")).size());
    }


}
