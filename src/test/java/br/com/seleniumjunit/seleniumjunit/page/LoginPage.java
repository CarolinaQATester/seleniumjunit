package br.com.seleniumjunit.seleniumjunit.page;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    // Locators
    private By username = By.xpath("//input[@name='username']");
    private By password = By.xpath("//input[@name='password']");
    private By btnLogin = By.tagName("button");
    private By tagMensagem = By.tagName("h6");

    public void preencherLoginValido() {
        type("Admin", username);
        type("admin123", password);
        click(btnLogin);
    }

    public String obterMensagem() {
        return super.getText(tagMensagem);
    }
}
