package definitions;

import cucumber.api.java.en.*;
import net.thucydides.core.annotations.Steps;
import steps.openUrlStep;


public class openUrlDefinition {
    private static String atmosphere = "";
    @Steps
    openUrlStep openUrlStep;

    public openUrlDefinition(){
        this.openUrlStep = new openUrlStep();
    }

    @Given("^que el usuario está en la página web(.*)$")
    public void openWeb(String atmosphere){
        openUrlDefinition.atmosphere = atmosphere.trim();
        openUrlStep.openNavegate(openUrlDefinition.atmosphere.toUpperCase());
        openUrlStep.openNavegate(atmosphere);
    }
}
