package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		 features="src/test/resources/features",
		 glue="stepDef",
		 tags="@Regression",
		 plugin= {"pretty","json:target/cucumber.json","html:target/cucumber-reports/cucumber.html"},
		 monochrome=true,
		 dryRun=false		 
		 
		)



public class TestRunner extends AbstractTestNGCucumberTests{
	/*
	 * private TestNGCucumberRunner testNGCucumberRunner;
	 * 
	 * @AfterClass(alwaysRun=true) public void tearDownClass() {
	 * if(testNGCucumberRunner==null) { return; }
	 * 
	 * File reportOutputDirectory=new File("target/cucumber-reports"); List<String>
	 * jsonFiles=new ArrayList<>(); jsonFiles.add("target/cucumber.json");
	 * 
	 * Configuration config=new
	 * Configuration(reportOutputDirectory,"TestAutomation");
	 * config.setBuildNumber("1");
	 * config.addClassifications("Build Number",config.getBuildNumber());
	 * config.addClassifications("Branch Name","API Automation");
	 * 
	 * ReportBuilder reportBuilder=new ReportBuilder(jsonFiles,config);
	 * reportBuilder.generateReports(); testNGCucumberRunner.finish(); }
	 */
  
  
  
}
