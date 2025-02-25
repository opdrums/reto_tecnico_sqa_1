package steps;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import  pageObjects.seleccionarFechaPo;
import utils.driver_factory.driverFactory;
import utils.reporting.Report;

public class seleccionarFechaStep {
    private seleccionarFechaPo seleccionarFechaPo;

    public seleccionarFechaStep(){
        seleccionarFechaPo = new seleccionarFechaPo();
        seleccionarFechaPo.setDriver(driverFactory.getDriver());
    }

    @Step
    public void  flujoClickCampoFecha(){
        seleccionarFechaPo.campoFecha();
    }

    @Step
    public void seleccionarFechas(String fecha){
        seleccionarFechaPo.fechaCalendario(fecha);
    }

    @Step
    public void menuLateral(String menu){
        seleccionarFechaPo.seleccionItemMenuExample(menu);
    }

    @Step
    public void  seleccionarFechaRango(String mes, String año){
        seleccionarFechaPo.flujoRangoFecha(mes, año);
    }

    @Step
    public void validateFechaIngresada(){
        if(!seleccionarFechaPo.validacionFechaEntrada()){
            Report.reports("FAIL","usuario no ingreso fecha", Report.takeSnapShot(driverFactory.getDriver()));
            Assert.fail("usuario no ingreso fecha");
        }
        Report.reports("PASS","usuario ingreso fecha correctamente", Report.takeSnapShot(driverFactory.getDriver()));
    }
}
