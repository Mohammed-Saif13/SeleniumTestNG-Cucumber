package ecom.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecom.AbstractComponents.AbstractComponents;

public class landingPage extends AbstractComponents
{
	WebDriver driver;
	
	public landingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='userEmail']")
	private WebElement emailEle;
	
	@FindBy(xpath="//input[@type='password']")
	private WebElement passwordEle;
	
	@FindBy(xpath="//input[@id='login']")
	private WebElement loginBtn;
	
	@FindBy(css="[class*='flyInOut']")
	private WebElement loginFailedToastMsg;
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMsg()
	{
		waitForVisibilityOfWebElement(loginFailedToastMsg);
		return loginFailedToastMsg.getText();	
	}
	
	public ProductCatalogue loginApplication(String email,String password)
	{
		emailEle.sendKeys(email);
		passwordEle.sendKeys(password);
		loginBtn.click();
		ProductCatalogue productscatalogue = new ProductCatalogue(driver);
		return productscatalogue;
	}
	
}
