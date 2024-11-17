package data;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Pdf;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.print.PrintOptions;
import org.testng.Assert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import TestComponents.BaseTest;

public class PagePrinter extends BaseTest
{
	WebDriver driver ;
	public PagePrinter( WebDriver driver)
	{
		this.driver = driver;
	}
	public void printPage()
	{
		PrintsPage printer = (PrintsPage) driver;
		PrintOptions po = new PrintOptions();
		po.setOrientation(PrintOptions.Orientation.LANDSCAPE);
		po.setPageRanges("0-1");
		po.setBackground(true);
		Pdf printedPage =printer.print(po);
		Assert.assertNotNull(printedPage);
		
		
		
	}
	
	
}