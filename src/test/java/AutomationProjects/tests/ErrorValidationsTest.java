package AutomationProjects.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.testng.Assert;
import TestComponents.BaseTest;
import TestComponents.Retry;
import ecom.pageObjects.CartPage;
import ecom.pageObjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest
{
	@Test(groups="Error Handling",retryAnalyzer=Retry.class)
	public void loginErrorValidation()  throws InterruptedException, IOException 
	{		 
		 lp.loginApplication("fit.dsf@gmail.com", "gsfadtsfhj");
		Assert.assertEquals("Incorrect email or passwrd.", lp.getErrorMsg());
		
	}
	@Test(groups="Error Handling",retryAnalyzer = Retry.class)
	public void productErrorValidation() throws IOException
	{
		Properties prop = new Properties();
		FileReader fr = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configFiles\\config.properties");
		prop.load(fr);
		
		List<String> pNames = new ArrayList<String>();
		pNames.add("ZARA COAT 3");
		pNames.add("JORDANS");
		ProductCatalogue productCatalogue =lp.loginApplication(prop.getProperty("userId"),prop.getProperty("pwd"));
		productCatalogue.addProductToCartByName(pNames);
		List<String> prodNames = new ArrayList<String>();
		prodNames.add("ZARA COAT");
		prodNames.add("JORDANO");
		CartPage cp = productCatalogue.goToCartPage();
		Boolean[] match = cp.verifyProductDisplay(prodNames);
		Assert.assertTrue(Arrays.stream(match).filter(m->!m).findAny().isPresent());
		
		
		
	}
}
