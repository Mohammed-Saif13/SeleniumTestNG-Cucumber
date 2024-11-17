package ecom.AbstractComponents;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Pdf;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ecom.pageObjects.CartPage;
import ecom.pageObjects.OrderPage;

public class AbstractComponents
{
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) 
	{
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartBtn;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderPageBtn;
	
	public void waitForVisibilityOfElement(By findBy)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForVisibilityOfWebElement(WebElement bs)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(bs));
	}
	
	public void waitForInvisibilityofWebElement(WebElement bt)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOf(bt));	
	}
	public void waitForInvisibilityofElement(By bt)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOfElementLocated(bt));	
	}
	
	public OrderPage goToOrderPage()
	{
		actionClassInitialisation().moveToElement(orderPageBtn).build().perform();
		orderPageBtn.click(); 
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	public CartPage goToCartPage()
	{
		actionClassInitialisation().moveToElement(cartBtn).build().perform();
		cartBtn.click(); 
		CartPage cp = new CartPage(driver);
		return cp ;
	}
	public Actions actionClassInitialisation()
	{
		return new Actions(driver);
	}
	
	public void printPage()
	{
		PrintsPage printer = (PrintsPage) driver;
		PrintOptions po = new PrintOptions();
		po.setOrientation(PrintOptions.Orientation.LANDSCAPE);
//		po.setPageRanges("0-1");
		po.setBackground(true);
		Pdf printedPage =printer.print(po);
		Assert.assertNotNull(printedPage);
		
	}
	
	public void takeScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File (System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(src, destFile);
		
	}
	
	
	
	
}
