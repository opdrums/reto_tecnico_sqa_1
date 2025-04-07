package task.flujo_calendario;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static userInterfaces.calendario.*;

public class Calendario implements Task {

    private final String ingresaMes;
    private final String ingresoAño;


    public Calendario(String año, String mes){
        this.ingresaMes = mes;
        this.ingresoAño = año;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(BUTTON_CALENDARIO_MES_AÑO, isVisible()).forNoMoreThan(20).seconds(),
                Click.on(BUTTON_CALENDARIO_MES_AÑO),

                Scroll.to(CAMPO_FECHA),
                WaitUntil.the(CAMPO_FECHA, isVisible()).forNoMoreThan(20).seconds(),
                Click.on(CAMPO_FECHA),

                WaitUntil.the(CAMPO_MES, isVisible()).forNoMoreThan(20).seconds(),
                SelectFromOptions.byVisibleText(ingresaMes).from(CAMPO_MES),

                WaitUntil.the(CAMPO_AÑO, isVisible()).forNoMoreThan(20).seconds(),
                SelectFromOptions.byVisibleText(ingresoAño).from(CAMPO_AÑO),
                Click.on(SELECCIONA_DIA)
        );
    }

    public static Performable second() {
        try {
            Thread.sleep(2000);  // Espera de 1 segundo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Calendario flujoCalendario(String mes, String año){
        return instrumented(Calendario.class, mes, año);
    }
}
