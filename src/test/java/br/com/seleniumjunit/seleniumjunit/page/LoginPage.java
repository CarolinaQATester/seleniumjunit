package br.com.seleniumjunit.seleniumjunit.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    // Locators    private By tagMensagem = By.tagName("h6");


    public By inputUsername = By.xpath("//*[@id='user-name']");

    public By inputPassword = By.xpath("//*[@id='password']");

    public By buttonLogin = By.xpath("//*[@id='login-button']");

    public By h6Dashboard = By.xpath("//span[@class='title']");




    public void preencherLoginValido() {
        type("standard_user", inputUsername);
        type("secret_sauce", inputPassword);
        click(buttonLogin);
    }

    public String obterMensagem() {
        return super.getText(h6Dashboard);
    }
}
