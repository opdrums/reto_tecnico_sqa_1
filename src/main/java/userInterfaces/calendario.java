package userInterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class calendario {

    public  static  final  Target BUTTON_CALENDARIO_MES_AÑO = Target.the("campo de fecha y año")
            .located(By.xpath("//*[@id='content']/div[1]/ul/li[6]/a"));

    public static  final  Target CAMPO_FECHA = Target.the("click campo fecha")
            .locatedBy("#datepicker");

    public static final Target CAMPO_MES = Target.the("campo mes")
            .located(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[1]"));

    public static final Target CAMPO_AÑO = Target.the("campo año")
            .located(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[2]"));

    public  static  final Target SELECCIONA_DIA = Target.the("dia")
            .located(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[5]/td[4]/a"));

}
