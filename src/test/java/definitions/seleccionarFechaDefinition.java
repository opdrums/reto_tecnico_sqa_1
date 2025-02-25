package definitions;
import cucumber.api.java.en.*;
import steps.seleccionarFechaStep;
import net.thucydides.core.annotations.Steps;

public class seleccionarFechaDefinition {
    @Steps
    seleccionarFechaStep seleccionar = new seleccionarFechaStep();

    @Given("el usuario hace clic en el campo de fecha")
    public void clickCampFecha(){
        seleccionar.flujoClickCampoFecha();
    }

    @Given("^el usuario seleccionar una caracteristica de la lista(.*)$")
    public void clickMenuLateral(String menu){
        seleccionar.menuLateral(menu);
    }

    @When("^selecciona una fecha dentro del rango(.*)$")
    public void ingresarFecha(String fecha){
        seleccionar.seleccionarFechas(fecha);
    }

    @And("^Selecciona el mes (.*) y año (.*)$")
    public  void  flujoSeleccionarFechaRango(String mes, String año){
        seleccionar.seleccionarFechaRango(mes, año);
    }

    @Then("la fecha seleccionada debe aparecer correctamente en el campo de entrada")
    public  void validacionFechaEntradaT(){
        seleccionar.validateFechaIngresada();
    }
}
