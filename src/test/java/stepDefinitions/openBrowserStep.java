package stepDefinitions;

import cucumber.api.java.en.Given;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import task.home.openBrowser;
import userInterfaces.HomePage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class openBrowserStep {

    @Managed(driver = "chrome") //contiene el managed del driver
    WebDriver navegate; // creo un webdriver con el nombre x

    private HomePage homePage = new HomePage(); // creo una variable privada que contiene la clase del homepage

    static {
        //creo un bloque estatico que establece el ecsenario para los actores se almacena a nivel general del proyecto
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^el cliente(.*) ingresa al navegador$")
    public void openNavegate(String actorName) {
        theActorCalled(actorName).can(BrowseTheWeb.with(navegate));
        theActorCalled(actorName).wasAbleTo(openBrowser.on(homePage));
    }
}