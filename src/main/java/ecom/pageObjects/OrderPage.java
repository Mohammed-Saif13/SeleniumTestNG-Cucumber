package ecom.pageObjects;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecom.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents
{
	WebDriver driver;

	public OrderPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr[@class='ng-star-inserted']/td[2]")
	List<WebElement> orderPageProducts;
	
	By orderPageProds=By.xpath("//tr[@class='ng-star-inserted']/td[2]");
	
	public List<WebElement> getOrderPageProducts()
	{
		waitForVisibilityOfElement(orderPageProds);
		return orderPageProducts;
	}
	
	
	public Boolean[] verifyOrderedProducts(List<String> pNames)
	{
		return	pNames.stream().map(pName->getOrderPageProducts().stream().anyMatch(order->order.getText().equalsIgnoreCase(pName)))
			.collect(Collectors.toList()).toArray(new Boolean[0]);
	}
	
}

