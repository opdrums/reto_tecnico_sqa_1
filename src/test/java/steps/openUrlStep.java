package steps;
import net.thucydides.core.annotations.Step;
import utils.Utils;
import utils.driver_factory.driverFactory;
import pageObjects.openUrlPo;

public class openUrlStep{
    private openUrlPo openUrlPo;

    public openUrlStep() {
        openUrlPo = new openUrlPo();
        openUrlPo.setDriver(driverFactory.getDriver());
    }

    @Step
    public void openNavegate(String ambiente) {
        try {
            openUrlPo.getDriver().get(Utils.readProperty("configurations", "web_" + ambiente));
        } catch (Exception e) {
        }
    }
}
