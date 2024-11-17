package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src/test/java/cucumber",
		glue="stepDefinition",
		monochrome = true,
		dryRun=false,
		plugin = {"pretty","json:target/json-report/cucumber.json"},
		tags= ""
		//name="Logo" //checks for this keyword in the scenario statement of all feature files
		)

public class TestNGTestRunner extends AbstractTestNGCucumberTests
{

}
