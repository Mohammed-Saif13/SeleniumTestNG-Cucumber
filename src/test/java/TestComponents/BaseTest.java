package TestComponents;

import org.testng.annotations.AfterMethod;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.crypto.dsig.keyinfo.PGPData;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import data.PagePrinter;
import ecom.pageObjects.landingPage;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest
{
	public WebDriver driver;
	public landingPage lp ;
	public org.apache.log4j.Logger log;
	
//	static public ExtentTest test;
//	public ExtentReports extent = ExtentReporterNG.getReporterObject();
	
	public List<String> pNames = new ArrayList<String>();

	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileReader fr = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configFiles\\config.properties");
		prop.load(fr);
		
		
		if(prop.getProperty("browser").equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (prop.getProperty("browser").equalsIgnoreCase("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (prop.getProperty("browser").equalsIgnoreCase("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setAcceptInsecureCerts(true);
		
		log= org.apache.log4j.LogManager.getLogger(BaseTest.class);
		log.info("WebDriver Initialised");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		log.info("Implicitily wait for 10 seconds");
//		driver.manage().addCookie(new Cookie("key","Value"));
//		Cookie cookie = driver.manage().getCookieNamed("key");
//		driver.manage().deleteCookieNamed("key");
		
		
				
				

		return driver ;
	}
	
	@BeforeMethod(alwaysRun = true)
	public landingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
//		List<String> pNames = new ArrayList<String>();
		lp = new landingPage(driver); // IF WE add landing page.lp then this.lp shows as null & tests do not execute
		lp.goTo();
		
		return lp ;
	}
	
	public String getScreenshot(String testcaseName , WebDriver driver) throws IOException
	{
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File Destfile = new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		FileUtils.copyFile(source,Destfile);
		
		return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
	}
	
	
	@AfterMethod()
	public void tearDown()
	{
		
		// CODE FOR TAKING SCREENSHOT IN CUCUMBER
//		if(scenario.isFailed())
//		{
//			final byte[] scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
//			scenario.attach(scr, "image/png", scenario.getName());
//		}
		//driver.manage().deleteAllCookies();
		//driver.findElement(By.cssSelector(".fa-sign-out")).click();
		driver.quit();
	}
}
