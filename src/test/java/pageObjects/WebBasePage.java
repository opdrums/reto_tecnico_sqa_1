package pageObjects;
import net.thucydides.core.pages.PageObject;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.driver_factory.driverFactory;
import utils.reporting.Report;
import java.util.List;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;


public class WebBasePage extends PageObject {

    private static final int WAIT_TIMEOUT = 10;
    private String loadedBar = "//*[@class='MuiCircularProgress-svg' or @class='loading-ripple' or contains(@class,'MuiCircularProgress') or contains(@class,'ui active transition visible') ]";
    private static final int POLLING = 100;

    //bloque de codigo de tipo booleano que contiene si elemento es visible
    protected boolean isVisible(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //metodo para dar un back
    public void returnLastPage() {
        getDriver().navigate().back();
    }

    //metodo para refrescar una pagina
    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    // metodo que retorna web element de tipo xpath y reemplaza un string
    public WebElement getElementXpathReplecable(String xpath, String element) {
        return element(By.xpath(xpath.replace("Replaceable", element)));
    }

    //metodo  que retorna  web element de tipo xpath
    public WebElement getElementXpath(String xpath) {
        return element(By.xpath(xpath));
    }

    //metodo para dar un click a un elemento de una lista
    public void clickElementList(String locator, String elm){
        WebElement element = getElementXpathReplecable(locator,elm.trim());
        waitUntilElementIsVisible(element);
        element.click();
    }

    //metodo para dar click a elemento
    public void clickElementLocator(String locator){
        WebElement element = getElementXpath(locator);
        waitUntilElementIsVisible(element);
        element.click();
    }

    public void clickElementIframe(String locator){
        WebElement iframe = getDriver().findElement(By.tagName("iframe"));
        getDriver().switchTo().frame(iframe);

        WebElement datePickerField = getDriver().findElement(By.id(locator));
        datePickerField.click();
    }

    //metodo para seleccionar un elemento de la lista por el select
    public void selectElementListValue(String locator, String text){
        WebElement element = getElementXpath(locator);
        Select select = new Select(element);
        select.selectByValue(text);
    }

    //metodo para envia un texto de una lista
    public void sendTextLocator (String locator, String TextField){
        WebElement element = getElementXpath(locator);
        waitUntilElementIsVisible(element);
        element.sendKeys(TextField);
    }

    //metodo para validar un elemento de una lista
    public boolean validateElmentMain(String locator,String elm){
        WebElement element = getElementXpathReplecable(locator, elm);
        waitUntilElementIsVisibleNonThrow(element, 10);
        return isVisible(element);
    }

    //metodo para validar un elemento
    public boolean validateElmentLocator(String locator){
        WebElement element = getElementXpath(locator);
        waitUntilElementIsVisibleNonThrow(element,10);
        return isVisible(element);
    }

    //metodo para obtener el texto de un elemento de una lista
    public String getTextElementMain(String locator, String elm){
        WebElement element = getElementXpathReplecable(locator, elm);
        waitUntilElementIsVisible(element);
        return element.getText();
    }

    //metodo para obtener el texto de un elemento
    public String getTextElementLocator(String locator){
        WebElement element = getElementXpath(locator);
        waitUntilElementIsVisible(element);
        return element.getText();
    }

    //metodo para obtener el texto por partes
    public String getTextElmLocalator(String locator){
        WebElement element = getElementXpath(locator);
        waitUntilElementIsVisible(element);
        String fullText = element.getText();
        String[] parts = fullText.split(" : ");
        return parts.length > 1 ? parts[1].trim() : "";
    }


    //metodo para hacer una espera implicita y espera que se ha visible si no manda un excepcion
    public void waitUntilElementIsVisible(WebElement element) {
        try {
            await().atMost(WAIT_TIMEOUT, SECONDS).until(() -> isVisible(element));
        } catch (ConditionTimeoutException e) {
            Report.reports("FAIL",String.format("No found element: %s: ", element), Report.takeSnapShot(driverFactory.getDriver())) ;
            throw new ConditionTimeoutException(String.format("No found element \nElement: %s: ", element));
        }
    }

    //metodo para hacer una espera implicita y genera una exepcion si no es visible
    public void waitUntilElementIsVisibleNonThrow(WebElement element, int WAIT_TIMEOUT) {
        try {
            await().atMost(WAIT_TIMEOUT, SECONDS).until(() -> isVisible(element));
        } catch (ConditionTimeoutException e) {
        }
    }

    //metodo para hacer una espera que cargue la pagina
    public void waitUntilLoadPage() {
        try {
            new WebDriverWait(getDriver(), WAIT_TIMEOUT, POLLING).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loadedBar)));
        } catch (Exception e) {
        }
    }


    //metodo para hacer un scroll a un elemento
    public void moverScrollAUnElemento(WebElement element) {
        try {
            waitUntilElementIsVisible(element);
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
            waitTime(1);
            waitUntilElementIsVisible(element);
        }catch (Exception e){
        }
    }

    //metodo para mover el cursos a un elemento
    public void moverCursorAElemento(String locator) {
        WebElement element = getElementXpath(locator);
        waitFor(element).isVisible();
        withAction().moveToElement(element).build().perform();
    }

    //metodo para hacer una espera explicita
    public void waitTime(int segundos){
        try {
            Thread.sleep(segundos*1000);
        }catch (InterruptedException ignored){

        }
    }

    //metodo para dar click una alerta de texto
    public String getTextAlert() {
        try{
            return getDriver().switchTo().alert().getText();
        }catch (Exception e){
            return "Alert not found";
        }
    }

    //metodo para dar click una alerta de buton
    public void clickAcceptAlert() {
        try{
            getDriver().switchTo().alert().accept();
        }catch (Exception e){
        }
    }
}