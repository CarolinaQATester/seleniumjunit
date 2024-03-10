package br.com.seleniumjunit.seleniumjunit.tests;

import br.com.seleniumjunit.seleniumjunit.page.LoginPage;
import io.qameta.allure.*;
import org.junit.Test;

@Epic("Teste regressivo")
@Feature("Login Page Test")
public class LoginPageTest extends BaseTest {
    LoginPage loginPage;

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verificar titulo da pagina")
    public void deveVerificarLoginPage() {
        //criar um objeto de Login Page
       loginPage = new LoginPage(driver);
       //verifica o titulo da pagina
        loginPage.deveVerificarOTituloDaPagina();


    }
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Deve realizar login com credenciais validas")
    public void devePreencherCredencialValida(){

        loginPage = new LoginPage(driver);
        loginPage.loginValido("standard_user", "secret_sauce");
        loginPage.deveClicarLogin();

        loginPage.deveVerificarLoginSucesso();

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Deve realizar login com credenciais invalidas")
    public void devePreencherCredencialInvalida(){
        loginPage = new LoginPage(driver);
        loginPage.loginInvalido("standard_use", "secret_sauce");
        loginPage.deveClicarLogin();

        loginPage.deveObterMensagemUsuarioInvalido();
    }
}
