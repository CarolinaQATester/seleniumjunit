package br.com.seleniumjunit.seleniumjunit.tests;

import br.com.seleniumjunit.seleniumjunit.page.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LoginPageTest {
    private LoginPage loginPage;
    private final String URL = "https://www.saucedemo.com/";

    @Before
    public void setUp() {
        this.loginPage = new LoginPage();
        this.loginPage.visit(this.URL);
    }

    @After
    public void tearDown() {
        this.loginPage.quitWebDriver();
    }

    @Test
    public void preencherLoginValido() {
        this.loginPage.preencherLoginValido();

        Assertions.assertEquals("Products", this.loginPage.obterMensagem(),
                "A mensagem exibida após o login não corresponde à esperada.");

        Assertions.assertNotEquals(URL, this.loginPage.getCurrentUrl(),
                "A URL após o login não deveria ser igual à URL inicial.");
    }
}
