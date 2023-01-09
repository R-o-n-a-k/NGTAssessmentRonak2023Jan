package com.app.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AddToBag {
	WebDriver driver;
	
	public AddToBag(WebDriver driver) {
		this.driver = driver;
	}

	public void login() throws Throwable, Exception {
		Properties propObj = new Properties();
		String rootFolder = System.getProperty("user.dir");
		propObj.load(new FileInputStream(rootFolder + "//src//test//resources//mydata//myFile.properties"));
		driver.get(propObj.getProperty("appLoginLink"));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id=\"mobileNumberPass\"]"))
				.sendKeys(propObj.getProperty("appUseremailId"));
		driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys(propObj.getProperty("appPwd"));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[.=\"LOGIN\"]")).click();
		Thread.sleep(31000);
		driver.findElement(By.xpath("//button[.=\"LOGIN\"]")).click();
		
	}
	
	public void addToCartItems() throws Exception {
		WebElement searchBar=driver.findElement(By.xpath("//input[@placeholder]"));
		searchBar.sendKeys("redmi");
		searchBar.sendKeys(Keys.RETURN);
		driver.findElement(By.xpath("//img[@title=\'Bewakoof Black & Green Mickey Face Printed Xiaomi Redmi Note 8 Pro Back Case']")).click();
		Thread.sleep(2000);
		String currentHandle = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
		    if (!handle.equalsIgnoreCase(currentHandle)) {
		        driver.switchTo().window(handle);
		    }
		}
		Thread.sleep(2000);
		WebElement addToBagButton= driver.findElement(By.cssSelector("div.pdp-add-to-bag"));
		addToBagButton.click();
		Thread.sleep(2000);
		
	}
	
}
