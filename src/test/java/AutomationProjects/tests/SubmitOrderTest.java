package AutomationProjects.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import TestComponents.BaseTest;
import data.ReadXLSData;
import ecom.pageObjects.CartPage;
import ecom.pageObjects.CheckOutPage;
import ecom.pageObjects.ConfirmationPage;
import ecom.pageObjects.OrderPage;
import ecom.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	//List<String> pNames = new ArrayList<String>();

	@Test(dataProviderClass = ReadXLSData.class , dataProvider = "excel", groups= {"Purchase","functional"})
	public void submitOrder(String username, String password , String pn1 ,String pn2) throws InterruptedException, IOException
	{
		pNames.add(pn1);
		pNames.add(pn2);
		System.out.println(pNames);

//		Properties cred = new Properties();
//		FileInputStream fis = new FileInputStream(
//				System.getProperty("user.dir") + "\\src\\test\\resources\\configFiles\\creds.properties");
//		cred.load(fis);

		ProductCatalogue productscatalogue = lp.loginApplication(username,password);
		log.info("User Logged in with "+username+" and - "+password );
		List<WebElement> products = productscatalogue.getProductsList();
		productscatalogue.addProductToCartByName(pNames);
		log.info("Products -"+pNames+" added to cart");
		CartPage cp = productscatalogue.goToCartPage();
		Thread.sleep(800);
		Boolean[] match = cp.verifyProductDisplay(pNames);
		Assert.assertFalse(Arrays.stream(match).filter(m -> !m).findAny().isPresent());
		cp.totalPriceValidation();
		CheckOutPage cop = cp.checkOut();
		cop.selectCountry();
		ConfirmationPage confirmPage = cop.submitOrder();
		String confirmationText = confirmPage.getConfirmationMessage();
		Assert.assertTrue(confirmationText.equalsIgnoreCase("Thankyou for the order."));
		pNames.clear();

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() throws IOException 
	{
		this.pNames.add("ZARA COAT 3");
		this.pNames.add("IPHONE 13 PRO");
	
		Properties creds = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\configFiles\\creds.properties");
		creds.load(fis);

		ProductCatalogue productscatalogue = lp.loginApplication(creds.getProperty("email"),
				creds.getProperty("password"));
		OrderPage orderPage = productscatalogue.goToOrderPage();
		Boolean[] match = orderPage.verifyOrderedProducts(pNames);

		Assert.assertFalse(Arrays.stream(match).filter(m -> !m).findAny().isPresent());
		pNames.clear();
	}
	
//	@DataProvider
//	public Object[][] getData()
//	{
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email","fit.dsf@gmail.com");
//		map.put("password","Qwerty1!"); 
//		
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email","anshika@gmail.com");
//		map1.put("password","Iamking@000");
//		
//		return new Object[][] { {map},{map1} }; }

}
