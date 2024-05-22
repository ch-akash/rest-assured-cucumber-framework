package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefs", "hooks"},
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", "pretty"}
)
public class CucumberTests extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = true)
    public Object[][] parallelScenarioProvider() {
        return super.scenarios();
    }

    @Test(description = "Runs Cucumber Scenarios in parallel", dataProvider = "parallelScenarioProvider")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        super.runScenario(pickleWrapper, featureWrapper);
    }
}
