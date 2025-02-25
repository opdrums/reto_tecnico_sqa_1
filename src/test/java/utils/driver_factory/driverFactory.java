package utils.driver_factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.reporting.Report;

import java.util.ArrayList;
public class driverFactory {
    static WebDriver driver;

    //metodo estatico de tipo webdriver que contiene 2 parametos
    //donde se instancia el driver
    public static WebDriver getDriverManager(String browser, String controlador) {
        //condicional del navegador
        if (browser.equals("CHROME")) {
            System.setProperty("webdriver.chrome.driver", controlador);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-features=EnableEphemeralFlashPermission");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-blink-features=AutomationControlled");
            driver = new ChromeDriver(options);
        }else if (browser.equals("MOZILLA")) {
            System.setProperty("webdriver.gecko.driver", controlador);
            driver = new FirefoxDriver();
        } else if (browser.equals("CHROME MOBIL")) {
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("mobileEmulation",
                    new java.util.HashMap<String, Object>() {{
                        put("deviceName", "iPhone 6");
                    }});
            System.setProperty("webdriver.chrome.driver", controlador);
            driver = new ChromeDriver(options);
        }


        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        driver.quit();
    }

    public static boolean cambiarANuevaPestana(int numeroPestana) {
        try {
            ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs2.get(numeroPestana));
            return true;
        } catch (Exception e) {
            Report.reports("FAIL", "No se pudo cambiar a la nueva pestaña");
            return false;
        }
    }

}
