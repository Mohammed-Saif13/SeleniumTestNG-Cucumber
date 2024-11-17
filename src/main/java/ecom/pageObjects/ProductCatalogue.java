package ecom.pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecom.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents
{
	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@id='toast-container']")
	WebElement prod;
	@FindBy(xpath="//div[@class='card-body']")
	List<WebElement> products;
	
	By prodoctsBy = By.cssSelector(".mb-3");
	By toastMsg =By.cssSelector("#toast-container");
	By spiral = By.cssSelector(".ng-animating");
	By spinner = By.cssSelector(".ngx-spinner-overlay");
	
	public List<WebElement> getProductsList()
	{
		waitForVisibilityOfElement(prodoctsBy);
		return products;
	}
	
	
	public void addProductToCartByName(List<String> pNames) throws IOException
	{	
		if(pNames!= null)
		{
			for(String prodName : pNames)
			{
				WebElement prod = getProductsList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(prodName))
								.findFirst().orElse(null);
				addToCart(prod);
	        }
		}
		
	}
	
	public void addToCart(WebElement prod) throws IOException
	{

		if (prod != null) 
		{
			 // Click the 'Add to Cart' button for the found product
			actionClassInitialisation().moveToElement(prod).build().perform();
	        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	        
	        // Wait for the two specified elements to become invisible
	        waitForVisibilityOfElement(toastMsg); // Replace with actual selector
	        waitForInvisibilityofElement(spiral);
	        waitForInvisibilityofElement(spinner);
	        super.takeScreenshot("pc1", driver);
            
        } 
		else
		{
            System.out.println("Product not found ");
            driver.close();
        }
		
	}
	
	
		
}


