package ecom.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecom.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {

	WebDriver driver ;
	public ConfirmationPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By thankYouText = By.cssSelector(".hero-primary");
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMsg;
	
	public String getConfirmationMessage()
	{
		waitForVisibilityOfElement(thankYouText);
		return confirmationMsg.getText();
	}

}
