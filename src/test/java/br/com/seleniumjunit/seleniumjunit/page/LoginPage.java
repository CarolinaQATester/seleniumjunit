package br.com.seleniumjunit.seleniumjunit.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPage {

    WebDriver driver;

    // Locators
     By inputUsername = By.xpath("//*[@id='user-name']");

     By inputPassword = By.xpath("//*[@id='password']");

     By buttonLogin = By.xpath("//*[@id='login-button']");

     By tituloLoginPage = By.xpath("//title");

     By msgUsuarioInvalido = By.tagName("h3");

     By msgUsuarioBloqueado = By.tagName("h3");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Definir credencial valida
    public void devePreencherCampoUserName(String username) {
        driver.findElement(inputUsername).sendKeys(username);

    }
    public void devePreencherCampoPassword(String password){
        driver.findElement(inputPassword).sendKeys(password);
    }

    //Clicar no botao login
    public void deveClicarLogin(){
        driver.findElement(buttonLogin).click();
    }
    @Step("Verificar o titulo da pagina de login Page")
    public void deveVerificarOTituloDaPagina(){
        String loginPageTitulo = driver.findElement(tituloLoginPage).getText();
        assertTrue(loginPageTitulo.contains("Swag Labs"));
    }
    @Step("Verificar a mensagem de usuario invalido")
    public void deveObterMensagemUsuarioInvalido(){
        String credencialInvalidaErroMessage = driver.findElement(msgUsuarioInvalido).getText();
        assertTrue(credencialInvalidaErroMessage.contains("Epic sadface: Username and password do not match any user in this service"));
    }
    @Step("Verificar a mensagem de usuario bloqueado")
    public void deveObterMensagemUsuarioBloqueado(){
        String usuarioBloqueadoErroMessage = driver.findElement(msgUsuarioBloqueado).getText();
        assertTrue(usuarioBloqueadoErroMessage.contains("Epic sadface: Sorry, this user has been locked out."));
    }

    public void loginValido(String userName, String password){
        this.devePreencherCampoUserName(userName);
        this.devePreencherCampoPassword(password);
        this.deveClicarLogin();
    }
    public void loginInvalido(String userName, String password){
        this.devePreencherCampoUserName(userName);
        this.devePreencherCampoPassword(password);
        this.deveClicarLogin();
    }
}
