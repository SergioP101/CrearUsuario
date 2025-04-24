package RRHH;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features",
        glue = "RRHH",
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true
)
public class RunCucumberTest {
}