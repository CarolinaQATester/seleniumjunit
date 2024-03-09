package br.com.seleniumjunit.seleniumjunit.tests;

import br.com.seleniumjunit.seleniumjunit.page.LoginPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    public static WebDriver driver;
    LoginPage loginPage;
    private final String URL = "https://www.saucedemo.com/";

    @Step("Iniciar aplicação")
    @BeforeEach
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);

    }

    @Step("Fechar aplicação")
    @AfterEach
    public void tearDown() {
        driver.close();
    }

}
