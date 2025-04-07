package task.home;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.openqa.selenium.WebDriver;
import userInterfaces.HomePage;

import static net.serenitybdd.screenplay.Tasks.instrumented;

// clase de tarea para abrir el navegador y maximizarlo
public class openBrowser implements Task {
    private HomePage homePage;

    public openBrowser(HomePage homePage) {
        this.homePage = homePage;
    }

    public static openBrowser on(HomePage homePage) {
        return instrumented(openBrowser.class, homePage);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        // Ahora abrir la página de inicio
        homePage.openHomePage();
    }
}
