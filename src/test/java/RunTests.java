import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        tags = "~@wip",
//        tags = "@negative",
//        tags = "(@smoke or @ui) and (not @slow)",
        glue = {"steps"},
        plugin = {"pretty","json:build/cucumber-report/cucumber.json"}
)

public class RunTests {
}