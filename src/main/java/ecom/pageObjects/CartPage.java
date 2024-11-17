package ecom.pageObjects;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ecom.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents
{
	WebDriver driver ;
	
	public CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartSection h3")
	List<WebElement>cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	@FindBy(css=".prodTotal p")
	List<WebElement> p ;
	
	@FindBy(xpath="//span[@class ='value']")
	WebElement tpF;
	
	
	public Boolean[] verifyProductDisplay(List<String> pNames)
	{
//		for(String pName:pNames)
//		{
//			match1 =cartProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(pName));
//			Arrays.fill(match,match1);
//		}
		
		return pNames.stream().map(pName->cartProducts.stream().anyMatch(pr->pr.getText().equalsIgnoreCase(pName)))
				.collect(Collectors.toList()).toArray(new Boolean[0]);
		
	}
	
	public CheckOutPage checkOut()
	{
		actionClassInitialisation().moveToElement(checkOutEle).build().perform();
		checkOutEle.click();
		CheckOutPage cop = new CheckOutPage(driver);
		return cop;
	}

	public void totalPriceValidation()
	{
		String tp = tpF.getText().replace("$", "").trim();
		int total = Integer.parseInt(tp);
	
		int totalPrice = 0;	
		List<String>prices= p.stream().map(s->s.getText().replace("$","").trim()).collect(Collectors.toList());
		for( String price : prices)
		{
			int intPrice = Integer.parseInt(price);
			totalPrice = totalPrice + intPrice;
		}
		Assert.assertEquals(total, totalPrice);
		System.out.println(totalPrice);
	}
}
