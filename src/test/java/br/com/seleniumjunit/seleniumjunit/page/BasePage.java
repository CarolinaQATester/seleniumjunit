package br.com.seleniumjunit.seleniumjunit.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/***
 * Classe abstrata é para fornecer uma base comum para outras classes que herdarão dela. Uma classe abstrata no java,
 * isso significa que ela não pode ser instanciada diretamente, mas deve ser estendida por outras classes.
 */

public abstract class BasePage {
    //Variaveis
    private WebDriver driver;
    private Actions action;
    private WebDriverWait wait;
    private Select select;

    //O construtor inicializa o WebDriver (ChromeDriver neste caso) e maximiza a janela do navegador ao criar uma instância da classe.
    public BasePage() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    //Metodo para abre a URL especificada no navegador.
    public void visit(String url) {
        this.driver.get(url);
    }
    //Metddo para retorna a URL atual da página.
    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }
    //Método para fecha o navegador.
    public void quitWebDriver() {
        this.driver.quit();
    }
    //Método para encontra um elemento na página com base no localizador (por exemplo, ID, classe, etc.).
    public WebElement findElement(By locator) {
        return this.driver.findElement(locator);
    }
    //Método para insere texto em um campo de entrada.
    public void type(String input, By locator) {
        this.driver.findElement(locator).sendKeys(input);
    }
    //Método para verifica se um elemento está visível na página.
    public Boolean isDisplayed(By locator) {
        try {
            return this.driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    //Método para clica em um elemento.
    public void click(By locator) {
        this.driver.findElement(locator).click();
    }
    //Método para obtém o texto de um elemento.
    public String getText(By locator) {
        return this.driver.findElement(locator).getText();
    }
    //método para move o cursor do mouse para um elemento.
    public void actionMoveToElementPerform(By locator) {
        if (this.action == null) {
            this.action = new Actions(this.driver);
        }
        WebElement element = this.driver.findElement(locator);
        action.moveToElement(element).perform();
    }
    //método para move o cursor do mouse para um elemento e clica nele.
    public void actionMoveToElementClickPerform(By locator) {
        if (this.action == null) {
            this.action = new Actions(this.driver);
        }
        WebElement element = this.driver.findElement(locator);
        action.moveToElement(element).click().build().perform();
    }
    //método para obtém o valor de um atributo específico de um elemento.
    public String getTextByAttribute(By locator, String attributeName) {
        return this.driver.findElement(locator).getAttribute(attributeName);
    }
    // método para aguarda até que um elemento seja visível dentro do tempo especificado.
    public WebElement waitVisibilityOfElementLocated(By locator, Duration time) {
        wait = new WebDriverWait(driver, time);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    //método para aguarda até que um elemento seja visível dentro do tempo padrão (10 segundos).
    public WebElement waitVisibilityOfElementLocated(By locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    //Método para verifica se uma mensagem está presente no código-fonte da página.
    public Boolean isContainedInPageSource(String message) {
        return driver.getPageSource().contains(message);
    }
    //Método para seleciona uma opção em um elemento select com base no valor.
    public void selectByValue(By locator, String value) {
        select = new Select(findElement(locator));
        select.selectByValue(value);
    }
    //Método para limpa o conteúdo de um campo de entrada.
    public void clear(By locator) {
        this.driver.findElement(locator).clear();
    }
}
