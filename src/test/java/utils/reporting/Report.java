package utils.reporting;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Report {
    private static String nameFichero;
    private static ExtentTest test;
    private static ExtentReports htmlReporter;

    public static void startReport(String report) {
        htmlReporter = new ExtentReports(System.getProperty("user.dir") + "/"+nameFichero, false);
        test = htmlReporter.startTest(report.toUpperCase());
        htmlReporter.flush();
    }

    public static void reports(String status, String message) {
        switch (status) {
            case "PASS":
                test.log(LogStatus.PASS, message);
                break;
            case "FAIL":
                test.log(LogStatus.FAIL, message);
                break;
            case "INFO":
                test.log(LogStatus.INFO, message);
                break;
            case "WARNING":
                test.log(LogStatus.WARNING, message);
                break;
        }
        htmlReporter.flush();
    }

    public static void reports(String status, String message, String url) {
        switch (status) {
            case "PASS":
                test.log(LogStatus.PASS, message + test.addBase64ScreenShot(url));
                break;
            case "FAIL":
                test.log(LogStatus.FAIL, message + test.addBase64ScreenShot(url));
                break;
            case "INFO":
                test.log(LogStatus.INFO, message + test.addBase64ScreenShot(url));
                break;
            case "WARNING":
                test.log(LogStatus.WARNING, message + test.addBase64ScreenShot(url));
                break;
        }
        htmlReporter.flush();
    }

    public static void finishReport() {
        htmlReporter.endTest(test);
        htmlReporter.flush();
    }

    public static void initReport() {
        nameFichero=("Report/ReportWeb"+System.getProperty("tags")+".html").replace("null","").replace(" and not @Deprecated","").replace("@","");
        File repositorio1 = new File("Report/");
        if (repositorio1.exists()) {
            for (File f1 : repositorio1.listFiles()) {
                if(("Report/"+f1.getName()).equals(nameFichero)) {
                    f1.delete();
                }
            }
        }
        File fichero = new File(nameFichero);
        fichero.delete();
    }

    public static String takeSnapShot(WebDriver driver) {
        try {
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            byte[] imageInByte = scrShot.getScreenshotAs(OutputType.BYTES);
            byte[] encodedArr = Base64.encodeBase64(imageInByte);
            return "data:image/png;base64," + new String(encodedArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void reportCaseFail(String caso) {
        try {
            String ruta = "Report/CaseFail.txt";
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
                caso="FAILED CASES IN AUTOMATED REGRESSION:\n\n"+caso;
                FileWriter fw = new FileWriter(file,true);
            }
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\n");
            bw.write(caso);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}