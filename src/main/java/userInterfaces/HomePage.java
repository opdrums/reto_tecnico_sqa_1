package userInterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import net.thucydides.core.annotations.DefaultUrl;

//@DefaultUrl("homepage.url") // O cualquier URL predeterminada que desees
public class HomePage extends PageObject {
    private EnvironmentVariables environmentVariables; // variable de tipo privado

    public HomePage() { //creo la instancia de la clase
        //referencia a la instancia de la creacion de una variable de entorno
        this.environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
    }

    public String getHomepageUrl() {
        //creo un metodo de tipo string que retorno una variable de entorno que contiene una propiedad del archivo serenity.properties
        return environmentVariables.getProperty("homepage.url");
    }

    public void openHomePage() {
        //creo un Metodo que abre la url pero verifica si la url viene vacio o null
        String url = getHomepageUrl();
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("The homepage URL is not defined in serenity.properties or is invalid");
        }
        this.openUrl(url); //hace referencia a la instancia que abre la url
    }
}