package stepDefinition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import TestComponents.BaseTest;
import ecom.pageObjects.CartPage;
import ecom.pageObjects.CheckOutPage;
import ecom.pageObjects.ConfirmationPage;
import ecom.pageObjects.ProductCatalogue;
import ecom.pageObjects.landingPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest
{
	public landingPage lp;
	public ProductCatalogue productscatalogue;
	public CartPage cp ;
	public CheckOutPage cop;
	public ConfirmationPage confirmPage;
	
	
	
	public List<String>pNames = new ArrayList<String>();
	

	@Given("User is on the login Page")
	public void user_is_on_the_login_page() throws IOException 
	{
		lp=launchApplication();
	}

	@When("^User used valid username(.+) and password(.+)$")
	public void user_used_valid_username_and_password(String email , String password) 
	{
		productscatalogue = lp.loginApplication(email,password);
	}
	@When("adds items to cart and submits the order")
	public void adds_items_to_cart_and_submits_the_order() throws InterruptedException, IOException 
	{

	    pNames.add("ZARA COAT 3");
		pNames.add("IPHONE 13 PRO");
		List<WebElement> products = productscatalogue.getProductsList();
		productscatalogue.addProductToCartByName(pNames);
		
			pNames.add("ZARA COAT 3");
			pNames.add("IPHONE 13 PRO");
		 	cp = productscatalogue.goToCartPage();
			Thread.sleep(1000);
			Boolean[] match = cp.verifyProductDisplay(pNames);
			AssertJUnit.assertFalse(Arrays.stream(match).filter(m -> !m).findAny().isPresent());
			cp.totalPriceValidation();
			cop = cp.checkOut();
			cop.selectCountry();
			confirmPage = cop.submitOrder();
	}
	
	 @Then("{string} is displayed on the screen")
	 public void is_displayed_on_the_screen(String str1)
	 {
		// String confirmationText = confirmPage.getConfirmationMessage();
			Assert.assertTrue(str1.equalsIgnoreCase("Thankyou for the order."));
	 }
}
