package stepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.Actor;
import task.flujo_calendario.Calendario;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static userInterfaces.calendario.CAMPO_FECHA;

public class calendarioStep {

    @When("^el usuario ingresa el mes (.*) y el año (.*) en el campo de fecha$")
    public void seleccionoMesYAño(String mes, String año){
        theActorInTheSpotlight().wasAbleTo(
                Calendario.flujoCalendario(mes, año)
        );
    }

    @Then("el sistema debería mostrar la fecha seleccionada")
    public void validacionFecha(){
        theActorInTheSpotlight().wasAbleTo(
                Ensure.that(CAMPO_FECHA.waitingForNoMoreThan(Duration.ofSeconds(15))).isDisplayed()
        );
    }
}
