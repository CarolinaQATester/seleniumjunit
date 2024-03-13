package br.com.seleniumjunit.seleniumjunit.tests;

import br.com.seleniumjunit.seleniumjunit.page.LoginPage;
import io.qameta.allure.*;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

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
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Deve realizar login com usuario bloqueado")
    public void deveRealizarLoginComUsuarioBloqueado(){
        loginPage = new LoginPage(driver);
        loginPage.loginBloqueado("locked_out_user", "secret_sauce");
        loginPage.deveClicarLogin();

        loginPage.deveObterMensagemUsuarioBloqueado();
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Deve realizar login com usuario problema")
    @Ignore
    public void deveRealizarLoginComUsuarioComProblema(){
        // Chamando o método para preencher as credenciais válidas
        this.devePreencherCredencialValida();

        // Capturando a imagem da página
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Especificando o local onde a imagem capturada será salva
        File savedImage = new File("./image/");

        try {
            // Copiando o arquivo de imagem capturado para o local especificado
            FileUtils.copyFile(screenshotFile, savedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginPage.deveClicarItemTitulo();
        // Comparando as imagens
        boolean imagesEqual = loginPage.compareImages(
                "./image/sl-404.168b1cce.jpg",
                "./image/sauce-pullover-1200x1500.51d7ffaf.jpg"
        );

        // Verificando se as imagens são iguais e falhando o teste se não forem
        Assert.assertTrue("As imagens são diferentes", imagesEqual);
    }
}
