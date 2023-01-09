package com.app.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.app.pages.AddToBag;
import com.app.pages.SearchProduct;

public class AppTests {
	WebDriver driver;
	SearchProduct search;
	AddToBag bag;

	@BeforeMethod
	public void setup() throws Exception {
		String rootFolder = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", rootFolder + "//src//test//resources//drivers//chromedriver.exe");
		driver = new ChromeDriver();

		Properties propObj = new Properties();
		propObj.load(new FileInputStream(rootFolder + "//src//test//resources//mydata//myFile.properties"));

		String link = propObj.getProperty("appLink");

		search = new SearchProduct(driver);
		bag = new AddToBag(driver);

		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.get(link);
	}

	@Test
	public void scenario1() {

		String expectedUrl = "https://www.myntra.com/redmi";
		search.searchItem();
		String actualPageTitle = driver.getCurrentUrl();
		Assert.assertEquals(actualPageTitle, expectedUrl);
	}

	@Test
	public void scenario4() throws Exception, Throwable {

		Properties propObj = new Properties();
		String rootFolder = System.getProperty("user.dir");
		propObj.load(new FileInputStream(rootFolder + "//src//test//resources//mydata//myFile.properties"));
		driver.get(propObj.getProperty("appCartLink"));
		Thread.sleep(5000);
		String expString = "Hey, it feels so light!";
		String actString = driver.findElement(By.className("emptyCart-base-emptyText")).getText();
		Assert.assertEquals(expString, actString);

		driver.navigate().back();
		bag.addToCartItems();
		WebElement count = driver.findElement(By.xpath("//span[@data-reactid=\"904\"]"));
		String actualCount = count.getText();
		Assert.assertEquals(actualCount, "1");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
