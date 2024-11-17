package AutomationProjects.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ecom.pageObjects.landingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		
		driver.get("https://rahulshettyacademy.com/client");
		Actions a = new Actions(driver);
		WebDriverWait wd = new WebDriverWait(driver, Duration.ofSeconds(5));
		String p1Name="IPHONE 13 PRO";
		String p2Name="ZARA COAT 3";
		
		
		//driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("fit.dsf@gmail.com");
		a.sendKeys(driver.findElement(By.xpath("//input[@id='userEmail']")),"fit.dsf@gmail.com").build().perform();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Qwerty1!");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));
		
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='card-body']"));
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(p2Name)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); 
		//Thread.sleep(1500);
	//		THE BELOW STEP EXECUTES FASTER THAN THIS ONE.
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));Thread.sleep(1000);
		WebElement prod1 = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(p1Name)).findFirst().orElse(null);
		prod1.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); 
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean match =	cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(p1Name));
		Assert.assertTrue(match);
		
		String tp = driver.findElement(By.xpath("//span[@class ='value']")).getText().replace("$", "");
		int total = Integer.parseInt(tp);
		//System.out.println(total);
		
		
		int totalPrice = 0;
		List<WebElement> p = driver.findElements(By.cssSelector(".prodTotal p"));
		List<String>p1= p.stream().map(s->s.getText().replace("$","").trim()).collect(Collectors.toList());
		List<Integer> prices ;
		
		//String[] result = Arrays.stream(attributes.split(",")).map(String::trim).toArray(String[]::new);
		for( int i =0;i<p1.size();i++)
		{
			int intPrice = Integer.parseInt(p1.get(i));
			totalPrice = totalPrice + intPrice;
		}
		Assert.assertEquals(total, totalPrice);
		System.out.println(totalPrice);
		a.scrollByAmount(0, 700).build().perform();
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".payment__title")).getText(),"Payment Method"); 
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
		
		
		//SignOut
		
//		driver.findElement(By.cssSelector(".fa-sign-out")).click();
//		driver.quit();
//		
		

		
		
		
		
		
	}

}
