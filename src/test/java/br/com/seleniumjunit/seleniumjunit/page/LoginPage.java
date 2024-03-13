package br.com.seleniumjunit.seleniumjunit.page;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;


public class LoginPage {

    WebDriver driver;

    // Locators
     By inputUsername = By.xpath("//input[@id='user-name']");

     By inputPassword = By.xpath("//input[@id='password']");

     By buttonLogin = By.xpath("//*[@id='login-button']");

     By tituloLoginPage = By.xpath("//title[contains(text(),'Swag Labs')]");

     By tituloProducts = By.xpath("//span[contains(text(),'Products')]");

     By msgUsuarioInvalido = By.tagName("h3");

     By msgUsuarioBloqueado = By.tagName("h3");

     By image1 = By.xpath("//body/div[@id='root']/div[@id='page_wrapper']/div[@id='contents_wrapper']/div[@id='inventory_container']/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]/img[1]");
     By image2 = By.xpath("//body/div[@id='root']/div[@id='page_wrapper']/div[@id='contents_wrapper']/div[@id='inventory_item_container']/div[1]/div[1]/div[1]/img[1]");

     By inventarioItemNome = By.id("item_4_title_link");

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

    public void deveClicarItemTitulo(){
        driver.findElement(inventarioItemNome).click();
    }
    @Step("Verificar o titulo da pagina de login Page")
    public void deveVerificarOTituloDaPagina(){
        String loginPageTitulo = driver.findElement(tituloLoginPage).getText();
        System.out.println(loginPageTitulo.contains("Swag Labs"));
        Assert.assertFalse(loginPageTitulo.contains("Swag Labs"));
    }
    public void deveVerificarLoginSucesso(){
        String loginSucesso = driver.findElement(tituloProducts).getText();
        System.out.println(loginSucesso.contains("Products"));
        Assert.assertTrue(loginSucesso.contains("Products"));
    }
    @Step("Verificar a mensagem de usuario invalido")
    public void deveObterMensagemUsuarioInvalido(){
        String credencialInvalidaErroMessage = driver.findElement(msgUsuarioInvalido).getText();
        Assert.assertTrue(credencialInvalidaErroMessage.contains("Epic sadface: Username and password do not match any user in this service"));
    }
    @Step("Verificar a mensagem de usuario bloqueado")
    public void deveObterMensagemUsuarioBloqueado(){
        String usuarioBloqueadoErroMessage = driver.findElement(msgUsuarioBloqueado).getText();
        Assert.assertTrue(usuarioBloqueadoErroMessage.contains("Epic sadface: Sorry, this user has been locked out."));
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
    public void loginBloqueado(String userName, String password){
        this.devePreencherCampoUserName(userName);
        this.devePreencherCampoPassword(password);
        this.deveClicarLogin();
    }
    public boolean compareImages(String image1, String image2) {
        try {
            // Lendo as imagens a serem comparadas
            BufferedImage img1 = ImageIO.read(new File(image1));
            BufferedImage img2 = ImageIO.read(new File(image2));

            // Obtendo largura e altura das imagens
            int width1 = img1.getWidth();
            int width2 = img2.getWidth();
            int height1 = img1.getHeight();
            int height2 = img2.getHeight();

            // Verificando se as dimensões das imagens são iguais
            if (width1 != width2 || height1 != height2) {
                return false;
            }

            // Iterando sobre os pixels das imagens
            for (int y = 0; y < height1; y++) {
                for (int x = 0; x < width1; x++) {
                    // Comparando os valores RGB dos pixels
                    if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            // Lidando com exceções de leitura de imagens
            e.printStackTrace();
            return false;
        }

        // Retornando true se as imagens são iguais
        return true;
    }
}
