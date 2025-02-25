package pageObjects;

public class seleccionarFechaPo extends WebBasePage{
    private String calendario = "//*[@id='ui-datepicker-div']/table/tbody//a[@data-date='Replaceable']";
    private String campo = "datepicker";
    private String validateFecha= "//*[@id='datepicker']";
    private String botonRangoFecha = "//*[@id='content']/div[1]/ul/li[6]/a";
    private String rangoFecha = "//*[@id='ui-datepicker-div']/div/div/select[1]";
    private String rangoAño = "//*[@id='ui-datepicker-div']/div/div/select[2]";
    private String seleccionarTapMenuLateral = "//*[@id='content']/div[1]/ul//a[text()='Replaceable']";


    public void campoFecha(){
        clickElementIframe(campo);
    }

    public  void seleccionItemMenuExample(String menu){
        clickElementList(seleccionarTapMenuLateral, menu);
        waitTime(3);
    }

    public void fechaCalendario(String fecha){
        clickElementList(calendario, fecha);
        waitTime(5);
    }

    public boolean validacionFechaEntrada(){
        return validateElmentLocator(validateFecha);
    }

    public void seleccionarCampoMenu(){
        clickElementLocator(botonRangoFecha);
    }

    public void flujoRangoFecha(String mes, String año){
        selectElementListValue(rangoFecha, mes);
        selectElementListValue(rangoAño, año);
    }
}
