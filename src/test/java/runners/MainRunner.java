package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.runner.RunWith;
import utils.readDate.BeforeSuite;
import utils.readDate.DataToFeature;
import utils.reporting.Report;

import java.io.IOException;

@RunWith(RunnerPersonalizado.class)
@CucumberOptions(
        features = "src/test/resources/features"
        ,glue = "definitions"
        ,tags = "@web"
        ,monochrome = true
        ,snippets = SnippetType.CAMELCASE
)
public class MainRunner {

    @BeforeSuite
    public static void test() throws InvalidFormatException, IOException {
        Report.initReport();
        DataToFeature.overrideFeatureFiles("./src/test/resources/features");
    }
}