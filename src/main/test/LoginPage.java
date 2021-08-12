
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class LoginPage {
    private WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    //данные для авторизации
    String user="testpushkarskaya@yandex.ru";
    String pass="testpushkarskaya2018";
    public void authData(){

}
//Элементы страницы
    private By loginButton=By.xpath("//button[@class='header2__auth js-open-modal']");
    private By usernameField= By.xpath("//input[@name='email' and @type='text' and @placeholder='Электронная почта'] ");
    private By passwordField=By.xpath("//input[@type='password']");
    private By signInButton=By.xpath("//div[@class='new-log-reg__body']/descendant::button[@type='submit' and contains(text(),'Войти')]");
    private By errorMessage=By.id("alert");



    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public void clicksignInButton(){
        driver.findElement(signInButton).click();
    }

    public void setUsername(String username){
        driver.findElement(usernameField).sendKeys(username);
    }

    public void setPassword(String pass){
        driver.findElement(passwordField).sendKeys(pass);
    }


    // ToHome -> void, params = void
    // Auth -> void, pass, login
    // ToLkPage -> void, params = void


    public void auth(){
        clickLoginButton();
    }

    public void login() {
        //данные для авторизации
        setUsername(user);
        setPassword(pass);
    }

    public void toLkPage(){
        clicksignInButton();
    }

}
