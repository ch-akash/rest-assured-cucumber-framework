package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefs", "hooks"},
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", "pretty"}
)
public class CucumberSequentialTests extends AbstractTestNGCucumberTests {


}
