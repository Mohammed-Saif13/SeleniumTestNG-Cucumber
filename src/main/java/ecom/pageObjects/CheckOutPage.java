package ecom.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ecom.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents
{
	WebDriver driver ;
	public CheckOutPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		Assert.assertEquals(driver.findElement(By.cssSelector(".payment__title")).getText(),"Payment Method"); 
	}
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement country;
	@FindBy(css=".action__submit")
	WebElement submitBtn;
	
	
	
	public void selectCountry()
	{
		actionClassInitialisation().sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
		waitForVisibilityOfElement(By.cssSelector(".ta-results"));
		country.click();
		
	}
	public ConfirmationPage submitOrder()
	{
		submitBtn.click();
		ConfirmationPage confirmPage = new ConfirmationPage(driver);
		return confirmPage;
	}
	
	

}
